package test.addressbook.pack.tests;

import org.testng.annotations.Test;
import test.addressbook.pack.model.GroupData;

public class AddGroupTest extends TestBase {

    @Test
    public void testAddGroup() {

        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }

}
