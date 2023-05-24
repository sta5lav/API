package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final FacultyRepository facultyRepository;

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }


    public Student addStudent(Student student) {
        student.setId(null);
        student.setFaculty(Optional.ofNullable(student.getFaculty())
                .filter(f -> f.getId() != null)
                .flatMap(f -> facultyRepository.findById(f.getId()))
                .orElse(null)
        );
        return studentRepository.save(student);
    }

    public Optional<Student> getById(long id) {
        return studentRepository.findById(id);
    }

    public Student updateStudent(long id, Student student) {
        if (studentRepository.existsById(id)) {
            student.setId(id);
            student.setFaculty(Optional.ofNullable(student.getFaculty())
                    .filter(f -> f.getId() != null)
                    .flatMap(f -> facultyRepository.findById(f.getId()))
                    .orElse(null)
            );
            return studentRepository.save(student);
        }
        return null;
    }

    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }

    public Collection<Student> getAllById(Integer age) {
        return studentRepository.findAllByAge(age);
    }

    public void deleteStudentById(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> findStudentByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }


    public Optional<Faculty> getFacultyOfStudentById(long id) {
        return studentRepository.findById(id)
                .map(Student::getFaculty);
    }
}
