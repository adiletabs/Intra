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
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public ArrayList<String> getTeacherLogins() { return teacherLogins; }
    public void addTeacher(String login) { teacherLogins.add(login); }
    public void deleteTeacher(String login) { teacherLogins.remove(login); }

    public ArrayList<String> getStudentLogins() { return studentLogins; }
    public void addStudent(String login) { studentLogins.add(login); }
    private void deleteStudent(String login) { studentLogins.remove(login); }

    public int getCreditNumber() { return creditNumber; }
    public void setCreditNumber(int creditNumber) { this.creditNumber = creditNumber; }

    public CourseStatus getStatus(String login) {
        return statuses.get(login);
    }
    public void alterStatus(String login, CourseStatus courseStatus) {
        if (statuses.get(login) != null) {
            statuses.replace(login, courseStatus);
        }
    }

    public int getScore(String login, int mode) {
        return scores.get(login)[mode];
    }
    public int[] getScores(String login) {
        return scores.get(login);
    }
    public void addScore(String login, int mode, int score) {
        scores.get(login)[mode] += score;
    }

    @Override
    public ArrayList<CourseFile> getFiles() {
        return files;
    }

    @Override
    public CourseFile getFile(String title) {
        CourseFile file = null;

        for (CourseFile courseFile: files) {
            if (courseFile.getTitle().equals(title)) {
                file = courseFile;

                break;
            }
        }

        return file;
    }

    @Override
    public void addFile(CourseFile courseFile) {
        files.add(courseFile);
    }

    @Override
    public void deleteFile(String title) {
        for (CourseFile courseFile: files) {
            if (courseFile.getTitle().equals(title)) {
                files.remove(courseFile);

                break;
            }
        }
    }
}