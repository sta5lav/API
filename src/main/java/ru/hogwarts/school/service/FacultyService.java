package ru.hogwarts.school.service;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;


@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    private final StudentRepository studentRepository;

    public FacultyService(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    Logger logger = LoggerFactory.getLogger(Faculty.class);


    public Faculty addFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }


    public Optional<Faculty> getById(long id) {
        logger.info("Was invoked method for get faculty by id = {}", id);
        return facultyRepository.findById(id);
    }

    public Faculty updateFaculty(long id, Faculty faculty) {
        logger.info("Was invoked method for update faculty");
        if (facultyRepository.existsById(id)) {
            faculty.setId(id);
            return facultyRepository.save(faculty);
        }
        return null;
    }

    public Collection<Faculty> getAll() {
        logger.info("Was invoked method for get all faculties");
        return facultyRepository.findAll();
    }

    public Collection<Faculty> getAllByColor(String color) {
        logger.info("Was invoked method for get faculty by color = {}", color);
        return facultyRepository.findAllByColor(color);
    }

    public void deleteFacultyById(long id) {
        logger.warn("Was invoked method for delete faculty = {}",
                facultyRepository.getReferenceById(id).getName());
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getFacultyByColorOrNameIgnoreCase(String color) {
        logger.info("Was invoked method for get faculty by color(name) = {}", color);
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(color, color);
    }

    public Collection<Student> getStudentsOfFacultyById(long id) {
        logger.info("Was invoked method for get all students by faculty");
        return studentRepository.findAllByFaculty_Id(id);
    }

    public String getMaxLengthOfNameFaculty() {
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElse(null);

    }


    public String sum() {
        long start = System.currentTimeMillis();
        long sum = Stream.iterate(1, a -> a +1)
                .limit(1_000_000)
                .reduce(0, Integer::sum);
        start = System.currentTimeMillis() - start;
        return  "Затраченное время = " + start + ", сумма равна = " + sum;
    }
}
