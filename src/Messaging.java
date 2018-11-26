public interface Messageable {
    boolean sendMessage(String login, String title, String text);
    String getMessages();
    String readMessage(String title);
}
