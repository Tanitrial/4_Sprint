package ru.yandex.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OrderPage {

    private final WebDriver driver;

    // Локатор поля ввода имени
    private final By nameInput = By.xpath(".//input[@placeholder='* Имя']");
    // Локатор поля ввода фамилии
    private final By surnameInput = By.xpath(".//input[@placeholder='* Фамилия']");
    // Локатор поля ввода адреса
    private final By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // Локатор поля выбора станции метро
    private final By metroStationInput = By.xpath(".//input[@placeholder='* Станция метро']");
    // Локатор поля ввода номера телефона
    private final By phoneNumberInput =
            By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Локатор кнопки "Далее"
    private final By nextButton = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setName(String firstName) {
        driver.findElement(nameInput).sendKeys(firstName);
    }

    public void setSurname(String secondName) {
        driver.findElement(surnameInput).sendKeys(secondName);
    }

    public void setAddress(String address) {
        driver.findElement(addressInput).sendKeys(address);
    }

    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
    }

    public void chooseMetroStation() {
        driver.findElement(metroStationInput).click();
        driver.findElement(metroStationInput).sendKeys(Keys.DOWN);
        driver.findElement(metroStationInput).sendKeys(Keys.ENTER);
    }

    public void fillAllRequiredFields(String name, String surname, String address, String phoneNumber) {
        setName(name);
        setSurname(surname);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        chooseMetroStation();
    }

    // Метод клика по кнопке "Далее"
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }
}