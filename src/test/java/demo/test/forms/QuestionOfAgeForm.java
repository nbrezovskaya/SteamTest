package demo.test.forms;

import org.openqa.selenium.By;
import webdriver.BaseEntity;
import webdriver.elements.Button;
import webdriver.elements.DropDownList;
import webdriver.elements.Label;


public class QuestionOfAgeForm extends BaseEntity {
    private Label lbLogoAgeForm = new Label(By.xpath("//*[@id='agecheck_form']"), getLoc("loc.ageForm"));

    public void questionOfAge(String itemAgeDay, String itemAgeMonth, String itemAgeYear) {
        if (lbLogoAgeForm.isPresent()) {
            new DropDownList(By.xpath("//select[@name='ageDay']"), getLoc("loc.selectDay")).click();
            new Button(By.xpath(String.format("//select[@name='ageDay']//option[@value='%1$s']", itemAgeDay)), getLoc("loc.day")).click();
            new DropDownList(By.xpath("//select[@name='ageMonth']"), getLoc("loc.selectMonth")).click();
            new Button(By.xpath(String.format("//select[@name='ageMonth']//option[@value='%1$s']", itemAgeMonth)), getLoc("loc.month")).click();
            new DropDownList(By.xpath("//select[@name='ageYear']"), getLoc("loc.selectYear")).click();
            new Button(By.xpath(String.format("//select[@name='ageYear']//option[@value='%1$s']", itemAgeYear)), getLoc("loc.year")).click();
            new Button(By.xpath(".//form[@id='agecheck_form']/a"), getLoc("loc.enter")).click();
        }
    }

    @Override
    protected String formatLogMsg(String message) {
        return null;
    }
}
