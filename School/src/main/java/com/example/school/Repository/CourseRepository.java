package com.example.school.Repository;

import com.example.school.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
    List<Course> findAll();
    Course findCourseById(Integer id );
    List<Course>findCoursesByTeacher_Id(Integer id);
}
