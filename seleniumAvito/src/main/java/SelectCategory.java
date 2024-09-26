import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectCategory {
    public WebDriver driver;

    public SelectCategory(WebDriver driver) {
        this.driver = driver;
    }

    public void setCategory(String nameCategory) {
        Select select = new Select(driver.findElement(By.cssSelector("#category")));
        select.selectByVisibleText(nameCategory);
    }
}
