package pages.bank.payments.categories.communal.zhkuMoskva;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class PayZhkuMoskva extends ZhkuMoskvaPage {

    public PayZhkuMoskva setCodePay(String codePay)
    {
        $(By.xpath("//input[@name = 'provider-payerCode']")).setValue(codePay);
        return this;
    }

    public PayZhkuMoskva setPeriod(String codePay)
    {
        $(By.xpath("//input[@name = 'provider-period']")).setValue(codePay);
        return this;
    }

    public PayZhkuMoskva setInsurance(String codePay)
    {
        $(By.xpath("//span[contains(text(), 'Сумма добровольного страхования жилья')]/../div/input")).setValue(codePay); //TODO: стоит ли завязываться на текст подсказки?
        return this;
    }

    public PayZhkuMoskva setPay(String sumPay)
    {
        $(By.xpath("//span[contains(text(), 'Сумма платежа')]/../div/input")).setValue(sumPay);//TODO: стоит ли завязываться на текст подсказки?
        return this;
    }

    public void codePayError()
    {

    }

    public void periodError()
    {

    }
}