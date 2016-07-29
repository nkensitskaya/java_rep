package test.addressbook.pack.tests;

import org.testng.annotations.Test;

public class DeleteGroupTest extends TestBase{

    @Test
    public void testDeleteGroup() {

        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().delete();
        app.getGroupHelper().returnToGroupPage();
    }

}
