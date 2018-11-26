import java.util.ArrayList;

public interface ManagingFiles {
    ArrayList<CourseFile> getFiles();

    CourseFile getFile(String title);

    void addFile(CourseFile courseFile);

    void deleteFile(String title);
}