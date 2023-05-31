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
    id serial primary key ,
    stamp text not null ,
    model text not null ,
    cost int
);
CREATE TABLE drivers(
    id serial primary key ,
    name text not null ,
    age int,
    documents boolean,
    transport_id serial references transport (id)
);

insert into transport (stamp, model, cost)
values ( 'Mark1', 'Model1', 100000),
       ( 'Mark2', 'Model2', 100000),
       ( 'Mark3', 'Model3', 100000);

insert into drivers( name, age, documents, transport_id)
    values ( 'Ivan', 18, true, 1),
           ( 'Petr', 22, true, 1),
           ( 'Alexandr', 21, true, 2),
           ( 'Pavel', 27, true, 3);


-- STEP3 from HomeWork
SELECT student.name as Имя, student.age as Возраст, faculty.name as Факультет
FROM student left JOIN faculty ON student.faculty_id = faculty.id;

SELECT student.name, avatar.student_id, avatar.file_path
FROM student inner join avatar ON student.id = avatar.student_id;



