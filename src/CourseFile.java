public class CourseFile {
    private String title, text;

    public CourseFile(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() { return title; }

    public String getText() { return text; }

    @Override
    public String toString() {
        return title + "\n\n" + text;
    }
}
