import com.codeborne.selenide.Selenide;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.bank.payments.categories.communal.CommunalPaymentPage;
import pages.bank.payments.categories.communal.zhkuMoskva.PayZhkuMoskva;
import pages.bank.payments.*;
import pages.bank.payments.categories.communal.zhkuMoskva.ZhkuMoskvaPage;
import pages.topPanel.SecondMenu;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.sleep;
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
        if (navigatePage.getRegionPaymentText() != "г. Москва") { //если регион не "г. Москва", то откроем такой. ЗЫ: задание написано криво, т.к. "г. Москва" не может быть тут.
            navigatePage.clickRegionPayment()
                    .selectRegionFromTable("г. Москва");
        }
        String savePayment = navigatePage.getTextCustomElement(1); //Сохраним, что у нас первым в списке оплат
        assertEquals(savePayment, "ЖКУ-Москва");
        Object currentPage = navigatePage.clickZhkuMoskva();
        //TODO: явный слип это не хорошо, нужно исправить
        sleep(2000);
        //сделаем первый скрин для сравнения  старниц
        Screenshot screenshot1 = new AShot().takeScreenshot(getWebDriver());
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
        //скриним окно второй раз, для сравнения
        //TODO: янвый слип это не хорошо, нужно исправить
        sleep(2000);
        Screenshot screenshot2 = new AShot().takeScreenshot(getWebDriver());
        //дифф скриншотов
        ImageDiffer imageDifferWithIgnored = new ImageDiffer();
        //если одинаковые - то возвращает false
        ImageDiff diff = imageDifferWithIgnored.makeDiff(screenshot1, screenshot2);
        //посмотрим, где разница в скринах
        BufferedImage diffImage = diff.getMarkedImage();
        File outputfile = new File("saved.png");
        ImageIO.write(diffImage, "png", outputfile);
        assertFalse(diff.hasDiff());
    }
}
