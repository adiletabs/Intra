import java.util.ArrayList;

public interface Searching {
    Student findStudent(String login);

    Teacher findTeacher(String login);

    Course findCourse(String id);
}
