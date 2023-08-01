package com.example.StudentDB.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/student")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path = "{studentId}")
    public Optional<Student> getStudentById(@PathVariable("studentId") Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping(path = "/name/{studentName}")
    public Optional<Student> getStudentByName(@PathVariable("studentName") String studentName) {
        return studentService.getStudentByName(studentName);
    }

    @GetMapping(path = "/email/{studentEmail}")
    public Optional<Student> getStudentByEmail(@PathVariable("studentEmail") String studentEmail) {
        return studentService.getStudentByEmail(studentEmail);
    }

    @PostMapping
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudentById(studentId);
    }

    @DeleteMapping(path = "/deleteAll")
    public void deleteAll() {
        studentService.deleteAll();
    }

    @PutMapping(path = "{studentId}")
    public void updateStudentById(@PathVariable("studentId") Long studentId, @RequestParam(required = false) String name, @RequestParam(required = false) String email) {
        studentService.updateStudentById(studentId, name, email);
    }
}
