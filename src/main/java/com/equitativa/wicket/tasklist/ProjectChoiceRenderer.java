package com.equitativa.wicket.tasklist;

import com.equitativa.model.Project;
import com.equitativa.model.Project;
import org.apache.wicket.markup.html.form.IChoiceRenderer;

public class ProjectChoiceRenderer implements IChoiceRenderer<Project> {

    @Override
    public Object getDisplayValue(Project project) {
        return project.getName(); // Display the user's name in the dropdown
    }

    @Override
    public String getIdValue(Project project, int index) {
        return project.getId().toString(); // Use the user's ID as the value of the dropdown option
    }
}
