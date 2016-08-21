package test.addressbook.pack.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.pack.model.GroupData;
import test.addressbook.pack.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class DeleteGroupTest extends TestBase{

    @BeforeMethod
    public void precondition() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testDeleteGroup() {
        Groups beforeList = app.group().all();
        GroupData deletedGroup = beforeList.iterator().next();
        app.group().delete(deletedGroup);
        Groups afterList = app.group().all();

        assertEquals(afterList.size(),beforeList.size()-1);
        assertThat(afterList, equalTo(beforeList.without(deletedGroup)));

    }

}
