package com.pavelvic.ya_calc_ui_tests;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.*;

@FindBy(className = "calculator__wrapper")
public class CalculatorElement extends HtmlElement {

    @FindBy(xpath = "//div[@class = 'calculator__wrapper']//span[@class = \"input__box\"]/input")
    private TextInput expression;

    @FindBy(className = "calculator-display__result")
    private TextBlock result;

    @FindBy(className = "calculator-display__input-string")
    private TextBlock input;

    @FindBy(className = "calculator-display__error")
    private TextBlock error;

    @FindBy(name = "switcher")
    private Radio switcherDegRad;

    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '0']/..")
    private Button nullBtn;

    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '1']/..")
    private Button oneBtn;

    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '2']/..")
    private Button twoBtn;

    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '4']/..")
    private Button fourBtn;

    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '5']/..")
    private Button fiveBtn;

    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = ',']/..")
    private Button separatorBtn;

    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = 'π']/..")
    private Button piBtn;

    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '√']/..")
    private Button sqrtBtn;

    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = 'cos']/..")
    private Button cosBtn;

    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '×']/..")
    private Button multiplyBtn;

    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '÷']/..")
    private Button divisionBtn;

    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '( )']/..")
    private Button bracketsBtn;

    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = 'C']/..")
    private Button clearBtn;

    @FindBy(xpath = "//*[@class = \"button2__text\" and text() = '=']/..")
    private Button equalBtn;

    public TextInput getExpression() {
        return expression;
    }

    public TextBlock getInput() {
        return input;
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

    public Button getClearBtn() {
        return clearBtn;
    }

    public Button getEqualBtn() {
        return equalBtn;
    }

    public Button getButton(CalcButtons calcButton) {
        switch (calcButton) {
            case NULL:
                return nullBtn;
            case ONE:
                return oneBtn;
            case TWO:
                return twoBtn;
            case FOUR:
                return fourBtn;
            case FIVE:
                return fiveBtn;
            case SEP:
                return separatorBtn;
            case PI:
                return piBtn;
            case SQRT:
                return sqrtBtn;
            case COS:
                return cosBtn;
            case MULTIPLY:
                return multiplyBtn;
            case DIV:
                return divisionBtn;
            case BRACKETS:
                return bracketsBtn;
            case CLEAR:
                return clearBtn;
            case EQUAL:
                return equalBtn;
            default:
                return null;
        }
    }
}