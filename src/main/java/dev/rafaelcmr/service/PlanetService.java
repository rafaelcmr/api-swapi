package dev.rafaelcmr.service;

import dev.rafaelcmr.models.Planet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlanetService {

    //GET
    Page<Planet> paginate(Pageable pageable);
    Planet find(String name);
    Planet findId(String id);

    //POST
    Planet save(Planet planet) throws Exception;

    //DELETE
    void delete(String name);
    void deleteId(String id);

    //UTILS
    int count();
    int[] validNamePlanetAndFilmCount(String name);
}
