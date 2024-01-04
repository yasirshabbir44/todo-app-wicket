package com.mycompany;

// TodoListPage.java
import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Alert;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class TodoListPage extends WebPage implements Serializable {

    private TaskService taskService;

    public TodoListPage() {
        taskService = new TaskService();

        // Form for adding new tasks
        Form<Task> taskForm = new Form<>("taskForm", new CompoundPropertyModel<>(new Task("New Task", LocalDate.now(), Priority.MEDIUM)));
        taskForm.add(new TextField<>("description"));
        taskForm.add(new TextField<>("dueDate"));
        taskForm.add(new ListChoice<>("priority", List.of(Priority.HIGH, Priority.MEDIUM, Priority.LOW)));
        taskForm.add(new Button("addTaskButton") {
            @Override
            public void onSubmit() {
                Task newTask = taskForm.getModelObject();
                taskService.addTask(newTask);
                taskForm.setModelObject(new Task("New Task", LocalDate.now(), Priority.MEDIUM)); // Reset the form
            }
        });
        add(taskForm);


        // Display the list of tasks
        add(new ListView<Task>("tasks", new PropertyModel<>(taskService, "tasks")) {
            @Override
            protected void populateItem(ListItem<Task> item) {
                Task task = item.getModelObject();
                item.add(new Label("description", task.getDescription()));
                item.add(new Label("dueDate", task.getDueDate().toString()));
                item.add(new Label("priority", task.getPriority().toString()));
                item.add(new CheckBox("completed", new PropertyModel<>(task, "completed")));
                item.add(new Button("deleteTaskButton") {
                    @Override
                    public void onSubmit() {
                        taskService.deleteTask(task);
                    }
                });
            }
        });
    }
}
