package common;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Методы поиска по локаторам.
 *
 * @author n.khabaev
 * @version 1.0
 */
public class FindByLocators {
    /**
     * @param type - тип элемента (div, span, a)
     * @param text - текст в рамках этого элемента
     * @return найденный элемент
     */
    private SelenideElement findByTypeAndText(final String type,
                                              final String text) {
        return ($(By.xpath("//" + type + "[text()='" + text + "']")));
    }

    /**
     * @param type - тип элемента (div, span, a)
     * @param id   - id элемента
     * @return найденный элемент
     */
    public SelenideElement findByTypeAndID(final String type, final String id) {
        return ($(type + "#" + id));
    }

    /**
     * @param buttonText - текст кнопки
     * @return найденный элемент
     */
    public SelenideElement findButtonByText(final String buttonText) {
        return ($(By.xpath("//*[text() = '" + buttonText + "']/ancestor-or-self::button[@data-qa-file='UIButton']")));
    }
}
