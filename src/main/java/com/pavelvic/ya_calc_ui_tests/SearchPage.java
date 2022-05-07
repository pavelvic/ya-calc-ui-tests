package com.pavelvic.ya_calc_ui_tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

    public WebDriver driver;

    @FindBy(xpath = "//*[@id=\"text\"]")
    private WebElement searchField;

    @FindBy (xpath = "//*[contains(text(), 'Найти')]//..")
    private WebElement searchBtn;

    public SearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void inputSearchText (String searchText) {
        searchField.sendKeys(searchText);
    }

    public void clickSearchBtn () {
        searchBtn.click();
    }
}