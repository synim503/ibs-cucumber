package ru.ibs.qa.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import org.openqa.selenium.support.ui.Sleeper;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ExperimentalSteps {

    @И("открыта страница по адресу {string}")
    public void открыта_страница_по_адресу(String string) {
        // Реализация
    }


    @И("выполнено нажатие на {string}")
    public void выполнено_нажатие_на(String string) {
        // Реализация
    }

    @И("поле {string} видимо")
    public void поле_видимо(String string) {
        // Реализация
    }

    @И("поле {string} заполняется значением {string}")
    public void поле_заполняется_значением(String string, String string2) {
        // Реализация
    }

    @И("элемент {string} присутствует")
    public void элемент_присутствует(String string) {

    }
    
    @И("следующие поля видимы:")
    public void следующие_поля_видимы(List<String> dataTable) {
        // Реализация
    }

    @И("заполняются поля:")
    public void заполняются_поля(Map<String, String> dataTable) {
        // Реализация
    }

    @И("значения полей:")
    public void значения_полей(DataTable dataTable) {
        // Реализация
    }

    @И("^значение поля \"([^\"]*)\" (равно|содержит значение) \"(.*)\"$")
    public void значение_поля_равно(String string, String string1, String string2) throws InterruptedException {
        // Реализация
        Sleeper.SYSTEM_SLEEPER.sleep(Duration.ofMillis(new Random().nextInt(3000)));
    }

}
