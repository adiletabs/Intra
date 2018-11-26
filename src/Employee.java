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
    public void sendMessage(Message message, String login) {
        for (Employee e: Controller.teachers) {
            if (e.getLogin().equals(login)) {
                e.messages.add(message);
                return;
            }
        }
        for (Employee e: Controller.executors) {
            if (e.getLogin().equals(login)) {
                e.messages.add(message);
                return;
            }
        }
        for (Employee e: Controller.orManagers) {
            if (e.getLogin().equals(login)) {
                e.messages.add(message);
                return;
            }
        }
        for (Employee e: Controller.managers) {
            if (e.getLogin().equals(login)) {
                e.messages.add(message);
                return;
            }
        }
    }

    @Override
    public String getMessages() {
        String res = "";
        for (Message msg: messages) {
            res += msg.getTitle() + "\nSender: " + msg.getSender() + "\n\n";
        }
        return messages.isEmpty() ? "No messages found" : res;
    }

    @Override
    public String readMessage(String title) {
        String res = "Error. There is no message with such title\n";

        for (Message msg: messages) {
            if (msg.getTitle().equals(title)) {
                res = msg.toString() + '\n';
                break;
            }
        }
        return res;
    }
}