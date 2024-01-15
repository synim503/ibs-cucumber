package ru.ibs.qa.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
    private WebDriver driver;
    private static DriverManager INSTANCE = null;

    private DriverManager(){
        System.setProperty("webdriver.chromedriver.driver", "/src/test/resources/chromedriver.exe");
    }

    public static DriverManager getDriverManager(){
        if(INSTANCE == null){
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }

    public WebDriver getDriver(){
        if(driver == null){
            initDriver();
        }
        return driver;
    }

    public void quitDriver(){
        if(driver !=null){
            driver.quit();
            driver = null;
        }
    }

    private void initDriver(){
        if(driver==null){
            driver = new ChromeDriver();
        }
    }

}
