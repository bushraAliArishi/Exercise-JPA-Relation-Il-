package com.example.school.service;

import com.example.school.ApiResponse.ApiException;
import com.example.school.Model.Course;
import com.example.school.Model.Teacher;
import com.example.school.Repository.CourseRepository;
import com.example.school.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public List<Course> getAll(){
        return courseRepository.findAll();
    }
    public Course getById(Integer id){
        return courseRepository.findCourseById(id);
    }

    public void addCourse (Course course){
        courseRepository.save(course);
    }

    public void assignCourseToTeacher(Integer teacher_id,Integer course_id){
        Teacher teacher=teacherRepository.findTeacherById(teacher_id);
        Course course =courseRepository.findCourseById(course_id);

        if (teacher==null || course==null){

            throw new ApiException("cant assign");
        }
        course.setTeacher(teacher);
        courseRepository.save(course);
    }
    public void updateCourse(Integer id, Course course){
        Course updatedCourse =courseRepository.findCourseById(id);
        if (updatedCourse==null){
            throw new ApiException(" not found ");
        }

        updatedCourse.setTeacher(course.getTeacher());
        updatedCourse.setName(course.getName());
        courseRepository.save(updatedCourse);
    }
    public void deleteCourse(Integer id){
        Course updatedCourse =courseRepository.findCourseById(id);
        if (updatedCourse==null){
            throw new ApiException(" not found ");
        }

        courseRepository.delete(updatedCourse);
    }
}
