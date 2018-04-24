package pages.bank.payments.categories.communal;

import org.openqa.selenium.By;
import pages.topPanel.SecondMenu;

import static com.codeborne.selenide.Selenide.$;

public class RegionsPaymentPage extends SecondMenu {
    public CommunalPaymentPage clickReturn()
    {
        $(By.xpath("//div[@data-qa-file = 'BackButton'][text()='Назад']")).click();
        return new CommunalPaymentPage();
    }

    //TODO: добавить ловлю исключения.
    public CommunalPaymentPage selectRegionFromTable(String region) //Выберем регион из списка на странице
    {
        $(By.xpath("//span[@data-qa-file = 'UILink'][text()='" + region + "']")).click();
        return new CommunalPaymentPage();
    }
}
