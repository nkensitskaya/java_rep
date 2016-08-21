package test.addressbook.pack.tests;

import org.testng.annotations.Test;
import test.addressbook.pack.model.GroupData;
import test.addressbook.pack.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddGroupTest extends TestBase {

    @Test
    public void testAddGroup() {

        app.goTo().groupPage();
        Groups beforeList = app.group().all();
        GroupData group = new GroupData().withName("test2").withFooter("test2").withHeader("test2");
        app.group().create(group);
        Groups afterList = app.group().all();

        assertThat(afterList.size(), equalTo(beforeList.size()+1));
        assertThat(afterList, equalTo(beforeList.withAdded(group.withId(afterList.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
