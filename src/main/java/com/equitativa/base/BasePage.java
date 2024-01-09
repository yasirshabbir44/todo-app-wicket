package com.equitativa.base;

import com.equitativa.home.HomePage;
import com.equitativa.todo.TaskListPage;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

/**
 * A base page class to be used as a parent for the pages we're implementing.
 * This would do some common page configuration, such as adding bootstrap.
 */
public abstract class BasePage extends WebPage {


    private static final String BOOTSTRAP_MIN_CSS = "https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css";
    private static final String JQUERY_COM_JQUERY_3_5_1 = "https://code.jquery.com/jquery-3.5.1.slim.min.js";
    private static final String POPPER_MIN_JS = "https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js";
    private static final String DIST_JS_BOOTSTRAP_MIN_JS = "https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js";

    private static final String MAIN_CSS_URL = "/style.css";
    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(CssReferenceHeaderItem.forUrl(BOOTSTRAP_MIN_CSS));
        response.render(JavaScriptReferenceHeaderItem.forUrl(JQUERY_COM_JQUERY_3_5_1));
        response.render(JavaScriptReferenceHeaderItem.forUrl(POPPER_MIN_JS));
        response.render(JavaScriptReferenceHeaderItem.forUrl(DIST_JS_BOOTSTRAP_MIN_JS));
        response.render(CssReferenceHeaderItem.forUrl(MAIN_CSS_URL));
    }

    public BasePage(){
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
    }

}