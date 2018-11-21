import java.util.ArrayList;

public interface ManagingCourses {
    ArrayList<String> getCourses();

    Course getCourse(String courseName);

    void addCourses(Course course);

    void deleteCourse(Course course);
}

