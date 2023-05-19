package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class StudentService {

    private static long idS = 0;

    private final Map<Long, Student> students = new HashMap<>();



    public Student addStudent(Student student) {
        student.setId(idS++);
        students.put(student.getId(), student);
        return student;
    }

    public Student getById(long id) {
        if(students.containsKey(id)){
            return students.get(id);
        }
        return null;
    }

    public Student updateStudent(long id, Student student) {
        if(students.containsKey(id)){
            students.replace(id, student);
        }
        return null;
    }

    public Collection<Student> getAll() {
        return Collections.unmodifiableCollection(students.values());
    }

    public Collection<Student> getAllById(int age) {
        return students.values().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }

    public Student deleteStudentById(long id) {
        if(students.containsKey(id)){
            return students.remove(id);
        }
        return null;
    }




}
