package com.mserpa.esper.poc;


public class Event {

    private String name;
    private String status;
    private String parent;

    public Event() {
    }

    public Event(String name, String status, String parent) {
        this.name = name;
        this.status = status;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
