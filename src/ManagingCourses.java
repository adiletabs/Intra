import java.util.ArrayList;

public interface ManagingCourses {

    ArrayList<Course> getCourses();

    void addCourses(String courseId);

    void deleteCourse(String courseId);
}