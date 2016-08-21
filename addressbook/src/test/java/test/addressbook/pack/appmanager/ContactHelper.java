package test.addressbook.pack.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import test.addressbook.pack.model.ContactData;
import test.addressbook.pack.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void fillForm(ContactData contactData, boolean creation) {
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
    public void selectContact(int index) {

        wd.findElements(By.name("selected[]")).get(index).click();
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
    }

    public void initNewContactCreation() {
        click(By.linkText("add new"));
    }

    public void edit(int id) {
        wd.findElement(By.cssSelector("a[href = 'edit.php?id=" + id + "']")).click();
    }

    public void submitUpdate() {
        click(By.name("update"));
    }

    public void deleteSelectedContactContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void deleteContact(int index) {
        selectContact(index);
        deleteSelectedContact();
        popupConfirm();
        goHome();
    }

    public void deleteContact(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        popupConfirm();
        goHome();
    }

    private void popupConfirm(){
        wd.switchTo().alert().accept();
    }

    private void deleteSelectedContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void goHome() {

        if (isElementPresent(By.id("maintable"))) {
            return;
        } else {
            wd.findElement(By.linkText("home")).click();
        }
    }

    public void create(ContactData contact) {

        initNewContactCreation();
        fillForm((contact),true);
        submitNewContact();
        returnToContactList();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstName = element.findElement(By.xpath("./td[3]")).getText();
            String lastName = element.findElement(By.xpath("./td[2]")).getText();
            String address = element.findElement(By.xpath("./td[4]")).getText();
            String email = element.findElement(By.xpath("./td[5]")).getText();
            //ContactData contact = new ContactData(id, email,null,address,null,firstName,null,lastName,null,null);
            ContactData contact = new ContactData()
                    .withId(id)
                    .withEmail(email)
                    .withAddress(address)
                    .withFirstName(firstName)
                    .withLastName(lastName);
            contacts.add(contact);
        }
        return contacts;
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstName = element.findElement(By.xpath("./td[3]")).getText();
            String lastName = element.findElement(By.xpath("./td[2]")).getText();
            String address = element.findElement(By.xpath("./td[4]")).getText();
            String email = element.findElement(By.xpath("./td[5]")).getText();
            //ContactData contact = new ContactData(id, email,null,address,null,firstName,null,lastName,null,null);
            ContactData contact = new ContactData()
                    .withId(id)
                    .withEmail(email)
                    .withAddress(address)
                    .withFirstName(firstName)
                    .withLastName(lastName);
            contacts.add(contact);
        }
        return contacts;
    }

}
