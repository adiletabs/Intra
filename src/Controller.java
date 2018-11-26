import javax.swing.plaf.ComponentInputMapUIResource;
import java.io.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private Mode mode;
    private Admin admin;
    private User user;

    private Teacher teacher;
    private ArrayList<Course> curCourses;

    private Student student;

    private Manager manager;

    private ORManager orManager;

    private Executor executor;


    public Controller() {
        loadData();
    }

    private static final Scanner sc = new Scanner(System.in);

    private static final String PATH = "/home/dontnicemebr0/IdeaProjects/Intra/src/";
    private static final String LOG = "log.txt";

    private static final String DATE_PATTERN = "dd.MM.yy HH:mm";

    private static final String EXCEPT_CLASS = "Class not found!";
    private static final String EXCEPT_FILE = "File not found!";
    private static final String EXCEPT_IO = "Input / Output exception!";

    private static final String TEACHERS = "teachers.out";
    private static final String STUDENTS = "students.out";
    private static final String ORMANAGERS = "ormanagers.out";
    private static final String MANAGERS = "managers.out";
    private static final String EXECUTORS = "executors.out";
    private static final String NEWS = "news.out";

    public static ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    public static ArrayList<Manager> managers = new ArrayList<Manager>();
    public static ArrayList<ORManager> orManagers = new ArrayList<ORManager>();
    public static ArrayList<Executor> executors = new ArrayList<Executor>();

    public static ArrayList<Student> students = new ArrayList<Student>();

    public static ArrayList<Course> courses = new ArrayList<Course>();

    public static ArrayList<News> news = new ArrayList<News>();

//  MAIN METHOD
    public void begin() {
        System.out.println("Are you enter as admin or user?");

        String ans = sc.nextLine().toLowerCase();

        if (!(ans.equals("user") || ans.equals("admin"))) return;

        System.out.println("Enter your login and password (2 lines)");

        String login = sc.nextLine().toLowerCase();
        String password = sc.nextLine();

        switch (ans) {
            case "admin":
                sessionAdmin(login, password);
                break;
            case "user":
                sessionUser(login, password);
                break;
        }

        saveData();
    }

//  USER
    private void sessionUser(String login, String password) {
        ArrayList<User> list = new ArrayList<>();

        boolean found = false;

        list.addAll(teachers);
        list.addAll(students);
        list.addAll(managers);
        list.addAll(orManagers);
        list.addAll(executors);

        for (User u: list) {
            if (u.getLogin().equals(login) && u.getPassword().equals(password)) {
                user = u;

                found = true;

                Controller.writeLog("User " + u.getLogin() + " logged in!");

                switch (u.getClass().toString().split(" ")[1]) {
                    case "Student":
                        sessionStudent();
                        break;
                    case "Teacher":
                        sessionTeacher();
                        break;
                    case "Manager":
                        sessionManager();
                        break;
                    case "ORManager":
                        sessionORManager();
                        break;
                    case "Executor":
                        sessionExecutor();
                        break;
                }

                break;
            }
        }
        if (!found) {
            System.out.println("Invalid login or password!");
        }

    }

//  STUDENT
    private void sessionStudent() {
        student = (Student) user;
        mode = Mode.STUDENT;

        System.out.println("You are logged as student!");

        String ans = "";

        while (!ans.equals("exit")) {
            System.out.println("Choose the option you want");
            System.out.println("1. Courses");
            System.out.println("2. Transcript");
            System.out.println("3. News");
            System.out.println("4. Registration");
            System.out.println("5. Edit Info");


            ans = sc.nextLine();

            switch (ans) {
                case "1":
                    showCourses(Mode.STUDENT);
                    break;
                case "2":
                    studentTranscript();
                    break;
                case "3":
                    showNews();
                    break;
                case "4":
                    studentRegistration();
                    break;
                case "5":
                    studentInfo();
                    break;
            }
        }
    }

    private void studentCourse(int ind) {
        String ans = "";

        while (!ans.equals("exit")) {
            System.out.println("Choose the option you want");
            System.out.println("1. Show Marks");
            System.out.println("2. Show Course Files");
            System.out.println("3. Show Course Info");
            System.out.println("4. Show Teachers` Info");

            ans = sc.nextLine();

            switch (ans) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Not valid option!");
                    break;
            }
        }
    }

    private void studentTranscript() {

    }

    private void studentRegistration() {

    }

    private void studentInfo() {
        Mode curMode = Mode.STUDENT;

        String ans = "";

        while (!ans.equals("exit")) {
            System.out.println(student);

            System.out.println("What do you want to edit?");

            ans = sc.nextLine();

            switch (ans) {
                case "faculty":
                    alterFaculty(curMode, student);
                    break;
                case "degree":
                    alterDegree(student);
                    break;
                case "year of study":
                    alterYearOfStudy(student);
                    break;
                case "phone":
                    alterPhone(curMode, student);
                    break;
                case "email":
                    alterEmail(curMode, student);
                    break;
                case "birthdate":
                    alterBdate(curMode, student);
                    break;
                case "gender":
                    alterGender(curMode, student);
                    break;
            }
        }
    }

