package com.pavelvic.ya_calc_ui_tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CalculatorTest {

    public static WebDriver driver;
    public static SearchPage searchPage;
    public static ResultPage resultPage;

    //настройка окружения перед тестами + открытие главной страницы
    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        searchPage = new SearchPage(driver);
        resultPage = new ResultPage(driver);
        driver.manage().window().maximize();
        driver.get(ConfProperties.getProperty("mainpage"));
        searchPage.inputSearchText("Калькулятор");
        searchPage.clickSearchBtn();
    }

    @DataProvider
    public Object[][] possibleInputs() {
        return new Object[][]{
                {"√(144)", "12", "DEG"},
                {"1,5*100", "150", "DEG"},
                {"cos(p/2 )", "0","RAD"},

        };
    }

    //загрузился ли калькулятор в результатах поиска
    @Test
    public void testCalculatorInFastResultSection() {
        assertThat(resultPage.getFastSearchResultType().orElse("smth othr"), is("calculator"));
    }

    //тест ввода значений с клавиатурыв текствое поле
    @Test(dataProvider = "possibleInputs")
    public void testKeyboardInput(String input, String expected, String mode) {
        //установка режима калькулятора
        if (mode.equals("RAD")) resultPage.clickRad();

        //вводим значения
        resultPage.inputExpression(input);

        //ожидание для прогрузки
        try {
            Thread.sleep(1*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //равно
        resultPage.clickEqualBtn();

        //TODO разобраться с таймаутами и поставить средствами селениум в правильном месте
        //ожидание для прогрузки
        try {
            Thread.sleep(1*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //проверяем
        assertThat(resultPage.getResult(),is(expected));

        //сбрасываем значения
        resultPage.clickClearBtn();
    }

    //убираем за собой
    @AfterClass
    public void tearDown() {
        //driver.quit();
    }
}