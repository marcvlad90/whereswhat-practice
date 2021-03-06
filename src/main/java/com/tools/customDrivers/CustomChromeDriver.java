package com.tools.customDrivers;

import java.util.HashMap;

import net.thucydides.core.webdriver.DriverSource;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.tools.constants.Constants;

public class CustomChromeDriver implements DriverSource {

    @Override
    public WebDriver newDriver() {
        return setCustomChrome();
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }

    @SuppressWarnings("deprecation")
    private WebDriver setCustomChrome() {
        System.out.println("Custom chrome driver instance is created now...");
        System.setProperty("webdriver.chrome.driver", Constants.WEB_DRIVERS_PATH + "chromedriver.exe");
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download.default_directory", Constants.DOWNLOAD_FILE_PATH);
        chromePrefs.put("plugins.always_open_pdf_externally", true);
        chromePrefs.put("profile.default_content_settings.popups", 0);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        return new ChromeDriver(cap);
    }
}
