import java.util.ArrayList;

public class Student extends User {
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

    public Student(String lastName, String firstName, String login,
                   String id, Degree degree, Faculty faculty, int yearOfStudy) {
        super(lastName, firstName, login);
        this.id = id;
        this.degree = degree;
        this.yearOfStudy = yearOfStudy;
    }

    public String getId() { return id; }

    public int getYearOfStudy() { return yearOfStudy; }

    public void incrementYearOfStudy() { yearOfStudy++; }

    public String getCourses() {
        String res = getFullName() + " is not registered for any courses\n";
        if (!courses.isEmpty()) {
            res = "Courses of " + getFullName() + ":\n\n";
            for (String s: courses) res += (s + '\n');
        }
        return res;
    }
}
