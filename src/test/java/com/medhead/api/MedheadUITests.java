package com.medhead.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MedheadUITests {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        String chromeDriverPath = Paths.get("drivers", "chromedriver").toAbsolutePath().toString();
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSearchFormDisplayed() {
        driver.get("http://localhost:3000");
        WebElement searchForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("form")));
        assertTrue(searchForm.isDisplayed());
    }

    @Test
    public void testSearchFunctionality() {
        driver.get("http://localhost:3000");
        WebElement addressInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-testid='user-address']")));
        addressInput.sendKeys("46 Romford Road, London");
        assertEquals("46 Romford Road, London", addressInput.getAttribute("value"));

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-testid='find-hospitals']")));
        searchButton.click();

        WebElement results = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='results-count']")));
        assertTrue(results.isDisplayed());
    }
}
