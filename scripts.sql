select * from student;

select * from student as s
where age between 10 and 20;

select count(*) as "количество студентов" from student;

select * from student as s
where name like '%о%';

select * from student
where age < 20;

select * from student as s
order by age;




