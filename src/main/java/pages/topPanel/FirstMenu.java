package pages.topPanel;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class FirstMenu {
    public void clickBank() {
        $(By.xpath("//span[@data-qa-file='FirstMenu'][contains(text(),'Банк')]")).click();
    }

}
