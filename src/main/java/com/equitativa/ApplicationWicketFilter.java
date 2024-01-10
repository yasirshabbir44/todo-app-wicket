package com.equitativa;

import com.google.inject.Provider;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.apache.wicket.protocol.http.IWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;

@Singleton
public class ApplicationWicketFilter extends WicketFilter {

    @Inject
    private Provider<WebApplication> webApplicationProvider;

    @Override
    protected IWebApplicationFactory getApplicationFactory() {
        return new IWebApplicationFactory() {
            @Override
            public WebApplication createApplication(WicketFilter filter) {
                return webApplicationProvider.get();
            }

            @Override
            public void destroy(WicketFilter filter) {
            }
        };
    }
}
