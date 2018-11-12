public abstract class Employee extends User implements Messageable {
    public Employee(String firstName, String lastName, String login) {
        super(firstName, lastName, login);
    }

    @Override
    public void sendMessage(Employee emp, String title, String text) {
        
    }
}
