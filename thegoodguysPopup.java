import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class thegoodguysPopup {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "iPhone.*");
        capabilities.setCapability("platformVersion", "14");
        capabilities.setCapability("platformName", "ios");
        capabilities.setCapability("isRealMobile", true);
        capabilities.setCapability("video", true);
        capabilities.setCapability("visual", true);
        capabilities.setCapability("network", true);
        capabilities.setCapability("devicelog", true);
        capabilities.setCapability("autoAcceptAlerts", true);
        capabilities.setCapability("build", "iOSLocationPopupWeb");

        AppiumDriver driver = new AppiumDriver(
                new URL("https://LT_USERNAME:LT_ACCESSKEY@mobile-hub.lambdatest.com/wd/hub"),
                capabilities);
        try {
            driver.get("https://www.thegoodguys.com.au");
            Thread.sleep(3000);
            //Get all the contexts here
            Set contextNames = driver.getContextHandles();
            for (Object contextName : contextNames) {
                System.out.println(contextNames);
            }
            String context = driver.getContext();
            System.out.println(context);
            Thread.sleep(5000);
            //swtich to native app context
            driver.context((String) contextNames.toArray()[0]); // set context to WEBVIEW_1
            String context1 = driver.getContext();
            System.out.println(context1);
            Thread.sleep(5000);
            //CLick on pop element
            driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Allow Once\"]")).click();
            Thread.sleep(5000);
            //Swtich back to webview context
            driver.context((String) contextNames.toArray()[1]); // set context to WEBVIEW_1
            String context2 = driver.getContext();
            System.out.println(context2);
            driver.quit();
        } catch (Exception e) {
            driver.executeScript("lambda-status=failed");
            driver.quit();
        }

    }
}
