package com.krishnasaparia.Covid19.models;

public class LocationState {
    private String state;
    private String country;
    private int latestCases;

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestCases() {
        return latestCases;
    }

    public void setLatestCases(int latestCases) {
        this.latestCases = latestCases;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Override
    public String toString() {
        return "LocationState{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestCases=" + latestCases +
                '}';
    }
}
