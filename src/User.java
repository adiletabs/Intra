import java.io.Serializable;

public abstract class User extends Person implements Serializable {
    private String login;
    private String password;
    private String email;
    private String phone;

    public static String defaultPassword = "Kbtu111";

    {
        password = defaultPassword;
        email = "";
        phone = "";
    }

    public User(String lastName, String firstName, String login) {
        super(lastName, firstName);
        this.login = login;
    }

    public String getLogin() { return login; }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        String emailInfo = "Email: " + email + '\n';
        String phoneInfo = "Phone number: " + phone + '\n';
        String loginInfo = "Intranet login: " + login + '\n';
        return super.toString() + emailInfo + phoneInfo + loginInfo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof User)) return false;
        User u = (User) obj;
        return u.login.equals(login);
    }

    public int hashCode() { return login.hashCode(); }
}