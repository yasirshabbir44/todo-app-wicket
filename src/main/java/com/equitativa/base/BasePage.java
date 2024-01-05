package com.equitativa.base;

import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.WebPage;

/**
 * A base page class to be used as a parent for the pages we're implementing.
 * This would do some common page configuration, such as adding bootstrap.
 */
public abstract class BasePage extends WebPage {


    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(CssReferenceHeaderItem.forUrl("https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"));
        response.render(JavaScriptReferenceHeaderItem.forUrl("https://code.jquery.com/jquery-3.5.1.slim.min.js"));
        response.render(JavaScriptReferenceHeaderItem.forUrl("https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"));
        response.render(JavaScriptReferenceHeaderItem.forUrl("https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"));
    }

}
