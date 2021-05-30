package dev.rafaelcmr.models;

import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document
public class Planet {
    @Id
    private String id;
    @NotNull(message = "Nome não pode ser nulo")
    private String name;
    @NotNull(message = "Clima não pode ser nulo")
    private String climate;
    @NotNull(message = "Terreno não pode ser nulo")
    private String terrain;
    private int filmsCount;

    public Planet() {
    }

    public Planet(String name, String climate, String terrain) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
    }

    public Planet(String name, String climate, String terrain, int filmsCount) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
        this.filmsCount = filmsCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getFilmsCount() {
        return filmsCount;
    }

    public void setFilmsCount(int filmsCount) {
        this.filmsCount = filmsCount;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", climate='" + climate + '\'' +
                ", terrain='" + terrain + '\'' +
                ", filmsCount=" + filmsCount +
                '}';
    }
}
