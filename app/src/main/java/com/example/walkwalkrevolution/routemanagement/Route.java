package com.example.walkwalkrevolution.routemanagement;

import java.io.Serializable;

public class Route implements Serializable, Comparable<Route> {
    private String name;
    private String startLoc;
    private long steps;
    private double distance;
    private long time;
    //Features eventually
    private boolean favorite;

    // Constructor
    public Route(String name, String startLocation, long steps, double distance, long time) {
        this.name = name;
        this.startLoc = startLocation;
        this.steps = steps;
        this.distance = distance;
        this.time = time;
    }

    // Name setter
    public void setName(String name){
        this.name = name;
    }

    // Name getter
    public String getName(){
        return this.name;
    }

    // startLoc setter
    public void setStartLoc(String startLoc) {
        this.startLoc = startLoc;
    }

    // startLoc getter
    public String getStartLoc() {
        return this.startLoc;
    }

    // Steps getter
    public long getSteps(){
        return this.steps;
    }

    // Steps setter
    public void setSteps(long steps){
        this.steps = steps;
    }

    // Distance getter
    public double getDistance(){
        return this.distance;
    }

    // Distance setter
    public void setDistance(double distance){
        this.distance = distance;
    }

    // Time getter
    public long getTime(){
        return this.time;
    }

    // Time setter
    public void setTime(long time){
        this.time = time;
    }

    // favorite setter
    public void setFavorite(boolean fav) {
        this.favorite = fav;
    }

    // startLoc getter
    public boolean getFavorite() {
        return this.favorite;
    }

    //favorite toggle
    public void toggleFavorite(){
        this.favorite = !favorite;
    }

    @Override
    public int compareTo(Route route) {
        return this.getName().compareTo(route.getName());
    }
}
