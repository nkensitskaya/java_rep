package test.addressbook.pack;

import org.testng.annotations.Test;

public class AddGroupTest extends TestBase {

    @Test
    public void testAddGroup() {

        gotoGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("test1", "test1", "test1"));
        submitGroupCreation();
        returnToGroupPage();
    }

}