//  TEACHER
    private void sessionTeacher() {
        teacher = (Teacher) user;
        mode = Mode.TEACHER;

        curCourses = teacher.getCourses();

        String ans = "";

        System.out.println("You are logged as teacher!");

        while (!ans.equals("exit")) {
            System.out.println("Choose the option you want");
            System.out.println("1. Send Message");
            System.out.println("2. Show Messages");
            System.out.println("3. News");
            System.out.println("4. Courses");
            System.out.println("5. Send order");
            System.out.println("6. Edit Info");

            ans = sc.nextLine();

            switch (ans) {
                case "1":
                    writeMessage();
                    break;
                case "2":
                    showMessages();
                    break;
                case "3":
                    showNews();
                    break;
                case "4":
                    showCourses(Mode.TEACHER);
                    break;
                case "5":
                    sendOrder();
                    break;
                case "6":
                    teacherInfo();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Not valid option!");
                    break;
            }
        }
    }

    private void teacherCourse(int ind) {
        String ans = "";

        while (!ans.equals("exit")) {
            System.out.println("Choose the option you want");
            System.out.println("1. Add file");
            System.out.println("2. Delete file");
            System.out.println("3. Show students");
            System.out.println("4. Show Info");

            ans = sc.nextLine();

            switch (ans) {
                case "1":
                    teacherAddFile(ind);
                    break;
                case "2":
                    teacherDeleteFile(ind);
                    break;
                case "3":
                    showNews();
                    break;
                case "4":
                    System.out.println(curCourses.get(ind));
                    break;
                default:
                    System.out.println("Not valid option!");
                    break;
            }
        }
    }

    private void teacherAddFile(int ind) {
        System.out.println("Load the file you want to add");

        System.out.println("What`s its title?");
        String title = sc.nextLine();

        System.out.println("What`s its text?");
        String text = sc.nextLine();

        CourseFile courseFile = new CourseFile(title, text, teacher.getLogin());

        curCourses.get(ind).addFile(courseFile);

        System.out.println("Success!");
    }

    private void teacherDeleteFile(int ind) {
        System.out.println("What`s the title of file you want to delete?");

        String title = sc.nextLine();

        curCourses.get(ind).deleteFile(title);

        System.out.println("Success!");
    }

    private void sendOrder() {
        System.out.println("Write order you want to send");

        System.out.println("What`s its title?");
        String title = sc.nextLine();

        System.out.println("What`s its text?");
        String text = sc.nextLine();

        Order order = new Order(title, text, teacher.getLogin());

        teacher.sendOrder(order);
    }

    private void teacherInfo() {
        Mode curMode = Mode.TEACHER;

        String ans = "";

        while (!ans.equals("exit")) {
            System.out.println(teacher);
            System.out.println("What do you want to edit?");

            ans = sc.nextLine();

            switch (ans) {
                case "faculty":
                    alterFaculty(curMode, teacher);
                    break;
                case "position":
                    alterPosition(teacher);
                    break;
                case "phone":
                    alterPhone(curMode, teacher);
                    break;
                case "email":
                    alterEmail(curMode, teacher);
                    break;
                case "birthdate":
                    alterBdate(curMode, teacher);
                    break;
                case "gender":
                    alterGender(curMode, teacher);
                    break;
            }
        }
    }

//  STUDENT + TEACHER (MANAGING COURSE)
    private void showCourses(Mode mode) {
    String ans = "";

    while (!ans.equals("exit")) {
        System.out.println("Your courses:");

        for (int i = 0; i < curCourses.size(); ++i) {
            System.out.println((i + 1) + ". " + curCourses.get(i).getCourseName());
        }

        System.out.println("Select course you want");

        ans = sc.nextLine();

        try {
            int ind = Integer.decode(ans);

            ind--;

            if (ind > -1 && ind < curCourses.size()) {
                switch (mode) {
                    case TEACHER:
                        teacherCourse(ind);
                        break;
                    case STUDENT:
                        studentCourse(ind);
                        break;
                }
            }
            else {
                System.out.println("Wrong selection");
            }
        }
        catch (Exception e) {
            System.out.println("Wrong selection");
        }
    }
}

