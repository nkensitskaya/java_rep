package test.addressbook.pack.tests;

import org.testng.annotations.Test;
import test.addressbook.pack.model.GroupData;

public class AddGroupTest extends TestBase {

    @Test
    public void testAddGroup() {

        app.gotoGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("test1", "test1", "test1"));
        app.submitGroupCreation();
        app.returnToGroupPage();
    }

}
