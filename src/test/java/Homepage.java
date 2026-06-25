import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Homepage {
    protected WebDriver driver;

    public Homepage(WebDriver driver) {
        this.driver = driver;
    }

    public Homepage navigate() {
        driver.get("http://localhost:5173/");
        return this;
    }

    public WebElement navigateToPage(String targetPath) {
        WebElement newPage = driver.findElement(By.cssSelector("a[href*='" + targetPath + "']"));
        return newPage;
    }

    public String get_title() {
        String title = driver.getTitle();
        return title;
    }

    public String getHeadingText() {
        WebElement heading = driver.findElement(By.tagName("h1"));
        return heading.getText();
    }


}