package test.addressbook.pack.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.addressbook.pack.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class AddGroupTest extends TestBase {

    @Test
    public void testAddGroup() {

        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> beforeList = app.getGroupHelper().getGroupList();
        GroupData group = new GroupData("test2", "test2", "test2");
        app.getGroupHelper().createGroup(group);
        List<GroupData> afterList = app.getGroupHelper().getGroupList();

        Assert.assertEquals(afterList.size(),beforeList.size()+1);

        int max = afterList.stream().max(((o1, o2) -> Integer.compare(o1.getId(),o2.getId()))).get().getId();
        beforeList.add(group);
        group.setId(max);

        Comparator<? super GroupData> byId = (gr1, gr2) -> Integer.compare(gr1.getId(),gr2.getId());
        beforeList.sort(byId);
        afterList.sort(byId);
        Assert.assertEquals(afterList,beforeList);
    }

}
