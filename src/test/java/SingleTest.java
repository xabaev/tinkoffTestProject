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
        SecondMenu currentPage = new SecondMenu() //после открытия страницы находимся на главной. Главная не описана, так что SecondTab
                .tabPayments()
                .clickCommunalPayment();
        if (((CommunalPaymentPage) currentPage).getRegionPaymentText() != "г. Москва") { //если регион не "г. Москва", то откроем такой. ЗЫ: задание написано криво, т.к. "г. Москва" не может быть тут.
            ((CommunalPaymentPage) currentPage).clickRegionPayment()
                    .selectRegionFromTable("г. Москва");
        }
        String savePayment = ((CommunalPaymentPage) currentPage).getTextCustomElement(1); //Сохраним, что у нас первым в списке оплат
        assertEquals(savePayment, "ЖКУ-Москва");
        ((CommunalPaymentPage) currentPage).clickZhkuMoskva()//Продолжаем открывать странички
                .clickPayZhku()
                .clickPay();
    }
}
