package dev.rafaelcmr.controller;

import dev.rafaelcmr.models.Planet;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SwapiControllerTests {

    private final String baseUrl = "http://localhost:";
    private final String PLANET_TEST = "Tatooine";

    @LocalServerPort
    private int PORT;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCanPageAllPlanets() {
        String uri = this.baseUrl + this.PORT + "/";
        String response = this.restTemplate.getForObject(uri, String.class);
        assertThat(response).contains("pageable");

    }
    @Test
    @DisplayName("GET listar planeta usando @RequestParam passando o nome")
    public void testCanGetPlanet() {
        String uri = this.baseUrl + this.PORT + "/";
        Planet payload = new Planet("Tatooine", "arid", "desert");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Planet> entity = new HttpEntity<Planet>(payload, headers);
        Planet response = this.restTemplate.postForObject(uri, entity, Planet.class);

        uri += "find/?name=" + response.getName();
        response = this.restTemplate.getForObject(uri, Planet.class);
        assertThat(response.getName()).isEqualTo("Tatooine");

    }
    @Test
    @DisplayName("GET listar planeta por ID")
    public void testCanGetPlanetWithId() {
        String uri = this.baseUrl + this.PORT + "/";
        Planet payload = new Planet("Tatooine", "arid", "desert");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Planet> entity = new HttpEntity<Planet>(payload, headers);

        Planet response = this.restTemplate.postForObject(uri, entity, Planet.class);

        uri += "planets/" + response.getId();
        response = this.restTemplate.getForObject(uri, Planet.class);
        assertThat(response.getName()).isEqualTo("Tatooine");

    }

    @Test
    @DisplayName("Api post para salvar planeta na database")
    public void testCanPostAPlanet() {
        String uri = this.baseUrl + this.PORT + "/";
        Planet payload = new Planet("Tatooine", "arid", "desert");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Planet> entity = new HttpEntity<Planet>(payload, headers);

        Planet response = this.restTemplate.postForObject(uri, entity, Planet.class);
        System.out.println(response.toString());
        assertThat(response.getId()).isNotEqualTo(null);
    }

    @Test
    @DisplayName("Deletar planeta passando o ID")
    public void testCanDeleteAPlanetWithId() {
        String uri = this.baseUrl + this.PORT + "/";
        Planet payload = new Planet("Tatooine", "arid", "desert");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Planet> entity = new HttpEntity<Planet>(payload, headers);

        Planet response = this.restTemplate.postForObject(uri, entity, Planet.class);
        uri += "delete/" + response.getId();
        this.restTemplate.delete(uri);
    }

    @Test
    @DisplayName("Deletar planeta com @RequestParam passando o nome")
    public void testCanDeleteAPlanet() {
        String uri = this.baseUrl + this.PORT + "/";
        Planet payload = new Planet("Tatooine", "arid", "desert");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Planet> entity = new HttpEntity<Planet>(payload, headers);

        Planet response = this.restTemplate.postForObject(uri, entity, Planet.class);
        uri += "delete/?name=" + response.getName();
        this.restTemplate.delete(uri);
    }
}
