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
    public void setId(String id) { this.id = id; }

    public Degree getDegree() { return degree; }
    public void setDegree(Degree degree) { this.degree = degree; }

    public Faculty getFaculty() { return faculty; }
    public void setFaculty(Faculty faculty) { this.faculty = faculty; }

    public int getYearOfStudy() { return yearOfStudy; }
    public void setYearOfStudy(int yearOfStudy) { this.yearOfStudy = yearOfStudy; }

    public void incrementYearOfStudy() { yearOfStudy++; }

    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }

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
    public void deleteCourse(String courseId) { courses.remove(courseId); }

    @Override
    public String toString() { return super.toString(); }

    @Override
    public boolean equals(Object obj) { return super.equals(obj); }

    @Override
    public int hashCode() { return super.hashCode(); }
}
