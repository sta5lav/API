package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.entity.StudentsByCategory;

import java.util.Collection;


public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findAllByAge(int age);

    Collection<Student> findByAgeBetween(int min, int max);

    Collection<Student> findAllByFaculty_Id(long id);

    @Query(value = "SELECT count(*) FROM student", nativeQuery = true)
    Collection<StudentsByCategory> getAllStudents();

    @Query(value = "SELECT avg(age) as averageAge FROM student", nativeQuery = true)
    Integer getAverageAgeOfStudents();

    @Query(value = "SELECT * FROM student ORDER BY ID DESC LIMIT 5", nativeQuery = true)
    Collection<StudentsByCategory> getFiveLastStudents();

}
