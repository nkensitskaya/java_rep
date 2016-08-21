package test.addressbook.pack.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.pack.model.GroupData;
import test.addressbook.pack.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class EditGroupTest extends TestBase{

    @BeforeMethod
    public void precondition() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testEditGroup() {
        Groups groupsBefore = app.group().all();
        GroupData editGroup = groupsBefore.iterator().next();
        GroupData group = new GroupData().withId(editGroup.getId()).withName("test_edited").withHeader("test1").withFooter("test_edited");
        app.group().edit(group);
        Groups groupsAfter = app.group().all();

        assertEquals(groupsAfter.size(),groupsBefore.size());
        assertThat(groupsAfter, equalTo(groupsBefore.without(editGroup).withAdded(group)));
    }

}
