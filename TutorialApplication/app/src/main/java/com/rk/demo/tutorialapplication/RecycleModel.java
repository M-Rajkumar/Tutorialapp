package com.rk.demo.tutorialapplication;

public class RecycleModel {

    String id;
    String team;

    public RecycleModel(String id, String team) {
        this.id = id;
        this.team = team;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
