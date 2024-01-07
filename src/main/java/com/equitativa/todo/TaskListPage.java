package com.equitativa.todo;

// TodoListPage.java
import com.equitativa.model.Priority;
import com.equitativa.model.Task;
import com.equitativa.TaskService;
import com.equitativa.base.BasePage;
import com.equitativa.home.HomePage;
import com.equitativa.panel.TaskPanel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.stream.Collectors.groupingBy;

public class TaskListPage extends BasePage {

    private TaskService taskService;

    private transient Map<Priority, List<Task>> tasksByStatusId;


    @Override
    protected void onInitialize() {
        super.onInitialize();
        loadActivitiesByStatus();
        populateStatusData();
    }

    private void loadActivitiesByStatus() {
        tasksByStatusId = taskService.getTasks()
                .stream()
                .collect(groupingBy((task) -> task.getPriority()));
    }


    private void populateStatusData() {
        var priorities = List.of(Priority.HIGH,Priority.MEDIUM,Priority.LOW);
        var priorityList = new ListView<>("priorityItem", priorities) {
            @Override
            protected void populateItem(ListItem<Priority> item) {
                item.add(new Label("enumLabel", new PropertyModel<>(item.getModelObject(), "name")));
                populateActivitiesForStatus(item);
            }
        };
        add(priorityList);
    }

    private void populateActivitiesForStatus(ListItem<Priority> taskStatusListItem) {
        Priority priority = taskStatusListItem.getModel().getObject();
        var tasks = tasksByStatusId.getOrDefault(priority, Collections.emptyList());
        var taskList = new RepeatingView("taskList");

        for (var task : tasks) {
            var taskCardComponent = new TaskPanel(taskList.newChildId(), new CompoundPropertyModel<>(task));
            taskList.add(taskCardComponent);
        }

        taskStatusListItem.add(taskList);
    }

    public TaskListPage() {
        taskService = new TaskService();

        // Form for adding new tasks
        Form<Task> taskForm = new Form<>("taskForm", new CompoundPropertyModel<>(new Task()));
        taskForm.add(new TextField<>("title"));
        taskForm.add(new TextField<>("description"));
        taskForm.add(new TextField<>("dueDate"));
        taskForm.add(new DropDownChoice<>("priority", List.of(Priority.HIGH, Priority.MEDIUM, Priority.LOW)));
        taskForm.add(new Button("addTaskButton") {
            @Override
            public void onSubmit() {
                Task newTask = taskForm.getModelObject();
                newTask.setId(UUID.randomUUID());
                System.out.println(newTask);
                taskService.addTask(newTask);
                taskForm.setModelObject(new Task()); // Reset the form

                loadActivitiesByStatus();
                //populateStatusData();
            }
        });
        add(taskForm);
        add(new Link<Void>("redirectToHomePage") {
            @Override
            public void onClick() {
                setResponsePage(HomePage.class);
            }
        });


//        // Display the list of tasks
//        add(new ListView<Task>("tasks", new PropertyModel<>(taskService, "tasks")) {
//            @Override
//            protected void populateItem(ListItem<Task> item) {
//                Task task = item.getModelObject();
//                item.add(new Label("title", task.getTitle()));
//                item.add(new Label("description", task.getDescription()));
//                item.add(new Label("dueDate", task.getDueDate().toString()));
//                item.add(new Label("priority", task.getPriority().toString()));
//                item.add(new CheckBox("completed", new PropertyModel<>(task, "completed")));
//                item.add(new Button("deleteTaskButton") {
//                    @Override
//                    public void onSubmit() {
//                        taskService.deleteTask(task);
//                    }
//                });
//            }
//        });
    }
}
