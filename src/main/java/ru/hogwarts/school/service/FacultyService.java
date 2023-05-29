package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.Optional;


@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    private final StudentRepository studentRepository;

    public FacultyService(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }




    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }


    public Optional<Faculty> getById(long id) {
        return facultyRepository.findById(id);
    }

    public Faculty updateFaculty(long id, Faculty faculty) {
        if (facultyRepository.existsById(id)) {
            faculty.setId(id);
            return facultyRepository.save(faculty);
        }
        return null;
    }

    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    public Collection<Faculty> getAllById(String color) {
        return facultyRepository.findAllByColor(color);
    }

    public void deleteFacultyById(long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getFacultyByColorOrNameIgnoreCase(String color) {
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(color, color);
    }

    public Collection<Student> ggetStudentsOfFacultyById(long id) {
        return studentRepository.findAllByFaculty_Id(id);
    }
}
