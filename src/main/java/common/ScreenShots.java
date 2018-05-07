package common;

import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ScreenShots {

    public void takeEtalonScreenshot(WebDriver driver)
    {
        Screenshot screenshot1 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
    }
}
