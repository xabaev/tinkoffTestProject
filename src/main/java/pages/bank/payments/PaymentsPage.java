package pages.bank.payments;

import org.openqa.selenium.By;
import pages.bank.payments.categories.CategoriesPage;
import pages.bank.payments.categories.communal.CommunalPaymentPage;
import pages.topPanel.SecondMenu;

import static com.codeborne.selenide.Selenide.$;

public class PaymentsPage extends SecondMenu {
    /**
     * @param name - имя платежной организации, которое необходимо ввести в поле поиска
     * @return - блок результатов поиска
     */
    public SuggestBlock inputPayment(String name) {
        $(By.xpath("//div[@data-qa-file = 'StatelessInput']/input")).setValue(name);
        return new SuggestBlock();
    }

    /**
     * @param mobile - телефон, который необходимо ввести в поле мобильного телефона
     */
    public void inputMobile(String mobile) {
        $(By.xpath("//div[@data-qa-file = 'UIPhoneContact']//input")).setValue(mobile);
    }

    public String getPaymentRegion() {
        return $(By.xpath("//span[@data-qa-file = 'PaymentsCatalogHeader'][@role='button']")).getText();
    }

    public CategoriesPage clickAllCategories() {
        $(By.xpath("//span[@data-qa-file = 'Button'][text()='Все категории']")).click();
        return new CategoriesPage();
    }

    public CommunalPaymentPage clickCommunalPayment() {
        $(By.xpath("//span[text()='ЖКХ']")).click();
        return new CommunalPaymentPage();
    }

    public void clickPaymentCategory(String categoryName)
    {
        if ($(By.xpath("//div[@data-qa-file = 'PaymentsPageMenu']//span[text() = '" + categoryName + "']")).isDisplayed()) {
            $(By.xpath("//div[@data-qa-file = 'PaymentsPageMenu']//span[text() = '" + categoryName + "']")).click();
        }
        else
        {
            $(By.xpath("//span[@data-qa-file = 'Button'][text() = 'Все категории']")).click();
            $(By.xpath("//span[text() = '" + categoryName + "']")).click();
        }
    }
}
