package pages.bank.payments.categories.communal.zhkuMoskva;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class PayZhkuMoskva extends ZhkuMoskvaPage {
    //TODO: сделать метод, динамически возвращающий элемент по тексту подсказки
    public PayZhkuMoskva setCodePay(String codePay) {
        $(By.xpath("//input[@name = 'provider-payerCode']")).setValue(codePay);
        return this;
    }

    public PayZhkuMoskva setPeriod(String codePay) {
        $(By.xpath("//input[@name = 'provider-period']")).setValue(codePay);
        return this;
    }

    public PayZhkuMoskva setInsurance(String codePay) {
        $(By.xpath("//span[contains(text(), 'Сумма добровольного страхования жилья')]/../div/input")).setValue(codePay);
        return this;
    }

    public PayZhkuMoskva setSumPay(String sumPay) {
        $(By.xpath("//div[@class = 'ui-form__fieldset ui-form__fieldset_inline ui-form__row_amount']")).click();
        $(By.xpath("//span[contains(text(), 'Сумма платежа')]/../div/input")).setValue(sumPay);
        return this;
    }

    public String getCodePayErrorText() {
        return $(By.xpath("//label[@for='payerCode']/../../div[@data-qa-file='UIFormRowError']")).getText();
    }

    public String getPeriodErrorText() {
        return $(By.xpath("//label[@for='period']/../../../div[@data-qa-file='UIFormRowError']")).getText();
    }

    public String getInsuranceErrorText() {
        return $(By.xpath("//span[contains(text(), 'Сумма добровольного страхования жилья')]/../../../div[@data-qa-file='UIFormRowError']")).getText();
    }

    public String getSumPayErrorText() {
        return $(By.xpath("//span[contains(text(), 'Сумма платежа')]/../../../div[@data-qa-file='UIFormRowError']")).getText();
    }

    public PayZhkuMoskva clickPay() {
        $(By.xpath("//button[@data-qa-file='UIButton']")).click();
        return this;
    }

}