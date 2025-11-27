package com.youtube.UI;

import net.serenitybdd.screenplay.targets.Target;

public class ListadeCancionesUI {
    public static Target LBL_NOMBRECANCION= Target.the("Lista canciones")
            .locatedBy("//yt-formatted-string[@class='style-scope ytd-video-renderer' and contains(@aria-label,'{0}')]");
}
