package test.addressbook.pack;

import org.testng.annotations.Test;

public class AddContactTest extends TestBase{

    @Test
    public void AddContactTest() {
        initNewContactCreation();
        fillContactForm(new ContactData("test@email.com", "123123123", "test", "mr", "test", "test", "test", "test"));
        submitNewContact();
        returnToContactList();
    }

}
