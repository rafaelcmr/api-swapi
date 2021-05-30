package dev.rafaelcmr.controller;

import dev.rafaelcmr.models.Planet;
import dev.rafaelcmr.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PlanetController {

    @Autowired
    private PlanetService service;

    @GetMapping
    public ResponseEntity<Page<Planet>> getPlanets(Pageable pageable) {
        try {
            return ResponseEntity.ok(this.service.paginate(pageable));

        } catch(Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/planets/{id}")
    public ResponseEntity<Planet> getPlanetId(@PathVariable String id) {
        try {
            return ResponseEntity.ok(this.service.findId(id));

        } catch(Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find")
    public ResponseEntity<Planet> getPlanet(@RequestParam String name) {
        try {
            return ResponseEntity.ok(this.service.find(name));

        } catch(Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<Planet> postPlanet(@RequestBody @Valid Planet planet) throws Exception {
        try {
            return new ResponseEntity<>(this.service.save(planet), HttpStatus.CREATED);

        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletePlanet(@RequestParam String name) {
        try {
            this.service.delete(name);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePlanetId(@PathVariable String id) {
        try {
            this.service.deleteId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
