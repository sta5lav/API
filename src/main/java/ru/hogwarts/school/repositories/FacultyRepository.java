package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.entity.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Collection<Faculty> findAllByColor(String color);

    Collection<Faculty> findByColorIgnoreCaseOrNameIgnoreCase(String color, String name);


}