//  MANAGER
    private void sessionManager() {
        manager = (Manager) user;
        mode = Mode.MANAGER;

        String ans = "";

        System.out.println("You are logged as manager!");

        while (!ans.equals("exit")) {
            System.out.println("Choose the option you want");
            System.out.println("1. Send Message");
            System.out.println("2. Show Messages");
            System.out.println("3. News");
            System.out.println("4. Find Teacher");
            System.out.println("5. Find Student");
            System.out.println("6. Find Course");
            System.out.println("7. Add News");
            System.out.println("8. Edit Info");

            ans = sc.nextLine();

            switch (ans) {
                case "1":
                    writeMessage();
                    break;
                case "2":
                    showMessages();
                    break;
                case "3":
                    showNews();
                    break;
                case "4":
                    managerFindTeacher(Mode.MANAGER);
                    break;
                case "5":
                    managerFindStudent(Mode.MANAGER);
                    break;
                case "6":
                    managerFindCourse(Mode.MANAGER);
                    break;
                case "7":
                    managerWriteNews();
                    break;
                case "8":
                    managerInfo();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Not valid option!");
                    break;
            }
        }


    }

    private void managerWriteNews() {
        System.out.println("Write News` title");
        String title = sc.nextLine();

        System.out.println("Write News` text");
        String text = sc.nextLine();

        News news = new News(title, text, manager.getLogin(), manager.getFaculty());

        manager.addNews(news);
    }

    private void managerInfo() {
        Mode curMode = Mode.MANAGER;

        String ans = "";

        while (!ans.equals("exit")) {
            System.out.println(manager);
            System.out.println("What do you want to edit?");

            ans = sc.nextLine();

            switch (ans) {
                case "faculty":
                    alterFaculty(curMode, manager);
                    break;
                case "phone":
                    alterPhone(curMode, manager);
                    break;
                case "email":
                    alterEmail(curMode, manager);
                    break;
                case "birthdate":
                    alterBdate(curMode, manager);
                    break;
                case "gender":
                    alterGender(curMode, manager);
                    break;
            }
        }
    }

//  ORMANAGER
    private void sessionORManager() {
        orManager = (ORManager) user;
        mode = Mode.ORMANAGER;

        String ans = "";

        System.out.println("You are logged as OR manager!");

        while (!ans.equals("exit")) {
            System.out.println("Choose the option you want");
            System.out.println("1. Send Message");
            System.out.println("2. Show Messages");
            System.out.println("3. News");
            System.out.println("4. Find Teacher");
            System.out.println("5. Find Student");
            System.out.println("6. Find Course");
            System.out.println("7. Edit Info");

            ans = sc.nextLine();

            switch (ans) {
                case "1":
                    writeMessage();
                    break;
                case "2":
                    showMessages();
                    break;
                case "3":
                    showNews();
                    break;
                case "4":
                    managerFindTeacher(Mode.ORMANAGER);
                    break;
                case "5":
                    managerFindStudent(Mode.ORMANAGER);
                    break;
                case "6":
                    managerFindCourse(Mode.ORMANAGER);
                    break;
                case "7":
                    orManagerInfo();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Not valid option!");
                    break;
            }
        }
    }

    private void orManagerInfo() {
        System.out.println(orManager);
        Mode curMode = Mode.ORMANAGER;

        String ans = "";

        while (!ans.equals("exit")) {
            System.out.println("What do you want to edit?");

            ans = sc.nextLine();

            switch (ans) {
                case "phone":
                    alterPhone(curMode, orManager);
                    break;
                case "email":
                    alterEmail(curMode, orManager);
                    break;
                case "birthdate":
                    alterBdate(curMode, orManager);
                    break;
                case "gender":
                    alterGender(curMode, orManager);
                    break;
            }
        }
    }

