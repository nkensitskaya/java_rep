package test.addressbook.pack.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import test.addressbook.pack.model.ContactData;
import test.addressbook.pack.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contacts count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;


    public static void main(String[] args) throws IOException {

        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        save(contacts,new File(file));
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s,%s,%s,%s,%s,%s,%s\n",
                    contact.getFirstName(),
                    contact.getLastName(),
                    contact.getAddress(),
                    contact.getMobilePhone(),
                    contact.getPhoneHome(),
                    contact.getWorkPhone(),
                    contact.getEmail()));
        }
        writer.close();
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstName(String.format("FirstName %s", i))
            .withLastName(String.format("LastName %s", i))
            .withAddress(String.format("test address %s", i))
            .withMobilePhone("111-11-11")
            .withPhoneHome("222-22-1221")
            .withWorkPhone("33-1331-1331")
            .withEmail(String.format("test%s@test.com", i)));
        }
        return contacts;
    }
}
