package test.addressbook.pack.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.addressbook.pack.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class EditGroupTest extends TestBase{

    @Test
    public void testEditGroup() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isGroupExist()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        List<GroupData> groupsBefore = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(groupsBefore.size()-1);
        app.getGroupHelper().openForEditGroup();
        GroupData group = new GroupData(groupsBefore.get(groupsBefore.size()-1).getId(),"test_edited", "test1", "test_edited");
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().updateGroup();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> groupsAfter = app.getGroupHelper().getGroupList();
        Assert.assertEquals(groupsAfter.size(),groupsBefore.size());

        groupsBefore.remove(groupsBefore.size()-1);
        groupsBefore.add(group);
        Comparator<? super GroupData> byId = (gr1, gr2) -> Integer.compare(gr1.getId(),gr2.getId());
        groupsBefore.sort(byId);
        groupsAfter.sort(byId);
        Assert.assertEquals(groupsAfter,groupsBefore);
    }
}
