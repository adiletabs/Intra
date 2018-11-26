import java.io.Serializable;
import java.util.Date;

public class News extends Message implements Serializable {
    private Faculty faculty;

    public News(String title, String text, String sender, Faculty faculty) {
        super(title, text, sender);
        this.faculty = faculty;
    }

    public Faculty getFaculty() { return faculty; }
    public void setFaculty(Faculty faculty) { this.faculty = faculty; }

    @Override
    public String toString() {
        String info = super.toString(), facultyInfo;
        try {
            facultyInfo = "Faculty: " + faculty.toString();
        }
        catch (Exception e) {
            facultyInfo = "";
        }
        info += facultyInfo + '\n';
        return info;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof News)) return false;
        News news = (News) obj;
        return super.equals(obj);
    }

    @Override
    public int hashCode() { return super.hashCode(); }
}