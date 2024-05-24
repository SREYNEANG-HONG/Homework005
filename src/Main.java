import exception.CourseNotFoundException;
import view.View;

public class Main {
    public static void main(String[] args) throws CourseNotFoundException {
        View view = new View();
        view.menu();
    }
}