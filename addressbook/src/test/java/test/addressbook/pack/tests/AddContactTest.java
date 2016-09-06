package test.addressbook.pack.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.addressbook.pack.model.ContactData;
import test.addressbook.pack.model.Contacts;
import test.addressbook.pack.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class AddContactTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromXML() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
        String line = reader.readLine();
        String xml = "";
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream sxtream = new XStream();
        sxtream.processAnnotations(ContactData.class);
        List<ContactData> contacts = (List<ContactData>) sxtream.fromXML(xml);
        return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
        String line = reader.readLine();
        String json = "";
        while (line != null) {
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
        return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validContactsFromJson")
    public void AddContactTest(ContactData contact) {
        Contacts contactsBefore = app.contact().all();
        /*ContactData contact = new ContactData()
                .withEmail("test2@email.com")
                .withPhoneHome("123123123")
                .withAddress("test2")
                .withTitle("mr2")
                .withFirstName("test2")
                .withMiddleName("test2")
                .withLastName("test2")
                .withNickname("test2")
                .withGroup("test2");*/
        app.contact().create(contact);
        assertEquals(app.group().count(),contactsBefore.size()+1);
        Contacts contactsAfter = app.contact().all();

//        assertEquals(contactsAfter.size(),contactsBefore.size()+1);
        assertThat(contactsAfter, equalTo(contactsBefore.withAdded(contact.withId(contactsAfter.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

}
