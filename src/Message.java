import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Message implements Serializable {
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
        String dateInfo = new SimpleDateFormat("dd.MM.yyyy").format(date);
        String timeInfo = new SimpleDateFormat("HH:mm").format(date);
        String info = title + "\n\n" + text + "\n\n";
        info += "Sender: " + sender + '\n' + "Date: " + dateInfo + '\n' + "Time: " + timeInfo + '\n';
        return info;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Message)) return false;
        Message msg = (Message) obj;
        return msg.title.equals(title) && msg.text.equals(text) &&
                msg.sender.equals(sender) && msg.date.equals(date);
    }
}
