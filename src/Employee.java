import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

public abstract class Employee extends User implements Messageable {
    private ArrayList<Message> messages;

    {
        messages = new ArrayList<>();
    }

    public Employee(String firstName, String lastName, String login) {
        super(firstName, lastName, login);
    }

    @Override
    public boolean sendMessage(String login, String title, String text) {
        Date now = Calendar.getInstance().getTime();
        Message msg = new Message(title, text, getFullName(), now);
        boolean sent = false;
        for (Employee e: Controller.employees) {
            if (e.getLogin().equals(login)) {
                e.messages.add(msg);
                sent = true;
                break;
            }
        }
        return sent;
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
