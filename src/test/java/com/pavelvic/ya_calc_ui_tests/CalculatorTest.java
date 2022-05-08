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

    //загрузился ли калькулятор в результатах поиска
    @Test
    public void testHasCalculatorInFastResultSection() {
        String expected = "calculator";
        String actual = resultPage.getFastSearchResultType().orElse("smth othr");
        assertThat(actual, is(expected));
    }

    //тест ввода значений с клавиатурыв текствое поле
    @Test(dataProvider = "possibleInputs")
    public void testKeyboardInput(String input, String expectedResult, String mode) {
        //установка режима калькулятора
        if (mode.equals("RAD")) resultPage.clickRad();

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

        //сбрасываем значения для след теста
        resultPage.clickClearBtn();
    }

    @Test (description = "test case: sqrt(144) = 12")
    public void testSqrt144is12WithManualInput () {
        String expected = "12";
        resultPage.clickSqrtBtn();
        resultPage.clickOneBtn();
        resultPage.clickFourBtn();
        resultPage.clickFourBtn();
        resultPage.clickEqualBtn();
        String actual = resultPage.getResult();
        assertThat(actual,is(expected));
        resultPage.clickClearBtn();
    }

    @Test(description = "test case: 1.5*100 = 150")
    public void testMultiply1point5and100Is150WithManualInput () {
        String expected = "150";
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
        resultPage.clickClearBtn();
    }

    @Test (description = "test case: cos(pi/2) = 0")
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
        resultPage.clickClearBtn();
        resultPage.clickDeg(); //возвращаем в режим по умолачанию DEG
    }

    //убираем за собой
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}