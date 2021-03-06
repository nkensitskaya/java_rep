package test.addressbook.pack.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.pack.model.ContactData;
import test.addressbook.pack.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class EditContactTest extends TestBase{

    @BeforeMethod
    public void precondition() {
        if (app.db().contacts().size() == 0) {
            app.goTo().goHome();
            app.contact().create(new ContactData()
                    .withEmail("test@email.com")
                    .withPhoneHome("123123123")
                    .withAddress("test")
                    .withTitle("mr")
                    .withFirstName("test")
                    .withMiddleName("test")
                    .withLastName("test")
                    .withNickname("test"));
                    //.withGroup("test2"));
        }

    }

    @Test
    public void testEditContact() {
        Contacts contactsBefore = app.db().contacts();
        ContactData editContact = contactsBefore.iterator().next();
        ContactData contact = new ContactData()
                .withId(editContact.getId())
                .withEmail("test_edit@email.com")
                .withPhoneHome("123123123")
                .withAddress("test_edit")
                .withTitle("mr")
                .withFirstName("test_edit")
                .withMiddleName("test_edit")
                .withLastName("test_edit")
                .withNickname("test_edit");
        app.contact().editContact(contact);
        assertEquals(app.group().count(),contactsBefore.size());
        Contacts contactsAfter = app.db().contacts();

        assertThat(contactsAfter, equalTo(contactsBefore.withAdded(contact).without(editContact)));
        verifyContactListInUI();
    }

}