//  SEARCHING (MANAGER + ORMANAGER)
    private void managerFindTeacher(Mode mode) {
        System.out.println("Enter teacher`s login");
        String login = sc.nextLine();

        Teacher searchTeacher = null;

        for (Teacher t: teachers) {
            if (t.getLogin().equals(login)) {
                searchTeacher = t;

                break;
            }
        }

        if (searchTeacher == null) {
            System.out.println("Teacher not found!");
        }
        else {
            System.out.println(searchTeacher);

            if (mode == Mode.MANAGER) {
                String ans = "";

                while (!ans.equals("exit")) {
                    System.out.println("Do you want to edit info?");

                    ans = sc.nextLine();

                    switch (ans) {
                        case "yes":
                            managerEditTeacher(searchTeacher);
                            break;
                        case "no":
                            return;
                        default:
                            System.out.println("Not valid option!");
                            break;
                    }

                }
            }
            else if (mode == Mode.ORMANAGER) {
                String ans = "";

                while (!ans.equals("exit")) {
                    System.out.println("Do you want to edit info or manage courses?");

                    ans = sc.nextLine();

                    switch (ans) {
                        case "edit info":
                            managerEditTeacher(searchTeacher);
                            break;
                        case "manage courses":
                            managerTeacherCourses(searchTeacher);
                            break;
                        case "exit":
                            return;
                        default:
                            System.out.println("Not valid option!");
                            break;
                    }
                }
            }
        }
    }

    private void managerEditTeacher(Teacher searchTeacher) {
        Mode curMode = Mode.TEACHER;

        String ans = "";

        while (!ans.equals("exit")) {
            System.out.println(searchTeacher);
            System.out.println("What do you want to edit?");

            ans = sc.nextLine();

            switch (ans) {
                case "faculty":
                    alterFaculty(curMode, searchTeacher);
                    break;
                case "position":
                    alterPosition(searchTeacher);
                    break;
                case "phone":
                    alterPhone(curMode, searchTeacher);
                    break;
                case "email":
                    alterEmail(curMode, searchTeacher);
                    break;
                case "birthdate":
                    alterBdate(curMode, searchTeacher);
                    break;
                case "gender":
                    alterGender(curMode, searchTeacher);
                    break;
            }
        }
    }

    private void managerTeacherCourses(Teacher searchTeacher) {
        String ans = "";

        while (!ans.equals("exit")) {
            System.out.println("Do you want to add or delete course?");

            ans = sc.nextLine();

            if (ans.equals("add") || ans.equals("delete")) {
                System.out.println("Enter course`s id");

                String id = sc.nextLine();

                boolean found = false;

                if (ans.equals("add")) {
                    for (Course c : courses) {
                        if (c.getId().equals(id)) {
                            found = true;

                            break;
                        }
                    }
                }
                else if (ans.equals("delete")) {
                    for (Course c: searchTeacher.getCourses()) {
                        if (c.getId().equals(id)) {
                            found = true;

                            break;
                        }
                    }
                }

                if (found) {
                    if (ans.equals("add")) {
                        searchTeacher.addCourses(id);
                    }
                    else if (ans.equals("delete")) {
                        searchTeacher.deleteCourse(id);
                    }

                    System.out.println("Success!");

                }
                else {
                    System.out.println("Course not found!");
                }

            }

            else if (ans.equals("exit")) {
                return;
            }

            else {
                System.out.println("Invalid option!");
            }

        }

    }

    private void managerFindStudent(Mode mode) {
        System.out.println("Enter student`s login");
        String login = sc.nextLine();

        Student searchStudent = null;

        for (Student s: students) {
            if (s.getLogin().equals(login)) {
                searchStudent = s;

                break;
            }
        }

        if (searchStudent == null) {
            System.out.println("Student not found!");
        }
        else {
            System.out.println(searchStudent);

            if (mode == Mode.MANAGER) {

                String ans = "";

                while (!ans.equals("exit")) {
                    System.out.println("Do you want to edit info?");

                    ans = sc.nextLine();

                    switch (ans) {
                        case "yes":
                            managerEditStudent(searchStudent);
                            break;
                        case "no":
                            return;
                        default:
                            System.out.println("Not valid option!");
                            break;
                    }

                }
            }
            else if (mode == Mode.ORMANAGER) {
                String ans = "";

                while (!ans.equals("exit")) {
                    System.out.println("Do you want to edit info or manage courses?");

                    ans = sc.nextLine();

                    switch (ans) {
                        case "edit info":
                            managerEditStudent(searchStudent);
                            break;
                        case "manage courses":
                            managerStudentCourses(searchStudent);
                            break;
                        case "exit":
                            return;
                        default:
                            System.out.println("Not valid option!");
                            break;
                    }
                }
            }
        }
    }

    private void managerEditStudent(Student searchStudent) {
        Mode curMode = Mode.STUDENT;

        String ans = "";

        while (!ans.equals("exit")) {
            System.out.println(searchStudent);
            System.out.println("What do you want to edit?");

            ans = sc.nextLine();

            switch (ans) {
                case "faculty":
                    alterFaculty(curMode, searchStudent);
                    break;
                case "degree":
                    alterDegree(searchStudent);
                    break;
                case "phone":
                    alterPhone(curMode, searchStudent);
                    break;
                case "email":
                    alterEmail(curMode, searchStudent);
                    break;
                case "birthdate":
                    alterBdate(curMode, searchStudent);
                    break;
                case "gender":
                    alterGender(curMode, searchStudent);
                    break;
            }
        }
    }

    private void managerStudentCourses(Student searchStudent) {
        String ans = "";

        while (!ans.equals("exit")) {
            System.out.println("Do you want to add or delete course?");

            ans = sc.nextLine();

            if (ans.equals("add") || ans.equals("delete")) {
                System.out.println("Enter course`s id");

                String id = sc.nextLine();

                boolean found = false;

                if (ans.equals("add")) {
                    for (Course c : courses) {
                        if (c.getId().equals(id)) {
                            found = true;

                            break;
                        }
                    }
                }
                else if (ans.equals("delete")) {
                    for (Course c: searchStudent.getCourses()) {
                        if (c.getId().equals(id)) {
                            found = true;

                            break;
                        }
                    }
                }

                if (found) {
                    if (ans.equals("add")) {
                        searchStudent.addCourses(id);
                    }
                    else if (ans.equals("delete")) {
                        searchStudent.deleteCourse(id);
                    }

                    System.out.println("Success!");

                }
                else {
                    System.out.println("Course not found!");
                }

            }

            else if (ans.equals("exit")) {
                return;
            }

            else {
                System.out.println("Invalid option!");
            }

        }

    }

    private void managerFindCourse(Mode mode) {

    }

    private void alterFaculty(Mode mode, User user) {
        System.out.println("Which faculty you select?");
        Faculty faculty;

        String ans = sc.nextLine();

        switch (ans) {
            case "FIT":
                faculty = Faculty.FIT;
                break;
            case "BS":
                faculty = Faculty.BS;
                break;
            case "CMC":
                faculty = Faculty.CMC;
                break;
            case "ISE":
                faculty = Faculty.ISE;
                break;
            case "KMA":
                faculty = Faculty.KMA;
                break;
            case "FOGI":
                faculty = Faculty.FOGI;
                break;
            case "CED":
                faculty = Faculty.CED;
                break;
            case "FGE":
                faculty = Faculty.FGE;
                break;
            default:
                System.out.println("Not valid faculty!");
                return;
        }

        switch (mode) {
            case STUDENT:
                Student student = (Student) user;
                student.setFaculty(faculty);
                break;
            case TEACHER:
                Teacher teacher = (Teacher) user;
                teacher.setFaculty(faculty);
                break;
            case MANAGER:
                Manager manager = (Manager) user;
                manager.setFaculty(faculty);
                break;
        }

        System.out.println("Success!");
    }

    private void alterDegree(Student student) {
        System.out.println("Which degree you select?");

        String ans = sc.nextLine().toLowerCase();

        Degree degree;

        switch (ans) {
            case "foundation":
                degree = Degree.FOUNDATION;
                break;
            case "bachelor":
                degree = Degree.BACHELOR;
                break;
            case "master":
                degree = Degree.MASTER;
                break;
            case "phd":
                degree = Degree.PHILOSOPHY_DOCTOR;
                break;
            default:
                System.out.println("Not valid degree!");
                return;
        }

        student.setDegree(degree);

        System.out.println("Success!");
    }

    private void alterYearOfStudy(Student student) {
        System.out.println("Which year do you study?");

        String ans = sc.nextLine();

        try {
            int ind = Integer.decode(ans);

            if (ind > -1 && ind < 8) {
                student.setYearOfStudy(ind);

                System.out.println("Success!");
            }
            else {
                System.out.println("Not valid year of study!");
            }
        }
        catch (Exception e) {
            System.out.println("Not valid year of study!");
        }
    }

    private void alterPosition(Teacher teacher) {
        System.out.println("Which position you select?");

        String ans = sc.nextLine().toLowerCase();

        TeacherPosition position;

        switch (ans) {
            case "professor":
                position = TeacherPosition.PROFESSOR;
                break;
            case "associate professor":
                position = TeacherPosition.ASSOCIATE_PROFESSOR;
                break;
            case "assistant professor":
                position = TeacherPosition.ASSISTANT_PROFESSOR;
                break;
            case "senior lecturer":
                position = TeacherPosition.SENIOR_LECTURER;
                break;
            case "lecturer":
                position = TeacherPosition.LECTURER;
                break;
            case "assistant":
                position = TeacherPosition.ASSISTANT;
                break;
            case "tutor":
                position = TeacherPosition.TUTOR;
                break;
            default:
                System.out.println("Not valid position!");
                return;
        }

        teacher.setPosition(position);

        System.out.println("Success!");
    }

