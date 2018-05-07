package common;

import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ScreenShots {

    public void takeEtalonScreenshot(WebDriver driver)
    {
        Screenshot screenshot1 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
    }

    public static ImageDiff makeDiff(Screenshot screenshot1, Screenshot screenshot2) throws IOException {
        ImageDiffer imageDifferWithIgnored = new ImageDiffer();
        //если одинаковые - то возвращает false
        ImageDiff diff = imageDifferWithIgnored.makeDiff(screenshot1, screenshot2);
        //посмотрим, где разница в скринах
        BufferedImage diffImage = diff.getMarkedImage();
        File diffFile = new File(getWebDriver().manage().window().getSize() + "diff.png");
        ImageIO.write(diffImage, "png", diffFile);
        return diff;
    }
}
