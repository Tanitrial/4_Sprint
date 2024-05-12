package ru.yandex.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SuccessOrderPage {
    private final WebDriver driver;

    // Локатор сообщения об успешном создании заказа
    private final By successOrderMessage = By.xpath(".//div[text()='Заказ оформлен']");

    public SuccessOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSuccessOrderCreationMessageVisible() {
        return driver.findElement(successOrderMessage).isDisplayed();
    }


}
