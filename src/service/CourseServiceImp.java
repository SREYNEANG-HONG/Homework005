package service;

import model.Course;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class CourseServiceImp implements CourseService {

    public List<Course> tempoList = new ArrayList<>();
    public List<Course> courses;

    public CourseServiceImp() {
        this.courses = new ArrayList<>();
    }

    @Override
    public void addNewCourse(String courseTitle , String[] instructorName , String[] courseRequirement) {


        Integer id = new Random().nextInt(1000);
        String courseStartedDate = "2024-05-23";

        Course course = new Course(id, courseTitle, instructorName , courseRequirement , courseStartedDate);
        courses.add(course);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("course.csv", true);
            String content = course.getId() + ","
                    + course.getCourseTitle() + ","
                    + course.getInstructorName() + ","
                    + course.getCourseRequirement() + ","
                    + course.getStartDate() + "\n";
            fileOutputStream.write(content.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.close();
        } catch (Exception ignore) {
        }
    }



    @Override
    public void listAllCourses() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("course.csv"));
            String data;
            String[] contents;
            courses.clear();
            while ((data = bufferedReader.readLine()) != null) {
                if (!data.trim().isEmpty()) {
                    contents = data.split(",");
                    String[] instructors = contents[2].split(",");
                    String[] courseRequire = contents[3].split(",");
                    if (contents.length == 5) { // Ensure correct number of elements
                        courses.add(new Course(Integer.parseInt(contents[0]), contents[1], instructors, courseRequire, contents[4]));
                    } else {
                        System.out.println("Invalid data format: " + data);
                    }
                }
            }
            bufferedReader.close();
        } catch (Exception ingore) {
        }



        Table table = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);

        table.addCell("Course ID");
        table.addCell("Course Title");
        table.addCell("Instructor Name");
        table.addCell("Course Requirement");
        table.addCell("Start Date");

        for (Course course : courses) {
            table.addCell(course.getId().toString());
            table.addCell(course.getCourseTitle());
            table.addCell(Arrays.toString(course.getInstructorName()).toString());
            table.addCell(Arrays.toString(course.getCourseRequirement()).toString());
            table.addCell(course.getStartDate().toString());
        }
        System.out.println(table.render());
    }

    @Override
    public void findCourseById(Integer courseId) {

        if (courseId <= 0) {
            System.out.println("Invalid course ID.");
            return;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("course.csv"));
            String data;
            String[] contents;
            Course foundCourse = null;
            while ((data = bufferedReader.readLine()) != null) {
                contents = data.split(",");
                if (contents.length == 5 && Integer.parseInt(contents[0]) == courseId) {
                    foundCourse = new Course(Integer.parseInt(contents[0]), contents[1], contents[2].split(","), contents[3].split(","), contents[4]);
                    break;
                }
            }
            bufferedReader.close();
            if (foundCourse != null) {
                Table table = new Table(5);
                table.addCell("Course ID");
                table.addCell("Course Title");
                table.addCell("Instructor Name");
                table.addCell("Course Requirement");
                table.addCell("Start Date");

                table.addCell(foundCourse.getId().toString());
                table.addCell(foundCourse.getCourseTitle());
                table.addCell(Arrays.toString(foundCourse.getInstructorName()).toString());
                table.addCell(Arrays.toString(foundCourse.getCourseRequirement()).toString());
                table.addCell(foundCourse.getStartDate().toString());

                System.out.println(table.render());
            } else {
                System.out.println("Course with this ID not found.");
            }
        } catch (Exception ingore) {
        }
    }

    @Override
    public void findCourseByName(String courseName) {

    }

//    public List<Course> findCourseByCourseTitle(String courseTitle) {
//        return courses.stream()
//                .filter(e->e.getCourseTitle().toLowerCase().contains(e.getCourseTitle().toLowerCase())
//                        || e.getCourseTitle().toLowerCase().equalsIgnoreCase(e.getCourseTitle()))
//                .collect(Collectors.toList());
//    }
}
