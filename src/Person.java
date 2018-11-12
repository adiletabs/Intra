import java.util.Date;

public abstract class Person {
    private String firstName, lastName;
    private Gender gender;
    private Date birthDate;

    {
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
        String nameInfo = "Name: " + this.getFullName() + '\n';
        String genderInfo = "Gender: " + this.getGender().toString() + '\n';
        String birthInfo = "BirthDate: " + this.getBirthDate().toString() + '\n';
        return nameInfo + genderInfo + birthInfo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Person)) return false;
        Person p = (Person) obj;
        return p.firstName.equals(firstName) && p.lastName.equals(lastName) &&
                p.gender.equals(gender) && p.birthDate.equals(birthDate);
    }

    // hashCode
}
