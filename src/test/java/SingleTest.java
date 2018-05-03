import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.bank.payments.categories.communal.CommunalPaymentPage;
import pages.bank.payments.categories.communal.zhkuMoskva.PayZhkuMoskva;
import pages.topPanel.SecondMenu;

import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;

public class SingleTest {

    @BeforeTest
    public void openStartPage()
    {
        open("https://www.tinkoff.ru");
    }

    @Test
    public void Test1() {
        CommunalPaymentPage navigatePage = new SecondMenu() //после открытия страницы находимся на главной. Главная не описана, так что SecondTab
                .tabPayments()
                .clickCommunalPayment();
        if (navigatePage.getRegionPaymentText() != "г. Москва") { //если регион не "г. Москва", то откроем такой. ЗЫ: задание написано криво, т.к. "г. Москва" не может быть тут.
            navigatePage.clickRegionPayment()
                    .selectRegionFromTable("г. Москва");
        }
        String savePayment = navigatePage.getTextCustomElement(1); //Сохраним, что у нас первым в списке оплат
        assertEquals(savePayment, "ЖКУ-Москва");
        Object currentPage = navigatePage.clickZhkuMoskva()//Продолжаем открывать странички
                .clickPayZhku()
                .clickPay();
        assertEquals(((PayZhkuMoskva) currentPage).getCodePayErrorText(),"Поле обязательное");
        assertEquals(((PayZhkuMoskva) currentPage).getPeriodErrorText(),"Поле обязательное");
        assertEquals(((PayZhkuMoskva) currentPage).getSumPayErrorText(),"Поле обязательное");
        ((PayZhkuMoskva) currentPage).setCodePay("0").setPeriod("0").setSumPay("0").setInsurance("10");
        ((PayZhkuMoskva) currentPage).clickPay();
        assertEquals(((PayZhkuMoskva) currentPage).getCodePayErrorText(),"Поле неправильно заполнено");
        assertEquals(((PayZhkuMoskva) currentPage).getPeriodErrorText(),"Поле заполнено некорректно");
        assertEquals(((PayZhkuMoskva) currentPage).getSumPayErrorText(),"Минимум — 10 \u20BD");
        assertEquals(((PayZhkuMoskva) currentPage).getInsuranceErrorText(),"Сумма добровольного страхования не может быть больше итоговой суммы.");
    }
}
