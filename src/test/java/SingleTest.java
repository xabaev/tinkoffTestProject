import com.codeborne.selenide.Configuration;
import common.FindByLocators;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.bank.payments.PaymentsPage;
import pages.bank.payments.SuggestBlock;
import pages.bank.payments.categories.communal.CommunalPaymentPage;
import pages.bank.payments.categories.communal.zhkuMoskva.PayZhkuMoskva;
import pages.bank.payments.categories.communal.zhkuMoskva.ZhkuMoskvaPage;
import pages.topPanel.SecondMenu;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class SingleTest {


    @BeforeClass
    public void openStartPage() {
        ChromeDriverManager.getInstance().setup();
        Configuration.browser = "chrome";
        open("https://www.tinkoff.ru");
        getWebDriver().manage().window().maximize();
    }

    @Test
    public void Test1() {
        FindByLocators findElement = new FindByLocators();
        SecondMenu secondMenu = new SecondMenu();
        PaymentsPage paymentsPage = new PaymentsPage();
        CommunalPaymentPage communalPaymentPage = new CommunalPaymentPage();
        ZhkuMoskvaPage zhkuMoskvaPage = new ZhkuMoskvaPage();
        PayZhkuMoskva payZhkuMoskva = new PayZhkuMoskva();
        SuggestBlock suggestBlock = new SuggestBlock();

        secondMenu.openSecondTabByText("Платежи");
        sleep(300);
        paymentsPage.clickPaymentCategory("ЖКХ");
        //если не в %parameterName, то откроем %parameterName.
        if (!communalPaymentPage.getRegionPaymentText().equals("Москве")) {
            communalPaymentPage.clickRegionPayment()
                    .selectRegionFromTable("г. Москва");
        }
        //Сохраним, что у нас первым в списке оплат
        String savePayment = communalPaymentPage.getTextCustomElement(1);
        assertEquals(savePayment, "ЖКУ-Москва");
        communalPaymentPage.clickPaymentOrganization("ЖКУ-Москва");
        //Сохраним урл страницы, что бы потом сравнить
        sleep(1000);
        String previousWebAddress = url();
        //перейдем на страницу таба "Оплатить ЖКУ в москве", и кликтем по кнопке, что бы удостовериться, что мы точно на этой странице
        zhkuMoskvaPage.clickTabByText("Оплатить ЖКУ в Москве");
        findElement.findButtonByText("Оплатить ЖКУ в Москве").click();

        //уйдем обратно на страницу "Платежи"
        //Через "Узнать задолженность за ЖКУ в Москве", т.к. на табе "Оплатить ЖКУ в Москве" нет secondTab
        payZhkuMoskva.clickTabByText("Узнать задолженность за ЖКУ в Москве").
                openSecondTabByText("Платежи");

        //провеим, что по  сохраненному имени платежа ищемтся именно "ЖКУ-Москва"
        assertEquals(paymentsPage
                        .inputPayment(savePayment)
                        .getTextSuggestBlock(1),
                "ЖКУ-Москва");

        suggestBlock.clickSuggestBlock("ЖКУ-Москва");

        //Проверим, что находимся на той же странице, что и в начале теста
        sleep(1000);
        String followingWebAddress = url();
        assertEquals(previousWebAddress, followingWebAddress);

        //Уходим на Платежи, и переходим на регион Санкт-Петербург
        secondMenu.openSecondTabByText("Платежи");
        paymentsPage.clickPaymentCategory("ЖКХ");
        communalPaymentPage.clickRegionPayment()
                .selectRegionFromTable("г. Санкт-Петербург");

        assertFalse($$(By.xpath("//ul[@data-qa-file='UIScrollList']/li/span/a/span[text()]")).texts().contains("ЖКУ-Москва"),
                "На странице г. Санкт-Петербург присутствует ЖКУ-Москва");
    }


    /*    public static Object[] dataForTest() {
            return new List[]{Arrays.asList(new Object[][]{
                    {"123", "25.2018", "15002", "15001", "Поле неправильно заполнено", "Поле заполнено некорректно", "Сумма добровольного страхования не может быть больше итоговой суммы.", "Максимум — 15 000 \u20BD"},
                    {"123", "25.2018", "15002", "9", "Поле неправильно заполнено", "Поле заполнено некорректно", "Сумма добровольного страхования не может быть больше итоговой суммы.", "Минимум — 10 \u20BD"},
                    {"", "25.2018", "15002", "9", "Поле обязательное", "Поле заполнено некорректно", "Сумма добровольного страхования не может быть больше итоговой суммы.", "Минимум — 10 \u20BD"},
                    {"123", "", "15002", "9", "Поле неправильно заполнено", "Поле обязательное", "Сумма добровольного страхования не может быть больше итоговой суммы.", "Минимум — 10 \u20BD"},
                    {"123", "25.2018", "15002", "", "Поле неправильно заполнено", "Поле заполнено некорректно", "Поле обязательное", "Поле обязательное"},
            })};
        }
    */
    @DataProvider(name = "Validation")
    public static Object[][] credentials() {
        return new Object[][]
                {
                        {"123", "25.2018", "15002", "15001", "Поле неправильно заполнено", "Поле заполнено некорректно", "Сумма добровольного страхования не может быть больше итоговой суммы.", "Максимум — 15 000 \u20BD"},
                        {"123", "25.2018", "15002", "9", "Поле неправильно заполнено", "Поле заполнено некорректно", "Сумма добровольного страхования не может быть больше итоговой суммы.", "Минимум — 10 \u20BD"}
                };
    }

        @Test(dataProvider = "Validation")
        public void testValidation (String codePay, String period, String sumInsurance, String sumPay, String
        messageCodePay, String messagePeriod, String messageSumInsurance, String messageSumPay)
        {
            PayZhkuMoskva payZhkuMoskva = new PayZhkuMoskva();
            FindByLocators findElement = new FindByLocators();
            open("https://www.tinkoff.ru/zhku-moskva/oplata/?tab=pay");

            findElement.findButtonByText("Оплатить ЖКУ в Москве").click();

            assertEquals(payZhkuMoskva.getCodePayErrorText(), "Поле обязательное");
            assertEquals(payZhkuMoskva.getPeriodErrorText(), "Поле обязательное");
            assertEquals(payZhkuMoskva.getSumPayErrorText(), "Поле обязательное");

            payZhkuMoskva.setInputByHint("Код плательщика за ЖКУ в Москве", codePay)
                    .setInputByHint("За какой период оплачиваете коммунальные услуги", period)
                    .setInputByHint("Сумма добровольного страхования жилья из квитанции за ЖКУ в Москве", sumInsurance)
                    .setInputByHint("Сумма платежа", sumPay)
                    .clickPay();

            assertEquals(payZhkuMoskva.getErrorByHint("Код плательщика за ЖКУ в Москве"), messageCodePay);
            assertEquals(payZhkuMoskva.getErrorByHint("За какой период оплачиваете коммунальные услуги"), messagePeriod);
            assertEquals(payZhkuMoskva.getErrorByHint("Сумма платежа"), messageSumPay);
            assertEquals(payZhkuMoskva.getErrorByHint("Сумма добровольного страхования жилья из квитанции за ЖКУ в Москве"), messageSumInsurance);

        }
    }