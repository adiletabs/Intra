import java.io.Serializable;
import java.util.ArrayList;

public class Teacher extends Employee implements ManagingCourses, Serializable {
    private ArrayList<String> courses;
    private TeacherPosition position;
    private Faculty faculty;

    public Teacher (String lastName, String firstName, String login) {
        super(lastName, firstName, login);
    }

    public TeacherPosition getPosition() { return position; }
    public void setPosition(TeacherPosition position) { this.position = position; }

    public Faculty getFaculty() { return faculty; }
    public void setFaculty(Faculty faculty) { this.faculty = faculty; }

    public void sendOrder(Order order) {
        Executor.orders.add(order);
        Executor.saveOrders();
    }

    @Override
    public ArrayList<Course> getCoursesObj() {
        ArrayList<Course> curCourses = new ArrayList<>();

        for (Course c: Controller.courses) {
            for (String s: courses) {
                if (c.getCourseName().equals(s) && (c.getTeacherLogins().contains(getLogin()))) {
                    curCourses.add(c);
                }
            }

        }

        return curCourses;
    }

    @Override
    public ArrayList<String> getCourses() { return courses; }

    @Override
    public Course getCourse(String courseName) {
        Course course = null;

        for (Course c: Controller.courses) {
            if (c.getCourseName().equals(courseName) && c.getTeacherLogins().contains(getLogin())) {
                course = c;

                break;
            }
        }
        return course;
    }

    @Override
    public void addCourses(Course course) {
        courses.add(course.getCourseName());
    }

    @Override
    public void deleteCourse(Course course) {
        courses.remove(course.getCourseName());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Teacher) {
            Teacher other = (Teacher) obj;

            if (!courses.equals(other.getCourses())) return false;

            else if (!position.equals(other.getPosition())) return false;

            else if (!faculty.equals(other.faculty)) return false;

            else return super.equals(other);
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
