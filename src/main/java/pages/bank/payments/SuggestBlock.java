package pages.bank.payments;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SuggestBlock extends PaymentsPage {
    /**
     * @param divNum - номер div в подблоке результатов поиска. 1 - название компании, 2 - комментарий к платежу
     * @return - текст в переданном div
     */
    public String getTextSuggestBlock(Integer divNum) {
        return $(By.xpath("//div[@data-qa-file='SuggestEntry']/div[text()]")).getText();
    }

    /**
     * @param divNum - номер div в списке поиска.
     *               TODO:проблема - под номером 1 возвращается два элемента, т.к. "Искать в других регионах" вынесен в отдельный SuggestBlock
     */
    public void clickSuggestBlock(Integer divNum) {
        $(By.xpath("//div[@data-qa-file='GridColumn'][" + divNum + "]")).click();
    }

    /**
     * @param name - текст в найденном поиске
     */
    public void clickSuggestBlock(String name) {
        $(By.xpath("//div[@data-qa-file='SuggestEntry']/div[text()='" + name + "']")).click();
    }
}
