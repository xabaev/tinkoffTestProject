package pages.bank.payments.categories.communal;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import pages.bank.payments.categories.communal.zhkuMoskva.ZhkuMoskvaPage;
import pages.topPanel.SecondMenu;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class CommunalPaymentPage extends SecondMenu {
    public String getRegionPaymentText(){
        return $(By.xpath("//span[@data-qa-file = 'Link']")).getText();
    }

    public RegionsPaymentPage clickRegionPayment(){
        sleep(300);
        $(By.xpath("//span[@data-qa-file = 'Link']")).shouldBe(Condition.visible).click();
        return new RegionsPaymentPage();
    }

     public void clickCustomElement (Integer number) //выберем N-ный элемент из таблицы на странице
     {
         $(By.xpath("//ul[@data-qa-file = 'UIScrollList']/li[" + number + "]")).click();
     }

    public void clickCustomElement (String name) //выберем N-ный элемент из таблицы на странице
    {
        $(By.xpath("//span[text()='" + name + "']")).click();
    }

     public String getTextCustomElement (Integer number) //получим текст N-ного элемента из табоицы на странице
     {
         $(By.xpath("//ul[@data-qa-file = 'UIScrollList']/li[" + number + "]/span[2]/a/span")).should(Condition.visible);
         return $(By.xpath("//ul[@data-qa-file = 'UIScrollList']/li[" + number + "]/span[2]/a/span")).text();
     }

//TODO: какой то костыль, должно быть нормально параметризировано, кажется в class clickCustomElement
    public ZhkuMoskvaPage clickZhkuMoskva () //Нажмем на ЖКУ-Москва
    {
        $(By.xpath("//span[text()='ЖКУ-Москва']")).click();
        return new ZhkuMoskvaPage();
    }

}