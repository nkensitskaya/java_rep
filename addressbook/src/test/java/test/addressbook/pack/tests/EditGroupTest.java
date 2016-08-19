package test.addressbook.pack.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.pack.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class EditGroupTest extends TestBase{

    @BeforeMethod
    public void precondition() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData("test1", null, null));
        }
    }

    @Test
    public void testEditGroup() {
        List<GroupData> groupsBefore = app.group().list();
        int index = groupsBefore.size()-1;
        GroupData group = new GroupData(groupsBefore.get(index).getId(),"test_edited", "test1", "test_edited");
        app.group().edit(index, group);
        List<GroupData> groupsAfter = app.group().list();
        Assert.assertEquals(groupsAfter.size(),groupsBefore.size());

        groupsBefore.remove(index);
        groupsBefore.add(group);
        Comparator<? super GroupData> byId = (gr1, gr2) -> Integer.compare(gr1.getId(),gr2.getId());
        groupsBefore.sort(byId);
        groupsAfter.sort(byId);
        Assert.assertEquals(groupsAfter,groupsBefore);
    }

}
