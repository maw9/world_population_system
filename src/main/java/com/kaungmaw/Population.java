package com.kaungmaw;

public class Population {
    private final String countryName;
    private final int latestPopulation;

    public Population(String countryName, int latestPopulation) {
        this.countryName = countryName;
        this.latestPopulation = latestPopulation;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getLatestPopulation() {
        return latestPopulation;
    }

}
