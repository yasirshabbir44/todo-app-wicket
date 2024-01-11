package com.equitativa.wicket.tasklist;

// TodoListPage.java

import com.equitativa.wicket.base.BasePage;
import com.equitativa.model.*;
import com.equitativa.service.PersonService;
import com.equitativa.service.ProjectService;
import com.equitativa.service.TaskService;
import com.equitativa.wicket.home.Home;
import com.equitativa.wicket.tasklist.renderer.ProjectChoiceRenderer;
import com.equitativa.wicket.tasklist.renderer.UserChoiceRenderer;
import com.equitativa.wicket.taskpanel.TaskPanel;
import com.google.inject.Inject;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.stream.Collectors.groupingBy;

public class TaskListPage extends BasePage implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private transient PersonService personService;

    @Inject
    private transient TaskService taskService;

    @Inject
    private transient ProjectService projectService;

    private final Form<Task> taskForm = new Form<>("taskForm", new CompoundPropertyModel<>(new Task()));

    private transient Map<Status, List<Task>> tasksByStatusId;


    public TaskListPage() {
        // Form for adding new tasks
        add(new Link<Void>("redirectToHomePage") {
            @Override
            public void onClick() {
                setResponsePage(Home.class);
            }
        });

        // taskService.testData();
        taskForm.add(new TextField<>("title"));
        taskForm.add(new TextArea<>("description"));
        taskForm.add(new DateTextField("dueDate"));
        taskForm.add(new DropDownChoice<>("priority", List.of(Priority.HIGH, Priority.MEDIUM, Priority.LOW)));

        DropDownChoice<Person> userDropDown = new DropDownChoice<>("userDropDown", Model.of(), personService.getAllUsers());
        userDropDown.setChoiceRenderer(new UserChoiceRenderer()); // Create a custom ChoiceRenderer if needed


        DropDownChoice<Project> projectDropDown = new DropDownChoice<>("projectDropDown", Model.of(), projectService.getAllProjects());
        projectDropDown.setChoiceRenderer(new ProjectChoiceRenderer()); // Create a custom ChoiceRenderer if needed



        taskForm.add(userDropDown);
        taskForm.add(projectDropDown);
        taskForm.add(new Button("addTaskButton") {
            @Override
            public void onSubmit() {
                Person person = (Person) taskForm.get("userDropDown").getDefaultModel().getObject();
                Project project = (Project) taskForm.get("projectDropDown").getDefaultModel().getObject();
                Task newTask = taskForm.getModelObject();
                newTask.setId(UUID.randomUUID());
                newTask.setPerson(person);
                newTask.setProject(project);
                newTask.setStatus(Status.PENDING);
                System.out.println(newTask);
                taskService.save(newTask);
                // taskService.addTask(newTask);
                taskForm.setModelObject(new Task()); // Reset the form
                loadActivitiesByStatus();
            }
        });
        add(taskForm);

    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        loadActivitiesByStatus();
        populateStatusData();
    }

    public void loadActivitiesByStatus() {
        tasksByStatusId = taskService.getAllTasks()
                .stream()
                .collect(groupingBy((task) -> task.getStatus()));

    }

    public void updateTask(Task task) {
        taskService.update(task);
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

}
