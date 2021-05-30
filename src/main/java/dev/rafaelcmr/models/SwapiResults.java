package dev.rafaelcmr.models;

import java.util.Arrays;
import java.util.List;

public class SwapiResults {

    private List<SwapiPlanet> results;

    public SwapiResults(List<SwapiPlanet> results) {
        this.results = results;
    }

    public SwapiResults() {
    }

    public List<SwapiPlanet> getResults() {
        return results;
    }

    public void setResults(List<SwapiPlanet> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "SwapiResults{" +
                "results=" + results +
                '}';
    }
}
