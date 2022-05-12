package com.pavelvic.ya_calc_ui_tests;


import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

@FindBy(xpath = "//form")
public class SearchArrow extends HtmlElement {

    @FindBy(id = "text")
    private TextInput requestInput;

    @FindBy (xpath = "//span[@class = 'button__text' and text()='Найти']//..")
    private Button searchButton;

    public void search(String request) {
        requestInput.sendKeys(request);
        searchButton.click();
    }
}
