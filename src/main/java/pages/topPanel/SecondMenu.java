package pages.topPanel;

import org.openqa.selenium.By;
import pages.bank.credit.CreditPage;
import pages.bank.creditCards.CreditCardsPage;
import pages.bank.debitCards.DebitCardsPage;
import pages.bank.deposit.DepositPage;
import pages.bank.mortgage.MontgagePage;
import pages.bank.payments.PaymentsPage;

import static com.codeborne.selenide.Selenide.$;

/**
 * Структура страницы подменю.
 *
 * @author n.khabaev
 * @version 1.0
 */
public class SecondMenu extends FirstMenu {
    /**
     *
     * @return страницу кредитных карт
     */
    public CreditCardsPage tabCreditCards() {
        $(By.xpath("//span[contains(text(), 'Кредитные карты')][@data-qa-file = 'SecondMenu']")).click();
        return new CreditCardsPage();
    }
    /**
     *
     * @return страницу дебетовых карт
     */
    public DebitCardsPage tabDebitCards() {
        $(By.xpath("//span[contains(text(), 'Дебетовые карты')][@data-qa-file = 'SecondMenu']")).click();
        return new DebitCardsPage();
    }
    /**
     *
     * @return страницу кредитов
     */
    public CreditPage tabCredit() {
        $(By.xpath("//span[contains(text(), 'Кредит')][@data-qa-file = 'SecondMenu']")).click();
        return new CreditPage();
    }
    /**
     *
     * @return страницу депозитов
     */
    public DepositPage tabDeposit() {
        $(By.xpath("//span[contains(text(), 'Депозит')][@data-qa-file = 'SecondMenu']")).click();
        return new DepositPage();
    }
    /**
     *
     * @return страницу ипотеки
     */
    public MontgagePage tabMontgage() {
        $(By.xpath("//span[contains(text(), 'Ипотека')][@data-qa-file = 'SecondMenu']")).click();
        return new MontgagePage();
    }
    /**
     *
     * @return страницу платежей
     */
    public PaymentsPage tabPayments() {
        $(By.xpath("//span[contains(text(), 'Платежи')][@data-qa-file = 'SecondMenu']")).click();
        return new PaymentsPage();
    }

    /**
     *
     * @param tabText текст, по которому открыть страницу
     */
    public void openSecondTabByText(final String tabText) {
        $(By.xpath("//span[text() = '" + tabText + "'][@data-qa-file = 'SecondMenu']")).click();
    }
}
