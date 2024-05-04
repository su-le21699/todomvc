package common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Browsers {
    private static WebDriver driver;
    private static int MAX_TIME_OUT = 30;
    private static WebDriverWait wait;
    public static Actions actions;

    //Selenium owner methods
    public static void openBrowser(String name) {
        switch (name.toLowerCase()) {
            case "firefox" -> driver = new FirefoxDriver();
            case "chrome" -> driver = new ChromeDriver();
            case "safari" -> driver = new SafariDriver();
            case "edge" -> driver = new EdgeDriver();
            default -> driver = new ChromeDriver();
        }
        ;
        wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_TIME_OUT));
        actions = new Actions(driver);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void captureScreenShot(String tcName) {
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(String.format("target/screenshot-%s-%s.png", tcName, System.currentTimeMillis()));
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeBrowser() {
        driver.quit();
    }

    public static void visit(String url) {
        driver.get(url);
    }

    public static void click(By locator) {
        driver.findElement(locator).click();
    }

    public static void sendkeys(By locator, String withText) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(withText);
    }

    public static String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public static String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public static boolean isDisplayed(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public static void hover(By locator) {
        actions.moveToElement(driver.findElement(locator)).perform();

    }

    public static WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    public static void doubleClick(By locator) {
        actions.doubleClick(driver.findElement(locator)).perform();
    }

    public static void executeScript(String script, Object... arguments) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script, arguments);
    }
}
