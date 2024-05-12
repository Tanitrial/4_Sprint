package ru.yandex;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;


// Базовый тестовый класс, для настройки драйвера. Другие тестовые классы наследуются от базового.
public class BaseTest {
    //Объявляем переменную драйвера
    protected WebDriver driver;

    // Инициализируем драйвер и указываем, какой использовать: ChromeDriver() или FirefoxDriver()
    @Before
    public void startUp() {
        // WebDriverManager.firefoxdriver().setup();
        driver = new ChromeDriver();
        // driver = new FirefoxDriver(); // если хотим  без бага
    }

    @After
    public void cleanUp() {
        // Закрываем сессию драйвера
        driver.quit();
    }

    // Неявное ожидание заданное количество секунд
    public void implicitlyWait(long numberOfSeconds) {
        driver.manage().timeouts().implicitlyWait(numberOfSeconds, TimeUnit.SECONDS);
    }

}
