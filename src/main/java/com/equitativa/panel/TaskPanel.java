package com.equitativa.panel;

import com.equitativa.model.Task;
import lombok.AllArgsConstructor;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

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
        addTaskLabel(task);
    }

    private void addTaskLabel(Task task) {
        var taskLabel = new Label("label", task.getTitle());
        //taskLabel.add(new AttributeAppender("style", "background-color:" + task.getLabel().getColor() + " !important;"));
        add(taskLabel);
    }

    private void addTaskNameAndLink(Task task) {
        var taskLink = new WebMarkupContainer("link");
        taskLink.add(new AttributeModifier("href", "/task/view?id=" + task.getId()));
        add(taskLink);
        taskLink.add(new Label("name", task.getTitle()));
    }
}
