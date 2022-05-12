package com.pavelvic.ya_calc_ui_tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.pavelvic.ya_calc_ui_tests.Utils.waitSomething;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.exists;

public class CalculatorTest {

    public static WebDriver driver;
    public static SearchPage searchPage;
    public static ResultPage resultPage;

    /** чек лист:
     * проверить ввод формулы с клавиатуры (прямой / обратный порядок ввода)
     * проверить ввод формулы кнопками на форме (прямой / обратный порядок ввода)
     * проверить работу функциональности autocomplete (ввод с клавиатуры / с кнопок приложения)
     * */

    @Step("Инициализация драйвера")
    private void driverInit() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Step("Инициализация веб-страниц")
    private void initPages () {
        searchPage = new SearchPage(driver);
        resultPage = new ResultPage(driver);
    }

    @Step("Открытие тестируемой веб-страницы")
    private void openPage () {
        driver.get(ConfProperties.getProperty("mainpage"));
    }

    @Step ("Поиск '{input}'")
    private void searchSomething (String input) {
        searchPage.search(input);

    }

    //настройка окружения перед тестами + открытие страницы с тестируемым приложением
    @BeforeClass (description = "Настройка окружения")
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

    //тест кейсы для ввода данных с клавиатуры в формате {"последовательность символов для ввода с клавиатуры", "результат", "режим вычисления (RAD / DEG)"}
    @DataProvider
    private Object[][] possibleInputs() {
        return new Object[][]{
                {"√  (144)", "12", "DEG"},
                {"1,5* 100", "150", "DEG"},
                {"cos(p/ 2)", "0","RAD"},

                 /*для некоторых вводимых формул у программы есть функция autocomplete
                 * из наших кейсов это формулы "√(144)" и "cos(p / 2)"
                 * для этих формул необязательно вводить с клавиатуры полность и последовательно символы "√", "(", "144", ")"
                 * программа умеет подставлять нужные знаки. Например, для вычисления "√(144) = 12" достаточно ввести с клавиатуры последовательно только  "√", "144" и нажать "равно",
                 * программа должна сама подставить недостающие символы и посчитать результат, эту функцию и проверяют следующие кейсы
                 * аналогично для "cos(p / 2)" достаточно ввести с клавиатуры только "c" (cos), "p" (число Пи), "/" (деление), "2" (цифра два)
                 * **/
                {"√144", "12", "DEG"},
                {"cp/2", "0", "RAD"},

                /*тест кейсы с измененённым порядком аргументов*/
                {"144√", "", "DEG"}, //не окончательное выражение введено
                {"p/2 cos", "Ошибка", "RAD"}, //ошибка ввода
                {"100   * 1,5", "150", "DEG"}, //от перестановки множителей произведение не меняется

        };
    }

    //тест кейсы для ввода данных путем нажатия кнопок в интерфейсе {"условное обозначение кнопки калькулятора через ';'", "результат", "режим вычисления (RAD / DEG)"}
    @DataProvider
    private Object[][] possibleManualInputs() {
        return new Object[][]{
                {"ONE;FOUR;FOUR;SQRT", "12", "DEG"},
                {"SQRT;ONE;FOUR;FOUR", "12", "DEG"},
                {"SQRT;ONE;FOUR;FOUR;BRACKETS", "12", "DEG"},
                {"ONE;SEP;FIVE;MULTIPLY;ONE;NULL;NULL", "150", "DEG"},
                {"ONE;NULL;NULL;MULTIPLY;ONE;SEP;FIVE;", "150", "DEG"},
                {"COS;PI;DIV;TWO", "0", "RAD"},
                {"PI;DIV;TWO;COS", "Ошибка", "RAD"},
        };
    }


    //приоритет 1, так как другие тесты не имеют смысла без проверки что мы получили главную страницу
    //приоритет 2 у тесте наличия калькулятора в резлуьтатах поиска калькулятор для работы, остальные тесты - приоритет 3
    @Feature("Инициализация главной страницы")
    @Step ("Появилась главная страница с формой для ввода и кнопкой 'Найти'")
    @Test (priority = 1, description = "Загрузка главной страницы")
    public void testHasMainPageWithSearchArrowElement() {
        assertThat(searchPage.getSearchArrow(), exists());
    }

    @Feature("Инициализация калькулятора")
    @Step ("Калькулятор загрузился на странице с результатами поиска")
    @Test (priority = 2, description = "Загрузка калькулятора")
    public void testHasCalculatorInFastResultSection() {
        assertThat(resultPage.getCalculatorElement(), exists());
    }

    @Feature("Ввод формул с клавиатуры")
    @Step ("Введено с клавиатуры {input}, получено {expectedResult} в режиме ({mode})")
    @Test(dataProvider = "possibleInputs", priority = 3, description = "Ввод данных с клавиатуры")
    public void testKeyboardInput(String input, String expectedResult, String mode) {
        //установка режима калькулятора
        modeSwitcher(mode);

        //вводим значения
        resultPage.inputExpression(input+ Keys.ENTER);

        //получаем результат и проверяем с ожиданием
        assertThat(expectedResult.equals("Ошибка") ? resultPage.getError() : resultPage.getResult(),is(expectedResult));

        //сброс
        resultPage.inputExpression(""+Keys.ESCAPE);
        Utils.waitSomething(100);
    }

    @Feature("Ввод формул путём нажания кнопок")
    @Step ("Введена следующая комбинация кнопок: {input}. Получено {expectedResult} в режиме ({mode})")
    @Test(dataProvider = "possibleManualInputs", priority = 3, description = "Ввод данных путём нажатия кнопок")
    public void testManualInput (String input, String expectedResult, String mode) {
        modeSwitcher(mode);
        List<CalcButtons> inputButtons = Arrays.stream(input.split(";")).map(CalcButtons::valueOf).collect(Collectors.toList());
        inputButtons.forEach(p->resultPage.clickButton(p));
        resultPage.clickEqualBtn();
        assertThat(expectedResult.equals("Ошибка") ? resultPage.getError() : resultPage.getResult(),is(expectedResult));

    }

    @AfterMethod (description = "Сброс состояния калькулятора")
    private void clearButtonClk () {
        resultPage.clickClearBtn();
    }

    @AfterClass (description = "Закрытие драйвера")
    public void tearDown() {
        driver.quit();
    }
}