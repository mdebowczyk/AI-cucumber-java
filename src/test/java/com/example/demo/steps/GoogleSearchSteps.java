package com.example.demo.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class GoogleSearchSteps {

    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run in headless mode
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Given("I am on the Google search page")
    public void i_am_on_the_google_search_page() {
        driver.get("https://www.google.com");
        // Google might show a consent form. Let's try to accept it.
        try {
            driver.findElement(By.xpath("//div[text()='I agree']")).click();
        } catch (Exception e) {
            // Element not found, we are probably not on a consent page.
        }
         try {
            driver.findElement(By.xpath("//button[div[contains(text(),'Accept all')]]")).click();
        } catch (Exception e) {
             // Element not found, we are probably not on a consent page.
        }
    }

    @When("I enter {string} in the search bar")
    public void i_enter_in_the_search_bar(String searchTerm) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(searchTerm);
    }

    @When("I click the search button")
    public void i_click_the_search_button() {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(Keys.RETURN);
    }

    @Then("the search results for {string} are displayed")
    public void the_search_results_for_are_displayed(String searchTerm) {
        assertTrue(driver.getTitle().contains(searchTerm));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
