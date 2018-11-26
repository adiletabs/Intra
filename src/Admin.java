import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

public class Admin implements Serializable {
    private String login;
    private String password;

    {
        login = "";
        password = "";
    }

    private final String PATH = "/home/dontnicemebr0/IdeaProjects/Intra/src/";
    private final String FILE = "admin.txt";

    public Admin() {
        getData();
    }

    public void addUser(String lastName, String name, String newLogin, Mode mode) {
        switch (mode) {
            case STUDENT:
                addStudent(lastName, name, newLogin);
                break;
            case TEACHER:
                addTeacher(lastName, name, newLogin);
                break;
            case MANAGER:
                addStudent(lastName, name, newLogin);
                break;
            case ORMANAGER:
                addTeacher(lastName, name, newLogin);
                break;
            case EXECUTOR:
                addStudent(lastName, name, newLogin);
                break;
        }
    }

    public boolean deleteUser(String login) {
        for (User u: Controller.students) {
            if (u.getLogin().equals(login)) {
                Controller.students.remove(u);
                return true;
            }
        }
        for (User u: Controller.managers) {
            if (u.getLogin().equals(login)) {
                Controller.managers.remove(u);
                return true;
            }
        }
        for (User u: Controller.orManagers) {
            if (u.getLogin().equals(login)) {
                Controller.orManagers.remove(u);
                return true;
            }
        }
        for (User u: Controller.executors) {
            if (u.getLogin().equals(login)) {
                Controller.executors.remove(u);
                return true;
            }
        }
        for (User u: Controller.teachers) {
            if (u.getLogin().equals(login)) {
                Controller.teachers.remove(u);
                return true;
            }
        }
        return false;
    }

    private void addExecutor(String lastName, String name, String newLogin) {
        Executor executor = new Executor(lastName, name, newLogin);

        if (!Controller.executors.contains(executor)) {
            Controller.executors.add(executor);
        }
        else {
            System.out.println(executor.getClass().toString() + " already exists!");
        }
    }

    private void addManager(String lastName, String name, String newLogin) {
        Manager manager = new Manager(lastName, name, newLogin);

        if (!Controller.managers.contains(manager)) {
            Controller.managers.add(manager);
        }
        else {
            System.out.println(manager.getClass().toString() + " already exists!");
        }
    }

    private void addORManager(String lastName, String name, String newLogin) {
        ORManager orManager = new ORManager(lastName, name, newLogin);

        if (!Controller.orManagers.contains(orManager)) {
            Controller.orManagers.add(orManager);
        }
        else {
            System.out.println(orManager.getClass().toString() + " already exists!");
        }
    }

    private void addTeacher(String lastName, String name, String newLogin) {
        Teacher teacher = new Teacher(lastName, name, newLogin);

        if (!Controller.teachers.contains(teacher)) {
            Controller.teachers.add(teacher);
        }
        else {
            System.out.println(teacher.getClass().toString() + " already exists!");
        }
    }

    private void addStudent(String lastName, String name, String newLogin) {
        Student student = new Student(lastName, name, newLogin);

        if (!Controller.students.contains(student)) {
            Controller.students.add(student);
        }
        else {
            System.out.println(student.getClass().toString() + " already exists!");
        }
    }

    private void getData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(PATH + FILE));

            String firstLine = br.readLine();
            String secondLine = br.readLine();

            String r_login, r_password;

            try {
                r_login = firstLine.split(" ")[1];
                r_password = secondLine.split(" ")[1];
                login = r_login;
                password = r_password;
            }
            catch (Exception e) {
                System.out.println("Wrong data in " + FILE);
            }
        }
        catch (IOException e) {
            System.out.println("Cannot read from " + FILE);
        }
    }

    public String toString() { return "Admin login: " + login; }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Admin)) return false;
        Admin a = (Admin) obj;
        return !login.equals("") && a.login.equals(login);
    }

    public int hashCode() { return login.hashCode(); }
}