package webdriver;

import static webdriver.Logger.getLoc;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;

import javax.naming.NamingException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.opera.core.systems.OperaDriver;
import webdriver.Browser.Browsers;

/**
 * The class-initializer-based browser string parameter.
 */
public abstract class BrowserFactory {

	/**
	 * Setting up Driver
	 * @param type Browser type
	 * @return RemoteWebDriver
	 */
	public static RemoteWebDriver setUp(final Browsers type) {
		
	//	DesiredCapabilities capabilitiesProxy = null;
		File fileDownload = new File("src//Download");
		String downloadFilePath = fileDownload.getAbsolutePath();

		
		
		RemoteWebDriver driver = null;
		File myFile = null;
		switch (type) {
		case CHROME:

			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilePath);
			chromePrefs.put("download.prompt_for_download", "false");
			chromePrefs.put("safebrowsing.enabled", "false");
			chromePrefs.put("intl.accept_languages",new PropertiesResourceManager(Browser.PROPERTIES_FILE).getProperty("locale"));
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			URL myTestURL = ClassLoader.getSystemResource("chromedriver.exe");
			try {
				myFile = new File(myTestURL.toURI());
			} catch (URISyntaxException e1) {
				Logger.getInstance().debug(e1.getMessage());
			}
			System.setProperty("webdriver.chrome.driver", myFile.getAbsolutePath());
			driver = new ChromeDriver(cap);
			break;

		case FIREFOX:

			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.download.dir", downloadFilePath);
			profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/msword, application/csv, application/ris, text/csv, image/png, application/pdf, text/html, text/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");
			profile.setPreference("browser.download.manager.showWhenStarting", false);
			profile.setPreference("browser.download.manager.focusWhenStarting", false);
			profile.setPreference("browser.download.useDownloadDir", true);
			profile.setPreference("browser.helperApps.alwaysAsk.force", false);
			profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
			profile.setPreference("browser.download.manager.closeWhenDone", true);
			profile.setPreference("browser.download.manager.showAlertOnComplete", false);
			profile.setPreference("browser.download.manager.useWindow", false);
			profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
			profile.setPreference("pdfjs.disabled", true);

			driver = new FirefoxDriver(profile);
			break;

		case IEXPLORE:
			//local security request flag INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS
			//(but this flag may cause appearing "skipped" tests)
			if(new PropertiesResourceManager(Browser.PROPERTIES_FILE).getProperty("localrun").equalsIgnoreCase("true")){
				DesiredCapabilities cp = DesiredCapabilities.internetExplorer();
				cp.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				URL myTestURL2 = ClassLoader.getSystemResource("IEDriverServer.exe");
				try {
					myFile = new File(myTestURL2.toURI());
				} catch (URISyntaxException e1) {
					Logger.getInstance().debug(e1.getMessage());
				}
				System.setProperty("webdriver.ie.driver", myFile.getAbsolutePath());
				driver = new InternetExplorerDriver(cp);
			// better to avoid
			}else{
				// now remote connection will be refused, so use selenium server instead
				driver = new InternetExplorerDriver();
			}
			break;
			
		case OPERA:
			//work on v.11-12 (Presto engine)
			
			driver = new OperaDriver();
			break;
		case SAFARI:
			//work on v.5.1+
			
			driver = new SafariDriver();
			break;
		default:
			break;
		}
		driver.manage().window().maximize();
		return driver;
	}

	/**
	 * Setting up Driver
	 * @param type Browser type
	 * @return RemoteWebDriver
	 * @throws NamingException NamingException
	 */
	public static RemoteWebDriver setUp(final String type) throws NamingException {
		for (Browsers t : Browsers.values()) {
			if (t.toString().equalsIgnoreCase(type)) {
				return setUp(t);
			}
		}
		throw new NamingException(getLoc("loc.browser.name.wrong")+":\nchrome\nfirefox\niexplore\nopera\nsafari");
	}
}
