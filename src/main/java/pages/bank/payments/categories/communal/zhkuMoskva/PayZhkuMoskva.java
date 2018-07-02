package pages.bank.payments.categories.communal.zhkuMoskva;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByAll;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static org.openqa.selenium.By.xpath;
import static org.testng.Assert.assertEquals;

/**
 * Структура страницы категории ЖКУ-Москва.
 *
 * @author n.khabaev
 * @version 1.0
 */
public class PayZhkuMoskva extends ZhkuMoskvaPage {
    /**
     * Нажатие на "Оплатить".
     */
    private void clickPay() {
        $(xpath("//button[@data-qa-file='UIButton']")).click();
    }

    /**
     * Метод, сетящий передаваемое значение в инпут по хинту инпута.
     * @param hint  хинт инпута на странице
     * @param value значение, которое нужно засетить
     * @return эту же страницу
     */
    public PayZhkuMoskva setInputByHint(final String hint, final String value) {
        SelenideElement input = $(new ByAll(
                xpath("//span[contains(text(),'" + hint + "')]/../../input"),
                xpath("//span[contains(text(),'" + hint + "')]//..//input")));
        actions().click(input).perform();
        input.setValue(value);
        clickPay();
        return this;
    }

    /**
     * Метод, возвращающий текст ошибки под переданным инпутом.
     * Если ошибки нет - вернется null.
     *
     * @param hint хинт инпута
     * @return текст ошибки
     */
    private String getErrorByHint(final String hint) {
        SelenideElement containerErrorMessage = $(By.xpath(".//span[contains(text(), '" + hint + "')]"
                + "//ancestor::div[@data-qa-file='FormFieldWrapper'][1]//div[@data-qa-file='UIFormRowError']"));
        try {
            containerErrorMessage.waitUntil(appear, 3000);
            return containerErrorMessage.getText();
        } catch (Throwable ignore) {
            return null;
        }
    }

    /**
     * Проверим, совпадает ли ожидаемый текст ошибки с ошибкой под инпутом.
     * траливали
     * @param hint      хинт в инпуте, у которогу нужно найти текст ошибки
     * @param textError ожидаемый текст ошибки
     */
    public void assertTextErrorByHint(final String hint, final String textError) {
        assertEquals(textError, getErrorByHint(hint));
    }
}
