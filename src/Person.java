import java.io.Serializable;
import java.util.Date;

public abstract class Person implements Serializable {
    private String firstName;
    private String lastName;
    private Gender gender;
    private Date birthDate;

    {
        firstName = "";
        lastName = "";
        gender = null;
        birthDate = null;
    }

    public Person(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return this.getLastName() + " " + this.getFirstName();
    }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date date) {
        this.birthDate = date;
    }

    @Override
    public String toString() {
        String genderInfo, birthInfo;
        try {
            genderInfo = "Gender: " + gender.toString() + '\n';
        }
        catch (Exception e) {
            genderInfo = "";
        }
        try {
            birthInfo = "BirthDate: " + this.getBirthDate().toString() + '\n';
        }
        catch (Exception e) {
            birthInfo = "";
        }
        String nameInfo = "Name: " + this.getFullName() + '\n';
        return nameInfo + genderInfo + birthInfo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Person)) return false;
        Person p = (Person) obj;
        return (!firstName.equals("") && p.firstName.equals(firstName)) &&
                (!lastName.equals("") && p.lastName.equals(lastName)) &&
                (gender != null && p.gender.equals(gender)) &&
                (birthDate != null && p.birthDate.equals(birthDate));
    }

    @Override
    public int hashCode() {
        return lastName.hashCode();
    }
}