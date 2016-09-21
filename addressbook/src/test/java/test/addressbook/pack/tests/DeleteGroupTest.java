package test.addressbook.pack.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.pack.model.GroupData;
import test.addressbook.pack.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class DeleteGroupTest extends TestBase{

    @BeforeMethod
    public void precondition() {
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testDeleteGroup() {
        Groups beforeList = app.db().groups();
        GroupData deletedGroup = beforeList.iterator().next();
        app.group().delete(deletedGroup);
        assertEquals(app.group().count(),beforeList.size()-1);
        Groups afterList = app.db().groups();

        assertThat(afterList, equalTo(beforeList.without(deletedGroup)));
        verifyGroupListInUI();

    }

}
