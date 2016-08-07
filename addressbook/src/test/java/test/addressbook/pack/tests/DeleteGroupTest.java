package test.addressbook.pack.tests;

import org.testng.annotations.Test;
import test.addressbook.pack.model.GroupData;

public class DeleteGroupTest extends TestBase{

    @Test
    public void testDeleteGroup() {

        app.getNavigationHelper().gotoGroupPage();

        if (! app.getGroupHelper().isGroupExist()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().delete();
        app.getGroupHelper().returnToGroupPage();
    }

}
