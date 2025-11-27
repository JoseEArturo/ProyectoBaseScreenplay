package com.youtube.interactions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;

import java.util.List;
import java.util.Random;

import static com.youtube.UI.ListadeCancionesUI.LBL_NOMBRECANCION;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SeleccionYReproduccion implements Interaction {

    private final String nombreCancion;

    public SeleccionYReproduccion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        List<WebElementFacade> listaCanciones = LBL_NOMBRECANCION.of(nombreCancion).resolveAllFor(actor);
        Random random= new Random();
        int index= random.nextInt(listaCanciones.size());
        WebElementFacade cancion = listaCanciones.get(index);
        cancion.click();
    }

    public static Performable click(String nombreCancion){
        return instrumented(SeleccionYReproduccion.class, nombreCancion);
    }
}
