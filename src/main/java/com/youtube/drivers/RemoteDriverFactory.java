package com.youtube.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import static com.youtube.utils.Constantes.URL_ALTERNA_DESCARGA_EDGE_DRIVER;

public class RemoteDriverFactory {

    public static WebDriver createWebDriver(String browserName) throws IOException {
        switch (browserName.toLowerCase()) {
            case "chrome":
                return createChromeDriver();
            case "edge":
                return createEdgeDriver();
            case "firefox":
                return createFirefoxDriver();
            default:
                throw new IllegalArgumentException("Navegador no soportado: " + browserName);
        }
    }

    private static WebDriver createChromeDriver() {
        HashMap<String, Object> preferences = new HashMap<>();
        preferences.put("profile.default_content_settings.automatic_downloads", 1);
        preferences.put("download.prompt_for_download", false);
        preferences.put("default_content_settings.popups", 0);
        preferences.put("plugins.always_open_pdf_externally", true);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(
                "start-maximized",
                "test-type",
                "no-sandbox",
                "lang=es",
                "disable-popup-blocking",
                "disable-infobars",
                "disable-download-notification",
                "ignore-certificate-errors",
                "allow-running-insecure-content",
                "disable-translate",
                "always-authorize-plugins",
                "disable-extensions",
                "disable-gpu",
                "disable-default-apps",
                "remote-allow-origins=*"
        );

        chromeOptions.setExperimentalOption("prefs", preferences);

        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(chromeOptions);
    }

    private static WebDriver createEdgeDriver() throws IOException {
        WebDriverManager.getInstance(DriverManagerType.EDGE)
                .driverRepositoryUrl(new URL(URL_ALTERNA_DESCARGA_EDGE_DRIVER))
                .setup();

        EdgeOptions options = new EdgeOptions();
        options.setAcceptInsecureCerts(true);
        options.addArguments(
                "--start-maximized",
                "--test-type",
                "--ignore-certificate-errors",
                "--disable-infobars",
                "--disable-gpu",
                "--disable-default-apps",
                "--disable-popup-blocking",
                "--remote-allow-origins=*",
                "--disable-blink-features=AutomationControlled",
                "--lang=es-CO",
                "--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0"
        );
        return new EdgeDriver(options);

    }

    private static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--disable-infobars", "--disable-extensions", "--disable-notifications",
                "--disable-translate", "--disable-background-timer-throttling", "--disable-sync");
        firefoxOptions.addPreference("network.automatic-ntlm-auth.allow-non-fqdn", true);

        return new FirefoxDriver(firefoxOptions);
    }
}
