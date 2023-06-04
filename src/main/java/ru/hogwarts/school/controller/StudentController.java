package ru.hogwarts.school.controller;


import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.entity.StudentsByCategory;
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

    @GetMapping("/findByAgeBetween")
    public Collection<Student> getStudentByAgeBetween(@RequestParam("min") int min,
                                                      @RequestParam("max") int max) {
        return studentService.findStudentByAgeBetween(min, max);
    }

    @GetMapping
    public Collection<Student> getAllStudents
            (@RequestParam (value = "age", required = false) Integer age) {
        return Optional.ofNullable(age)
                .map(a -> studentService.getAllByAge(age))
                .orElseGet(studentService::getAll);
    }

    @GetMapping(path = "/{id}/faculty")
    public Optional<Faculty> getFacultyOfStudentById(@PathVariable long id) {
        return studentService.getFacultyOfStudentById(id);
    }

    @GetMapping(path = "/getAllStudents")
    public Collection<StudentsByCategory> getAllStudentsSQLRequest() {
        return studentService.getAllStudents();
    }

    @GetMapping(path = "/getAverageAgeOfStudents")
    public Integer getAverageAgeOfStudents() {
        return studentService.getAverageAgeOfStudents();
    }

    @GetMapping(path = "/getFiveLastStudents")
    public Collection<StudentsByCategory> getFiveLastStudents() {
        return studentService.getFiveLastStudents();
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
