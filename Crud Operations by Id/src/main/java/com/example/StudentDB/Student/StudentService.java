package com.example.StudentDB.Student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        Optional<Student> emailCheck = studentRepository.findStudentByEmail(student.getEmail());
        if(emailCheck.isPresent()) throw new IllegalStateException("Email is already taken");
        else studentRepository.save(student);
    }

    public void deleteStudentById(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudentById(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Id is not found"));
        Optional<Student> emailFound = studentRepository.findStudentByEmail(email);
        if(name.length() > 0 && !Objects.equals(name, student.getName())) student.setName(name);
        if(emailFound.isPresent()) throw new IllegalStateException("Email is already exist");
        else student.setEmail(email);
    }

    public Optional<Student> getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Id is not found"));
        return studentRepository.findById(studentId);
    }

    public Optional<Student> getStudentByName(String studentName) {
        return studentRepository.findStudentByName(studentName);
    }

    public Optional<Student> getStudentByEmail(String studentEmail) {
        return studentRepository.findStudentByEmail(studentEmail);
    }

    public void deleteAll() {
        studentRepository.deleteAll();
    }
}
