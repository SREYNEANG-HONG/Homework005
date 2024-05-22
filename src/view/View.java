package view;

import service.CourseService;
import service.CourseServiceImp;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class View {
    public static void line(int a, char b) {
        for (int i = 0; i <= a; i++) {
            System.out.print(b);
        }
        System.out.println();
    }


    CourseServiceImp courseServiceImp = new CourseServiceImp();
    static CourseService courseService = new CourseServiceImp();

    public static void menu_op(){
        System.out.println("1. Add new Course");
        System.out.println("2. List Courses");
        System.out.println("3. Find Course By ID");
        System.out.println("4. Find Course By Title");
        System.out.println("5. Remove Course By ID");
        System.out.println("0. Exit");

    }
    public static void menu() {
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
                    System.out.print("[+] Insert course title: ");
                    String courseTitle = input.nextLine();

//                    System.out.print("[+] Insert Instructor Name: ");
//                    String instructorName[] = new String[]{new Scanner(System.in).nextLine().trim()};

                    System.out.println("[!] You can insert multi instructor name by splitting [,] ".toUpperCase(Locale.ROOT));
                    System.out.print("[+] Insert Instructor Name : ");
                    String inName = new Scanner(System.in).nextLine();
                    String [] instructorName = inName.split(",");
                    for(int i=0;i<instructorName.length;i++){
                        instructorName[i] = instructorName[i].trim();
                    }

//                    System.out.print("[+] Insert Course Requirement: ");
//                    String courseRequirement[] = new String[]{new Scanner(System.in).nextLine().trim()};

                    System.out.println("[!] You can insert multi Course Requirement by splitting [,] ".toUpperCase(Locale.ROOT));
                    System.out.print("[+] Insert Course Requirement : ");
                    String courseRequire = new Scanner(System.in).nextLine();
                    String [] courseRequirement = courseRequire.split(",");
                    for(int i=0;i<courseRequirement.length;i++){
                        courseRequirement[i] = courseRequirement[i].trim();
                    }

                    courseService.addNewCourse(courseTitle, instructorName,courseRequirement);
                    break;
                }
                case 2: {
                    courseService.listAllCourses();
                    break;
                }
                case 3: {
//                    System.out.print("[+] Insert course ID: ");
                    System.out.print("[+] Find course by ID: ");
                    Integer courseId = input.nextInt();
                    courseService.findCourseById(courseId);
                    break;
                }
                case 4: {
//                    System.out.print("[+] Find Course By Title: ");
                    System.out.print("[+] Find course by Name: ");
                    String courseName = input.nextLine();
                    courseService.findCourseByName(courseName);
                    break;
                }
                case 5: {
                    System.out.print("[+] Remove Course By ID: ");
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