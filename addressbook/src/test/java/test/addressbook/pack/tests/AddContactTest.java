package test.addressbook.pack.tests;

import org.testng.annotations.Test;
import test.addressbook.pack.model.ContactData;

public class AddContactTest extends TestBase {

    @Test
    public void AddContactTest() {
        app.getContactHelper().createNewContact(new ContactData("test@email.com", "123123123", "test", "mr", "test", "test", "test", "test", "test1"));
    }

}
