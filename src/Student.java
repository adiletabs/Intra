import java.io.Serializable;
import java.util.ArrayList;

public class Student extends User implements ManagingCourses, Serializable {
    private String id;
    private Degree degree;
    private Faculty faculty;
    private int yearOfStudy;
    private double gpa;
    private ArrayList<String> courses;

    private static int commonID = 0;

    {
        yearOfStudy = 1;
        courses = new ArrayList<String>();
    }

    private String registerID() {
        String res = "";
        commonID++;
        if (commonID < 10)
            res = "00000" + commonID;
        else if (commonID < 100)
            res = "0000" + commonID;
        else if (commonID < 1000)
            res = "000" + commonID;
        else if (commonID < 10000)
            res = "00" + commonID;
        else if (commonID < 100000)
            res = "0" + commonID;
        else
            res = Integer.toString(commonID);
        return res;
    }

    public Student(String lastName, String firstName, String login) {
        super(lastName, firstName, login);
        id = registerID();
    }

    public String getId() { return id; }

    public int getYearOfStudy() { return yearOfStudy; }
    public void setYearOfStudy(int yearOfStudy) { this.yearOfStudy = yearOfStudy; }

    public void incrementYearOfStudy() { yearOfStudy++; }

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
