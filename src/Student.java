import java.io.Serializable;
import java.util.ArrayList;

public class Student extends User implements ManagingCourses, Serializable {
    private String id;
    private Degree degree;
    private Faculty faculty;
    private int yearOfStudy;
    private double gpa;
    private ArrayList<String> courses;

    {
        yearOfStudy = 1;
        courses = new ArrayList<String>();
    }

    public Student (String lastName, String firstName, String login) {
        super(lastName, firstName, login);
    }

    public String getId() { return id; }

    public int getYearOfStudy() { return yearOfStudy; }

    public void incrementYearOfStudy() { yearOfStudy++; }

    public Faculty getFaculty() { return faculty; }
    public void setFaculty(Faculty faculty) { this.faculty = faculty; }

    public Degree getDegree() { return degree; }
    public void setDegree(Degree degree) { this.degree = degree; }

    @Override
    public ArrayList<String> getCourses() {
        return null;
    }

    @Override
    public ArrayList<Course> getCoursesObj() {
        ArrayList<Course> curCourses = new ArrayList<>();

        for (Course c: Controller.courses) {
            for (String s: courses) {
                if (c.getCourseName().equals(s) && (c.getTeacherLogins().contains(getLogin())
                        || c.getStudentLogins().contains(getLogin()))) {
                    curCourses.add(c);
                }
            }

        }

        return curCourses;
    }

    @Override
    public Course getCourse(String courseName) {
        return null;
    }

    @Override
    public void addCourses(Course course) {

    }

    @Override
    public void deleteCourse(Course course) {

    }

//    @Override
//    public ArrayList<String> getCourses() {
//        String res = getFullName() + " is not registered for any courses\n";
//        if (!courses.isEmpty()) {
//            res = "Courses of " + getFullName() + ":\n\n";
//            for (String s: courses) res += (s + '\n');
//        }
//        return res;
//    }
}
