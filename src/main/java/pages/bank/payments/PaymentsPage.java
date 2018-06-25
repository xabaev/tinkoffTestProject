package pages.bank.payments;

import org.openqa.selenium.By;
import pages.topPanel.SecondMenu;

import static com.codeborne.selenide.Selenide.$;
/**
 * Структура страницы Платежей.
 *
 * @author n.khabaev
 * @version 1.0
 */
public class PaymentsPage extends SecondMenu {
    /**
     * @param name - имя платежной организации, которое необходимо ввести в поле поиска
     * @return - блок результатов поиска
     */
    public SuggestBlock inputPayment(final String name) {
        $(By.xpath("//div[@data-qa-file = 'StatelessInput']/input")).setValue(name);
        return new SuggestBlock();
    }

    /**
     * Откроем переданную категорию
     * Если категории нет - выберем ее из "Все категории"
     * @param categoryName имя категории, которое нужно открыть
     */
    public void clickPaymentCategory(final String categoryName) {
        if ($(By.xpath("//div[@data-qa-file = 'PaymentsPageMenu']//span[text() = '" + categoryName + "']")).isDisplayed()) {
            $(By.xpath("//div[@data-qa-file = 'PaymentsPageMenu']//span[text() = '" + categoryName + "']")).click();
        } else {
            $(By.xpath("//span[@data-qa-file = 'Button'][text() = 'Все категории']")).click();
            $(By.xpath("//span[text() = '" + categoryName + "']")).click();
        }
    }
}
