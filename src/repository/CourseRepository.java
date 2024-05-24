package repository;

import exception.CourseNotFoundException;
import model.Course;
import service.CourseService;
import service.CourseServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CourseRepository {
    static CourseService courseService = new CourseServiceImp();
    static List<Course> courseList = new ArrayList<>(List.of(
            new Course(Course.generateId(), "Spring Boot", new String[]{"Hana", "Kokun"}, new String[]{"Basic OOP and Java"}, courseService.formatLocalDate())
    ));
    public static void addCourse() {
        try {
            int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
            System.out.print("[+] Insert course title: ");
            String title = new Scanner(System.in).nextLine();
            for (int i = 0; i < title.length(); i++) {
                if (Character.isDigit(title.charAt(i))) {
                    throw new CourseNotFoundException("Invalid course title: " + title);
                }
            }


            System.out.println("[!] You can insert multi instructor name by splitting [,] ".toUpperCase(Locale.ROOT));
            System.out.print("[+] Insert Instructor Name : ");
            String[] instructors = new Scanner(System.in).nextLine().split(",");
            for (int number : numbers) {
                for (String instructor : instructors) {
                    if (instructor.contains(String.valueOf(number))) {
                        throw new CourseNotFoundException("Invalid input instructor: " + instructor);
                    }
                }
            }

            System.out.println("[!] You can insert multi Course Requirement by splitting [,] ".toUpperCase(Locale.ROOT));
            System.out.print("[+] Insert Course Requirement : ");
            String[] requirements = new Scanner(System.in).nextLine().split(",");
            for (int number : numbers) {
                for (String requirement : requirements) {
                    if (requirement.contains(String.valueOf(number))) {
                        throw new CourseNotFoundException("Invalid input instructor: " + requirement);
                    }
                }
            }
            courseList.add(new Course(Course.generateId(), title, instructors, requirements, courseService.formatLocalDate()));
        } catch (CourseNotFoundException courseNotFoundException) {
            System.out.println(courseNotFoundException.getMessage());
        }
    }

    public static List<Course> getAllCourses() {
        return courseList;
    }

}
