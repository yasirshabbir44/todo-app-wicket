package com.mycompany;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);
		// TODO Add your page's components here

		add(new Label("pageTitle", "Task Management"));

		// Add your elegant task management content here
		// For simplicity, let's add a sample task
		add(new Label("taskDescription", "Sample Task: Complete Wicket Tutorial"));


		// Add a button to redirect to another page
		add(new Link<Void>("redirectToAnotherPage") {
			@Override
			public void onClick() {
				setResponsePage(TodoListPage.class);
			}
		});
	}
}
