import java.util.ArrayList;

public interface ManagingCourses {
    ArrayList<String> getCourses();

    ArrayList<Course> getCoursesObj();

    Course getCourse(String courseName);

    void addCourses(Course course);

    void deleteCourse(Course course);
}