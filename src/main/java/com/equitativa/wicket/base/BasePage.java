package com.equitativa.wicket.base;

import com.equitativa.wicket.tasklist.TaskListPage;
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
    private static final String POPPER_MIN_JS = "https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js";
    private static final String MAIN_CSS_URL = "/style.css";


    public BasePage() {
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(CssReferenceHeaderItem.forUrl(BOOTSTRAP_MIN_CSS));
        response.render(CssReferenceHeaderItem.forUrl(MAIN_CSS_URL));
        response.render(CssReferenceHeaderItem.forUrl("https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css"));
        response.render(CssReferenceHeaderItem.forUrl("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css"));
        response.render(CssReferenceHeaderItem.forUrl("/fontawesome-5.5/css/all.min.css"));
        response.render(CssReferenceHeaderItem.forUrl("/slick/slick.css"));
        response.render(CssReferenceHeaderItem.forUrl("/slick/slick-theme.css"));
        response.render(CssReferenceHeaderItem.forUrl("/magnific-popup/magnific-popup.css"));
        response.render(CssReferenceHeaderItem.forUrl("/css/bootstrap.min.css"));
        response.render(CssReferenceHeaderItem.forUrl("/css/templatemo-style.css"));


        response.render(JavaScriptReferenceHeaderItem.forUrl("/js/jquery-1.9.1.min.js"));
        response.render(JavaScriptReferenceHeaderItem.forUrl("/slick/slick.min.js"));
        response.render(JavaScriptReferenceHeaderItem.forUrl("/magnific-popup/jquery.magnific-popup.min.js"));
        response.render(JavaScriptReferenceHeaderItem.forUrl("/js/jquery.singlePageNav.min.js"));
        response.render(JavaScriptReferenceHeaderItem.forUrl("/js/bootstrap.min.js"));
        response.render(JavaScriptReferenceHeaderItem.forUrl(POPPER_MIN_JS));
        response.render(JavaScriptReferenceHeaderItem.forUrl("https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"));


    }

}
