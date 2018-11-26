import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        //Controller controller = new Controller();
        //controller.begin();
        Student s = new Student("Absatov", "Adilet", "a_absatov");
        s.setFaculty(Faculty.FIT);
        s.setDegree(Degree.BACHELOR);
        s.incrementYearOfStudy();
        s.setGender(Gender.MALE);
        s.setEmail("adl.absatov@gmail.com");
        s.setPhone("+77019798073");
        System.out.println(s.toString());
    }
}