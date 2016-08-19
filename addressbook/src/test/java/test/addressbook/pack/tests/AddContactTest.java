package test.addressbook.pack.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.addressbook.pack.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class AddContactTest extends TestBase {

    @Test
    public void AddContactTest() {
        List<ContactData> contactsBefore = app.contact().list();
        ContactData contact = new ContactData("test2@email.com", "123123123", "test2", "mr2", "test2", "test2", "test2", "test2",  "test2");
        app.contact().create(contact);
        List<ContactData> contactsAfter = app.contact().list();

        Assert.assertEquals(contactsAfter.size(),contactsBefore.size()+1);

        int max = contactsAfter.stream().max(((o1, o2) -> Integer.compare(o1.getId(),o2.getId()))).get().getId();
        contactsBefore.add(contact);
        contact.setId(max);

        Comparator<? super ContactData> byId  = (gr1, gr2) -> Integer.compare(gr1.getId(),gr2.getId());
        contactsBefore.sort(byId);
        contactsAfter.sort(byId);
        Assert.assertEquals(contactsAfter,contactsBefore);
    }

}
