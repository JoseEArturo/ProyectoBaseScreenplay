package com.youtube.UI;

import net.serenitybdd.screenplay.targets.Target;

public class DetalleDeLaCancion {
    public static Target LBL_TITLE = Target
            .the("songs collections").
            locatedBy("//h1[@class='style-scope ytd-watch-metadata']");
}
