package test.addressbook.pack.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.pack.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestContactData extends TestBase{

    @BeforeMethod
    public void precondition() {
        app.goTo().goHome();
        if (app.contact().list().size() == 0) {
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
    public void testContactPhones() {
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactDataFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactDataFromEditForm)));
        assertThat(contact.getEmail(), equalTo(mergeEmails(contactDataFromEditForm)));
        assertThat(contact.getAddress(), equalTo(contactDataFromEditForm.getAddress()));
    }

    @Test
    public void testContactDetailsPage(){
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactEditForm = app.contact().infoFromEditForm(contact);
        String contactDetails = app.contact().infoFromDetails(contact);

        assertThat(alignData(contactEditForm), equalTo(contactDetails + "\n"));
    }

    private String alignData(ContactData contact) {
        if (contact.getMobilePhone() != null) {
            contact.withMobilePhone("M: "+contact.getMobilePhone() + "\n");
        }
        if (contact.getPhoneHome() != null) {
            contact.withPhoneHome("H: "+contact.getPhoneHome() + "\n");
        }
        if (contact.getWorkPhone() != null) {
            contact.withWorkPhone("W: "+contact.getWorkPhone() + "\n");
        }
        return contact.getFirstName() + " " +contact.getLastName() + "\n" + contact.getAddress() + "\n\n" + contact.getPhoneHome()
                + contact.getMobilePhone() + contact.getWorkPhone();
    }

    private String mergeAllData(ContactData contact) {

        return Arrays.asList(contact.getFirstName(), contact.getLastName(), contact.getAddress(), contact.getAllPhones())
                .stream().filter((s) -> ! s.equals(""))
                .map(TestContactData::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getPhoneHome(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(TestContactData::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    private static String cleaned(String phone) {
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
}
