package test.addressbook.pack.tests;

import org.testng.annotations.Test;
import test.addressbook.pack.model.ContactData;

public class DeleteContactTest extends TestBase {

    @Test
    public void testDeleteContact(){

        app.getNavigationHelper().gotoHomePage();

        if (! app.getGroupHelper().isGroupExist()) {
            app.getContactHelper().createNewContact(new ContactData("test@email.com", "123123123", "test", "mr", "test", "test", "test", "test", "test1"));
        }
        app.getGroupHelper().selectGroup();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().popupConfirm();
        app.getNavigationHelper().gotoHomePage();
    }
}
