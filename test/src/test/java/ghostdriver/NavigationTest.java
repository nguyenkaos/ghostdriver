package ghostdriver;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class NavigationTest extends BaseTest {
    @Test
    public void navigateAroundMDN() {
        WebDriver d = getDriver();
        d.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        d.get("https://developer.mozilla.org/en-US/");
        assertTrue(d.getTitle().toLowerCase().contains("Mozilla".toLowerCase()));
        d.navigate().to("https://developer.mozilla.org/en/HTML/HTML5");
        assertTrue(d.getTitle().toLowerCase().contains("HTML5".toLowerCase()));
        d.navigate().refresh();
        assertTrue(d.getTitle().toLowerCase().contains("HTML5".toLowerCase()));
        d.navigate().back();
        assertTrue(d.getTitle().toLowerCase().contains("Mozilla".toLowerCase()));
        d.navigate().forward();
        assertTrue(d.getTitle().toLowerCase().contains("HTML5".toLowerCase()));
    }

    @Test
    public void navigateBackWithNoHistory() throws Exception {
        // Fresh Driver (every test gets one)
        WebDriver d = getDriver();

        // Navigate back and forward: should be a no-op, given we haven't loaded anything yet
        d.navigate().back();
        d.navigate().forward();

        // Make sure explicit navigation still works.
        d.get("http://google.com");
    }
}
