package com.youtube.UI;

import net.serenitybdd.screenplay.targets.Target;

public class PaginaInicialUI {
    public static Target TXT_BUSQUEDA= Target.the("Campo de busqueda de canciones")
            .locatedBy("//input[@name='search_query']");
}
