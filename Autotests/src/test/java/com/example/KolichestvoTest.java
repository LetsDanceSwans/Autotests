package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class KolichestvoTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }

    @Test
    public void  CheckVidachaTest() {
        driver.get("https://travel.yandex.ru/");
        //Выполняем шаги через POM
        homePage.kolichestvoInput();
        homePage.enterMaxGuest();
        homePage.maxNumber();
        homePage.enterMinGuest();
        System.out.println("ТЕСТ ПРОЙДЕН");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}