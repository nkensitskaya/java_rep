package test.addressbook.pack.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.addressbook.pack.model.GroupData;
import test.addressbook.pack.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddGroupTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroupsfromXML() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
        String line = reader.readLine();
        String xml = "";
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream sxtream = new XStream();
        sxtream.processAnnotations(GroupData.class);
        List<GroupData> groups = (List<GroupData>) sxtream.fromXML(xml);
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();}
    }

    @DataProvider
    public Iterator<Object[]> validGroupsfromJson() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
        String line = reader.readLine();
        String json = "";
        while (line != null) {
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();}
    }

    @Test (dataProvider = "validGroupsfromJson")
    public void testAddGroup(GroupData group) {

        app.goTo().groupPage();
        Groups beforeList = app.db().groups();
        app.group().create(group);
        assertThat(app.group().count(), equalTo(beforeList.size()+1));
        Groups afterList = app.db().groups();

        assertThat(afterList, equalTo(beforeList.withAdded(group.withId(afterList.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
