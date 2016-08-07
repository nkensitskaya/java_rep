package test.addressbook.pack.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import test.addressbook.pack.model.GroupData;

public class EditGroupTest extends TestBase{

    @Test
    public void testEditGroup() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isGroupExist()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().openForEditGroup();
        app.getGroupHelper().fillGroupForm(new GroupData("test_edited", "test1", "test_edited"));
        app.getGroupHelper().updateGroup();
        app.getGroupHelper().returnToGroupPage();
    }
}
