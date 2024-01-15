package ru.ibs.qa.steps;

import io.cucumber.java.Before;
import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.То;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.qa.manager.DriverManager;

import java.time.Duration;
import java.util.List;

public class MyStepdefs {
    private WebDriver driver;
    private WebDriverWait wait;

    private String name = "Капуста";
    private Type type = Type.VEGETABLE;
    private Boolean isExotic = false;
    public MyStepdefs() {
        this.driver = DriverManager.getDriverManager().getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    enum Type{
        VEGETABLE("Овощ"),FRUIT("Фрукт");
        private String dscr;

        Type(String dscr) {
            this.dscr = dscr;
        }

        public String getDscr() {
            return dscr;
        }
    }

    @Допустим("открывает сайт")
    public void открываетСайт() {
        driver.get("http://localhost:8080");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".navbar")));

    }

    @И("переходит на страницу товаров")
    public void переходитНаСтраницуТоваров() {
        WebElement navbarToggler = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.navbar-toggler")));
        if (navbarToggler.isDisplayed()) {
            navbarToggler.click();
        }
        WebElement sandbox = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.nav-item.dropdown")));
        sandbox.click();
        WebElement product = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.dropdown-item[href='/food']")));
        product.click();
    }

    @Если("пользователь коректно заполняет поля товара")
    public void пользовательКоректноЗаполняетПоляТовара() {

        WebElement add = driver.findElement(By.xpath("//button[@data-toggle='modal']"));
        add.click();

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        nameField.sendKeys(name);

        WebElement typeDropdown = driver.findElement(By.id("type"));
        Select selectType = new Select(typeDropdown);
        selectType.selectByValue(type.name());

        if(isExotic){
            WebElement checkBoxExotic = driver.findElement(By.id("exotic"));
            checkBoxExotic.click();
        }
        WebElement save = driver.findElement(By.id("save"));
        save.click();
    }

    @То("товар отобразится в общем списке")
    public void товарОтобразитсяВОбщемСписке() throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".table")));
        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table[@class='table']//tbody//tr")));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.xpath(".//td"));
            System.out.println(cells.get(0).getText());
            System.out.println(cells.get(1).getText());
            System.out.println(cells.get(2).getText());
            if (cells.get(1).getText().equals(type.getDscr()) &&
                    cells.get(0).getText().equals(name) &&
                    Boolean.parseBoolean(cells.get(2).getText()) == isExotic) {
                return;
            }
        }
        throw new Exception("Товар не отобразился") ;

    }

    @Если("пользователь некоректно заполняет поля товара")
    public void пользовательНекоректноЗаполняетПоляТовара() {
        WebElement add = driver.findElement(By.xpath("//button[@data-toggle='modal']"));
        add.click();

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        nameField.sendKeys("");

        WebElement typeDropdown = driver.findElement(By.id("type"));
        Select selectType = new Select(typeDropdown);
        selectType.selectByValue(type.name());

        if(isExotic){
            WebElement checkBoxExotic = driver.findElement(By.id("exotic"));
            checkBoxExotic.click();
        }
        WebElement save = driver.findElement(By.id("save"));
        save.click();
    }

    @То("товар добавится без наименования")
    public void товарДобавитсяБезНаименования() throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".table")));
        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table[@class='table']//tbody//tr")));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.xpath(".//td"));

            if (cells.get(1).getText().equals(type.getDscr()) &&
                    cells.get(0).getText().equals("") &&
                    Boolean.parseBoolean(cells.get(2).getText()) == isExotic) {
                return;
            }
        }
        throw new Exception("Това отобразился c наименованием") ;
    }
}
