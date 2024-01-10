package com.equitativa.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.persist.jpa.JpaPersistOptions;
import com.google.inject.servlet.GuiceServletContextListener;

public class GuiceServletConfiguration extends GuiceServletContextListener {


    private static final String PERSISTENCE_UNIT_NAME = "DreamHouseRealtyManagement";


    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
                new JpaPersistModule(PERSISTENCE_UNIT_NAME, JpaPersistOptions.builder().build()),
                new GuiceServletModule()
        );
    }


}
