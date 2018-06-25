package pages.bank.payments.categories.communal.zhkuMoskva;

import org.openqa.selenium.By;
import pages.topPanel.SecondMenu;

import static com.codeborne.selenide.Selenide.$;

/**
 * Страница оплаты ЖКУ-Москва - отличается от class Provider.
 *
 * @author n.khabaev
 * @version 1.0
 */
public class ZhkuMoskvaPage extends SecondMenu {
    /**
     *
     * @param tabText - текст на табе, на которую надо нажать
     * @return эту же страницу
     */
    //TODO кажется, клик по табу можно вынести в FindByLocators
    public ZhkuMoskvaPage clickTabByText(final String tabText) {
        $(By.xpath("//li[@data-qa-file='TabContainer']//*[text()='" + tabText + "']")).click();
        return this;
    }
}
