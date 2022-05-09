package com.pavelvic.ya_calc_ui_tests;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CalculatorTest {

    public static WebDriver driver;
    public static SearchPage searchPage;
    public static ResultPage resultPage;

    //настройка окружения перед тестами + открытие страницы с тестируемым приложением
    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        searchPage = new SearchPage(driver);
        resultPage = new ResultPage(driver);
        driver.get(ConfProperties.getProperty("mainpage"));
        searchPage.inputSearchText("Калькулятор");
        searchPage.clickSearchBtn();
    }

    @DataProvider
    private Object[][] possibleInputs() {
        return new Object[][]{
                {"√(144)", "12", "DEG"},
                {"1,5*100", "150", "DEG"},
                {"cos(p / 2)", "0","RAD"},

        };
    }

    private void waitSomething(long millis)
    {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //загрузился ли калькулятор в результатах поиска (приоритет 1, так как другие тесты не имеют смысла без проверки окружения, остальные тесты - приоритет 2)
    @Step ("Калькулятор загрузился на странице с результатами поиска")
    @Test (priority = 1)
    public void testHasCalculatorInFastResultSection() {
        String expected = "calculator";
        String actual = resultPage.getFastSearchResultType().orElse("smth othr");
        assertThat(actual, is(expected));
    }

    //тест ввода значений с клавиатурыв текствое поле
    @Step ("Ввод с клавиатуры {input} = {expectedResult} ({mode})")
    @Test(dataProvider = "possibleInputs", priority = 2)
    public void testKeyboardInput(String input, String expectedResult, String mode) {
        //установка режима калькулятора
        switch (mode) {
            case  "DEG":
                resultPage.clickDeg();
                break;
            case "RAD":
                resultPage.clickRad();
                break;
        }

        //вводим значения
        resultPage.inputExpression(input);

        //нужна пауза, чтобы кликнуть равно гарантированно после ввода и не раньше
        //TODO как правильно сделать ожидание? Средствами Selenium, многопоточность, простой while c запросом
        waitSomething(100);

        //равно
        resultPage.clickEqualBtn();

        //TODO как правильно сделать ожидание? Средствами Selenium, многопоточность, простой while c запросом
        //нужна пазуа, чтобы метод ганатированно запросил и получил результат после нажатия равно
        waitSomething(100);

        //получаем результат и проверяем с ожиданием
        assertThat(resultPage.getResult(),is(expectedResult));
    }

    @Test (description = "test case: sqrt(144) = 12", priority = 2)
    public void testSqrt144is12WithManualInput () {
        String expected = "12";
        resultPage.clickDeg();
        resultPage.clickSqrtBtn();
        resultPage.clickOneBtn();
        resultPage.clickFourBtn();
        resultPage.clickFourBtn();
        resultPage.clickEqualBtn();
        String actual = resultPage.getResult();
        assertThat(actual,is(expected));
    }

    @Test(description = "test case: 1.5*100 = 150", priority = 2)
    public void testMultiply1point5and100Is150WithManualInput () {
        String expected = "150";
        resultPage.clickDeg();
        resultPage.clickOneBtn();
        resultPage.clickSeparatorBtn();
        resultPage.clickFiveBtn();
        resultPage.clickMultiplyBtn();
        resultPage.clickOneBtn();
        resultPage.clickNullBtn();
        resultPage.clickNullBtn();
        resultPage.clickEqualBtn();
        String actual = resultPage.getResult();
        assertThat(actual,is(expected));
    }

    @Test (description = "test case: cos(pi/2) = 0", priority = 2)
    public void testCosPiDiv2Is0WithManualInput() {
        String expected = "0";
        resultPage.clickRad(); //переключаем в режим RAD
        resultPage.clickCosBtn();
        resultPage.clickPiBtn();
        resultPage.clickDivisionBtn();
        resultPage.clickTwoBtn();
        resultPage.clickEqualBtn();
        String actual = resultPage.getResult();
        assertThat(actual,is(expected));
    }

    //очищаем поле ввода после каждого теста
    @AfterMethod
    private void clearButtonClk () {
        resultPage.clickClearBtn();
    }

    //убираем за собой
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}