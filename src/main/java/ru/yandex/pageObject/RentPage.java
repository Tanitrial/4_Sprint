package ru.yandex.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentPage {

    private final WebDriver driver;

    // Локатор поля выбора даты доставки
    private final By deliveryDateInput = By.cssSelector("input[placeholder='* Когда привезти самокат']");
    // Локатор поля выбора срока аренды
    private final By rentalPeriodField = By.cssSelector("div.Dropdown-root");
    // Локатор поля для комментария
    private final By commentField = By.cssSelector("input[placeholder='Комментарий для курьера']");


    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitRentPageWillBeLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(deliveryDateInput));
    }

    public void enterValidDeliveryDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        // Получаем текущую дату, прибавляем к ней 1 день и форматируем
        String date = LocalDate.now().plusDays(1).format(formatter);
        driver.findElement(deliveryDateInput).sendKeys(date); // Вводим дату в поле с датой доставки
        driver.findElement(deliveryDateInput).sendKeys(Keys.ENTER); //  Enter
    }

    // Метод выбирает срок аренды из выпадающего меню
    public void chooseRentalPeriod(String rentalPeriod) {
        driver.findElement(rentalPeriodField).click(); // Кликаем на поле выбора срока аренды
        // Выбираем срок аренды из выпадающего списка и кликаем на него
        String rentalPeriodLocator = String.format(".//div[text()='%s']", rentalPeriod);
        driver.findElement(By.xpath(rentalPeriodLocator)).click();
    }

    // Метод выбирает цвет самоката
    public void chooseColour(String colour) {
        driver.findElement(By.id(colour)).click();
    }

    public void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void fillRequiredFields(String rentalPeriod, String colour, String comment) {
        enterValidDeliveryDate();
        chooseRentalPeriod(rentalPeriod);
        chooseColour(colour);
        setComment(comment);
    }

    public void clickOrderButton() {
        // Локатор кнопки заказа
        By orderButtonLocator = By.xpath("//button[text()='Заказать']");
        driver.findElement(orderButtonLocator).click();

    }

    public void clickOrderConfirmationButton() {
        // Локатор кнопки подтверждения заказа
        By orderConfirmationButtonLocator = By.xpath("//button[text()='Да']");
        driver.findElement(orderConfirmationButtonLocator).click();
    }
}



