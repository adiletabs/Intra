import java.util.ArrayList;

public class Controller {
    private Mode currentMode;
    private String currentLogin;
    private ArrayList<Course> currentCourses;
    private ArrayList<CourseFile> currentFiles;
    private ArrayList<Message> currentMessages;

    public static ArrayList<Employee> employees = new ArrayList<Employee>();

    public static ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    public static ArrayList<Manager> managers = new ArrayList<Manager>();
    public static ArrayList<ORManager> orManagers = new ArrayList<ORManager>();
    public static ArrayList<Executor> executors = new ArrayList<Executor>();

    public static ArrayList<Student> students = new ArrayList<Student>();


    public static ArrayList<Course> courses = new ArrayList<Course>();

    public static ArrayList<News> news = new ArrayList<News>();

    public void begin() {

    }

}
