package com.youtube.stepDefinitions;

import com.youtube.models.NombreCancion;
import com.youtube.questions.ValidarCancion;
import com.youtube.tasks.BuscarCancionTask;
import com.youtube.tasks.SeleccionCancionTask;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.es.*;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.model.util.EnvironmentVariables;
import org.hamcrest.Matchers;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.*;

public class YoutubeStepDefinition {

    private static EnvironmentVariables environmentVariables;
    NombreCancion nombreCancion;

    @Before
    public void setStage(){
        setTheStage(new OnlineCast());
    }

    @Dado("que el usuario abre el navegador en la pagina de youtube")
    public void queElUsuarioAbreElNavegadorEnLaPaginaDeYoutube() throws InterruptedException {
        String baseUrl = environmentVariables.optionalProperty("environments.qa.base.url").get();
        theActorCalled("user").wasAbleTo(
                Open.url(baseUrl)
        );

    }
    @Cuando("el usuario ingresa el nombre de la cancion y presione enter")
    public void elUsuarioIngresaElNombreDeLaCancion(DataTable dataTable) {
        nombreCancion = NombreCancion.setData(dataTable).get(0);

        theActorInTheSpotlight().attemptsTo(
                BuscarCancionTask.on(nombreCancion)
        );

    }

    @Entonces("el usuario podra reproducir una cancion.")
    public void elUsuarioPodraReproducirUnaCancion(DataTable dataTable) {
        nombreCancion = NombreCancion.setData(dataTable).get(0);
        String cancionBuscada = nombreCancion.getNombreCancion();

        theActorInTheSpotlight().attemptsTo(
                SeleccionCancionTask.on(cancionBuscada)
        );

        theActorInTheSpotlight().should(
                seeThat(
                        ValidarCancion.from(), Matchers.equalTo(true)
                )
        );

    }
}
