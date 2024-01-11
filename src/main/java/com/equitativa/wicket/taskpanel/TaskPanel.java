package com.equitativa.wicket.taskpanel;

import com.equitativa.model.Status;
import com.equitativa.model.Task;
import com.equitativa.repo.TaskService;
import com.equitativa.util.LocalDateFormatter;
import com.equitativa.wicket.tasklist.TaskListPage;
import com.google.inject.Inject;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.image.ExternalImage;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.Locale;

public class TaskPanel extends Panel {

    private final IModel<Task> taskModel;
    @Inject
    private transient TaskService taskService;

    @Inject
    private transient LocalDateFormatter localDateFormatter;


    public TaskPanel(String id, IModel<Task> taskModel) {
        super(id, taskModel);
        this.taskModel = taskModel;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        populateTaskData();
    }

    private void populateTaskData() {
        Task task = taskModel.getObject();
        addTaskNameAndLink(task);
        add(new Label("description", task.getDescription()));
        add(new Label("dueDate", localDateFormatter.convertToString(task.getDueDate(), Locale.getDefault())));
        add(new ExternalImage("personImage", Model.of(task.getPerson().getImageUrl())));


        CheckBox statusCompleted = new CheckBox("statusCompleted", Model.of(task.getStatus().equals(Status.COMPLETED)));
        // Add Ajax behavior to the checkbox
        statusCompleted.add(new AjaxFormComponentUpdatingBehavior("click") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                // Handle the click event
                task.setStatus(statusCompleted.getValue().equals("true") ? Status.COMPLETED : Status.PENDING);
                findParent(TaskListPage.class).updateTask(task);
                target.appendJavaScript("window.location.reload();");
            }
        });


        add(new AjaxLink<Void>("editButton") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                // Update the label text
                System.out.println(task);
                taskService.delete(task);
                target.appendJavaScript("window.location.reload();");
            }
        });
        add(new AjaxLink<Void>("deleteButton") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                // Update the label text
                System.out.println(task);
                taskService.delete(task);
                TaskListPage parent = findParent(TaskListPage.class);
                parent.loadActivitiesByStatus();
                target.appendJavaScript("window.location.reload();");
            }
        });
        // Delete button

        add(statusCompleted);

        addTaskLabel(task);
    }

    private void addTaskNameAndLink(Task task) {
        var taskLink = new WebMarkupContainer("link");
        taskLink.add(new AttributeModifier("href", "/task/view?id=" + task.getId()));
        add(taskLink);
        taskLink.add(new Label("name", task.getTitle()));
    }

    private void addTaskLabel(Task task) {
        var activityLabel = new Label("label", task.getPriority().toString());
        AttributeAppender attributeAppender = switch (task.getPriority()) {
            case LOW -> new AttributeAppender("style", "background-color: blue !important;");
            case MEDIUM -> new AttributeAppender("style", "background-color: #ffc107 !important;");
            case HIGH -> new AttributeAppender("style", "background-color: red !important;");
        };
        activityLabel.add(attributeAppender);
        add(activityLabel);
    }
}
