package test.addressbook.pack.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.addressbook.pack.model.ContactData;

import java.util.List;

public class AddContactTest extends TestBase {

    @Test
    public void AddContactTest() {
        List<ContactData> contactsBefore = app.getContactHelper().getContactsList();
        app.getContactHelper().createNewContact(new ContactData("test@email.com", "123123123", "test", "mr", "test", "test", "test", "test", "test1"));
        List<ContactData> contactsAfter = app.getContactHelper().getContactsList();

        Assert.assertEquals(contactsAfter.size(),contactsBefore.size()+1);

    }

}
