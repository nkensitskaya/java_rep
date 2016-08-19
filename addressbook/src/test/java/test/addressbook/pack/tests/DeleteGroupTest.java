package test.addressbook.pack.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.pack.model.GroupData;

import java.util.List;

public class DeleteGroupTest extends TestBase{

    @BeforeMethod
    public void precondition() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData("test1", null, null));
        }
    }

    @Test
    public void testDeleteGroup() {
        List<GroupData> beforeList = app.group().list();
        int index = beforeList.size()-1;
        app.group().delete(index);
        List<GroupData> afterList = app.group().list();

        Assert.assertEquals(afterList.size(),beforeList.size()-1);

        beforeList.remove(index);
        Assert.assertEquals(afterList,beforeList);
    }

}
