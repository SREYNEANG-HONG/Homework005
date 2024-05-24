package service;

import exception.CourseNotFoundException;
import model.Course;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import repository.CourseRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CourseServiceImp implements CourseService {


    public List<Course> courses;

    public CourseServiceImp() {
        this.courses = new ArrayList<>();
    }

    @Override
    public String formatLocalDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM d HH:mm:ss yyyy", Locale.ENGLISH);
        String dateTime = LocalDateTime.now().format(formatter);
        return dateTime;
    }

    @Override
    public void addNewCourse() {
        CourseRepository.addCourse();
    }

    @Override
    public List<Course> getAllCourses() {
        return CourseRepository.getAllCourses();
    }

//    @Override
//    public void addNewCourse(String courseTitle , String[] instructorName , String[] courseRequirement) {
//
//
////        public void addNewCourse() {
////            CourseRepository.addCourse();
////        }
//
//        Integer id = new Random().nextInt(1000);
//        String courseStartedDate = "2024-05-23";
//
//        Course course = new Course(id, courseTitle, instructorName , courseRequirement , courseStartedDate);
//        courses.add(course);
//
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream("course.csv", true);
//            String content = course.getId() + ","
//                    + course.getCourseTitle() + ","
//                    + course.getInstructorName() + ","
//                    + course.getCourseRequirement() + ","
//                    + course.getStartDate() + "\n";
//            fileOutputStream.write(content.getBytes(StandardCharsets.UTF_8));
//            fileOutputStream.close();
//        } catch (Exception ignore) {
//        }
//    }
//

//
//    @Override
//    public void listAllCourses() {
//
//        try {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader("course.csv"));
//            String data;
//            String[] contents;
//            courses.clear();
//            while ((data = bufferedReader.readLine()) != null) {
//                if (!data.trim().isEmpty()) {
//                    contents = data.split(",");
//                    String[] instructors = contents[2].split(",");
//                    String[] courseRequire = contents[3].split(",");
//                    if (contents.length == 5) { // Ensure correct number of elements
//                        courses.add(new Course(Integer.parseInt(contents[0]), contents[1], instructors, courseRequire, contents[4]));
//                    } else {
//                        System.out.println("Invalid data format: " + data);
//                    }
//                }
//            }
//            bufferedReader.close();
//        } catch (Exception ingore) {
//        }
//
//
//
//        Table table = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);
//
//        table.addCell("Course ID");
//        table.addCell("Course Title");
//        table.addCell("Instructor Name");
//        table.addCell("Course Requirement");
//        table.addCell("Start Date");
//
//        for (Course course : courses) {
//            table.addCell(course.getId().toString());
//            table.addCell(course.getCourseTitle());
//            table.addCell(Arrays.toString(course.getInstructorName()).toString());
//            table.addCell(Arrays.toString(course.getCourseRequirement()).toString());
//            table.addCell(course.getStartDate().toString());
//        }
//        System.out.println(table.render());
//    }

    @Override
    public void listAllCourses() {
        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        for (int i = 0; i < 5; i++) {
            table.setColumnWidth(i, 30, 30);
        }
        System.out.println("=".repeat(160));
        if (!(CourseRepository.getAllCourses().isEmpty())) {
            table.addCell("Course ID", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell("Title", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell("Instructor", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell("Requirement", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell("Start Date", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            for (Course course : CourseRepository.getAllCourses()) {
                table.addCell(String.valueOf(course.getId()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(course.getCourseTitle(), new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(Arrays.toString(course.getInstructorName()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(Arrays.toString(course.getCourseRequirement()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(course.getStartDate(), new CellStyle(CellStyle.HorizontalAlign.CENTER));
            }
            System.out.println(table.render());
        } else {
            System.out.println("[+] No Course !");
            ;
        }
        System.out.println("=".repeat(160));
    }


    @Override
    public Course findCourseById(Integer courseId) throws CourseNotFoundException {
        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        for (int i = 0; i < 5; i++) {
            table.setColumnWidth(i, 30, 30);
        }
        var courses = CourseRepository.getAllCourses().stream().filter(e -> e.getId().toString().equals(courseId.toString().trim())).toList();
        if (courses.isEmpty()) {
            throw new CourseNotFoundException("[!] Course not found with ID: " + courseId);
        } else {
            table.addCell("Course ID", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell("Course Title", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell("Instructor name", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell("Course Requirement", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell("Start Date", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            for (Course course : courses) {
                table.addCell(String.valueOf(course.getId()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(course.getCourseTitle(), new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(Arrays.toString(course.getInstructorName()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(Arrays.toString(course.getCourseRequirement()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(course.getStartDate(), new CellStyle(CellStyle.HorizontalAlign.CENTER));
            }
            System.out.println(table.render());
        }
        return courses.getFirst();
    }

    @Override
    public Course findCourseByName(String courseName) throws CourseNotFoundException {
        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        for (int i = 0; i < 5; i++) {
            table.setColumnWidth(i, 30, 30);
        }
        var courses = CourseRepository.getAllCourses().stream().filter(e -> e.getCourseTitle().toLowerCase().contains(courseName.toLowerCase())).toList();
        if (courses.isEmpty()) {
            throw new CourseNotFoundException("[!] Course not found with title: " + courseName);
        } else {
            table.addCell("Course ID", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell("Course Title", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell("Instructor name", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell("Course Requirement", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell("Start Date", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            for (Course course : courses) {
                table.addCell(String.valueOf(course.getId()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(course.getCourseTitle(), new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(Arrays.toString(course.getInstructorName()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(Arrays.toString(course.getCourseRequirement()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(course.getStartDate(), new CellStyle(CellStyle.HorizontalAlign.CENTER));
            }
            System.out.println(table.render());
        }
        return courses.getFirst();
    }


    @Override
    public void removeCourseById(Integer courseId) throws CourseNotFoundException {
        List<Course> courses = CourseRepository.getAllCourses();
        List<Course> removeCourse = courses.stream().filter(a -> a.getId().toString().equals(courseId.toString().trim())).toList();
        courses.removeAll(removeCourse);
        if (!(removeCourse.isEmpty())) {
            System.out.println("[+] Removed course with " + courseId + " successfully!");
        } else {
            throw new CourseNotFoundException("[!] Course not found with ID: " + courseId);
        }


    }


}