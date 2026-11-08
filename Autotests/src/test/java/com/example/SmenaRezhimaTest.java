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

public class SmenaRezhimaTest {
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
        homePage.clickZhdVertical(); 
        homePage.clearInput();
        homePage.enterORIGIN();
        homePage.enterDESTINATION();
        homePage.selectDates();
        homePage.clickAviaVertical();
        wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.check_ORIGIN_CITY));
        wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.check_DESTINATION_CITY));
        boolean isDataSaved = driver.findElement(homePage.check_ORIGIN_CITY).isDisplayed() && driver.findElement(homePage.check_DESTINATION_CITY).isDisplayed();
        Assertions.assertTrue(isDataSaved, "Города не сохранились в инпутах!");
        System.out.println("ТЕСТ ПРОЙДЕН:Города сохранились в инпутах");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
