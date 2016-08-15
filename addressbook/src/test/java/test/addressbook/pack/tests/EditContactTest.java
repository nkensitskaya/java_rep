package test.addressbook.pack.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.addressbook.pack.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class EditContactTest extends TestBase{

    @Test
    public void testEditContact() {

        app.getNavigationHelper().gotoHomePage();
        if (! app.getGroupHelper().isGroupExist()) {
            app.getContactHelper().createNewContact(new ContactData("test@email.com", "123123123", "test", "mr", "test", "test", "test", "test", "test1"));
        }
        List<ContactData> contactsBefore = app.getContactHelper().getContactsList();
        app.getContactHelper().editContact(1);
        ContactData contact = new ContactData(contactsBefore.get(1).getId(),"test_edit@email.com", "123123123", "test_edit", "mr", "test_edit", "test_edit", "test_edit", "test_edit", null);
        app.getContactHelper().fillContactForm(contact,false);
        app.getContactHelper().submitUpdate();
        app.getContactHelper().returnToContactList();
        List<ContactData> contactsAfter = app.getContactHelper().getContactsList();

        Assert.assertEquals(contactsAfter.size(),contactsBefore.size());

       contactsBefore.remove(1);
       contactsBefore.add(contact);
        Comparator<? super ContactData> byId  = (gr1, gr2) -> Integer.compare(gr1.getId(),gr2.getId());
        contactsBefore.sort(byId);
        contactsAfter.sort(byId);
        Assert.assertEquals(contactsAfter,contactsBefore);
    }
}
