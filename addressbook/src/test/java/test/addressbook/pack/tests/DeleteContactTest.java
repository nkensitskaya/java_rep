package test.addressbook.pack.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.pack.model.ContactData;
import test.addressbook.pack.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class DeleteContactTest extends TestBase {

    @BeforeMethod
    public void precondition() {
        app.goTo().goHome();
        if (app.group().list().size() == 0) {
            app.contact().create(new ContactData()
                    .withEmail("test@email.com")
                    .withPhoneHome("123123123")
                    .withAddress("test")
                    .withTitle("mr")
                    .withFirstName("test")
                    .withMiddleName("test")
                    .withLastName("test")
                    .withNickname("test")
                    .withGroup("test2"));        }
    }

    @Test
    public void testDeleteContact(){

        Contacts contactsBefore = app.contact().all();
        ContactData deletedContact = contactsBefore.iterator().next();
        app.contact().deleteContact(deletedContact);
        assertEquals(app.group().count(),contactsBefore.size()-1);
        Contacts contactsAfter = app.contact().all();

//        assertEquals(contactsAfter.size(),contactsBefore.size()-1);
        assertThat(contactsAfter, equalTo(contactsBefore.without(deletedContact)));

    }

}
