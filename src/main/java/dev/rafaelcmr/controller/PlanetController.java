package dev.rafaelcmr.controller;

import dev.rafaelcmr.models.Planet;
import dev.rafaelcmr.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlanetController {

    @Autowired
    private PlanetService service;

    @GetMapping
    public Page<Planet> getPlanets(Pageable pageable) {
        return this.service.paginate(pageable);
    }

    @GetMapping("/planets/{id}")
    public Planet getPlanetId(@PathVariable String id) {
        return this.service.findId(id);
    }

    @GetMapping("/find")
    public Planet getPlanet(@RequestParam String name) {
        return this.service.find(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Planet postPlanet(@RequestBody Planet planet) throws Exception {
        return this.service.save(planet);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlanet(@RequestParam String name) {
        this.service.delete(name);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlanetId(@PathVariable String id) {
        this.service.deleteId(id);
    }
}
