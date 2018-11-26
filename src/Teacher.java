import java.io.Serializable;
import java.util.ArrayList;

public class Teacher extends Employee implements ManagingCourses, Serializable {
    private TeacherPosition position;
    private Faculty faculty;
    private ArrayList<String> courses;

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
    public ArrayList<Course> getCourses() {
        ArrayList<Course> curCourses = new ArrayList<>();
        for (String s: courses) {
            for (Course c: Controller.courses) {
                if (c.getId().equals(s)) {
                    curCourses.add(c);
                    break;
                }
            }
        }
        return curCourses;
    }

    @Override
    public void addCourses(String courseId) { courses.add(courseId); }

    @Override
    public void deleteCourse(String courseId) {
        courses.remove(courseId);
    }

    @Override
    public String toString() {
        String facultyInfo = "Faculty: " + faculty.toString() + '\n';
        String posInfo = "Position: " + position.toString() + '\n';
        return super.toString() + facultyInfo + posInfo;
    }

    @Override
    public boolean equals(Object obj) { return super.equals(obj); }

    @Override
    public int hashCode() { return super.hashCode(); }
}