import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Course implements ManagingFiles, Serializable {
    private String courseName;
    private ArrayList<String> teacherLogins;
    private ArrayList<String> studentLogins;
    private int creditNumber;
    private HashMap<String, CourseStatus> statuses;
    private HashMap<String, int[]> scores;
    private ArrayList<CourseFile> files;

    public Course (String courseName, int creditNumber, ArrayList<String> teacherLogins) {
        this.courseName = courseName;
        this.creditNumber = creditNumber;
        this.teacherLogins = teacherLogins;
    }

    public String getCourseName() { return courseName; }

    public ArrayList<String> getTeacherLogins() { return teacherLogins; }
    public void addTeacher(Teacher teacher) { teacherLogins.add(teacher.getLogin()); }
    public void deleteTeacher(Teacher teacher) { teacherLogins.remove(teacher.getLogin()); }

    public ArrayList<String> getStudentLogins() { return studentLogins; }
    public void addStudent(Student student) { studentLogins.add(student.getLogin()); }

    @Override
    public ArrayList<CourseFile> getFiles() {
        return null;
    }

    @Override
    public CourseFile getFile(String title) {
        return null;
    }

    @Override
    public void addFile(CourseFile courseFile) {

    }

    @Override
    public void deleteFile(String title) {

    }
}