//  EXECUTOR
    private void sessionExecutor() {
        executor = (Executor) user;
        mode = Mode.EXECUTOR;

        String ans = "";

        System.out.println("You are logged as executor!");

        while (!ans.equals("exit")) {

            System.out.println("Choose the option you want");
            System.out.println("1. Send Messages");
            System.out.println("2. Show Messages");
            System.out.println("3. News");
            System.out.println("4. Show new orders");
            System.out.println("5. Show not done orders");
            System.out.println("6. Show done orders");
            System.out.println("7. Show rejected orders");
            System.out.println("8. Edit Info");

            ans = sc.nextLine();

            switch (ans) {
                case "1":
                    writeMessage();
                    break;
                case "2":
                    showMessages();
                    break;
                case "3":
                    showNews();
                    break;
                case "4":
                    executorOrders(OrderStatus.NEW);
                    break;
                case "5":
                    executorOrders(OrderStatus.NOT_DONE);
                    break;
                case "6":
                    executorOrders(OrderStatus.DONE);
                    break;
                case "7":
                    executorOrders(OrderStatus.REJECTED);
                    break;
                case "8":
                    executorInfo();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Not valid option!");
                    break;
            }
        }
    }

    private void executorOrders(OrderStatus status) {
        String ans = "";

        while (!ans.equals("exit")) {
            ArrayList<Order> orders = executor.getOrders(status);

            System.out.println("Select one order!");

            for (int i = 0; i < orders.size(); ++i) {
                System.out.println((i + 1) + ". " + orders.get(i).getTitle());
            }

            ans = sc.nextLine();

            try {
                int ind = Integer.decode(ans);

                ind--;

                if (ind > -1 && ind < orders.size()) {
                    executorOrder(orders.get(ind));
                }
                else {
                    System.out.println("Wrong selection");
                }
            }
            catch (Exception e) {
                if (ans.equals("exit")) {
                    break;
                }

                System.out.println("Wrong selection");
            }
        }
    }

    private void executorOrder(Order order) {
        System.out.println(order);

        String ans = "";

        if (order.getStatus() == OrderStatus.NEW) {
            System.out.println("Accept this order or reject?");

            ans = sc.nextLine().toLowerCase();

            switch (ans) {
                case "accept":
                    executor.changeOrderStatus(order, OrderStatus.NOT_DONE);
                    break;
                case "reject":
                    executor.changeOrderStatus(order, OrderStatus.REJECTED);
                    break;
                default:
                    System.out.println("Not valid operation!");
                    break;
            }
        }

        else if (order.getStatus() == OrderStatus.NOT_DONE) {
            System.out.println("Have this order done?");

            ans = sc.nextLine().toLowerCase();

            switch (ans) {
                case "yes":
                    executor.changeOrderStatus(order, OrderStatus.DONE);
                case "no":
                    break;
                default:
                    System.out.println("Not valid operation!");
                    break;
            }
        }

        System.out.println("Success!");

        Executor.saveOrders();
    }

    private void executorInfo() {
        System.out.println(executor);
        Mode curMode = Mode.EXECUTOR;

        String ans = "";

        while (!ans.equals("exit")) {
            System.out.println("What do you want to edit?");

            ans = sc.nextLine();

            switch (ans) {
                case "phone":
                    alterPhone(curMode, executor);
                    break;
                case "email":
                    alterEmail(curMode, executor);
                    break;
                case "birthdate":
                    alterBdate(curMode, executor);
                    break;
                case "gender":
                    alterGender(curMode, executor);
                    break;
            }
        }
    }

