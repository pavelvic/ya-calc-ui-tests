package com.pavelvic.ya_calc_ui_tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.pavelvic.ya_calc_ui_tests.Utils.waitSomething;

import java.util.Optional;
/**
 задача протестировать выражения (sqrt(144) = 12;      cos(Pi/2) = 0;     1,5 * 100 = 150):
 необходимые для этих выражений элементы страницы с калькулятором:
 - текстовое поле ввода;
 - переключатель DEG / RAD
 - кнопки калькулятора:
 - числа 0, 1, 2, 4, 5;
 - операции: запятая ","; Pi (π); sqrt (√); cos; знак умножения (*); знак деления (/); скобки "()"; кнопка сброса (C, clear); кнопка "равно" (=)
 Ограничения тестового задания говорят, что достаточно описать только эти поля. При реальном тестировании, конечно, следует моделировать страницу полностью
 Но сейчас это не нужно и создат много лишнего кода, поэтому ограничился только этими полями
 **/

public class ResultPage {

    public WebDriver driver;

    //калькулятор в результатах поиска
    @FindBy(xpath = "//*[@data-fast-name=\"calculator\"]")
    private WebElement fastSearchResult;//type marked list <li>

    //текстовое поле ввода формулы
    @FindBy(xpath = "//div[@class = 'calculator__wrapper']//span[@class = \"input__box\"]/input")
    private WebElement expression; //type: input text area

    //поле с результатом
    @FindBy(className = "calculator-display__result")
    private WebElement result; //type: text area

    //поле с инфой об ошибке
    @FindBy(className = "calculator-display__error")
    private WebElement error; //type:  text area

    //режим калькулятора DEG
    @FindBy(xpath = "//*[@value=\"deg\"]")
    private WebElement deg; //type: radiobutton

    //режим калькулятора RAD
    @FindBy(xpath = "//*[@value=\"rad\"]")
    private WebElement rad; //type: radiobutton


    //кнопка на калькуляторе "0"
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '0']/..")
    private WebElement nullBtn; //type: button

    //кнопка на калькуляторе "1"
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '1']/..")
    private WebElement oneBtn; //type: button

    //кнопка на калькуляторе "2"
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '2']/..")
    private WebElement twoBtn; //type: button

    //кнопка на калькуляторе "4"
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '4']/..")
    private WebElement fourBtn; //type: button

    //кнопка на калькуляторе "5"
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '5']/..")
    private WebElement fiveBtn; //type: button

    //кнопка "," (разделитель разрядов числа)
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = ',']/..")
    private WebElement separatorBtn; //type: button

    //кнопка "Pi" (число Пи)
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = 'π']/..")
    private WebElement piBtn; //type: button

    //кнопка "квардартный корень"
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '√']/..")
    private WebElement sqrtBtn; //type: button

    //кнопка "косинус" (cos)
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = 'cos']/..")
    private WebElement cosBtn; //type: button

    //кнопка "умножение" *
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '×']/..")
    private WebElement multiplyBtn; //type: button

    //кнопка "деление" /
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '÷']/..")
    private WebElement divisionBtn; //type: button

    //кнопка "скобки" ()
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '( )']/..")
    private WebElement bracketsBtn; //type: button

    //кнопка "сброс" С
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = 'C']/..")
    private WebElement clearBtn; //type: button

    //кнопка "равно" =
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '=']/..")
    private WebElement equalBtn; //type: button


    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //метод для ввода запроса в текстовое поле
    public void inputExpression (String input) {
        expression.sendKeys(input);
    }

    //получаем результат вычисления
    public String getResult() {
        return result.getText();
    }

    //получаем сообщение об ошибке
    public String getError() {
        return error.getText();
    }

    //кликаем для выбора режима калькулятора DEG
    public void clickDeg() {
        deg.click();
    }

    //кликаем для выбора режима калькулятора RAD
    public void clickRad() {
        rad.click();
    }

    /* блок методов нажатий на кнопки*/
    public void clickNullBtn() {
        nullBtn.click();
    }

    public void clickOneBtn() {
        oneBtn.click();
    }

    public void clickTwoBtn() {
        twoBtn.click();
    }

    public void clickFourBtn() {
        fourBtn.click();
    }

    public void clickFiveBtn() {
        fiveBtn.click();
    }

    public void clickSeparatorBtn() {
        separatorBtn.click();
    }

    public void clickPiBtn() {
        piBtn.click();
    }

    public void clickSqrtBtn() {
        sqrtBtn.click();
    }

    public void clickCosBtn() {
        cosBtn.click();
    }

    public void clickMultiplyBtn() {
        multiplyBtn.click();
    }

    public void clickDivisionBtn() {
        divisionBtn.click();
    }

    public void clickBracketsBtn() {
        bracketsBtn.click();
    }

    public void clickClearBtn() {
        waitSomething(100); //имитируем небольшие задержки перед нажатием, чтобы максимально повысить вероятность обновления значения в полях веб-страницы
        clearBtn.click();
    }

    public void clickEqualBtn() {
        waitSomething(100); //имитируем небольшие задержки перед нажатием, чтобы максимально повысить вероятность обновления значения в полях веб-страницы
        equalBtn.click();
    }

    //наличие калькулятора на странице поиска (в Optional заворачивается строка 'Calculator' атрибута data-fast-name, если такого нет - null)
    public Optional<String> getFastSearchResultType() {
        return Optional.ofNullable(fastSearchResult.getAttribute("data-fast-name"));
    }
}
