import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AvitoSelen {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));

        //��� 1
        driver.get("https://www.avito.ru/");

        //��� 2
        //���� ��������� �� id
        Select select = new Select(driver.findElement(By.cssSelector("#category")));

        //������� ��� �������� ��������
        select.getOptions().forEach(webElement -> {
            System.out.println(webElement.getText());
        });

        //�������� ���������
        select.selectByVisibleText("���������� � ����������");

        //��� 3
        //���� ���� ����� � ��������� "�������"
        driver.findElement(By.xpath("//input[@data-marker='search-form/suggest']")).sendKeys("�������");

        //��� 4
        //���� ������� ������ ������� � ������� �� ����
        driver.findElement(By.xpath("//div[@data-marker='search-form/region']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //� ����� ���� ���� ���� ������ ������� � ��������� "�����������"
        driver.findElement(By.xpath("//div/input[@data-marker='popup-location/region/input']")).sendKeys("�����������");

        //��� 5
        //���� ������ ������� �� ����������� ������ � ������� �� ����
        WebElement searchRegion = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(webDriver -> driver.findElement(By.xpath("//li[@data-marker='suggest(0)']")));
        System.out.println(searchRegion.getText());
        searchRegion.click();

        driver.findElement(By.xpath("//button[@data-marker='popup-location/save-button']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //��� 6
        //���� checkbox � ������ ��������, ���� ���������, �� ������� �� ���� � �������� ������
        WebElement checkbox = driver.findElement(By.xpath("//input[@data-marker='delivery-filter/input']"));
        if (!checkbox.isSelected()) {
            checkbox.sendKeys(Keys.SPACE);
        }
        driver.findElement(By.xpath("//button[@data-marker='search-filters/submit-button']")).click();

        //��� 7
        //���� �������� ������� � �������� ��������������� ��������
        Select selectFilter = new Select(driver.findElement(By.xpath(
                "//div[contains(@class, 'index-content')]" +
                        "//select[contains(@class, 'select-select') and position()=1]")));

        selectFilter.selectByVisibleText("������");

        //��� 8
        //� List ����� ��������� ��������� ��������
        List<WebElement> selectProduct = driver.findElements(By.xpath("//div[@data-marker='catalog-serp']" +
                "/div[@data-marker='item']"));

        //����������� �� ��������� � List
        for (int i = 0; i < 3; i++) {
            System.out.println(selectProduct.get(i).findElement(By.xpath(".//a[@data-marker='item-title']")).getText());
            System.out.println(selectProduct.get(i).findElement(By.xpath(".//span[@data-marker='item-price']")).getText());
        }

        driver.quit();
    }
}
