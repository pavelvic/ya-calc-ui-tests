package com.pavelvic.ya_calc_ui_tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class MainPageTest {

    @Test
    public void openPage() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://selenium.dev");

        driver.quit();
    }
}