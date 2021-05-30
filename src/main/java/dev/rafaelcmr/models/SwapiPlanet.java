package dev.rafaelcmr.models;

import java.util.Arrays;

public class SwapiPlanet {

    private String name;
    private String climate;
    private String terrain;
    private String[] films;

    public SwapiPlanet() {
    }

    public SwapiPlanet(String name) {
        this.name = name;
    }

    public SwapiPlanet(String name, String climate, String terrain, String[] films) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
        this.films = films;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String[] getFilms() {
        return films;
    }

    public void setFilms(String[] films) {
        this.films = films;
    }

    @Override
    public String toString() {
        return "SwapiPlanet{" +
                "name='" + name + '\'' +
                ", climate='" + climate + '\'' +
                ", terrain='" + terrain + '\'' +
                ", films=" + Arrays.toString(films) +
                '}';
    }
}