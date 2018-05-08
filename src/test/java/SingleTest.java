import com.codeborne.selenide.Selenide;
import common.ScreenShots;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.bank.payments.SuggestBlock;
import pages.bank.payments.categories.communal.CommunalPaymentPage;
import pages.bank.payments.categories.communal.zhkuMoskva.PayZhkuMoskva;
import pages.bank.payments.categories.communal.zhkuMoskva.ZhkuMoskvaPage;
import pages.topPanel.SecondMenu;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class SingleTest {

    @BeforeTest
    public void openStartPage() {
        open("https://www.tinkoff.ru");
        getWebDriver().manage().window().maximize();
    }

    @Test
    public void Test1() throws IOException {
        CommunalPaymentPage navigatePage = new SecondMenu() //после открытия страницы находимся на главной. Главная не описана, так что SecondTab
                .tabPayments()
                .clickCommunalPayment();
        if (!navigatePage.getRegionPaymentText().equals("г. Москва")) { //если регион не "г. Москва", то откроем такой. ЗЫ: задание написано криво, т.к. "г. Москва" не может быть тут.
            navigatePage.clickRegionPayment()
                    .selectRegionFromTable("г. Москва");
        }

        String savePayment = navigatePage.getTextCustomElement(1); //Сохраним, что у нас первым в списке оплат
        assertEquals(savePayment, "ЖКУ-Москва");

        Object currentPage = navigatePage.clickZhkuMoskva();

        //TODO: явный слип это не хорошо, нужно исправить на неявное ожидание элемента
        sleep(2000);
        //кливнем в угол странице, что бы убрать фокус с поля
        Selenide.actions().moveToElement($(By.xpath("//Body")), 0, 0).click().build().perform();
        //сделаем первый скрин для сравнения старниц
        Screenshot screenshot1 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(50)).takeScreenshot(getWebDriver());
        String previousWebAddress = title();

        currentPage = ((ZhkuMoskvaPage) currentPage).clickPayZhku()
                .clickPay();

        assertEquals(((PayZhkuMoskva) currentPage).getCodePayErrorText(), "Поле обязательное");
        assertEquals(((PayZhkuMoskva) currentPage).getPeriodErrorText(), "Поле обязательное");
        assertEquals(((PayZhkuMoskva) currentPage).getSumPayErrorText(), "Поле обязательное");

        ((PayZhkuMoskva) currentPage).setCodePay("0")
                .setPeriod("0")
                .setInsurance("10")
                .setSumPay("0")
                .clickPay();

        assertEquals(((PayZhkuMoskva) currentPage).getCodePayErrorText(), "Поле неправильно заполнено");
        assertEquals(((PayZhkuMoskva) currentPage).getPeriodErrorText(), "Поле заполнено некорректно");
        assertEquals(((PayZhkuMoskva) currentPage).getSumPayErrorText(), "Минимум — 10 \u20BD");
        assertEquals(((PayZhkuMoskva) currentPage).getInsuranceErrorText(), "Сумма добровольного страхования не может быть больше итоговой суммы.");

        currentPage = ((PayZhkuMoskva) currentPage).clickSearchDebt() //А вот тут ошибка на странице - нет Подменю с Платежами на странице "Оплатить ЖКУ в Москве"
                .tabPayments()
                .inputPayment(savePayment);

        assertEquals(((SuggestBlock) currentPage).getTextSuggestBlock(1), "ЖКУ-Москва");

        ((SuggestBlock) currentPage).clickSuggestBlock("ЖКУ-Москва");


        //TODO: янвый слип это не хорошо, нужно исправить на неявное ожидание элемента
        sleep(2000);
        //кликнем в угол страницы, что бы убрать фокус с поля
        Selenide.actions().moveToElement($(By.xpath("//Body")), 0, 0).click().build().perform();
        String followingWebAddress = title();
        //скриним окно второй раз, для сравнения
        Screenshot screenshot2 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(50)).takeScreenshot(getWebDriver());
        //дифф скриншотов
        assertFalse(ScreenShots.makeDiff(screenshot1, screenshot2).hasDiff());
        assertEquals(previousWebAddress, followingWebAddress);

        ((SuggestBlock) currentPage).tabPayments()
                .clickCommunalPayment()
                .clickRegionPayment()
                .selectRegionFromTable("г. Санкт-Петербург");

        assertFalse($$(By.xpath("//ul[@data-qa-file='UIScrollList']/li/span/a/span[text()]")).texts().contains("ЖКУ-Москва"), "На странице г. Санкт-Петербург присутствует ЖКУ-Москва");
    }
}
