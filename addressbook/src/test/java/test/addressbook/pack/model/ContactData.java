package test.addressbook.pack.model;

public class ContactData {
    private final String email;
    private final String phoneHome;
    private final String address;
    private final String title;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickname;
    private String group;

    public ContactData(String email, String phoneHome, String address, String title, String firstName, String middleName, String lastName, String nickname, String group) {
        this.email = email;
        this.phoneHome = phoneHome;
        this.address = address;
        this.title = title;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.group = group;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public String getAddress() {
        return address;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public String getGroup() {
        return group;
    }
}
