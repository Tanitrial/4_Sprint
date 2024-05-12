package ru.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.pageObject.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class OrderScooterTest extends BaseTest {

    private final String orderButton;   // Кнопка заказа (верхняя/нижняя)
    private final String name;          // Имя заказчика
    private final String surname;       // Фамилия заказчика
    private final String address;       // Адрес доставки
    private final String phoneNumber;   // Номер телефона

    private final String rentalPeriod;  // Период аренды из выпадающего меню
    private final String colour;        // Цвет самоката

    private final String comment;       // Комментарий

    public OrderScooterTest(String orderButton, String name, String surname, String address, String phoneNumber,
                            String rentalPeriod, String colour, String comment) {
        this.orderButton = orderButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.rentalPeriod = rentalPeriod;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderFormData() {
        return new Object[][]{
                {OrderButton.TOP_BUTTON, "Мангыт", "Едигей", "Московская, дом 1", "+7913521499", "сутки",
                        "black", "1"},
                {OrderButton.BOTTOM_BUTTON, "Герай", "Тохтамыш", "ул. Золотая, дом 1 квартира 1",
                        "+7913421406", "трое суток", "black",
                        "Очень нужно, даю 2х коней " + "И голову Едигея"
                },
        };
    }

    @Test
    public void checkOrderScooterValidData_expectScooterIsOrdered() {
        super.implicitlyWait(3);

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickOrderButton(orderButton);

        OrderPage orderFormPage = new OrderPage(driver);
        orderFormPage.fillAllRequiredFields(name, surname, address, phoneNumber);
        orderFormPage.clickNextButton();

        RentPage rentPage = new RentPage(driver);
        rentPage.waitRentPageWillBeLoaded();
        rentPage.fillRequiredFields(rentalPeriod, colour, comment);
        rentPage.clickOrderButton();
        rentPage.clickOrderConfirmationButton();

        QuestionPage confirmQuestionPage = new QuestionPage(driver);
        confirmQuestionPage.clickConfirmButton();

        findElement(By.xpath("//button[text()='Да']")).click();

        SuccessOrderPage successOrderCreationPage = new SuccessOrderPage(driver);

        boolean actual = successOrderCreationPage.isSuccessOrderCreationMessageVisible();
        Assert.assertTrue("Expected: a message is displayed that the order was created successfully", actual);
        driver.quit();
    }

    private WebElement findElement(By xpath) {
        return driver.findElement(xpath);
    }
}