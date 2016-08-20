package test.addressbook.pack.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.pack.model.ContactData;

import java.util.List;

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
                    .withGroup("test1"));        }
    }

    @Test
    public void testDeleteContact(){

        List<ContactData> contactsBefore = app.contact().list();
        int index = contactsBefore.size()-1;
        app.contact().deleteContact(index);
        List<ContactData> contactsAfter = app.contact().list();

        Assert.assertEquals(contactsAfter.size(),contactsBefore.size()-1);

        contactsBefore.remove(index);
        Assert.assertEquals(contactsAfter,contactsBefore);
    }

}
