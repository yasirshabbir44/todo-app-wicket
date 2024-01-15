package com.equitativa.wicket.tasklist;

// TodoListPage.java

import com.equitativa.model.Person;
import com.equitativa.model.Project;
import com.equitativa.model.Task;
import com.equitativa.model.enumerate.Priority;
import com.equitativa.model.enumerate.Status;
import com.equitativa.service.PersonService;
import com.equitativa.service.ProjectService;
import com.equitativa.service.TaskService;
import com.equitativa.wicket.base.BasePage;
import com.equitativa.wicket.event.UpdateEvent;
import com.equitativa.wicket.home.Home;
import com.equitativa.wicket.tasklist.renderer.ProjectChoiceRenderer;
import com.equitativa.wicket.tasklist.renderer.UserChoiceRenderer;
import com.equitativa.wicket.taskpanel.TaskPanel;
import com.google.inject.Inject;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.cycle.RequestCycle;

import java.io.Serializable;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class TaskListPage extends BasePage implements Serializable {
    private static final long serialVersionUID = 1L;
    private Form<Task> taskForm;
    private IModel<Task> taskModel = Model.of(new Task());
    private IModel<Person> personIModel = Model.of();
    private IModel<Project> projectIModel = Model.of();

    @Inject
    private transient PersonService personService;
    @Inject
    private transient TaskService taskService;
    @Inject
    private transient ProjectService projectService;
    private transient Map<Status, List<Task>> tasksByStatusId;


    public TaskListPage() {

        add(new Link<Void>("redirectToHomePage") {
            @Override
            public void onClick() {
                setResponsePage(Home.class);
            }
        });

        taskForm = configureTaskForm();
        add(taskForm);
        taskModel.setObject(new Task());

        // Add a behavior to execute JavaScript on page load
        addListenerOnReload();

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



    @Override
        public void onEvent(IEvent<?> event) {
        super.onEvent(event);

        if (event.getPayload() instanceof UpdateEvent) {
            updateTaskFormData((UpdateEvent) event.getPayload());
        }
    }

    private void updateTaskFormData(UpdateEvent payload) {

        Task receivedData = payload.getUpdatedData();
        RequestCycle requestCycle = RequestCycle.get();
        AjaxRequestTarget ajaxTarget = requestCycle.find(AjaxRequestTarget.class).orElse(null);

        if (ajaxTarget != null) {
            taskModel.setObject(receivedData);
            taskForm.get("projectDropDown").setDefaultModelObject(receivedData.getProject());
            taskForm.get("userDropDown").setDefaultModelObject(receivedData.getPerson());


            ajaxTarget.appendJavaScript("showTaskForm();");
            ajaxTarget.add(taskForm);

        }

    }


    private Form configureTaskForm() {
        this.taskForm = new Form<>("taskForm", new CompoundPropertyModel<>(taskModel));
        taskForm.add(new TextField<>("title").setOutputMarkupId(true));
        taskForm.add(new TextArea<>("description"));
        taskForm.add(new DateTextField("dueDate"));
        taskForm.add(new DropDownChoice<>("priority", List.of(Priority.HIGH, Priority.MEDIUM, Priority.LOW)));

        taskForm.add(new AjaxEventBehavior("UpdateEvent") {
            @Override
            protected void onEvent(AjaxRequestTarget target) {
                // Call the modifyFormValues() method in response to the custom event
                taskModel.getObject().setTitle("Modified Title");
                // You can also perform other actions using Ajax, if needed
            }
        });

        DropDownChoice<Project> projectDropDown = new DropDownChoice<>("projectDropDown",projectIModel, projectService.getAllProjects());
        projectDropDown.setChoiceRenderer(new ProjectChoiceRenderer()); // Create a custom ChoiceRenderer if needed

        DropDownChoice userDropDown = new DropDownChoice("userDropDown", personIModel, personService.getAllUsers());
        userDropDown.setChoiceRenderer(new UserChoiceRenderer()); // Create a custom ChoiceRenderer if needed


        taskForm.add(userDropDown);
        taskForm.add(projectDropDown);
        taskForm.add(new Button("addTaskButton") {
            @Override
            public void onSubmit() {
                Person person = (Person) taskForm.get("userDropDown").getDefaultModel().getObject();
                Project project = (Project) taskForm.get("projectDropDown").getDefaultModel().getObject();
                Task newTask = taskForm.getModelObject();
                newTask.setPerson(person);
                newTask.setProject(project);

                if(Objects.isNull(newTask.getId())){
                    newTask.setStatus(Status.PENDING);
                    newTask.setId(UUID.randomUUID());
                    taskService.save(newTask);
                }
                else{
                    taskService.update(newTask);
                }
                taskModel.setObject(new Task());// Reset the form
//                taskForm.get("projectDropDown").setDefaultModelObject(Model.of());
//                taskForm.get("userDropDown").setDefaultModelObject(Model.of());
                loadActivitiesByStatus();
            }
        });


        taskForm.add(new Button("cancelTaskButton") {

            @Override
            public void onSubmit() {
                taskModel.setObject(new Task());
                setResponsePage(TaskListPage.class);
//                taskForm.get("projectDropDown").setDefaultModelObject(Model.of());
//                taskForm.get("userDropDown").setDefaultModelObject(Model.of());
            }
        });

        return taskForm;
    }


    private void addListenerOnReload() {
        add(new Behavior() {
            @Override
            public void renderHead(Component component, IHeaderResponse response) {
                super.renderHead(component, response);
                taskModel.setObject(new Task());

            }
        });
    }
}
