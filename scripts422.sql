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
CREATE TABLE transport(
    id real primary key ,
    stamp text not null ,
    model text not null ,
    cost int
);
CREATE TABLE drivers(
    id real primary key ,
    name text not null ,
    age int,
    documents boolean,
    transport_id real references transport (id)
);

insert into transport (id, stamp, model, cost)
values (1, 'Mark1', 'Model1', 100000),
       (2, 'Mark2', 'Model2', 100000),
       (3, 'Mark3', 'Model3', 100000);

insert into drivers(id, name, age, documents, transport_id)
    values (1, 'Ivan', 18, true, 1),
           (2, 'Petr', 22, true, 1),
           (3, 'Alexandr', 21, true, 2),
           (4, 'Pavel', 27, true, 3);


-- STEP3 from HomeWork
SELECT student.name as Имя, student.age as Возраст, faculty.name as Факультет
FROM student INNER JOIN faculty ON student.faculty_id = faculty.id;

SELECT student.name, avatar.student_id, avatar.file_path
FROM student RIGHT JOIN avatar ON student.id = avatar.student_id;


-- Решение с наставником
select s.name, s.age from student s left join faculty f on s.faculty_id = f.id;
select s.* from student s inner join avatar a on a.student_id = s.id;

