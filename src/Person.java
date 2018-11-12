import java.util.Date;

public abstract class Person {
    private String firstName, lastName;
    private Gender gender;
    private Date birthDate;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String toString() {
        String nameInfo = "Name: " + this.getFullName() + '\n';
        String genderInfo = "Gender: " + this.getGender().toString() + '\n';
        String birthInfo = "BirthDate: " + this.getBirthDate().toString() + '\n';
        return nameInfo + genderInfo + birthInfo;
    }

    // equals, hashCode
}
