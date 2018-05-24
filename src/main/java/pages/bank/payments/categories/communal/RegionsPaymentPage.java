package pages.bank.payments.categories.communal;

import org.openqa.selenium.By;
import pages.topPanel.SecondMenu;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class RegionsPaymentPage extends SecondMenu {
    public CommunalPaymentPage clickReturn() {
        $(By.xpath("//div[@data-qa-file = 'BackButton'][text()='Назад']")).click();
        return new CommunalPaymentPage();
    }

    //TODO: добавить ловлю исключения.

    /**
     * @param region - Выберем регион из списка регионов на странице
     * @return - страницу коммунальных платежей. Для каждой категории платежей должен быть свой RegionsPaymentPage.
     */
    public CommunalPaymentPage selectRegionFromTable(String region)
    {
        sleep(300);
        $(By.xpath("//div[@data-qa-file = 'UIRegions']//*[text()='" + region + "'][@data-qa-file='Text']")).click();
        return new CommunalPaymentPage();
    }
}
