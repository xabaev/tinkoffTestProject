package pages.bank.payments;

import org.openqa.selenium.By;
import pages.bank.payments.categories.CategoriesPage;
import pages.bank.payments.categories.communal.CommunalPaymentPage;
import pages.topPanel.SecondMenu;

import static com.codeborne.selenide.Selenide.$;

public class PaymentsPage extends SecondMenu {
    public SuggestBlock inputPayment(String name)
    {
        $(By.xpath("//div[@data-qa-file = 'StatelessInput']/input")).setValue(name);
        return new SuggestBlock();
    }

    public void inputMobile(String mobile)
    {
        $(By.xpath("//div[@data-qa-file = 'UIPhoneContact']//input")).setValue(mobile);
    }

    public String getPaymentRegion()
    {
        return $(By.xpath("//span[@data-qa-file = 'PaymentsCatalogHeader'][@role='button']")).getText();
    }

    public CategoriesPage clickAllCategories()
    {
        $(By.xpath("//span[@data-qa-file = 'Button'][text()='Все категории']")).click();
        return new CategoriesPage();
    }

    public CommunalPaymentPage clickCommunalPayment()
    {
        $(By.xpath("//span[text()='ЖКХ']")).click();
        return new CommunalPaymentPage();
    }
}