//  ADMIN
    private void sessionAdmin(String login, String password) {
        admin = new Admin();

        if (admin.getLogin().equals(login) && admin.getPassword().equals(password)) {
            String ans = "";

            Controller.writeLog("Admin logged in!");

            while (!ans.equals("exit")) {
                System.out.println("Choose the option!");
                System.out.println("1. Add new user");
                System.out.println("2. Delete user");
                System.out.println("3. Show log file");

                ans = sc.nextLine();

                switch (ans) {
                    case "1":
                        adminAdd();
                        break;
                    case "2":
                        adminRemove();
                        break;
                    case "3":
                        adminLogs();
                        break;
                    case "exit":
                        return;
                    default:
                        System.out.println("Invalid option!");
                        break;
                }
            }


        }
        else {
            System.out.println("Invalid login or password!");
        }
    }

    private void adminAdd() {
        while (true) {
            System.out.println("Whom you want to add?");
            System.out.println("1. Student");
            System.out.println("2. Teacher");
            System.out.println("3. Manager");
            System.out.println("4. OR Manager");
            System.out.println("5. Executor");

            String ans = sc.nextLine();
            Mode mode;

            switch (ans) {
                case "1":
                    mode = Mode.STUDENT;
                    break;
                case "2":
                    mode = Mode.TEACHER;
                    break;
                case "3":
                    mode = Mode.MANAGER;
                    break;
                case "4":
                    mode = Mode.ORMANAGER;
                    break;
                case "5":
                    mode = Mode.EXECUTOR;
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Invalid option!");
                    continue;
            }

            System.out.println("Enter lastname");

            String lname = sc.nextLine();

            System.out.println("Enter firstname");

            String name = sc.nextLine();

            System.out.println("Enter login");

            String login = sc.nextLine();

            admin.addUser(lname, name, login, mode);
        }

    }

    private void adminRemove() {
        System.out.println("Enter user`s login you want to delete");

        String login = sc.nextLine();

        if (admin.deleteUser(login)) {
            System.out.println("Success!");
        }
        else {
            System.out.println("User not found!");
        }
    }

    private void adminLogs() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(PATH + LOG));

            String line = br.readLine();

            while (line != null) {
                System.out.println(line);

                line = br.readLine();
            }
        }
        catch (IOException e) {
            System.out.println(EXCEPT_IO);
        }
    }

