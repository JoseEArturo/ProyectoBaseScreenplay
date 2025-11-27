package com.youtube.interactions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;

import java.util.Collections;
import java.util.Map;

import static com.youtube.utils.Constantes.BASE_JSON_PLACEHOLDER;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConsumirServicio implements Interaction {

    private final String httpMethod;
    private final String endPoint;
    private final Object body;
    private final Map<String, String> headers;

    public ConsumirServicio(String httpMethod, String endPoint, Object body, Map<String, String> headers) {
        this.httpMethod = httpMethod;
        this.endPoint = endPoint;
        this.body = body;
        this.headers = headers != null ? headers : Collections.emptyMap();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        String fullUrl = BASE_JSON_PLACEHOLDER + endPoint;

        SerenityRest.given()
                .headers(this.headers)
                .contentType(this.headers.getOrDefault("Content-Type", "application/json"))
                .body(body != null ? body : "")
                .when()
                .request(httpMethod, fullUrl);
    }

    private static Performable crearInteraccion(String method, String resource, Object body, Map<String, String> headers) {
        return instrumented(ConsumirServicio.class, method, resource, body, headers);
    }

    // GET (sin body)
    public static Performable get(String resource, Map<String, String> headers) {
        return crearInteraccion("GET", resource, null, headers);
    }
    public static Performable get(String resource) {
        return get(resource, null);
    }

    // POST (con body)
    public static Performable post(String resource, Object body, Map<String, String> headers) {
        return crearInteraccion("POST", resource, body, headers);
    }
    public static Performable post(String resource, Object body) {
        return post(resource, body, null);
    }

    // PUT (con body)
    public static Performable put(String resource, Object body, Map<String, String> headers) {
        return crearInteraccion("PUT", resource, body, headers);
    }
    public static Performable put(String resource, Object body) {
        return put(resource, body, null);
    }

    // DELETE (sin body)
    public static Performable delete(String resource, Map<String, String> headers) {
        return crearInteraccion("DELETE", resource, null, headers);
    }
    public static Performable delete(String resource) {
        return delete(resource, null);
    }
}