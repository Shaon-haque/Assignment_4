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

public class New {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void openLoginPageTest() {
        driver.get("https://www.saucedemo.com/");
        Assert.assertEquals(driver.getTitle(), "Swag Labs");
    }

    @Test(priority = 2)
    public void enterUsernameTest() {
        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");
        Assert.assertEquals(username.getAttribute("value"), "standard_user");
    }

    @Test(priority = 3)
    public void enterPasswordTest() {
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");
        Assert.assertEquals(password.getAttribute("value"), "secret_sauce");
    }

    @Test(priority = 4)
    public void clickLoginButtonTest() {
        driver.findElement(By.id("login-button")).click();
        Assert.assertTrue(driver.findElement(By.className("inventory_list")).isDisplayed());
    }

    @Test(priority = 5)
    public void selectProductTest() {
        WebElement product = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        product.click();
        Assert.assertTrue(driver.findElement(By.className("inventory_details")).isDisplayed());
    }

    @Test(priority = 6)
    public void addToCartTest() {
        WebElement addToCart = driver.findElement(By.xpath("//button[text()='Add to cart']"));
        addToCart.click();
        Assert.assertEquals(driver.findElement(By.className("shopping_cart_badge")).getText(), "1");
    }

    @Test(priority = 7)
    public void openCartTest() {
        driver.findElement(By.className("shopping_cart_link")).click();
        Assert.assertTrue(driver.findElement(By.className("cart_list")).isDisplayed());
    }

    @Test(priority = 8)
    public void proceedToCheckoutTest() {
        driver.findElement(By.id("checkout")).click();
        Assert.assertTrue(driver.findElement(By.id("first-name")).isDisplayed());
    }

    @Test(priority = 9)
    public void enterCheckoutDetailsTest() {
        driver.findElement(By.id("first-name")).sendKeys("Shaon");
        driver.findElement(By.id("last-name")).sendKeys("Haque");
        driver.findElement(By.id("postal-code")).sendKeys("1215");
        driver.findElement(By.id("continue")).click();
        Assert.assertTrue(driver.findElement(By.className("cart_list")).isDisplayed());
    }

    @Test(priority = 10)
    public void completePurchaseTest() {
        driver.findElement(By.id("finish")).click();
        Assert.assertEquals(driver.findElement(By.className("complete-header")).getText(), "Thank you for your order!");
    }

    @Test(priority = 11)
    public void checkTwitterLinkTest() {
        WebElement twitter = driver.findElement(By.linkText("Twitter"));
        Assert.assertTrue(twitter.isDisplayed());
    }

    @Test(priority = 12)
    public void checkFacebookLinkTest() {
        WebElement facebook = driver.findElement(By.linkText("Facebook"));
        Assert.assertTrue(facebook.isDisplayed());
    }

    @Test(priority = 13)
    public void checkLinkedInLinkTest() {
        WebElement linkedin = driver.findElement(By.linkText("LinkedIn"));
        Assert.assertTrue(linkedin.isDisplayed());
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}

