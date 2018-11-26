import java.io.Serializable;
import java.util.ArrayList;

public class Manager extends Employee implements ManagingNews, Searching, Serializable {
    private Faculty faculty;

    public Manager (String lastName, String firstName, String login) {
        super(lastName, firstName, login);
    }

    public Faculty getFaculty() { return faculty; }
    public void setFaculty(Faculty faculty) { this.faculty = faculty; }

    @Override
    public void addNews(News news) {
        Controller.news.add(news);
    }

    @Override
    public void deleteNews(News news) {
        Controller.news.remove(news);
    }

    @Override
    public Student findStudent(String login) {
        Student student = null;
        for (Student s: Controller.students) {
            if (s.getLogin().equals(login)) {
                student = s;
                break;
            }
        }
        return student;
    }

    @Override
    public Teacher findTeacher(String login) {
        Teacher teacher = null;
        for (Teacher t: Controller.teachers) {
            if (t.getLogin().equals(login)) {
                teacher = t;
                break;
            }
        }
        return teacher;
    }

    @Override
    public Course findCourse(String id) {
        Course course = null;
        for (Course c: Controller.courses) {
            if (c.getId().equals(id)) {
                course = c;
                break;
            }
        }
        return course;
    }

    @Override
    public String toString() {
        String facultyInfo = "Faculty: " + faculty.toString() + '\n';
        return super.toString() + facultyInfo;
    }

    @Override
    public boolean equals(Object obj) { return super.equals(obj); }

    @Override
    public int hashCode() { return super.hashCode(); }
}