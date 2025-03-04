package org.example;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Main {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        //System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @Test(priority = 1)
    public void loginTest() {
        driver.get("https://www.saucedemo.com/");
        String title = driver.getTitle();
        System.out.println("Page title: " + title);
        Assert.assertEquals(title, "Swag Labs");

        WebElement username = driver.findElement(By.xpath("//input[@id='user-name']"));
        username.sendKeys("standard_user");
        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        password.sendKeys("secret_sauce");
        WebElement login = driver.findElement(By.xpath("//input[@class='submit-button btn_action']"));
        login.click();
    }

    @Test(priority = 2)
    public void addToCartTest() throws InterruptedException {
        WebElement product = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        product.click();
        Thread.sleep(2000);
        WebElement addToCart = driver.findElement(By.xpath("//button[text()='Add to cart']"));
        addToCart.click();
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    public void checkoutTest() throws InterruptedException {
        WebElement cart = driver.findElement(By.xpath("//a[contains(@class,'shopping_cart_link')]"));
        cart.click();
        Thread.sleep(2000);

        WebElement checkout = driver.findElement(By.xpath("//button[@data-test='checkout']"));
        checkout.click();
        Thread.sleep(2000);

        WebElement firstName = driver.findElement(By.xpath("//input[@id='first-name']"));
        firstName.sendKeys("shaon");
        WebElement lastName = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
        lastName.sendKeys("haque");
        WebElement postalCode = driver.findElement(By.xpath("//input[@id='postal-code']"));
        postalCode.sendKeys("1215");
        Thread.sleep(2000);

        WebElement continueButton = driver.findElement(By.xpath("//input[@id='continue' and @type='submit']"));
        continueButton.click();
        Thread.sleep(2000);

        WebElement finishButton = driver.findElement(By.xpath("//button[@class='btn btn_action btn_medium cart_button']"));
        finishButton.click();
        Thread.sleep(2000);
    }

    @Test(priority = 4)
    public void socialMediaLinksTest() throws InterruptedException {
        WebElement twitter = driver.findElement(By.xpath("//a[text()='Twitter']"));
        twitter.click();
        Thread.sleep(2000);
        WebElement facebook = driver.findElement(By.xpath("//a[text()='Facebook']"));
        facebook.click();
        Thread.sleep(2000);
        WebElement linkedin = driver.findElement(By.xpath("//a[text()='LinkedIn']"));
        linkedin.click();
        Thread.sleep(4000);
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}