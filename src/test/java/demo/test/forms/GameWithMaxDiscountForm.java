package demo.test.forms;

import demo.test.model.Game;
import org.openqa.selenium.By;
import webdriver.BaseEntity;
import webdriver.elements.Button;
import webdriver.elements.Label;


public class GameWithMaxDiscountForm extends BaseEntity {
    private Label discountOnPage = new Label(By.xpath("//*[@id='game_area_purchase']//div[@class='discount_pct']"), getLoc("loc.discount"));
    private Label priceOnPage = new Label(By.xpath("//*[@id='game_area_purchase']//div[@class='discount_final_price' and contains(text(),'USD')]"), getLoc("loc.price"));
    private Button btnInstallSteam = new Button(By.xpath("//*[@class='header_installsteam_btn_content']"), getLoc("loc.install"));

    public void assertDiscount(Game game) {
        int discount = -Integer.parseInt(discountOnPage.getText().replace("%", ""));
        assertEquals(game.getDiscount(), discount);
    }

    public void assertPrice(Game game) {
        String priceText = priceOnPage.getText().replace(" USD", "");
        assertEquals(game.getPriceText(), priceText);
    }

    public void installSteam() {
        btnInstallSteam.click();
    }

    @Override
    protected String formatLogMsg(String message) {
        return null;
    }
}
