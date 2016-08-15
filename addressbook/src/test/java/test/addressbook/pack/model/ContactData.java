package test.addressbook.pack.model;

public class ContactData {
    private int id;
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
        this.id = 0;
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

    public ContactData(int id, String email, String phoneHome, String address, String title, String firstName, String middleName, String lastName, String nickname, String group) {
        this.id = id;
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

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    public void setId(int id) {
        this.id = id;
    }
}
