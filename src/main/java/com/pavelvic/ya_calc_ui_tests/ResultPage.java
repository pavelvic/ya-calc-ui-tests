package com.pavelvic.ya_calc_ui_tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import static com.pavelvic.ya_calc_ui_tests.Utils.waitSomething;


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

    private CalculatorElement calculatorElement;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    //метод для ввода запроса в текстовое поле
    public void inputExpression (String input) {
        calculatorElement.getExpression().sendKeys(input);
    }

    //получаем результат вычисления
    public String getResult() {
        return calculatorElement.getResult().getText();
    }

    //получаем сообщение об ошибке
    public String getError() {
        return calculatorElement.getError().getText();
    }

    public void clickDeg() {
        calculatorElement.getSwitcherDegRad().selectByValue("deg");
    }

    public void clickRad() {
        calculatorElement.getSwitcherDegRad().selectByValue("rad");
    }

    public void clickClearBtn() {
        calculatorElement.getClearBtn().click();
    }

    public CalculatorElement getCalculatorElement() {
        return calculatorElement;
    }

    public void clickButton (CalcButtons calcButton) {calculatorElement.getButton(calcButton).click();}

    public void clickEqualBtn() {
        waitSomething(100); //имитируем работу пользователя и небольшую задержку нажатия кнопки
        calculatorElement.getEqualBtn().click();
    }
}