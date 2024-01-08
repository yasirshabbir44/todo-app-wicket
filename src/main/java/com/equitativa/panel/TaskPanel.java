package com.equitativa.panel;

import com.equitativa.model.Status;
import com.equitativa.model.Task;
import lombok.AllArgsConstructor;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class TaskPanel extends Panel {

    private IModel<Task> taskModel;

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
        add(new Label("description",  task.getDescription()));
        //add(new Label("collaborator", task.getCollaborator().getDisplayName()));
        add(new Label("createdAt", task.getDueDate()));
//        add(new Label("statusCompleted", task.getStatus().equals(Status.COMPLETED)));

        WebMarkupContainer conditionalDiv = new WebMarkupContainer("statusCompleted", taskModel) {
            @Override
            public boolean isVisible() {
                // Get the model object and check the condition
                Task task = taskModel.getObject();
                return task.getStatus().equals(Status.COMPLETED);
            }
        };
        conditionalDiv.add(AttributeModifier.append("class", "completed-task"));
        add(conditionalDiv);




        addTaskLabel(task);
    }

    private void addTaskNameAndLink(Task task) {
        var taskLink = new WebMarkupContainer("link");
        taskLink.add(new AttributeModifier("href", "/task/view?id=" + task.getId()));
        add(taskLink);
        taskLink.add(new Label("name", task.getTitle()));
    }

    private void addTaskLabel(Task task) {
        var activityLabel = new Label("label",task.getPriority().toString());
        AttributeAppender attributeAppender = switch (task.getPriority()) {
            case LOW -> new AttributeAppender("style", "background-color: blue !important;");
            case MEDIUM -> new AttributeAppender("style", "background-color: #ffc107 !important;");
            case HIGH -> new AttributeAppender("style", "background-color: red !important;");
        };
        activityLabel.add(attributeAppender);
        add(activityLabel);
    }
}
