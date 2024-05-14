package ru.yandex;

import ru.yandex.pageObject.MainPage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class QuestionsTests extends BaseTest {
    private static int index;
    private static String question;
    private static String answer;

    public QuestionsTests(int index, String question, String answer) {
        QuestionsTests.index = index;
        QuestionsTests.question = question;
        QuestionsTests.answer = answer;
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials(){
        return new Object[][] {
                { 0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                { 1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                { 2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30." },
                { 3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее." },
                { 4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010." },
                { 5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится." },
                { 6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои." },
                { 7, "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области." }
        };
    }

    @Test
    public void checkClickQuestionExpectTextIsDisplayed() {
        super.implicitlyWait(3);

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        String actualQuestion = mainPage.clickQuestion(index);
        String actualAnswer = mainPage.answerDisplayed(index);

        Assert.assertEquals("Check that we click on right question", actualQuestion, question);
        Assert.assertEquals("Check that we see right answer", actualAnswer, answer);
    }
}

  
