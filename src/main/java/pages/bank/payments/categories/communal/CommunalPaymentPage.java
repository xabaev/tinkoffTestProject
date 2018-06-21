package pages.bank.payments.categories.communal;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import pages.topPanel.SecondMenu;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Структура страницы комунальных платежей.
 *
 * @author n.khabaev
 * @version 1.0
 */
public class CommunalPaymentPage extends SecondMenu {
    /**
     * Получаем регион, в котором открылась страница
     *
     * @return текст региона
     */
    public String getRegionPaymentText() {
        return $(By.xpath("//span[@data-qa-file = 'Link']")).getText();
    }

    /**
     * sleep нужно для подгрузки страницы, иначе клик происходил раньше,
     * чем страница могла отрабортать этот клик.
     * TODO это не совсем правильно, нужно продумать, как заменить на ожидание элемента
     *
     * @return - страница с выбором региона
     */
    public RegionsPaymentPage clickRegionPayment() {
        sleep(500);
        $(By.xpath("//span[@data-qa-file = 'Link']"))
                .shouldBe(Condition.visible)
                .click();
        return new RegionsPaymentPage();
    }

    /**
     * @param number - выберем @number - ный элемент из таблицы на странице
     */
    public void clickCustomElement(final Integer number) {
        $(By.xpath("//ul[@data-qa-file = 'UIScrollList']/li[" + number + "]")).click();
    }

    /**
     * @param name - выберем элемент c @name - текстом из таблицы на странице
     */
    public void clickCustomElement(final String name) {
        $(By.xpath("//span[text()='" + name + "']")).click();
    }

    /**
     * @param number - получим текст N-ного элемента из таблицы на странице
     * @return - текст N-ного элемента из таблицы на странице
     */
    public String getTextCustomElement(final Integer number) {
        $(By.xpath("//ul[@data-qa-file = 'UIScrollList']/li[" + number + "]/span[2]/a/span"))
                .should(Condition.visible);
        return $(By.xpath("//ul[@data-qa-file = 'UIScrollList']/li[" + number + "]/span[2]/a/span")).text();
    }

    /**
     * @param paymentOrganization - имя платежной организации на странице
     */
    //TODO работает только если организация есть на первом массиве элементов.
    // Но можно спуститься ниже, и будет подгрузка следующих элементов.
    public void clickPaymentOrganization(final String paymentOrganization) {
        $(By.xpath("//ul//span[text()='" + paymentOrganization + "']")).click();
    }
}
