package test.addressbook.pack.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.addressbook.pack.model.GroupData;

import java.util.List;

public class AddGroupTest extends TestBase {

    @Test
    public void testAddGroup() {

        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> beforeList = app.getGroupHelper().getGroupList();
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        List<GroupData> afterList = app.getGroupHelper().getGroupList();

        Assert.assertEquals(afterList.size(),beforeList.size()+1);
    }

}
