import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import common.ScreenShots;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.bank.payments.PaymentsPage;
import pages.bank.payments.SuggestBlock;
import pages.bank.payments.categories.communal.CommunalPaymentPage;
import pages.bank.payments.categories.communal.zhkuMoskva.PayZhkuMoskva;
import pages.bank.payments.categories.communal.zhkuMoskva.SearchDebtMoskva;
import pages.bank.payments.categories.communal.zhkuMoskva.ZhkuMoskvaPage;
import pages.topPanel.SecondMenu;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import java.io.IOException;

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
    public void Test1() throws IOException {
        SecondMenu secondMenu = new SecondMenu();//после открытия страницы находимся на главной. Главная не описана, так что SecondTab
        PaymentsPage paymentsPage = new PaymentsPage();
        CommunalPaymentPage communalPaymentPage = new CommunalPaymentPage();
        ZhkuMoskvaPage zhkuMoskvaPage = new ZhkuMoskvaPage();
        PayZhkuMoskva payZhkuMoskva = new PayZhkuMoskva();
        SearchDebtMoskva searchDebtMoskva = new SearchDebtMoskva();
        SuggestBlock suggestBlock = new SuggestBlock();

        secondMenu.openSecondTabByText("Платежи");
        paymentsPage.clickPaymentCategory("ЖКХ");

        if (!communalPaymentPage.getRegionPaymentText().equals("Москве")) { //если не в москве, то откроем такой.
            communalPaymentPage.clickRegionPayment()
                    .selectRegionFromTable("г. Москва");
        }

        String savePayment = communalPaymentPage.getTextCustomElement(1); //Сохраним, что у нас первым в списке оплат
        assertEquals(savePayment, "ЖКУ-Москва");

        communalPaymentPage.clickPaymentOrganization("ЖКУ-Москва");

        //TODO: явный слип это не хорошо, нужно исправить на неявное ожидание элемента
        sleep(2000);
        //кливнем в угол странице, что бы убрать фокус с поля
        Selenide.actions().moveToElement($(By.xpath("//Body")), 0, 0).click().build().perform();
        //сделаем первый скрин для сравнения старниц
        Screenshot screenshot1 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(50)).takeScreenshot(getWebDriver());
        String previousWebAddress = url();

        zhkuMoskvaPage.clickTabByText("Оплатить ЖКУ в Москве");
        payZhkuMoskva.clickPay();

        assertEquals(payZhkuMoskva.getCodePayErrorText(), "Поле обязательное");
        assertEquals(payZhkuMoskva.getPeriodErrorText(), "Поле обязательное");
        assertEquals(payZhkuMoskva.getSumPayErrorText(), "Поле обязательное");

        payZhkuMoskva.setCodePay("0")
                .setPeriod("0")
                .setInsurance("10")
                .setSumPay("0")
                .clickPay();

        assertEquals(payZhkuMoskva.getCodePayErrorText(), "Поле неправильно заполнено");
        assertEquals(payZhkuMoskva.getPeriodErrorText(), "Поле заполнено некорректно");
        assertEquals(payZhkuMoskva.getSumPayErrorText(), "Минимум — 10 \u20BD");
        assertEquals(payZhkuMoskva.getInsuranceErrorText(), "Сумма добровольного страхования не может быть больше итоговой суммы.");

        payZhkuMoskva.clickSearchDebt().openSecondTabByText("Платежи");
        paymentsPage.inputPayment(savePayment);

        assertEquals(paymentsPage
                        .inputPayment(savePayment)
                        .getTextSuggestBlock(1),
                "ЖКУ-Москва");

        suggestBlock.clickSuggestBlock("ЖКУ-Москва");

        //TODO: янвый слип это не хорошо, нужно исправить на неявное ожидание элемента
        sleep(2000);
        //кликнем в угол страницы, что бы убрать фокус с поля
        Selenide.actions().moveToElement($(By.xpath("//Body")), 0, 0).click().build().perform();
        String followingWebAddress = url();
        //скриним окно второй раз, для сравнения
        Screenshot screenshot2 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(50)).takeScreenshot(getWebDriver());
        //дифф скриншотов
        assertFalse(ScreenShots.makeDiff(screenshot1, screenshot2).hasDiff(), "Скриншоты страниц " + followingWebAddress + " не совпадают. Смотри файл "
                + getWebDriver().manage().window().getSize() + "diff.png");
        assertEquals(previousWebAddress, followingWebAddress);

        secondMenu.openSecondTabByText("Платежи");
        paymentsPage.clickPaymentCategory("ЖКХ");
        communalPaymentPage.clickRegionPayment()
                .selectRegionFromTable("г. Санкт-Петербург");

        assertFalse($$(By.xpath("//ul[@data-qa-file='UIScrollList']/li/span/a/span[text()]")).texts().contains("ЖКУ-Москва"), "На странице г. Санкт-Петербург присутствует ЖКУ-Москва");
    }

    @Test
    public void testTest() {
        open("https://www.tinkoff.ru/zhku-moskva/oplata/");
        $(By.linkText("Оплатить ЖКУ в Москве")).click();

    }
}