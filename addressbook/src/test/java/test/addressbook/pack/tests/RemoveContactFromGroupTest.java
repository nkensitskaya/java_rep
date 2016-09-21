package test.addressbook.pack.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.pack.model.ContactData;
import test.addressbook.pack.model.Contacts;
import test.addressbook.pack.model.GroupData;
import test.addressbook.pack.model.Groups;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class RemoveContactFromGroupTest extends TestBase {

    @BeforeMethod (enabled = false)
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
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }


    @Test
    public void removeContactFromGroup() {
        app.goTo().goHome();
        ContactData selectedContact = null;
        Contacts contactsAll = app.db().contacts();
        for (ContactData contact : contactsAll) {
            if (contact.getGroups().size() > 0) {
                selectedContact = contact;
            }
        }
        GroupData selectedGroup = selectedContact.getGroups().iterator().next();
        app.contact().removeFromGroup(selectedContact,selectedGroup);

        assertTrue(selectedContact.getGroups().isIdExists(selectedGroup.getId()));

    }

}
