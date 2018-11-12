import java.util.Date;

public class Message {
    private String title, text, sender;
    private Date date;

    public Message(String title, String text, String sender, Date date) {
        this.title = title;
        this.text = text;
        this.sender = sender;
        this.date = date;
    }

    public String getTitle() { return title; }

    public String getText() { return text; }

    public String getSender() { return sender; }

    public Date getDate() { return date; }

    @Override
    public String toString() {
        String info = title + "\n\n" + text + "\n\n";
        info += "Sender: " + sender + '\n' + "Date: " + date.toString();
        return info;
    }
}
