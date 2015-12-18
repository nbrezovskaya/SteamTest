package demo.test.forms;


import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;

public class InstallSteamForm extends BaseForm {
    private Button btnInstallSteamNow = new Button (By.xpath(".//*[@id='about_install_steam_link']"),getLoc("loc.installNow"));

    public void installSteamNow(){
        btnInstallSteamNow.click();
    }

    public InstallSteamForm() {
        super(By.xpath(".//*[@id='about_install_steam_link']"),getLoc("loc.installNow") );
    }
}
