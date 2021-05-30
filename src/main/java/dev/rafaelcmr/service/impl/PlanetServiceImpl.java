package dev.rafaelcmr.service.impl;

import dev.rafaelcmr.exception.PlanetNotFoundException;
import dev.rafaelcmr.models.Planet;
import dev.rafaelcmr.models.SwapiPlanet;
import dev.rafaelcmr.models.SwapiResults;
import dev.rafaelcmr.repository.PlanetRepository;
import dev.rafaelcmr.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class PlanetServiceImpl implements PlanetService {

    @Autowired
    private PlanetRepository repository;


    @Override
    public Page<Planet> paginate(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @Override
    public Planet find(String name) {
        Planet model = this.repository.findByName(name);
        if(model == null) {
            throw new PlanetNotFoundException("Planeta não encontrado.");
        }

        return model;
    }

    @Override
    public Planet findId(String id) {
        Planet model = this.repository.findById(id).orElseThrow();
        if(model == null) {
            throw new PlanetNotFoundException("Deu ruim ein, nao achei com o ID ." + id);
        }

        return model;
    }

    @Override
    @Transactional
    public Planet save(Planet planet) throws Exception {
        Planet model = this.repository.findByName(planet.getName());
        int[] validator = validNamePlanetAndFilmCount(planet.getName());

        if(model != null) {
            return model;
        }
        if(validator[0] == 1) {
            throw new Exception("Planeta invalido");
        }
        planet.setFilmsCount(validator[1]);
        return this.repository.save(planet);
    }

    @Override
    public void delete(String name) {
        Planet model = this.repository.findByName(name);
        if(model == null) {
            throw new PlanetNotFoundException("Esse planeta nao existe no banco.");
        }
        this.repository.delete(model);
    }

    @Override
    public void deleteId(String id) {
        Planet model = this.repository.findById(id).orElseThrow();
        if(model == null) {
            throw new PlanetNotFoundException("Esse planeta nao existe no banco.");
        }
        this.repository.delete(model);
    }

    @Override
    public int count() {
        return (int) this.repository.count();
    }


    @Override
    public int[] validNamePlanetAndFilmCount(String name) {
        String baseUrl = "https://swapi.dev/api/planets/?search=";
        String uri = baseUrl + name;

        SwapiResults response = new RestTemplate().getForObject(uri, SwapiResults.class);
        SwapiPlanet planet = response.getResults()
                .stream().filter(a -> a.getName().equals(name)).findFirst()
                .orElse(new SwapiPlanet("EROU"));
        if(planet.getName().equals("EROU")) {
            return new int[]{1, 0};
        }
        return new int[]{0, planet.getFilms().length};

    }
}
