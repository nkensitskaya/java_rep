package test.addressbook.pack.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.pack.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class EditContactTest extends TestBase{

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
                    .withGroup("test1"));
            //app.contact().create(new ContactData("test@email.com", "123123123", "test", "mr", "test", "test", "test", "test", "test1"));
        }
    }

    @Test
    public void testEditContact() {
        List<ContactData> contactsBefore = app.contact().list();
        int index = 1;
        app.contact().edit(index);
        ContactData contact = new ContactData()
                .withId(contactsBefore.get(index).getId())
                .withEmail("test_edit@email.com")
                .withPhoneHome("123123123")
                .withAddress("test_edit")
                .withTitle("mr")
                .withFirstName("test_edit")
                .withMiddleName("test_edit")
                .withLastName("test_edit")
                .withNickname("test_edit");
        //ContactData contact = new ContactData(contactsBefore.get(index).getId(),"test_edit@email.com", "123123123", "test_edit", "mr", "test_edit", "test_edit", "test_edit", "test_edit", null);
        app.contact().fillForm(contact,false);
        app.contact().submitUpdate();
        app.contact().returnToContactList();
        List<ContactData> contactsAfter = app.contact().list();

        Assert.assertEquals(contactsAfter.size(),contactsBefore.size());

       contactsBefore.remove(index);
       contactsBefore.add(contact);
        Comparator<? super ContactData> byId  = (gr1, gr2) -> Integer.compare(gr1.getId(),gr2.getId());
        contactsBefore.sort(byId);
        contactsAfter.sort(byId);
        Assert.assertEquals(contactsAfter,contactsBefore);
    }
}
