package test.addressbook.pack.tests;

import org.testng.annotations.Test;

public class DeleteContactTest extends TestBase {

    @Test
    public void testDeleteContact(){

        app.getNavigationHelper().gotoHomePage();
        app.getGroupHelper().selectGroup();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().popupConfirm();
        app.getNavigationHelper().gotoHomePage();
    }
}
