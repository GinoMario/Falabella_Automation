package org.example.framework.browserManager;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManager{
    @Override
    public void createDriver() {
        System.setProperty("webdriver.gecko.driver", "./src/main/resources/drivers/mac/geckodriver");
        driver = new FirefoxDriver();
    }
}
