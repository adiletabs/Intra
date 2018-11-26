import java.io.Serializable;
import java.util.ArrayList;

public abstract class Employee extends User implements Messaging, Serializable {
    private ArrayList<Message> messages;

    {
        messages = new ArrayList<>();
    }

    public Employee(String firstName, String lastName, String login) {
        super(firstName, lastName, login);
    }

    @Override
    public boolean sendMessage(Message message, String login) {
        for (Employee e: Controller.teachers) {
            if (e.getLogin().equals(login)) {
                e.messages.add(message);
                return true;
            }
        }
        for (Employee e: Controller.executors) {
            if (e.getLogin().equals(login)) {
                e.messages.add(message);
                return true;
            }
        }
        for (Employee e: Controller.orManagers) {
            if (e.getLogin().equals(login)) {
                e.messages.add(message);
                return true;
            }
        }
        for (Employee e: Controller.managers) {
            if (e.getLogin().equals(login)) {
                e.messages.add(message);
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<String> getMessages() {
        ArrayList<String> messagesText =  new ArrayList<>();
        for (Message msg: messages) {
            messagesText.add(msg.getTitle() + "\nSender: " + msg.getSender() + "\n\n");
        }
        return messagesText;
    }

    @Override
    public String readMessage(int ind) {
        return messages.get(ind).toString();
    }

    @Override
    public String toString() { return super.toString(); }

    @Override
    public boolean equals(Object obj) { return super.equals(obj); }

    @Override
    public int hashCode() { return super.hashCode(); }
}