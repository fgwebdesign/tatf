package com.tatf.core.driver.manager;

import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriver extends DriverManager {
    /**
     * Crea el driver de Firefox con las opciones por defecto.
     */
    public FirefoxDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setAcceptInsecureCerts(true);

        this.driver = new org.openqa.selenium.firefox.FirefoxDriver(firefoxOptions);
        this.driver.manage().window().maximize();
        setDefaultConfig();
    }
}
