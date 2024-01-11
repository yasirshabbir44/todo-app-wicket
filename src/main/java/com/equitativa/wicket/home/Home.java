package com.equitativa.wicket.home;

import com.equitativa.wicket.base.BasePage;
import com.equitativa.wicket.tasklist.TaskListPage;
import org.apache.wicket.markup.html.link.Link;

public class Home extends BasePage {


    public Home() {
        add(new Link<Void>("redirectToHomePage") {
            @Override
            public void onClick() {
                setResponsePage(Home.class);
            }
        });

        add(new Link<Void>("redirectToTaskPage") {
            @Override
            public void onClick() {
                setResponsePage(TaskListPage.class);
            }
        });

        add(new Link<Void>("redirectToGetStarted") {
            @Override
            public void onClick() {
                setResponsePage(TaskListPage.class);
            }
        });

    }


}