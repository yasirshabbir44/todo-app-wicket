package com.equitativa.todo;

// TodoListPage.java

import com.equitativa.TaskService;
import com.equitativa.base.BasePage;
import com.equitativa.model.Priority;
import com.equitativa.model.Status;
import com.equitativa.model.Task;
import com.equitativa.panel.TaskPanel;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.stream.Collectors.groupingBy;

public class TaskListPage extends BasePage {

    private TaskService taskService;

    private transient Map<Status, List<Task>> tasksByStatusId;


    @Override
    protected void onInitialize() {
        super.onInitialize();
        loadActivitiesByStatus();
        populateStatusData();
    }

    private void loadActivitiesByStatus() {
        tasksByStatusId = taskService.getTasks()
                .stream()
                .collect(groupingBy((task) -> task.getStatus()));
    }

    public TaskListPage() {
        taskService = new TaskService();

        // Form for adding new tasks
        Form<Task> taskForm = new Form<>("taskForm", new CompoundPropertyModel<>(new Task()));
        taskForm.add(new TextField<>("title"));
        taskForm.add( new TextArea<>("description"));
        taskForm.add( new DateTextField("dueDate"));
        taskForm.add(new DropDownChoice<>("priority", List.of(Priority.HIGH, Priority.MEDIUM, Priority.LOW)));
        taskForm.add(new Button("addTaskButton") {
            @Override
            public void onSubmit() {
                Task newTask = taskForm.getModelObject();
                newTask.setId(UUID.randomUUID());
                newTask.setStatus(Status.PENDING);
                System.out.println(newTask);
                taskService.addTask(newTask);
                taskForm.setModelObject(new Task()); // Reset the form

                loadActivitiesByStatus();
                //populateStatusData();
            }
        });
        add(taskForm);

    }

    public void updateTask(Task task) {
        taskService.updateTask(task);
        loadActivitiesByStatus();
    }

    private void populateActivitiesForStatus(ListItem<Status> taskStatusListItem) {
        Status status = taskStatusListItem.getModel().getObject();
        var tasks = tasksByStatusId.getOrDefault(status, Collections.emptyList());
        var taskList = new RepeatingView("taskList");

        for (var task : tasks) {
            var taskCardComponent = new TaskPanel(taskList.newChildId(), new CompoundPropertyModel<>(task));
            taskList.add(taskCardComponent);
        }

        taskStatusListItem.add(taskList);
    }

    private void populateStatusData() {
        var statuses = List.of(Status.PENDING, Status.COMPLETED);
        var priorityList = new ListView<>("priorityItem", statuses) {
            @Override
            protected void populateItem(ListItem<Status> item) {
                Status status = item.getModelObject();
                Label label = new Label("enumLabel", new PropertyModel<>(status, "name"));
                item.add(label);
                populateActivitiesForStatus(item);
            }
        };
        add(priorityList);

    }

//    private void addTaskLabel(Priority priority) {
//        var activityLabel = new Label("label",priority.toString());
//        AttributeAppender attributeAppender = switch (priority) {
//            case LOW -> new AttributeAppender("style", "background-color: blue !important;");
//            case MEDIUM -> new AttributeAppender("style", "background-color: #ffc107 !important;");
//            case HIGH -> new AttributeAppender("style", "background-color: red !important;");
//        };
//        activityLabel.add(attributeAppender);
//        add(activityLabel);
//    }
}
