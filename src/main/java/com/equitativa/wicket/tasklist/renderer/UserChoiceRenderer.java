package com.equitativa.wicket.tasklist.renderer;

import com.equitativa.model.Person;
import org.apache.wicket.markup.html.form.IChoiceRenderer;

public class UserChoiceRenderer implements IChoiceRenderer<Person> {

    @Override
    public Object getDisplayValue(Person person) {
        return person.getName(); // Display the user's name in the dropdown
    }

    @Override
    public String getIdValue(Person person, int index) {
        return person.getId().toString(); // Use the user's ID as the value of the dropdown option
    }
}
