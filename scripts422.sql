-- В этом задании по описанию необходимо спроектировать таблицы,
--     связи между ними и корректно определить типы данных.
--     Здесь не важно, какой тип вы выберете, например, для данных, представленных в виде строки (varchar или text).
--     Важно, что вы выберете один из строковых типов, а не числовых, например.
--
-- Описание структуры: у каждого человека есть машина.
--     Причем несколько человек могут пользоваться одной машиной.
--     У каждого человека есть имя, возраст и признак того, что у него есть права (или их нет).
--     У каждой машины есть марка, модель и стоимость.
--     Также не забудьте добавить таблицам первичные ключи и связать их.
-- STEP2 from HomeWork
CREATE TABLE automobiles(
    id real primary key ,
    stamp text,
    model text,
    cost int
);
CREATE TABLE people(
    id real,
    name text primary key,
    age int,
    documents boolean,
    transport_id real references automobiles (id)
);









-- STEP3 from HomeWork
SELECT student.name, student.age, faculty.name
FROM student INNER JOIN faculty ON student.faculty_id = faculty.id;

SELECT student.name, avatar.student_id, avatar.file_path
FROM student RIGHT JOIN avatar ON student.id = avatar.student_id