//  GENERAL
    private void showNews() {
        int limit = 7;
        String ans = "";

        while (!ans.equals("exit")) {
            System.out.println("Select the news you want");

            for (int i = 0; i < news.size(); ++i) {
                System.out.println((i + 1) + ". " + news.get(news.size() - i - 1).getTitle());

                if (i == limit) break;
            }

            ans = sc.nextLine();

            try {
                int ind = Integer.decode(ans);

                ind--;

                System.out.println(ind);

                if (ind > -1 && ind < limit + 1) {
                    showNews(ind);
                }
                else {
                    System.out.println("Wrong selection");
                }
            }
            catch (Exception e) {
                if (ans.equals("exit")) {
                    break;
                }

                System.out.println("Wrong selection");
            }
        }


    }

    private void showNews(int ind) {
        News thisNews = news.get(news.size() - ind - 1);

        System.out.println(thisNews.getTitle());
        System.out.println(thisNews.getText());

        System.out.println(thisNews.getFaculty());
    }

    private void alterPhone(Mode mode, User user) {
        System.out.println("Enter you phone number");

        String ans = sc.nextLine();

        user.setPhone(ans);

        System.out.println("Success!");
    }

    private void alterEmail(Mode mode, User user) {
        System.out.println("Enter you email");

        String ans = sc.nextLine();

        user.setEmail(ans);

        System.out.println("Success!");
    }

    private void alterBdate(Mode mode, User user) {
        System.out.println("Enter birthdate`s day");
        String ans_day = sc.nextLine();

        System.out.println("Enter birthdate`s month");
        String ans_month = sc.nextLine();

        System.out.println("Enter birthdate`s year");
        String ans_year = sc.nextLine();

        try {
            int year = Integer.decode(ans_year);
            int month = Integer.decode(ans_month);
            int day = Integer.decode(ans_month);

            Date date = new Date(year, month, day);

            user.setBirthDate(date);

            System.out.println("Success!");
        }
        catch (Exception e) {
            System.out.println("Invalid date!");
        }
    }

    private void alterGender(Mode mode, User user) {
        System.out.println("Enter you phone number");
        Gender gender;

        String ans = sc.nextLine();

        switch (ans) {
            case "male":
                gender = Gender.MALE;
                break;
            case "female":
                gender = Gender.FEMALE;
                break;
            default:
                System.out.println("Not valid gender!");
                return;
        }

        user.setGender(gender);

        System.out.println("Success!");
    }

//  EMPLOYEE
    private void showMessages() {
        Employee employee;

        switch (mode) {
            case TEACHER:
                employee = teacher;
                break;
            case MANAGER:
                employee = manager;
                break;
            case ORMANAGER:
                employee = orManager;
                break;
            case EXECUTOR:
                employee = executor;
                break;
            default:
                return;
        }

        ArrayList<String> messages = employee.getMessages();

        String ans = "";

        while (!ans.equals("exit")) {
            System.out.println("Which message you want to show?");

            for (int i = 0; i< messages.size(); ++i) {
                System.out.println((i+1) + ". " + messages.get(i));
            }

            ans = sc.nextLine();

            try {
                int ind = Integer.decode(ans);

                ind--;

                if (ind > -1 && ind < messages.size()) {
                    System.out.println(employee.readMessage(ind));
                }
                else {
                    System.out.println("Wrong selection");
                }
            }
            catch (Exception e) {
                System.out.println("Wrong selection");
            }
        }

    }

    private void writeMessage() {
        Employee employee;

        switch (mode) {
            case TEACHER:
                employee = teacher;
                break;
            case MANAGER:
                employee = manager;
                break;
            case ORMANAGER:
                employee = orManager;
                break;
            case EXECUTOR:
                employee = executor;
                break;
            default:
                return;
        }

        System.out.println("Write reciever`s login");
        String login = sc.nextLine();

        System.out.println("Write message`s title");
        String title = sc.nextLine();

        System.out.println("Write message`s text");
        String text = sc.nextLine();

        Message message = new Message(title, text, employee.getLogin());

        if (employee.sendMessage(message, login)) {
            System.out.println("Message sent!");
        }
        else {
            System.out.println("Login not found!");
        }
    }

