package com.pavelvic.ya_calc_ui_tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class CalculatorTest {

    public static WebDriver driver;
    public static SearchPage searchPage;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        searchPage = new SearchPage(driver);
        driver.manage().window().maximize();
        driver.get(ConfProperties.getProperty("mainpage"));
    }

    @Test
    public void openPage() {
      searchPage.inputSearchText("Калькулятор");
      searchPage.clickSearchBtn();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}