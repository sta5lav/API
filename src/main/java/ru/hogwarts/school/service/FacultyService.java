package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class FacultyService {

    private static long idS = 0;

    private final Map<Long, Faculty> faculties = new HashMap<>();


    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(idS++);
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }


    public Faculty getById(long id) {
        if(faculties.containsKey(id)){
            return faculties.get(id);
        }
        return null;
    }

    public Faculty updateFaculty(long id, Faculty faculty) {
        if(faculties.containsKey(id)){
            faculties.replace(id, faculty);
        }
        return null;
    }

    public Collection<Faculty> getAll() {
        return Collections.unmodifiableCollection(faculties.values());
    }

    public Collection<Faculty> getAllById(String color) {
        return faculties.values().stream()
                .filter(student -> student.getColor().equals(color))
                .collect(Collectors.toList());
    }

    public Faculty deleteFacultyById(long id) {
        if(faculties.containsKey(id)){
            return faculties.remove(id);
        }
        return null;
    }



}
