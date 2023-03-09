package com.pavelvic.ya_calc_ui_tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.exists;

public class CalculatorTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private SearchPage searchPage;
    private ResultPage resultPage;

    @Step("Driver initialization")
    private void driverInit() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(10000, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().setScriptTimeout(5000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
    }

    @Step("Web pages initialization")
    private void initPages () {
        searchPage = new SearchPage(driver);
        resultPage = new ResultPage(driver);
    }

    @Step("Open target page")
    private void openPage () {
        driver.get(ConfProperties.getProperty("mainpage"));
    }

    @Step ("Search '{input}'")
    private void searchSomething (String input) {
        searchPage.search(input);

    }

    @BeforeClass (description = "Prepare environment")
    public void setup() {
       driverInit();
       initPages();
       openPage();
       searchSomething("Калькулятор");
    }

        private void modeSwitcher (String mode) {
        switch (mode) {
            case "DEG":
              resultPage.clickDeg();
                break;
            case "RAD":
              resultPage.clickRad();
                break;
        }
    }

    @DataProvider
    private Object[][] possibleInputs() {
        return new Object[][]{
                {"√  (144)", "12", "DEG"},
                {"1,5* 100", "150", "DEG"},
                {"cos(p/ 2)", "0","RAD"},
                {"√144", "12", "DEG"},
                {"cp/2", "0", "RAD"},
                {"144√", "", "DEG"},
                {"p/2 cos", "Ошибка", "RAD"},
                {"100   * 1,5", "150", "DEG"},
        };
    }

    @DataProvider
    private Object[][] possibleManualInputs() {
        return new Object[][]{
                {"ONE;FOUR;FOUR;SQRT", "12", "DEG"},
                {"SQRT;ONE;FOUR;FOUR", "12", "DEG"},
                {"SQRT;ONE;FOUR;FOUR;BRACKETS", "12", "DEG"},
                {"ONE;SEP;FIVE;MULTIPLY;ONE;NULL;NULL", "150", "DEG"},
                {"ONE;NULL;NULL;MULTIPLY;ONE;SEP;FIVE", "150", "DEG"},
                {"COS;PI;DIV;TWO", "0", "RAD"},
                {"PI;DIV;TWO;COS", "Ошибка", "RAD"},
        };
    }

    @Feature("Main page initialization")
    @Step ("Main page is opened. Button 'Найти' is presented")
    @Test (priority = 1, description = "Main page was loaded")
    public void testHasMainPageWithSearchArrowElement() {
        assertThat(searchPage.getSearchArrow(), exists());
    }

    @Feature("Calculator page init")
    @Step ("Calculator was loaded")
    @Test (priority = 2, description = "Загрузка калькулятора")
    public void testHasCalculatorInFastResultSection() {
        assertThat(resultPage.getCalculatorElement(), exists());
    }

    @Feature("Keyboard inputs")
    @Step ("Input {input}, result {expectedResult}, mode ({mode})")
    @Test(dataProvider = "possibleInputs", priority = 3, description = "Keyboard input")
    public void testKeyboardInput(String input, String expectedResult, String mode) {
        modeSwitcher(mode);
        resultPage.inputExpression(input+Keys.ENTER);
        assertThat(expectedResult.equals("Ошибка") ? resultPage.getError() : resultPage.getResult(),is(expectedResult));
    }

    @Feature("Mouse input")
    @Step ("Clicked: {input}, result {expectedResult}, mode ({mode})")
    @Test(dataProvider = "possibleManualInputs", priority = 3, description = "Mouse input")
    public void testManualInput (String input, String expectedResult, String mode) {
        modeSwitcher(mode);
        List<CalcButtons> inputButtons = Arrays.stream(input.split(";")).map(CalcButtons::valueOf).collect(Collectors.toList());
        inputButtons.forEach(p->resultPage.clickButton(p));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resultPage.clickEqualBtn();
        assertThat(expectedResult.equals("Ошибка") ? resultPage.getError() : resultPage.getResult(),is(expectedResult));
    }

    @AfterMethod (description = "Reset")
    private void clearButtonClk () {
        resultPage.clickClearBtn();
        WebElement we = driver.findElement(By.xpath("//*[@class = 'calculator__screen']//*[@class = 'input__box']/.."));
        wait.until(ExpectedConditions.attributeContains(we,"class","input_empty_yes"));
    }

    @AfterClass (description = "Close driver")
    public void tearDown() {
        driver.quit();
    }
}