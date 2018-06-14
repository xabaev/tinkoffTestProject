package pages.bank.payments.categories.communal.zhkuMoskva;

import org.openqa.selenium.By;
import pages.bank.payments.categories.communal.CommunalPaymentPage;
import pages.topPanel.SecondMenu;

import static com.codeborne.selenide.Selenide.$;

//страница оплаты ЖКУ-Москва - отличается от class Provider
public class ZhkuMoskvaPage extends SecondMenu {
    public CommunalPaymentPage clickBack() {
        $(By.xpath("//span[@data-qa-node='WrapTag']/div[@data-qa-file = 'BackwardButton']")).click();
        return new CommunalPaymentPage();
    }

    public PayZhkuMoskva clickPayZhku() {
        $(By.xpath("//span[@data-qa-file = 'Tab'][text()='Оплатить ЖКУ в Москве']")).click();
        return new PayZhkuMoskva();
    }

    public SearchDebtMoskva clickSearchDebt() {
        $(By.xpath("//span[@data-qa-file = 'Tab'][text()='Узнать задолженность за ЖКУ в Москве']")).click();
        return new SearchDebtMoskva();
    }

    //TODO: кажется, клик по табу можно вынести в FindByLocators
    public ZhkuMoskvaPage clickTabByText(String tabText)
    {
        $(By.xpath("//li[@data-qa-file='TabContainer']//*[text()='" + tabText + "']")).click();
        return this;
    }
}
