package com.pavelvic.ya_calc_ui_tests;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.*;

@FindBy(className = "calculator__wrapper")
public class CalculatorElement extends HtmlElement {

    @FindBy(xpath = "//div[@class = 'calculator__wrapper']//span[@class = \"input__box\"]/input")
    private TextInput expression;

    //поле с результатом
    @FindBy(className = "calculator-display__result")
    private TextBlock result;

    //поле с инфой об ошибке
    @FindBy(className = "calculator-display__error")
    private TextBlock error;

    //свитчер режима калькулятора
    @FindBy(name = "switcher")
    private Radio switcherDegRad;

    //кнопка на калькуляторе "0"
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '0']/..")
    private Button nullBtn;

    //кнопка на калькуляторе "1"
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '1']/..")
    private Button oneBtn;

    //кнопка на калькуляторе "2"
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '2']/..")
    private WebElement twoBtn;

    //кнопка на калькуляторе "4"
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '4']/..")
    private Button fourBtn;

    //кнопка на калькуляторе "5"
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '5']/..")
    private Button fiveBtn;

    //кнопка "," (разделитель разрядов числа)
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = ',']/..")
    private Button separatorBtn;

    //кнопка "Pi" (число Пи)
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = 'π']/..")
    private Button piBtn; //type: button

    //кнопка "квардартный корень"
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '√']/..")
    private Button sqrtBtn;

    //кнопка "косинус" (cos)
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = 'cos']/..")
    private Button cosBtn;

    //кнопка "умножение" *
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '×']/..")
    private Button multiplyBtn;

    //кнопка "деление" /
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '÷']/..")
    private Button divisionBtn;

    //кнопка "скобки" ()
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '( )']/..")
    private Button bracketsBtn;

    //кнопка "сброс" С
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = 'C']/..")
    private Button clearBtn;

    //кнопка "равно" =
    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '=']/..")
    private Button equalBtn;


    public TextInput getExpression() {
        return expression;
    }

    public TextBlock getResult() {
        return result;
    }

    public TextBlock getError() {
        return error;
    }

    public Radio getSwitcherDegRad() {
        return switcherDegRad;
    }

    public Button getNullBtn() {
        return nullBtn;
    }

    public Button getOneBtn() {
        return oneBtn;
    }

    public WebElement getTwoBtn() {
        return twoBtn;
    }

    public Button getFourBtn() {
        return fourBtn;
    }

    public Button getFiveBtn() {
        return fiveBtn;
    }

    public Button getSeparatorBtn() {
        return separatorBtn;
    }

    public Button getPiBtn() {
        return piBtn;
    }

    public Button getSqrtBtn() {
        return sqrtBtn;
    }

    public Button getCosBtn() {
        return cosBtn;
    }

    public Button getMultiplyBtn() {
        return multiplyBtn;
    }

    public Button getDivisionBtn() {
        return divisionBtn;
    }

    public Button getBracketsBtn() {
        return bracketsBtn;
    }

    public Button getClearBtn() {
        return clearBtn;
    }

    public Button getEqualBtn() {
        return equalBtn;
    }
}
