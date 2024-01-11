package com.equitativa;

import com.equitativa.wicket.home.Home;
import com.google.inject.Injector;
import de.agilecoders.wicket.core.Bootstrap;
import jakarta.inject.Inject;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 *
 * @see com.equitativa.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
    private final Injector injector;

    @Inject
    public WicketApplication(Injector injector) {
        this.injector = injector;
    }


    @Override
    public Class<? extends WebPage> getHomePage() {
        return Home.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();
        // Enable GUICE CDI
        getComponentInstantiationListeners().add(new GuiceComponentInjector(this, injector, false));
        Bootstrap.install(this);

        // Disable Content Security Policy for Testing Purposes
        getCspSettings().blocking().disabled();
    }
}
