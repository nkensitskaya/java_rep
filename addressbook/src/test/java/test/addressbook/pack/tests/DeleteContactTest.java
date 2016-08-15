package test.addressbook.pack.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.addressbook.pack.model.ContactData;

import java.util.List;

public class DeleteContactTest extends TestBase {

    @Test
    public void testDeleteContact(){

        app.getNavigationHelper().gotoHomePage();

        if (! app.getGroupHelper().isGroupExist()) {
            app.getContactHelper().createNewContact(new ContactData("test@email.com", "123123123", "test", "mr", "test", "test", "test", "test", "test1"));
        }
        List<ContactData> contactsBefore = app.getContactHelper().getContactsList();

        app.getGroupHelper().selectGroup(contactsBefore.size()-1);
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().popupConfirm();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> contactsAfter = app.getContactHelper().getContactsList();

        Assert.assertEquals(contactsAfter.size(),contactsBefore.size()-1);

        contactsBefore.remove(contactsBefore.size()-1);
        Assert.assertEquals(contactsAfter,contactsBefore);
    }
}
