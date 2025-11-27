package com.youtube.tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.List;
import java.util.Random;

import static com.youtube.UI.DetalleDeLaCancion.LBL_TITLE;
import static com.youtube.UI.ListadeCancionesUI.LBL_NOMBRECANCION;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SeleccionCancionTask implements Task {

    private final String nombreCancion;

    public SeleccionCancionTask(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        List<WebElementFacade> listaCanciones = LBL_NOMBRECANCION.of(nombreCancion).resolveAllFor(actor);
        Random random= new Random();
        int index= random.nextInt(listaCanciones.size());
        WebElementFacade cancion = listaCanciones.get(index);
        cancion.click();

        actor.attemptsTo(
                WaitUntil.the(LBL_TITLE, isVisible()).forNoMoreThan(15).seconds()
        );

    }

    public static SeleccionCancionTask on(String nombreCancion){

        return instrumented(SeleccionCancionTask.class,nombreCancion);
    }
}
