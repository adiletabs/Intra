import java.util.ArrayList;

public interface Messaging {
    void sendMessage(Message message, String login);

    ArrayList<String> getMessages();

    String readMessage(int ind);
}
