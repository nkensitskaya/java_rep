package test.addressbook.pack.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.addressbook.pack.model.ContactData;
import test.addressbook.pack.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class AddContactTest extends TestBase {

    @Test
    public void AddContactTest() {
        Contacts contactsBefore = app.contact().all();
        ContactData contact = new ContactData()
                .withEmail("test2@email.com")
                .withPhoneHome("123123123")
                .withAddress("test2")
                .withTitle("mr2")
                .withFirstName("test2")
                .withMiddleName("test2")
                .withLastName("test2")
                .withNickname("test2")
                .withGroup("test2");
        app.contact().create(contact);
        Contacts contactsAfter = app.contact().all();

        assertEquals(contactsAfter.size(),contactsBefore.size()+1);
        assertThat(contactsAfter, equalTo(contactsBefore.withAdded(contact.withId(contactsAfter.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

}
