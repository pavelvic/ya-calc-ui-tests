package com.pavelvic.ya_calc_ui_tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class ResultPage {

    private CalculatorElement calculatorElement;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    public void inputExpression (String input) {
        calculatorElement.getExpression().sendKeys(input);
    }

    public String getResult() {
        return calculatorElement.getResult().getText();
    }

    public String getError() {
        return calculatorElement.getError().getText();
    }

    public TextBlock getErrorElement() {
        return calculatorElement.getError();
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

    public void clickButton (CalcButtons calcButton) {
        calculatorElement.getButton(calcButton).click();
    }

    public void clickEqualBtn() {
        calculatorElement.getEqualBtn().click();
    }
}