package pages.bank.payments.categories.communal;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import pages.bank.payments.categories.communal.zhkuMoskva.ZhkuMoskvaPage;
import pages.topPanel.SecondMenu;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class CommunalPaymentPage extends SecondMenu {
    public String getRegionPaymentText() {
        return $(By.xpath("//span[@data-qa-file = 'Link']")).getText();
    }

    /**
     * sleep нужно для подгрузки страницы, иначе клик происходил раньше, чем страница могла отрабортать этот клик.
     * TODO: это не совсем правильно, нужно продумать, как заменить на ожидание элемента
     * @return - страница с выбором региона
     */
    public RegionsPaymentPage clickRegionPayment() {
        sleep(500);
        $(By.xpath("//span[@data-qa-file = 'Link']")).shouldBe(Condition.visible).click();
        return new RegionsPaymentPage();
    }

    /**
     * @param number - выберем @number - ный элемент из таблицы на странице
     */
    public void clickCustomElement(Integer number) //
    {
        $(By.xpath("//ul[@data-qa-file = 'UIScrollList']/li[" + number + "]")).click();
    }

    /**
     *
     * @param name - выберем элемент c @name - текстом из таблицы на странице
     */
    public void clickCustomElement(String name)
    {
        $(By.xpath("//span[text()='" + name + "']")).click();
    }

    /**
     * @param number - получим текст N-ного элемента из таблицы на странице
     * @return - текст N-ного элемента из таблицы на странице
     */
    public String getTextCustomElement(Integer number)
    {
        $(By.xpath("//ul[@data-qa-file = 'UIScrollList']/li[" + number + "]/span[2]/a/span")).should(Condition.visible);
        return $(By.xpath("//ul[@data-qa-file = 'UIScrollList']/li[" + number + "]/span[2]/a/span")).text();
    }

    /**
     * Нажмем на ЖКУ-Москва
     * @return - страница ЖКУ-Москва
     */
    //TODO: какой то костыль, должно быть нормально параметризировано, кажется, в class clickCustomElement
    public ZhkuMoskvaPage clickZhkuMoskva()
    {
        $(By.xpath("//span[text()='ЖКУ-Москва']")).click();
        return new ZhkuMoskvaPage();
    }

    /**
     *
     * @param paymentOrganization - имя платежной организации на странице
     */
    //TODO: работает только если организация есть на первом массиве элементов. Но можно спуститься ниже, и будет подгрузка следующих элементов.
    public void clickPaymentOrganization(String paymentOrganization)
    {
        $(By.xpath("//ul//span[text()='" + paymentOrganization + "']")).click();
    }

}