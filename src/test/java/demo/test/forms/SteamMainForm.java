package demo.test.forms;

import demo.test.menus.MainMenu;
import org.openqa.selenium.By;
import webdriver.elements.Label;

public class SteamMainForm extends MainMenu {
    private Label lbLogo = new Label(By.xpath("//*[@class='logo']"), getLoc("loc.steam.logo"));

    public void assertLogo() {
        assertEquals(true, lbLogo.isPresent());
    }

}
