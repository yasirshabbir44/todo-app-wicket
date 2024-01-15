package com.equitativa.wicket.event;

import com.equitativa.model.Task;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.event.IEventSource;

public class UpdateEvent implements IEvent<Void> {
    private final Task updatedData;

    public UpdateEvent(Task updatedData) {
        this.updatedData = updatedData;
    }

    public Task getUpdatedData() {
        return updatedData;
    }

    @Override
    public void stop() {

    }

    @Override
    public void dontBroadcastDeeper() {

    }

    @Override
    public Broadcast getType() {
        return null;
    }

    @Override
    public IEventSource getSource() {
        return null;
    }

    @Override
    public Void getPayload() {
        return null;
    }


}