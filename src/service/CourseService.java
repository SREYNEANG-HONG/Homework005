package service;

import model.Course;

import java.util.List;

public interface CourseService {
    void addNewCourse(String courseTitle , String [] instructorName , String [] courseRequirement);
    void listAllCourses();
    void findCourseById(Integer courseId);

//    List<Course> listAllCourse();
//    List<Course> searchStudentByName(String name);
    void findCourseByName(String courseName);
//    void removeCourseById(Integer courseId);
}
