import java.io.Serializable;
import java.util.ArrayList;

public class ORManager extends Employee implements Searching, Serializable {

    public ORManager (String lastName, String firstName, String login) {
        super(lastName, firstName, login);
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