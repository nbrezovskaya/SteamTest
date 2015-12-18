package demo.test.menus;

import org.openqa.selenium.By;
import webdriver.BaseEntity;
import webdriver.elements.Button;

import static java.lang.Thread.sleep;

public class MainMenu extends BaseEntity {
    public void mainMenuNavigate(String itemName, String nameOfListItems) {
        new Button(By.xpath(String.format("//a[contains(@class,'pulldown_desktop') and contains(text(),'%1$s')]", itemName)), getLoc("loc.navigate")).hoverAndWait();
        new Button(By.xpath(String.format("//div[@id='genre_flyout']//a[contains(text(),'%1$s')]", nameOfListItems)), getLoc("loc.genre")).clickAndWait();
    }

    @Override
    protected String formatLogMsg(String message) {
        return null;
    }
}
