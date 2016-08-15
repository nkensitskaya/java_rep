package test.addressbook.pack.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.addressbook.pack.model.GroupData;

import java.util.List;

public class DeleteGroupTest extends TestBase{

    @Test
    public void testDeleteGroup() {

        app.getNavigationHelper().gotoGroupPage();

        if (! app.getGroupHelper().isGroupExist()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        List<GroupData> beforeList = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(beforeList.size()-1);
        app.getGroupHelper().delete();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> afterList = app.getGroupHelper().getGroupList();

        Assert.assertEquals(afterList.size(),beforeList.size()-1);

        beforeList.remove(beforeList.size()-1);
        Assert.assertEquals(afterList,beforeList);
    }

}
