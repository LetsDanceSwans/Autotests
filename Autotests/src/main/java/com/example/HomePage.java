package com.example;

import java.time.Duration;
import java.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    
    // 1. Поля класса (драйвер и ожидание)
    private final WebDriver driver;
    private final WebDriverWait wait;

    // 2. Константные данные
    private static final String DESTINATION_CITY_HOTEL = "Мурманск";
    public static final String DESTINATION_CITY = "Сочи";
    public static final String ORIGIN_CITY = "Воркута";

    // 3. Статические локаторы (Просто адреса элементов, БЕЗ wait.until)
    private final By searchDropDownInputHOTEL = By.xpath("//*[@type='text' and @tabindex='0' and @role='combobox']");
    private final By searchDropDownInputDESTINATION = By.xpath("//input[@aria-expanded='true' and @aria-haspopup='listbox']");
    private final By searchDropDownInputORIGIN = By.xpath("//*[@type='text' and @tabindex='0' and @role='combobox']");
    private final By citySuggestItemHOTEL = By.xpath(String.format("//span[normalize-space()='%s']", DESTINATION_CITY_HOTEL));
    private final By citySuggestItemDESTINATION = By.xpath(String.format("//span[@data-qa='title' and normalize-space()='%s']", DESTINATION_CITY));
    private final By citySuggestItemORIGIN = By.xpath(String.format("//span[normalize-space()='%s']", ORIGIN_CITY));
    private final By staticSearchButton = By.xpath("//button//span[normalize-space()='Найти']");
    private final By staticVerticalTrain = By.xpath("//*[text()=\"Поезда\"]");
    private final By staticVerticalAvia = By.xpath("//*[text()=\"Авиа\"]");
    private final By staticClearInput = By.xpath("//*[@aria-label='Очистить поле']");
    public final By check_DESTINATION_CITY = By.xpath(String.format("//div[text()='%s']", DESTINATION_CITY));
    public final By check_ORIGIN_CITY = By.xpath(String.format("//div[text()='%s']", ORIGIN_CITY));
    private final By staticKolichestvo = By.xpath("//span[contains(text(), 'взрослых')]");
    private final By plusButton = By.xpath("//button[@data-qa='plusButton'and @tabindex='0']");
    private final By minusButton = By.xpath("//button[@data-qa='minusButton'and @tabindex='0']");
    private final By numerButton = By.xpath("(//*[@data-qa='plusButton' and @data-react-aria-pressable='true'])[2]");
    public final By redFrame = By.xpath("//label[//div[@data-qa='labelPlaceholder']]");
    


    // Динамические локаторы для дат (вычисляются на основе текущего дня)
    private final By arrivalDateLocator;
    private final By departureDateLocator;

    // 4. Конструктор класса
    public HomePage(WebDriver driver) {
        this.driver = driver;
        // Создаем WebDriverWait прямо здесь, внутри конструктора
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));

        // Вычисляем даты в момент создания страницы
        int todayDay = LocalDate.now().getDayOfMonth();
        int tomorrowDay = LocalDate.now().plusDays(1).getDayOfMonth();

        // Формируем локаторы для календаря
        this.arrivalDateLocator = By.xpath(String.format("//span[normalize-space()='%d']", todayDay));
        this.departureDateLocator = By.xpath(String.format("//span[normalize-space()='%d']", tomorrowDay));
    }

    // 5. Методы действий (Действия + Ожидания)
    
    // Метод для ввода города в отелях "Мурманск"
    public void enterDestinationHOTEL() {
        // Ждем, пока поле ввода станет кликабельным, на grandkids уровнях ищем элемент
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(searchDropDownInputHOTEL));
        input.click();
        input.sendKeys(DESTINATION_CITY_HOTEL);

        // Ждем появления Мурманск в выпадающем списке и кликаем
        WebElement suggest = wait.until(ExpectedConditions.elementToBeClickable(citySuggestItemHOTEL));
        suggest.click();
    }
    // Метод для ввода города отправления
    public void enterDESTINATION() {
        // Ждем, пока поле ввода станет кликабельным, на grandkids уровнях ищем элемент
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(searchDropDownInputDESTINATION));
        input.click();
        input.sendKeys(DESTINATION_CITY);
        WebElement suggest = wait.until(ExpectedConditions.elementToBeClickable(citySuggestItemDESTINATION));
        suggest.click();
    }
    // Метод для ввода города назначения
    public void enterORIGIN() {
        //Ждем, пока поле ввода станет кликабельным, на grandkids уровнях ищем элемент
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(searchDropDownInputORIGIN));
        input.click();
        input.sendKeys(ORIGIN_CITY);
        WebElement suggest = wait.until(ExpectedConditions.elementToBeClickable(citySuggestItemORIGIN));
        suggest.click();
    }
    // Метод для выбора дат (Сегодня и Завтра)
    public void selectDates() {
        WebElement arrival = wait.until(ExpectedConditions.elementToBeClickable(arrivalDateLocator));
        arrival.click();

        WebElement departure = wait.until(ExpectedConditions.elementToBeClickable(departureDateLocator));
        departure.click();
    }

    // Метод для нажатия кнопки "Найти"
    public void clickSearch() {
        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(staticSearchButton));
        searchBtn.click();
    }
    // Метод для перехода на вертикаль жд кнопкм "поезда"
    public void clickZhdVertical() {
        WebElement zhdVerticalBtn = wait.until(ExpectedConditions.elementToBeClickable(staticVerticalTrain));
        zhdVerticalBtn.click();
    }
    // Метод для перехода на вертикаль жд кнопкм "поезда"
    public void clickAviaVertical() {
        WebElement aviaVerticalBtn = wait.until(ExpectedConditions.elementToBeClickable(staticVerticalAvia));
        aviaVerticalBtn.click();
    }
    // Ждем появления кнопки очистки инпута и кликаем
    public void clearInput() {
        WebElement clearInput = wait.until(ExpectedConditions.elementToBeClickable(staticClearInput));
        clearInput.click();
    }
    public void kolichestvoInput() {
        WebElement Input = wait.until(ExpectedConditions.elementToBeClickable(staticKolichestvo));
        Input.click();
    }
    public void enterMaxGuest() {
        //Ждем, пока поле ввода станет кликабельным, на grandkids уровнях ищем элемент
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(plusButton));
        for (int i = 0; i < 18; i++) {
            input.click();
        }
    }
    public void enterMinGuest() {
        //Ждем, пока поле ввода станет кликабельным, на grandkids уровнях ищем элемент
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(minusButton));
        for (int i = 0; i < 19; i++) {
            input.click();
        }
    }
    public void maxNumber() {
        //Ждем, пока поле ввода станет кликабельным, на grandkids уровнях ищем элемент
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(numerButton));
        for (int i = 0; i < 19; i++) {
            input.click();
        }
    }
}