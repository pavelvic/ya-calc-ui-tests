package com.pavelvic.ya_calc_ui_tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {

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

    public WebDriver driver;

    //текстовое поле ввода формулы
    @FindBy(xpath = "//*[@id=\"uniq165185881674715189\"]")
    private WebElement inputExpression; //type: input text area

    //режим калькулятора DEG
    @FindBy(xpath = "//*[@id=\"uniq165185881674715187\"]")
    private WebElement deg; //type: radiobutton

    //режим калькулятора RAD
    @FindBy(xpath = "//*[@id=\"uniq165185881674715188\"]")
    private WebElement rad; //type: radiobutton


    //TODO: оптимизировать xpath через поиск по родителю элемента <span class="button2__text">0</span> и аналогично для остальных кнопок
    //кнопка на калькуляторе "0"
    @FindBy (xpath = "//*[@id=\"search-result\"]/li[2]/div/div/div/div/div[2]/div[5]/button[4]")
    private WebElement nullBtn; //type: button

    //кнопка на калькуляторе "1"
    @FindBy (xpath = "//*[@id=\"search-result\"]/li[2]/div/div/div/div/div[2]/div[4]/button[4]")
    private WebElement oneBtn; //type: button

    //кнопка на калькуляторе "2"
    @FindBy (xpath = "//*[@id=\"search-result\"]/li[2]/div/div/div/div/div[2]/div[4]/button[5]")
    private WebElement twoBtn; //type: button

    //кнопка на калькуляторе "4"
    @FindBy (xpath = "//*[@id=\"search-result\"]/li[2]/div/div/div/div/div[2]/div[3]/button[4]")
    private WebElement fourBtn; //type: button

    //кнопка на калькуляторе "5"
    @FindBy (xpath = "//*[@id=\"search-result\"]/li[2]/div/div/div/div/div[2]/div[3]/button[5]")
    private WebElement fiveBtn; //type: button

    //кнопка "," (разделитель разрядов числа)
    @FindBy (xpath = "//*[@id=\"search-result\"]/li[2]/div/div/div/div/div[2]/div[5]/button[5]")
    private WebElement separatorBtn; //type: button

    //кнопка "Pi" (число Пи)
    @FindBy (xpath = "//*[@id=\"search-result\"]/li[2]/div/div/div/div/div[2]/div[5]/button[2]")
    private WebElement piBtn; //type: button

    //кнопка "квардартный корень"
    @FindBy (xpath = "//*[@id=\"search-result\"]/li[2]/div/div/div/div/div[2]/div[3]/button[3]")
    private WebElement sqrtBtn; //type: button

    //кнопка "косинус" (cos)
    @FindBy (xpath = "//*[@id=\"search-result\"]/li[2]/div/div/div/div/div[2]/div[3]/button[2]")
    private WebElement cosBtn; //type: button

    //кнопка "умножение" *
    @FindBy (xpath = "//*[@id=\"search-result\"]/li[2]/div/div/div/div/div[2]/div[2]/button[7]")
    private WebElement multiplyBtn; //type: button

    //кнопка "деление" /
    @FindBy (xpath = "//*[@id=\"search-result\"]/li[2]/div/div/div/div/div[2]/div[1]/button[7]")
    private WebElement divisionBtn; //type: button

    //кнопка "скобки" ()
    @FindBy (xpath = "//*[@id=\"search-result\"]/li[2]/div/div/div/div/div[2]/div[1]/button[5]")
    private WebElement bracketsBtn; //type: button

    //кнопка "сброс" С
    @FindBy (xpath = "//*[@id=\"search-result\"]/li[2]/div/div/div/div/div[2]/div[1]/button[4]")
    private WebElement clearBtn; //type: button

    //кнопка "равно" =
    @FindBy (xpath = "//*[@id=\"search-result\"]/li[2]/div/div/div/div/div[2]/div[5]/button[6]")
    private WebElement equalBtn; //type: button


    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
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
        clearBtn.click();
    }

    public void clickEqualBtn() {
        equalBtn.click();
    }

}
