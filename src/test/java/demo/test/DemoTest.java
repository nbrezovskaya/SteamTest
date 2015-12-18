package demo.test;

import demo.test.model.Game;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import webdriver.BaseTest;
import demo.test.forms.*;

import java.io.File;


public class DemoTest extends BaseTest {
	private String ageDay;
	private String ageMonth;
	private String ageYear;
	private String fileName;

	@BeforeMethod
	@Parameters({"ageDay","ageMonth", "ageYear", "fileName" })
	public void BeforeMethod(String ageDay, String ageMonth, String ageYear, String fileName){
		this.ageDay = ageDay;
		this.ageMonth = ageMonth;
		this.ageYear = ageYear;
		this.fileName = fileName;

	}

	
	public void runTest() {

		browser.waitForPageToLoad();
		logger.step(1);
		SteamMainForm steamMainForm = new SteamMainForm();
		steamMainForm.assertLogo();

		logger.step(2);
		steamMainForm.mainMenuNavigate(getLoc("loc.navigate"), getLoc("loc.genre"));

		logger.step(3);
		LabelActionsForm labelActionsForm = new LabelActionsForm();
		labelActionsForm.clickSales();

		logger.step(4);
		Game maxDiscountGame = labelActionsForm.findMaxDiscountGame();
		labelActionsForm.clickGame(maxDiscountGame);

		logger.step(4.1);
		QuestionOfAgeForm questionOfAgeForm = new QuestionOfAgeForm();
		questionOfAgeForm.questionOfAge(ageDay, ageMonth, ageYear);

		logger.step(5);
		GameWithMaxDiscountForm gameWithMaxDiscount = new GameWithMaxDiscountForm();
		gameWithMaxDiscount.assertDiscount(maxDiscountGame);
		gameWithMaxDiscount.assertPrice(maxDiscountGame);

		logger.step(6);
		gameWithMaxDiscount.installSteam();

		logger.step(7);
		File fileDownload = new File("src//Download//" + fileName);
		InstallSteamForm installSteamForm = new InstallSteamForm();
		installSteamForm.installSteamNow();
		this.waitForFileDownload(fileDownload);
		org.testng.Assert.assertTrue(fileDownload.canRead());
		fileDownload.delete();


	}
}
