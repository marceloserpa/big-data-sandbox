package com.mserpa.esper.poc;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;


public class EsperEngine {

    private EPServiceProvider epServiceProvider;
    private EventUpdateListener listener;

    public EsperEngine() {
        this.epServiceProvider = EPServiceProviderManager.getDefaultProvider();
        this.listener = new EventUpdateListener();
        this.epServiceProvider.getEPAdministrator().getConfiguration().addEventType(Event.class);
    }

    public void registerDefaultEvent(String nameEvent){
        addEPL("create window "+nameEvent+"Status.win:length(2) as (name string, status string, parent string)");
        addEPL("insert into "+nameEvent+"Status select name, 'warning' as status, parent from Event(status='warning', name='"+nameEvent+"')");
        addEPL("insert into "+nameEvent+"Status select name, 'good' as status, parent from Event(status='up', name='"+nameEvent+"')");
        addEPL("insert into "+nameEvent+"Status select name, 'error' as status, parent from Event(status='down', name='"+nameEvent+"')");
    }

    public void addEPL(String epl){
        System.out.println(epl);
        epServiceProvider.getEPAdministrator().createEPL(epl).addListener(listener);
    }

    public void triggerEvent(Event event){
        epServiceProvider.getEPRuntime().sendEvent(event);
    }

}
