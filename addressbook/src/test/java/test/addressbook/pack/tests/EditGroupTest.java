package test.addressbook.pack.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.pack.model.GroupData;
import test.addressbook.pack.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class EditGroupTest extends TestBase{

    @BeforeMethod
    public void precondition() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testEditGroup() {
        Groups groupsBefore = app.db().groups();
        GroupData editGroup = groupsBefore.iterator().next();
        GroupData group = new GroupData().withId(editGroup.getId()).withName("test_edited").withHeader("test1").withFooter("test_edited");
        app.goTo().groupPage();
        app.group().edit(group);
        assertEquals(app.group().count(),groupsBefore.size());
        Groups groupsAfter = app.db().groups();

        assertThat(groupsAfter, equalTo(groupsBefore.without(editGroup).withAdded(group)));
    }

}
