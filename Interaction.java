package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Interaction {
    static WebDriver driver;

    public static void main(String[] args) {
        System.setProperty("webDriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/");

        try {
            interactDropDown();
            //interactTables();
            //interactFrames();
            //interactAlerts();
            //scrollPage();
            //doubleClick();
            //rightClick();
            //handleSpecialKeys();
            //uploadFile();
        } finally {
            driver.quit();
        }
    }

    static void interactDropDown() {
        System.out.println("interactWithDropDown");
        driver.get("https://the-internet.herokuapp.com/dropdown");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Select dropdown = new Select(driver.findElement(By.id("dropdown")));
        dropdown.selectByVisibleText("Option 1");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        dropdown.selectByIndex(2);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    static void interactTables() {
        driver.get("https://the-internet.herokuapp.com/tables");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement table = driver.findElement(By.id("table1"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(By.tagName("td"));
            for (WebElement col : cols) {
                System.out.print(col.getText() + "\t");
            }
            System.out.println();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static void interactFrames() {
        System.out.println("interactWithFrames");
        driver.get("https://the-internet.herokuapp.com/iframe");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.switchTo().frame("mce_0_ifr");
        WebElement editor = driver.findElement(By.id("tinymce"));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        /*editor.clear();
        editor.sendKeys("Hello, Frame!");*/
        driver.switchTo().defaultContent();
        WebElement title = driver.findElement(By.xpath("//h3"));
        System.out.println(title.getText());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    static void interactAlerts() {
        System.out.println("interactWithAlerts");
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        //WebElement alert = driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        //WebElement confirm = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
        WebElement prompt = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));

        prompt.click();

        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        alert.sendKeys("Hello!");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        alert.accept();

        // Verify the result text
        WebElement result = driver.findElement(By.id("result"));
        System.out.println("Result message: " + result.getText());
        //alert.accept();
        //alert.dismiss();
    }
    static void scrollPage() {
        System.out.println("scrollPage---------------");
        // Open a sample website
        driver.get("https://www.selenium.dev/documentation/en/");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Scroll down by 500 pixels
        js.executeScript("window.scrollBy(0,500);");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Scroll up by 500 pixels
        js.executeScript("window.scrollBy(0,-500);");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Scroll to bottom of the page
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Scroll back to top
        js.executeScript("window.scrollTo(0, 0);");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Find the element to scroll to
        WebElement element = driver.findElement(By.xpath("//a[text()='Selenium Overview']"));

        // Scroll to the element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        // Highlight the element (for visibility)
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    static void doubleClick() {
        System.out.println("doubleClick");

        driver.get("https://www.selenium.dev/documentation/en/");

        // Use WebDriverWait to wait until the input field is enabled
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='The Selenium Browser Automation Project']")));

        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//h1[text()='The Selenium Browser Automation Project']"));
        actions.doubleClick(element).perform();

        System.out.println("doubleClicked at :" + element.getText());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    static void rightClick() {
        System.out.println("rightClick");

        driver.get("https://www.selenium.dev/documentation/en/");

        // Use WebDriverWait to wait until the input field is enabled
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='The Selenium Browser Automation Project']")));

        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//h1[text()='The Selenium Browser Automation Project']"));
        actions.contextClick(element).perform();

        System.out.println("rightClicked at :" + element.getText());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    static void handleSpecialKeys() {
        System.out.println("handleSpecialKeys");
        driver.get("https://the-internet.herokuapp.com/key_presses");
        WebElement inputField = driver.findElement(By.id("target"));
        inputField.sendKeys("test");
        inputField.sendKeys(Keys.CONTROL + "a");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        inputField.sendKeys(Keys.ENTER);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    static void uploadFile() {
        System.out.println("uploadFile");
        driver.get("https://the-internet.herokuapp.com/upload");
        WebElement fileInput = driver.findElement(By.id("file-upload"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        fileInput.sendKeys("/home/ehsanul.vivasoft_bKash.com/Downloads/Interaction with Different Types of Web Elements in Selenium.pdf");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
