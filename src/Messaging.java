public interface Messaging {
    void sendMessage(Message message, String login);

    String getMessages();

    String readMessage(String title);
}
