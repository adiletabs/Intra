public class User extends Person {
    private String login, password, email, phone;

    public User(String firstName, String lastName, String login) {
        super(firstName, lastName);
        this.login = login;
    }

    public String getLogin() { return login; }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String toString() {
        String emailInfo = "Email: " + email + '\n';
        String phoneInfo = "Phone number: " + phone + '\n';
        String loginInfo = "Intranet login: " + login + '\n';
        return super.toString() + emailInfo + phoneInfo + loginInfo;
    }

    // equals, hashcode
}
