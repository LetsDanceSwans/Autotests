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

public class VidachaZHDTest {
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
        homePage.clickSearch();

        //Ждем, когда в URL появится привязка
        wait.until(ExpectedConditions.urlContains("/vorkuta--sochi/"));

        //Получаем данные для итоговой проверки
        String currentUrl = driver.getCurrentUrl();
        String currentTitle = driver.getTitle();
        String destination = "Воркута - Сочи";

        //Проверяем, что поиск прошел успешно (по URL или Title)
        boolean isSearchSuccessful = currentUrl.contains("/vorkuta--sochi/") || currentTitle.contains(destination);
        Assertions.assertTrue(isSearchSuccessful, 
            "ТЕСТ ПРОВАЛЕН: Результаты для '" + destination + "' не отобразились. URL: " + currentUrl);
        System.out.println("ТЕСТ ПРОЙДЕН: Страница выдачи отелей '" + destination + "' успешно загружена!");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
