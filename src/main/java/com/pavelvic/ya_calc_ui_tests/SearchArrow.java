package com.pavelvic.ya_calc_ui_tests;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

@FindBy(xpath = "//form")
public class SearchArrow extends HtmlElement {

    @FindBy(xpath = "//input[@name = 'text']")
    private TextInput requestInput;

    @FindBy (css = "button.websearch-button")
    private Button searchButton;

    public void search(String request) {
        requestInput.sendKeys(request);
        searchButton.click();
    }
}
