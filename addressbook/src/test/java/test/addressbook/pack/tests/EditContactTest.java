package test.addressbook.pack.tests;

import org.testng.annotations.Test;
import test.addressbook.pack.model.ContactData;

public class EditContactTest extends TestBase{

    @Test
    public void testEditContact() {

        app.getNavigationHelper().gotoHomePage();
        if (! app.getGroupHelper().isGroupExist()) {
            app.getContactHelper().createNewContact(new ContactData("test@email.com", "123123123", "test", "mr", "test", "test", "test", "test", "test1"));
        }
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(new ContactData("test_edit@email.com", "123123123", "test_edit", "mr", "test_edit", "test_edit", "test_edit", "test_edit", null),false);
        app.getContactHelper().submitUpdate();
        app.getContactHelper().returnToContactList();
    }
}
