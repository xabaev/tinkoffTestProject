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

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static java.util.Map.entry;
import static java.util.Map.ofEntries;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

/**
 * Класс тестового теста
 */
public class SingleTest {
    /**
     * Откроем тут браузер
     * и страницу тинькоффа
     * Откроем на весь экран
     */
    @BeforeClass
    public void openStartPage() {
        ChromeDriverManager.getInstance().setup();
        Configuration.browser = "chrome";
        open("https://www.tinkoff.ru");
        getWebDriver().manage().window().maximize();
    }

    /**
     * Тест проверяет переход по страницам
     * возвращение на ту же страницу
     * наличие или отсутствие нужной Платежной организации
     */
    @Test
    public void test1() {
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

    /**
     * Датапровайдер для генерации данных
     *
     * @return объект с мапами, в которых
     * Первая мапа - хит инпута и значение инпута
     * Вторая мапа - хит инпута и значение валидации под ним
     */
    @DataProvider(name = "Validation")
    public static Object[][] validation() {
        return new Object[][]{
                {ofEntries(entry("Код плательщика за ЖКУ в Москве", "555")),
                        ofEntries(entry("Код плательщика за ЖКУ в Москве", "Поле неправильно заполнено"))},
                {ofEntries(entry("За какой период оплачиваете коммунальные услуги", "00.0000")),
                        ofEntries(entry("За какой период оплачиваете коммунальные услуги", "Поле заполнено некорректно"))},
                {ofEntries(entry("Сумма платежа", "1")),
                        ofEntries(entry("Сумма платежа", "Минимум — 10 \u20BD"))},
                {ofEntries(entry("Сумма платежа", "151001")),
                        ofEntries(entry("Сумма платежа", "Максимум — 15 000 \u20BD"))},
                {ofEntries(entry("Сумма платежа", "15001"),
                        entry("Сумма добровольного страхования жилья из квитанции за ЖКУ в Москве", "15555")),
                        ofEntries(entry("Сумма добровольного страхования жилья из квитанции за ЖКУ в Москве", "Сумма добровольного страхования не может быть больше итоговой суммы."))},
                {ofEntries(entry("Код плательщика за ЖКУ в Москве", "")),
                        ofEntries(entry("Код плательщика за ЖКУ в Москве", "Поле обязательное"))},
                {ofEntries(entry("За какой период оплачиваете коммунальные услуги", "")),
                        ofEntries(entry("За какой период оплачиваете коммунальные услуги", "Поле обязательное"))},
                {ofEntries(entry("Сумма платежа", "")),
                        ofEntries(entry("Сумма платежа", "Поле обязательное"))}};
    }

    /**
     * Ьесь га проверку валидации на странице Платежей
     *
     * @param inputs мапа значений инпутов в структуре - <хит инпута; значение инпута>
     * @param errors мапа валидации инпутов в структуре - <хит инпута; ошибка под инпутом>
     */
    @Test(dataProvider = "Validation")
    public void testValidation(final Map<String, String> inputs, final Map<String, String> errors) {
        PayZhkuMoskva payZhkuMoskva = new PayZhkuMoskva();
        open("https://www.tinkoff.ru/zhku-moskva/oplata/?tab=pay");
        inputs.forEach(payZhkuMoskva::setInputByHint);
        errors.forEach(payZhkuMoskva::assertTextErrorByHint);
    }
}
