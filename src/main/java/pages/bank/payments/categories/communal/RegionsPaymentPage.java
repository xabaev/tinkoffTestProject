package pages.bank.payments.categories.communal;

import org.openqa.selenium.By;
import pages.topPanel.SecondMenu;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Структура страницы рениона платежей.
 *
 * @author n.khabaev
 * @version 1.0
 */
public class RegionsPaymentPage extends SecondMenu {
    /**
     * @param region - Выберем регион из списка регионов на странице
     * @return - страницу коммунальных платежей.
     * Для каждой категории платежей должен быть свой RegionsPaymentPage.
     */
    //TODO добавить ловлю исключения.
    public CommunalPaymentPage selectRegionFromTable(String region) {
        sleep(300);
        $(By.xpath("//div[@data-qa-file = 'UIRegions']//*[text()='" + region + "'][@data-qa-file='UILink']")).click();
        return new CommunalPaymentPage();
    }
}
