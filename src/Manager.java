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
    public Course findCourse(String courseName, ArrayList<String> teachers) {
        return null;
    }
}