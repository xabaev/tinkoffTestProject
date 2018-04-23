package common;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class FindByLocators {
    public static SelenideElement findByTypeAndText(String type, String text) {
        return ($(By.xpath("//" + type + "[text()='" + text + "']")));
    }

    public static SelenideElement findByTypeAndID(String type, String id) {
        return ($(type + "#" + id));
    }
}
