package com.equitativa.guice;


import com.equitativa.ApplicationWicketFilter;
import com.equitativa.WicketApplication;
import com.google.inject.Scopes;
import com.google.inject.persist.PersistFilter;
import com.google.inject.servlet.ServletModule;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;

import java.util.Map;

/**
 * A configuration to use Guice DI in the Wicket APP.
 * It will enable both the persistence filter and the wicket filter.
 *
 * @see <a href="https://software.danielwatrous.com/wicket-guice-including-unittests/">Wicket and Guice</a>
 */
public class GuiceServletModule extends ServletModule {

    private static final String WICKET_APPLICATION_CLASS_NAME_PARAM = "applicationClassName";

    @Override
    protected void configureServlets() {
        filter("/*").through(PersistFilter.class);
        filter("/*").through(WicketFilter.class, getWicketFilterInitParams());
        bind(WebApplication.class).to(WicketApplication.class);
        bind(WicketFilter.class)
                .to(ApplicationWicketFilter.class)
                .in(Scopes.SINGLETON);
    }

    private Map<String, String> getWicketFilterInitParams() {
        return Map.of(
                WicketFilter.FILTER_MAPPING_PARAM, "/*",
                WICKET_APPLICATION_CLASS_NAME_PARAM, WicketApplication.class.getName()
        );
    }
}
