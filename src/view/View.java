package view;

import exception.CourseNotFoundException;
import service.CourseService;
import service.CourseServiceImp;
import java.util.Scanner;

public class View {
    public static void line(int a, char b) {
        for (int i = 0; i <= a; i++) {
            System.out.print(b);
        }
        System.out.println();
    }

    static CourseService courseService = new CourseServiceImp();

    public static void menu_op(){
        System.out.println("1. Add new Course");
        System.out.println("2. List Courses");
        System.out.println("3. Find Course By ID");
        System.out.println("4. Find Course By Title");
        System.out.println("5. Remove Course By ID");
        System.out.println("0. Exit");

    }
    public static void menu() throws CourseNotFoundException {
        Scanner input = new Scanner(System.in);
        int op;
        do {
            line(34,'=');
            menu_op();
            line(34,'=');
            System.out.print("[+] Insert Option : ");

            op = input.nextInt();
            input.nextLine();
            switch (op) {
                case 1: {
                    courseService.addNewCourse();
                    break;
                }
                case 2: {
                    courseService.listAllCourses();
                    break;
                }
                case 3: {
                    System.out.print("[+] Find course by ID: ");
                    Integer courseId = input.nextInt();
                    courseService.findCourseById(courseId);
                    break;
                }
                case 4: {
                    System.out.print("[+] Find course by Name: ");
                    String courseName = input.nextLine();
                    courseService.findCourseByName(courseName);
                    break;
                }
                case 5: {
                    System.out.print("[+] Remove Course By ID: ");
                    Integer id = new Scanner(System.in).nextInt();
                    courseService.removeCourseById(id);
                    break;
                }
                case 0: {
                    System.out.println("Exiting... <3");
                    System.exit(0);
                }
                case 99: {
                    System.out.println("Exiting... <3");
                    System.exit(0);
                }
                default: {
                    System.out.println("Please choose again:");
                    break;
                }
            }
        } while (op != 0 && op != 99);
    }
}