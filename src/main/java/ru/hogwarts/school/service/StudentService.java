package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.entity.StudentsByCategory;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.*;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final FacultyRepository facultyRepository;

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    Logger logger = LoggerFactory.getLogger(Student.class);


    public Student addStudent(Student student) {
        logger.info("Was invoked method for create student");
        student.setId(null);
        student.setFaculty(Optional.ofNullable(student.getFaculty())
                .filter(f -> f.getId() != null)
                .flatMap(f -> facultyRepository.findById(f.getId()))
                .orElse(null)
        );
        logger.debug("{} was added", student.getName());
        return studentRepository.save(student);
    }

    public Optional<Student> getById(long id) {
        return studentRepository.findById(id);
    }

    public Student updateStudent(long id, Student student) {
        logger.info("Was invoked method for update student");
        if (studentRepository.existsById(id)) {
            student.setId(id);
            student.setFaculty(Optional.ofNullable(student.getFaculty())
                    .filter(f -> f.getId() != null)
                    .flatMap(f -> facultyRepository.findById(f.getId()))
                    .orElse(null)
            );
            logger.debug("{} was replaced", student.getName());
            return studentRepository.save(student);
        }
        return null;
    }

    public Collection<Student> getAll() {
        logger.info("Was invoked method for find all student");
        return studentRepository.findAll();
    }

    public Collection<Student> getAllByAge(Integer age) {
        logger.info("Was invoked method for find students by age");
        return studentRepository.findAllByAge(age);
    }

    public void deleteStudentById(long id) {
        logger.warn("Was invoked method for delete {}", studentRepository.getById(id).getName());
        studentRepository.deleteById(id);
    }

    public Collection<Student> findStudentByAgeBetween(int min, int max) {
        logger.info("Was invoked method for find student by age between {}", min + " and " + max);
        return studentRepository.findByAgeBetween(min, max);
    }

    public Optional<Faculty> getFacultyOfStudentById(long id) {
        logger.info("Was invoked method for get Faculty student by id = {}", id);
        return studentRepository.findById(id)
                .map(Student::getFaculty);
    }

    public Collection<StudentsByCategory> getAllStudents() {
        logger.info("Was invoked method for get all students");
       return studentRepository.getAllStudents();
    }

    public Integer getAverageAgeOfStudents() {
        logger.info("Was invoked method for get average age of student");
        return studentRepository.getAverageAgeOfStudents();
    }

    public Collection<StudentsByCategory> getFiveLastStudents() {
        logger.info("Was invoked method for get five last students");
        return studentRepository.getFiveLastStudents();
    }

}
