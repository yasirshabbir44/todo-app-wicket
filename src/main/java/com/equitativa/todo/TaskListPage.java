package com.equitativa.todo;

// TodoListPage.java
import com.equitativa.model.Priority;
import com.equitativa.model.Task;
import com.equitativa.TaskService;
import com.equitativa.base.BasePage;
import com.equitativa.home.HomePage;
import com.equitativa.panel.TaskPanel;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
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
                Priority priority = item.getModelObject();
                Label label = new Label("enumLabel", new PropertyModel<>(priority, "name"));
                Label statusLabel = new Label("statusLabel", new PropertyModel<>(priority, "name"));
                AttributeAppender attributeAppender = switch (priority) {
                    case LOW -> new AttributeAppender("style", "background-color: blue !important;");
                    case MEDIUM -> new AttributeAppender("style", "background-color: #ffc107 !important;");
                    case HIGH -> new AttributeAppender("style", "background-color: red !important;");
                };
                statusLabel.add(attributeAppender);
                item.add(label);
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
        taskForm.add( new TextArea<>("description"));
        taskForm.add( new DateTextField("dueDate"));
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
