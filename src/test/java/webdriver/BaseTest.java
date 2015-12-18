package webdriver;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import webdriver.elements.Button;

/**
 * An abstract class that describes the basic test application contains
 * methods for logging and field test settings (options)
 */
public abstract class BaseTest extends BaseEntity {

    /**
     * To override.
     */
    public abstract void runTest();
    private Button btnLanguage = new Button(By.xpath("//span[@id='language_pulldown']"), "Language");
    private Button btnRussianLanguage = new Button(By.xpath("//a[@class='popup_menu_item tight' and contains(@onclick, 'russian')]"), "Russian language");
    private Button btnEnglishLanguage = new Button(By.xpath("//a[@class='popup_menu_item tight' and contains(@onclick, 'english')]"), "English language");

    /**
     * Test
     *
     * @throws Throwable Throwable
     */
    @Test
    public void xTest() throws Throwable {
        Class<? extends BaseTest> currentClass = this.getClass();

        try {
            logger.logTestName(currentClass.getName());
            browser.navigate(Browser.getBaseUrl());
            String language = Browser.getLanguage();
            String textLanguage = btnLanguage.getText();
            if ("RU".equals(language)) {
                if(!textLanguage.equals("язык")){
                    btnLanguage.click();
                    btnRussianLanguage.clickAndWait();
                }else {
                    logger.info("Русскоязычная версия сайта");
                }
            } else {
                if(!textLanguage.equals("language")){
                    btnLanguage.click();
                    btnEnglishLanguage.clickAndWait();
                }else {
                    logger.info("English-language version site");
                }
            }
            runTest();
            logger.logTestEnd(currentClass.getName());
        } catch (Throwable e) {

            logger.warn("");
            logger.warnRed(getLoc("loc.test.failed"));
            logger.warn("");
            logger.fatal(e.getMessage());
        }

    }

    /**
     * Format logging
     *
     * @param message Message
     * @return Message
     */
    protected String formatLogMsg(final String message) {
        return message;
    }

}
