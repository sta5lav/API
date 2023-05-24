package ru.hogwarts.school.controller;


import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
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

    @GetMapping()
    public Collection<Faculty> getAllFaculties(@RequestParam (value = "color", required = false) String color) {
        return Optional.ofNullable(color)
                .map(a -> facultyService.getAllById(color))
                .orElseGet(facultyService::getAll);
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
