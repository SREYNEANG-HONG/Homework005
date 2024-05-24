package service;

import exception.CourseNotFoundException;
import model.Course;

import java.util.List;

public interface CourseService {
    String formatLocalDate();
//    void addNewCourse(String courseTitle , String [] instructorName , String [] courseRequirement);
void addNewCourse();
    List<Course> getAllCourses();
    void listAllCourses();
    Course findCourseById(Integer courseId) throws CourseNotFoundException;
    Course findCourseByName(String courseName) throws CourseNotFoundException;
    void removeCourseById(Integer courseId) throws CourseNotFoundException;

}
