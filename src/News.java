import java.util.Date;

public class News extends Message {
    private Faculty faculty;

    public News(String title, String text, String sender, Date date, Faculty faculty) {
        super(title, text, sender, date);
        this.faculty = faculty;
    }

    public Faculty getFaculty() { return faculty; }

    @Override
    public String toString() {
        String info = super.toString();
        info += "Faculty: " + faculty.toString() + '\n';
        return info;
    }
}
