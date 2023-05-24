package ru.hogwarts.school.controller;


import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    private  final StudentService studentService;

    @GetMapping(path = "/{id}")
    public Optional<Student> getStudentById(@PathVariable() long id) {
        return studentService.getById(id);
    }

    @GetMapping()
    public Collection<Student> getAllStudents
            (@RequestParam (value = "age", required = false) Integer age) {
        return Optional.ofNullable(age)
                .map(a -> studentService.getAllById(age))
                .orElseGet(studentService::getAll);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping(path = "/{id}")
    public Student updateStudent(@PathVariable long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteStudent(@PathVariable long id) {
        studentService.deleteStudentById(id);
    }

}
