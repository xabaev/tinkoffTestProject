package common;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class FindByLocators {
    public SelenideElement findByTypeAndText(String type, String text) {
        return ($(By.xpath("//" + type + "[text()='" + text + "']")));
    }

    public SelenideElement findByTypeAndID(String type, String id) {
        return ($(type + "#" + id));
    }

    public SelenideElement findButtonByText(String buttonText)
    {
        return ($(By.xpath("//*[text() = '" + buttonText + "']/ancestor-or-self::button[@data-qa-file='UIButton']")));
    }
}