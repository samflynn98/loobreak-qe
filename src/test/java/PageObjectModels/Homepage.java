package PageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Homepage {
    protected WebDriver driver;

    public Homepage(WebDriver driver) {
        this.driver = driver;
    }

    //Navigation
    public Homepage navigate() {
        driver.get("http://localhost:5173/");
        return this;
    }

    public WebElement navigateToPage(String targetPath) {
        WebElement newPage = driver.findElement(By.cssSelector("a[href*='" + targetPath + "']"));
        return newPage;
    }

    //Title & Heading
    public String getTitle() {
        String title = driver.getTitle();
        return title;
    }

    public String getHeadingText() {
        WebElement heading = driver.findElement(By.tagName("h1"));
        return heading.getText();
    }

    //Icebreakers
    public void toggleIcebreaker() {
        driver.findElement(By.cssSelector("[data-testid='icebreaker-reveal-btn']")).click();
    }

    public String getIcebreakerText() {
        String icebreaker = driver.findElement(By.cssSelector(".\\_icebreakerText_1ti99_10")).getText();
        return icebreaker;
    }

    public String getLootipText() {
        String lootip = driver.findElement(By.className("_tip_1ndiu_20")).getText();
        return lootip;
    }
}