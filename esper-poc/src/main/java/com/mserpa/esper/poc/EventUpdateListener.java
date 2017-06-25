package com.mserpa.esper.poc;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;


public class EventUpdateListener implements UpdateListener {

    @Override
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        String name = (String) newEvents[0].get("name");
        String status = (String) newEvents[0].get("status");
        System.out.println("Name: " + name + " Status: " + status);
    }
}
