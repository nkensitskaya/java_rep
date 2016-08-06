package test.addressbook.pack.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import test.addressbook.pack.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToContactList() {
        click(By.linkText("home page"));
    }

    public void submitNewContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"),contactData.getFirstName());
        type(By.name("middlename"),contactData.getMiddleName());
        type(By.name("lastname"),contactData.getLastName());
        type(By.name("nickname"),contactData.getNickname());
        type(By.name("title"),contactData.getTitle());
        type(By.name("address"),contactData.getAddress());
        type(By.name("home"),contactData.getPhoneHome());
        type(By.name("email"),contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initNewContactCreation() {
        click(By.linkText("add new"));
    }

    public void editContact() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void submitUpdate() {
        click(By.name("update"));
    }

    public void deleteContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }
}
