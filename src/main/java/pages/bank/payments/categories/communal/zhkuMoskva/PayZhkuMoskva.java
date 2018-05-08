package pages.bank.payments.categories.communal.zhkuMoskva;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class PayZhkuMoskva extends ZhkuMoskvaPage {
    /**
     * @param codePay - 10-значный код плательщика
     * @return - эту же страницу
     */
    //TODO: сделать метод, динамически возвращающий элемент по тексту подсказки
    public PayZhkuMoskva setCodePay(String codePay) {
        $(By.xpath("//input[@name = 'provider-payerCode']")).setValue(codePay);
        return this;
    }

    /**
     *
     * @param period - период платежа в формате ММ.ГГГГ
     * @return - эту же страницу
     */
    public PayZhkuMoskva setPeriod(String period) {
        $(By.xpath("//input[@name = 'provider-period']")).setValue(period);
        return this;
    }

    /**
     *
     * @param sumInsurance - сумма страхования жилья - не может быть больше суммы платежа
     * @return - эту же страницу
     */
    public PayZhkuMoskva setInsurance(String sumInsurance) {
        $(By.xpath("//span[contains(text(), 'Сумма добровольного страхования жилья')]/../div/input")).setValue(sumInsurance);
        return this;
    }

    /**
     *
     * @param sumPay - сумма платежа - не может быть меньше 10р, или меньше суммы страхования
     * @return - эту же страницу
     */
    public PayZhkuMoskva setSumPay(String sumPay) {
        $(By.xpath("//div[@class = 'ui-form__fieldset ui-form__fieldset_inline ui-form__row_amount']")).click();
        $(By.xpath("//span[contains(text(), 'Сумма платежа')]/../div/input")).setValue(sumPay);
        return this;
    }

    /**
     * Возвращает текст ошибки, которая находится под блоком "Код плательщика за ЖКУ в Москве"
     * @return - текст ошибки
     */
    public String getCodePayErrorText() {
        return $(By.xpath("//label[@for='payerCode']/../../div[@data-qa-file='UIFormRowError']")).getText();
    }

    /**
     * Возвращает текст ошибки, которая находится под блоком "Период"
     * @return - текст ошибки
     */
    public String getPeriodErrorText() {
        return $(By.xpath("//label[@for='period']/../../../div[@data-qa-file='UIFormRowError']")).getText();
    }

    /**
     * Возвращает текст ошибки, которая находится под блоком "Сумма добровольного страхования"
     * @return - текст ошибки
     */
    public String getInsuranceErrorText() {
        return $(By.xpath("//span[contains(text(), 'Сумма добровольного страхования жилья')]/../../../div[@data-qa-file='UIFormRowError']")).getText();
    }

    /**
     * Возвращает текст ошибки, которая находится под блоком "Сумма платежа"
     * @return - текст ошибки
     */
    public String getSumPayErrorText() {
        return $(By.xpath("//span[contains(text(), 'Сумма платежа')]/../../../div[@data-qa-file='UIFormRowError']")).getText();
    }

    /**
     * Нажатие на "Оплатить"
     * @return - эту же страницу.
     */
    //TODO: Нужно возвращать не эту же страницу, а следующую в рамках платежа. Для негативных проверок продуамть другой метод.
    public PayZhkuMoskva clickPay() {
        $(By.xpath("//button[@data-qa-file='UIButton']")).click();
        return this;
    }

}