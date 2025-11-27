package com.youtube.questions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static com.youtube.UI.DetalleDeLaCancion.LBL_TITLE;

public class ValidarCancion implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        WebElementFacade cancion = LBL_TITLE.resolveFor(actor);
        return cancion.isDisplayed();
    }

    public static Question<Boolean> from(){
        return new ValidarCancion();
    }
}