//  LOGS
    private static void writeLog(String msg) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(PATH + LOG, true));

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_PATTERN);

            bw.write(dtf.format(LocalDateTime.now())+ " - " + msg + "\n");

            bw.flush();
            bw.close();
        }
        catch (IOException e) {
            System.out.println(EXCEPT_IO);
        }
    }

//  SERIALIZATION
    private void loadData() {
        loadStudents();
        loadTeachers();
        loadExecutors();
        loadManagers();
        loadOrManagers();
    }

    private void loadStudents() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STUDENTS));

            students = (ArrayList<Student>) ois.readObject();

            ois.close();

        }
        catch (ClassNotFoundException e) {
            System.out.println(STUDENTS + ": " + EXCEPT_CLASS);
        }
        catch (FileNotFoundException e) {
            System.out.println(STUDENTS + ": " + EXCEPT_FILE);
        }
        catch (IOException e) {
            System.out.println(STUDENTS + ": " + EXCEPT_IO);
        }
    }

    private void loadTeachers() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TEACHERS));

            teachers = (ArrayList<Teacher>) ois.readObject();

            ois.close();

        }
        catch (ClassNotFoundException e) {
            System.out.println(TEACHERS + ": " + EXCEPT_CLASS);
        }
        catch (FileNotFoundException e) {
            System.out.println(TEACHERS + ": " + EXCEPT_FILE);
        }
        catch (IOException e) {
            System.out.println(TEACHERS + ": " + EXCEPT_IO);
        }
    }

    private void loadManagers() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MANAGERS));

            managers = (ArrayList<Manager>) ois.readObject();

            ois.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println(MANAGERS + ": " + EXCEPT_CLASS);
        }
        catch (FileNotFoundException e) {
            System.out.println(MANAGERS + ": " + EXCEPT_FILE);
        }
        catch (IOException e) {
            System.out.println(MANAGERS + ": " + EXCEPT_IO);
        }
    }

    private void loadOrManagers() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ORMANAGERS));

            orManagers = (ArrayList<ORManager>) ois.readObject();

            ois.close();

        }
        catch (ClassNotFoundException e) {
            System.out.println(ORMANAGERS + ": " + EXCEPT_CLASS);
        }
        catch (FileNotFoundException e) {
            System.out.println(ORMANAGERS + ": " + EXCEPT_FILE);
        }
        catch (IOException e) {
            System.out.println(ORMANAGERS + ": " + EXCEPT_IO);
        }
    }

    private void loadExecutors() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(EXECUTORS));

            executors = (ArrayList<Executor>) ois.readObject();

            ois.close();

        }
        catch (ClassNotFoundException e) {
            System.out.println(EXECUTORS + ": " + EXCEPT_CLASS);
        }
        catch (FileNotFoundException e) {
            System.out.println(EXECUTORS + ": " + EXCEPT_FILE);
        }
        catch (IOException e) {
            System.out.println(EXECUTORS + ": " + EXCEPT_IO);
        }
    }

//  DESERIALIZAION
    private static void saveData() {
        saveManagers();
        saveOrManagers();
        saveTeachers();
        saveStudents();
        saveExecutors();
    }

    private static void saveStudents() {
        try {
            ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream(STUDENTS));

            oot.writeObject(students);

            oot.flush();
            oot.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(STUDENTS + ": " + EXCEPT_FILE);
        }
        catch (IOException e) {
            System.out.println(STUDENTS + ": " + EXCEPT_IO);
        }
    }

    private static void saveTeachers() {
        try {
            ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream(TEACHERS));

            oot.writeObject(teachers);

            oot.flush();
            oot.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(TEACHERS + ": " + EXCEPT_FILE);
        }
        catch (IOException e) {
            System.out.println(TEACHERS + ": " + EXCEPT_IO);
        }
    }

    private static void saveManagers() {
        try {
            ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream(MANAGERS));

            oot.writeObject(managers);

            oot.flush();
            oot.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(MANAGERS + ": " + EXCEPT_FILE);
        }
        catch (IOException e) {
            System.out.println(MANAGERS + ": " + EXCEPT_IO);
        }
    }

    private static void saveOrManagers() {
        try {
            ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream(ORMANAGERS));

            oot.writeObject(orManagers);

            oot.flush();
            oot.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(ORMANAGERS + ": " + EXCEPT_FILE);
        }
        catch (IOException e) {
            System.out.println(ORMANAGERS + ": " + EXCEPT_IO);
        }
    }

    private static void saveExecutors() {
        try {
            ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream(EXECUTORS));

            oot.writeObject(executors);

            oot.flush();
            oot.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(EXECUTORS + ": " + EXCEPT_FILE);
        }
        catch (IOException e) {
            System.out.println(EXECUTORS + ": " + EXCEPT_IO);
        }
    }

}
