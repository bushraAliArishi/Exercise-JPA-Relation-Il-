package com.example.school.Service;

import com.example.school.Model.Teacher;
import com.example.school.Repository.AddressRepository;
import com.example.school.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final AddressRepository addressRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Integer id) {
        return teacherRepository.findTeacherById(id);
    }

    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void updateTeacher(Integer id, Teacher teacher) {
        Teacher existingTeacher = teacherRepository.findTeacherById(id);
        if (existingTeacher == null) {
            throw new RuntimeException("Teacher not found");
        }
        existingTeacher.setName(teacher.getName());
        existingTeacher.setAge(teacher.getAge());
        existingTeacher.setEmail(teacher.getEmail());
        existingTeacher.setSalary(teacher.getSalary());
        teacherRepository.save(existingTeacher);
    }

    public void deleteTeacher(Integer id) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        if (teacher == null) {
            throw new RuntimeException("Teacher not found");
        }
        teacherRepository.delete(teacher);
        addressRepository.delete(addressRepository.findAddressByTeacher_Id(teacher.getId()));
    }
}
