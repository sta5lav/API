package ru.hogwarts.school.controller;


import liquibase.pro.packaged.S;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(path = "/faculty")
public class FacultyController {


    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping(path = "/{id}")
    public Optional<Faculty> getStudentById(@PathVariable() long id) {
        return facultyService.getById(id);
    }

    @GetMapping(path = "filterByColorOrName")
    public Collection<Faculty> getAllByColorOrNameIgnoreCase(@RequestParam (value = "colorOrName", required = false) String colorOrName) {
        return facultyService.getFacultyByColorOrNameIgnoreCase(colorOrName);
    }

    @GetMapping()
    public Collection<Faculty> getAllFaculties(@RequestParam (value = "color", required = false) String color) {
        return Optional.ofNullable(color)
                .map(a -> facultyService.getAllByColor(color))
                .orElseGet(facultyService::getAll);
    }

    @GetMapping(path = "/{id}/students")
    public Collection<Student> getStudentsOfFacultyById(@PathVariable long id) {
        return facultyService.getStudentsOfFacultyById(id);
    }

    @GetMapping(path = "/getMaxLengthOfNameFaculty()")
    public String getMaxLengthOfNameFaculty() {
        return facultyService.getMaxLengthOfNameFaculty();
    }

    @GetMapping(path = "/sum")
    public String sum() {
        return facultyService.sum();
    }

    @PostMapping
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping(path = "/{id}")
    public Faculty updateFaculty(@PathVariable long id,@RequestBody Faculty faculty) {
        return facultyService.updateFaculty(id, faculty);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteStudent(@PathVariable long id) {
        facultyService.deleteFacultyById(id);
    }
}
