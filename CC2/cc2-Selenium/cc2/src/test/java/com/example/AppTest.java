package com.example;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest {
    WebDriver driver;
    ExtentReports reports;
    ExtentTest test;
    Logger log = Logger.getLogger(AppTest.class);

    @BeforeTest
    public void start() {
        WebDriverManager.chromedriver().setup();
        PropertyConfigurator.configure(("src/main/resources/log4j.properties"));
        driver = new ChromeDriver();
        ExtentSparkReporter ereport = new ExtentSparkReporter(
                "c:\\Users\\ROHINTH\\cc2-Selenium");
        reports = new ExtentReports();
        reports.attachReporter(ereport);
    }

    @BeforeMethod
    public void navigateUrl() {
        driver.navigate().to("https://www.barnesandnoble.com/");
    }

    @Test(priority = 1)
    public void testCase1() throws IOException, InterruptedException, NullPointerException {
        driver.findElement(By.xpath("//*[@id=\"rhf_header_element\"]/nav/div/div[3]/form/div/div[1]/a")).click();
        driver.findElement(By.linkText("Books")).click();
        Thread.sleep(3000);
        FileInputStream fs = new FileInputStream("d:\\dbankdemo.xlsx");
        log.info("url clicked");
        XSSFWorkbook work = new XSSFWorkbook(fs);
        XSSFSheet sheet = work.getSheet("login");
        XSSFRow row = sheet.getRow(2);
        String input = row.getCell(0).getStringCellValue();
        driver.findElement(By.xpath("//*[@id=\"rhf_header_element\"]/nav/div/div[3]/form/div/div[2]/div/input[1]"))
                .sendKeys(input);
        driver.findElement(By.xpath("//*[@id=\"rhf_header_element\"]/nav/div/div[3]/form/div/span/button")).click();
        Thread.sleep(5000);
        String name = driver
                .findElement(By.xpath("//*[@id=\"searchGrid\"]/div/section[1]/section[1]/div/div[1]/div[1]/h1"))
                .getText();
        if (name.contains("Chetan Bhagat")) {
            log.info("The text contains Chetan Bhagat");
        } else {
            log.error("The text doesnot contains Chetan Bhagat");
        }
    }

    @Test(priority = 2)
    public void testing2() throws Exception {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.barnesandnoble.com/");
        WebElement clickable = driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[4]/div/ul/li[5]/a"));
        Thread.sleep(2000);

        new Actions(driver)
                .clickAndHold(clickable)
                .perform();
        driver.findElement(By.linkText("Audiobooks Top 100")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(
                "/html/body/main/div[2]/div[1]/div/div[2]/div/div[2]/section[2]/ol/li[1]/div/div[2]/div[1]/h3/a"))
                .click();
        Thread.sleep(2000);
        // driver.findElement(By.xpath("/html/body/main/div[2]/div[2]/section/div[2]/div/div[3]/div[2]/ul/li[2]/div/div/label/span")).click();
        // Thread.sleep(2000);
        JavascriptExecutor js10 = (JavascriptExecutor) driver;
        js10.executeScript("window.scrollBy(0,400)");
        Thread.sleep(2000);
        driver.findElement(
                By.xpath("/html/body/main/div[2]/div[2]/section/div[2]/div/div[3]/div[2]/ul/li[2]/div/div/label/span"))
                .click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(
                "/html/body/main/div[2]/div[2]/section/div[2]/div/div[3]/div[2]/div[3]/div[1]/div[1]/form/input[5]"))
                .click();
        Thread.sleep(2000);

        driver.quit();
    }

    @Test(priority = 3)
    public void testing3() throws Exception {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.barnesandnoble.com/");
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // js.executeScript("window.scrollBy(0,2000)");
        // Thread.sleep(2000);
        driver.navigate().to("https://www.barnesandnoble.com/membership/");
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("window.scrollBy(0,2000)");
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/main/section/div[1]/div[2]/div/div/div[2]/div/div[73]/div/div[1]/a"))
                .click();
        Thread.sleep(2000);
        // WebElement check2=driver.findElement(By.xpath("//*[@id=\"dialog-title\"]"));
        // Assert.assertTrue(check2.getText().contains("Sign in or Create an Account"),
        // "Sign in first!!");
        driver.quit();
    }

}
