package com.youtube.utils;

import com.youtube.interactions.ConsumirServicio;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.questions.TheResponse;
import org.hamcrest.Matchers;

import java.util.HashMap;
import java.util.Map;

import static com.youtube.utils.Constantes.BASE_JSON_PLACEHOLDER;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class ServiceMethodsExample {

    private static Actor jose;

    public static void main(String[] args) {

        jose = Actor.named("Jose Arturo")
                .can(CallAnApi.at(BASE_JSON_PLACEHOLDER));

        Map<String, String> headersConAuth = new HashMap<>();
        headersConAuth.put("Authorization", "Bearer ABC123XYZ");
        headersConAuth.put("Accept", "application/json");

        System.out.println("Headers a enviar: " + headersConAuth);

        final String TITULO_POST = "Post con Token";
        final String nuevoPost = "{\"title\": \"" + TITULO_POST + "\", \"body\": \"Contenido\", \"userId\": 1}";

        System.out.println("\n--- A. Petición POST (Creación de Recurso) ---");

        jose.attemptsTo(
                ConsumirServicio.post("/posts", nuevoPost, headersConAuth)
        );

        System.out.println("Status Code: " + lastResponse().getStatusCode());

        try {
            jose.should(
                    seeThat("El código de estado del POST", TheResponse.statusCode(), Matchers.equalTo(201))
            );

            lastResponse().then().body("title", Matchers.equalTo(TITULO_POST));

            System.out.println("POST EXITOSO: Status 201 y Título devuelto correctamente.");

        } catch (AssertionError e) {
            System.err.println("VALIDACIÓN FALLÓ en POST: " + e.getMessage());
        }

        System.out.println("\n--- B. Petición GET (Lectura de Recurso) ---");

        jose.attemptsTo(
                ConsumirServicio.get("/posts/1", headersConAuth)
        );

        System.out.println("Status Code: " + lastResponse().getStatusCode());
        String tituloObtenido = lastResponse().jsonPath().getString("title");
        System.out.println("Título: " + tituloObtenido);

        try {
            jose.should(
                    seeThat("El código de estado del GET", TheResponse.statusCode(), Matchers.equalTo(200))
            );

            lastResponse().then().body("title", Matchers.containsString("sunt"));

            System.out.println("GET EXITOSO: Status 200 y Título contiene 'sunt'.");

        } catch (AssertionError e) {
            System.err.println("VALIDACIÓN FALLÓ en GET: " + e.getMessage());
        }

        System.out.println("\n--- EJEMPLO COMPLETADO ---");
    }
}