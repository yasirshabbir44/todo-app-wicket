package com.equitativa.temp;

import com.equitativa.base.BasePage;
import com.equitativa.wicket.home.HomePage;
import com.equitativa.wicket.tasklist.TaskListPage;
import org.apache.wicket.markup.html.link.Link;

public class Temp extends BasePage {


    public Temp() {
        add(new Link<Void>("redirectToHomePage") {
            @Override
            public void onClick() {
                setResponsePage(HomePage.class);
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