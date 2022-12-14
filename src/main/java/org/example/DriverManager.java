package org.example;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class DriverManager extends BasePage {
    public static final String USERNAME = LoadProp.getProperty("BROWSERSTACK_USERNAME");
    public static final String AUTOMATE_KEY = LoadProp.getProperty("BROWSERSTACK_ACCESS_KEY");
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    String browserName = LoadProp.getProperty("browser");
    //    String browserName = System.getProperty("browser");

    boolean runIncloud = Boolean.parseBoolean(LoadProp.getProperty("cloud"));

    MutableCapabilities capabilities = new MutableCapabilities();

        public void openBrowser() {
            //run in a cloud==================================================================================================================

            if(runIncloud){//this command to run it at the cloud windows
                System.out.println("Running in the cloud");
                //connect to cloud
                if (browserName.equalsIgnoreCase("Edge")) {
                    ///connect with browserstack
                    capabilities.setCapability("browserName", "Edge");
                    capabilities.setCapability("browserVersion", "106.0");
                    HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
                    browserstackOptions.put("os", "Windows");
                    browserstackOptions.put("osVersion", "10");
                    browserstackOptions.put("local", "false");
                    browserstackOptions.put("seleniumVersion", "4.1.0");
                    capabilities.setCapability("bstack:options", browserstackOptions);

                } else if (browserName.equalsIgnoreCase("Firefox"))
                {
                    ///connect with browserstack
                    capabilities.setCapability("browserName", "firefox");
                    capabilities.setCapability("browserVersion", "106.0");
                    HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
                    browserstackOptions.put("os", "Windows");
                    browserstackOptions.put("osVersion", "11");
                    browserstackOptions.put("local", "false");
                    browserstackOptions.put("seleniumVersion", "4.1.0");
                    capabilities.setCapability("bstack:options", browserstackOptions);
                } else {
                    System.out.println("Your browser name is wrong or missing implementation:" + browserName);
                }
                try {
                    driver = new RemoteWebDriver(new URL(URL), capabilities);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }

            }else {//This command to run in the local window
                System.out.println("Running locally");
                // run in locally============================================================================================================
                if (browserName.equalsIgnoreCase("Chrome")) {
                    // System.setProperty() method forms the basis for test automation on any browser
                    System.setProperty("webdriver.chrome.driver", "src/test/java/Driver/chromedriver");
                    // Test case automation on chrome
                    driver = new ChromeDriver();
                } else if (browserName.equalsIgnoreCase("Edge")) {
                    System.setProperty("webdriver.edge.driver", "src/test/java/Driver/msedgedriver");
                    driver = new EdgeDriver();
                } else if (browserName.equalsIgnoreCase("Firefox")) {
                    System.setProperty("webdriver.ie.driver", "src/test/java/Driver/geckodriver");
                    driver = new FirefoxDriver();

//          }  else if (browserName.equalsIgnoreCase("Safari")) {
//                System.setProperty("Webdriver..driver", "");
//                driver = new

                } else {
                    System.out.println("Please Change your browser Name" + browserName);
                }
            }
                // Instantiating as ChromeDriver
                driver.get(LoadProp.getProperty("url"));
                // Navigate the given URL and wait till page load
                driver.manage().window();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                // It returns instance of option interface and returns instance of window interface
        }
        public void closeBrowser(){
                //to close the web browser
                driver.quit();
        }

}
