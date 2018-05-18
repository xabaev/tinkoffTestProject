package pages.topPanel;

import org.openqa.selenium.By;
import pages.bank.credit.CreditPage;
import pages.bank.creditCards.CreditCardsPage;
import pages.bank.debitCards.DebitCardsPage;
import pages.bank.deposit.DepositPage;
import pages.bank.mortgage.MontgagePage;
import pages.bank.payments.PaymentsPage;

import static com.codeborne.selenide.Selenide.$;

public class SecondMenu extends FirstMenu {
    public CreditCardsPage tabCreditCards() {
        $(By.xpath("//span[contains(text(), 'Кредитные карты')][@data-qa-file = 'SecondMenu']")).click();
        return new CreditCardsPage();
    }

    public DebitCardsPage tabDebitCards() {
        $(By.xpath("//span[contains(text(), 'Дебетовые карты')][@data-qa-file = 'SecondMenu']")).click();
        return new DebitCardsPage();
    }

    public CreditPage tabCredit() {
        $(By.xpath("//span[contains(text(), 'Кредит')][@data-qa-file = 'SecondMenu']")).click();
        return new CreditPage();
    }

    public DepositPage tabDeposit() {
        $(By.xpath("//span[contains(text(), 'Депозит')][@data-qa-file = 'SecondMenu']")).click();
        return new DepositPage();
    }

    public MontgagePage tabMontgage() {
        $(By.xpath("//span[contains(text(), 'Ипотека')][@data-qa-file = 'SecondMenu']")).click();
        return new MontgagePage();
    }

    public PaymentsPage tabPayments() {
        $(By.xpath("//span[contains(text(), 'Платежи')][@data-qa-file = 'SecondMenu']")).click();
        return new PaymentsPage();
    }

    public void openSecondTab(String tabText) {
        $(By.xpath("//span[contains(text(), "+ tabText +")][@data-qa-file = 'SecondMenu']")).click();
    }

}
