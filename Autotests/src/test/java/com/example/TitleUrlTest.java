package com.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


public class TitleUrlTest {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeEach 
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }

    @Test
    public void CheckTitleUrlTest() {
        driver.get("https://travel.yandex.ru/");
        Assertions.assertEquals(driver.getCurrentUrl(), "https://travel.yandex.ru/", "URL страницы не соответствует ожидаемому!");
        Assertions.assertTrue(driver.getTitle().contains("Яндекс Путешествия"), "тайтл не корректен");
    }

    @AfterEach 
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}