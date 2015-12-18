package demo.test.forms;

import demo.test.model.Game;
import org.openqa.selenium.By;
import webdriver.BaseEntity;
import webdriver.elements.Button;
import webdriver.elements.DivElement;

public class LabelActionsForm extends BaseEntity {
    private Button btnSales = new Button (By.xpath("//div[@id='tab_select_Discounts']"),getLoc("loc.inset"));

    public void clickSales(){
        btnSales.clickAndWait();
    }

    public Game findMaxDiscountGame() {
        int i = 1;
        Game maxDiscountGame = null;
        DivElement element = new DivElement(By.xpath(".//*[@id='DiscountsRows']/div[" + i + "]"), "" + i + "");
        while (element.isPresent()) {
            String discountText = element.findInnerLabelText(By.xpath("//*[@id='DiscountsRows']/div[" + i + "]//div[@class='discount_pct']"));
            int discount = -Integer.parseInt(discountText.replace("%", ""));
            if (maxDiscountGame == null || maxDiscountGame.getDiscount() < discount) {
                String name = element.findInnerLabelText(By.xpath("//*[@id='DiscountsRows']/div[" + i + "]//div[@class='tab_item_name']"));
                String priceText = element.findInnerLabelText(By.xpath("//*[@id='DiscountsRows']/div[" + i + "]//div[@class='discount_final_price']"));
                maxDiscountGame = new Game(name, priceText, discount, element);
            }
            i++;
            element = new DivElement(By.xpath(".//*[@id='DiscountsRows']/div[" + i + "]"),"" + i + "");
        }
        return maxDiscountGame;
    }

    public void clickGame(Game game){
        game.getElement().click();
    }

    @Override
    protected String formatLogMsg(String message) {
        return null;
    }
}
