package com.pavelvic.ya_calc_ui_tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

    public WebDriver driver;

    //строка ввода поискового запроса
    @FindBy(xpath = "//*[@id=\"text\"]")
    private WebElement searchField; //text input

    //кнопка "Найти"
    @FindBy (xpath = "//*[contains(text(), 'Найти')]//..")
    private WebElement searchBtn; //button

    public SearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //метод для ввода запроса в текстовое поле
    public void inputSearchText (String searchText) {
        searchField.sendKeys(searchText);
    }

    //нажатие кнопки поиска
    public void clickSearchBtn () {
        searchBtn.click();
    }
}