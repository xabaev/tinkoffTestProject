import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.bank.payments.categories.communal.CommunalPaymentPage;
import pages.bank.payments.categories.communal.zhkuMoskva.PayZhkuMoskva;
import pages.topPanel.SecondMenu;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class SingleTest {

    @BeforeTest
    public void openStartPage()
    {
        open("https://www.tinkoff.ru");
    }

    @Test
    public void Test1() {
        SecondMenu currentPage = new SecondMenu()
                .tabPayments()
                .clickCommunalPayment();
        if (((CommunalPaymentPage) currentPage).getRegionPaymentText() != "г. Москва") {
            ((CommunalPaymentPage) currentPage).clickRegionPayment()
                    .selectRegionFromTable("г. Москва");
        }
        String savePayment = ((CommunalPaymentPage) currentPage).getTextCustomElement(1);
        assertEquals(savePayment, "ЖКУ-Москва");
        ((CommunalPaymentPage) currentPage).clickZhkuMoskva()
                .clickPayZhku();
    }
}
