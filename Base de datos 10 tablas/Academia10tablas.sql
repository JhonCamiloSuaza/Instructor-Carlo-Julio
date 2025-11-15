CREATE DATABASE AcademiaDB;
USE AcademiaDB;


--  CAMPUS
CREATE TABLE Campus (
  id_campus INT PRIMARY KEY IDENTITY,
  name VARCHAR(50) NOT NULL,
  address VARCHAR(100) NOT NULL,
  city VARCHAR(50) NOT NULL
);

--  STUDENT
CREATE TABLE Student (
  id_student INT PRIMARY KEY IDENTITY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  phone VARCHAR(20),
  id_campus INT NOT NULL,
  FOREIGN KEY (id_campus) REFERENCES Campus(id_campus)
);

--  TEACHER
CREATE TABLE Teacher (
  id_teacher INT PRIMARY KEY IDENTITY,
  name VARCHAR(50) NOT NULL,
  specialty VARCHAR(50) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  id_campus INT NOT NULL,
  FOREIGN KEY (id_campus) REFERENCES Campus(id_campus)
);

--  COURSE
CREATE TABLE Course (
  id_course INT PRIMARY KEY IDENTITY,
  name VARCHAR(50) NOT NULL,
  duration INT NOT NULL,
  level VARCHAR(20) NOT NULL
);

--  CLASSROOM
CREATE TABLE Classroom (
  id_classroom INT PRIMARY KEY IDENTITY,
  name VARCHAR(20) NOT NULL,
  capacity INT NOT NULL,
  id_campus INT NOT NULL,
  FOREIGN KEY (id_campus) REFERENCES Campus(id_campus)
);

--  SCHEDULE
CREATE TABLE Schedule (
  id_schedule INT PRIMARY KEY IDENTITY,
  day VARCHAR(15) NOT NULL,
  start_time TIME NOT NULL,
  end_time TIME NOT NULL
);

--  PAYMENT
CREATE TABLE Payment (
  id_payment INT PRIMARY KEY IDENTITY,
  id_student INT NOT NULL,
  amount DECIMAL(10,2) NOT NULL,
  payment_date DATE NOT NULL,
  payment_method VARCHAR(30) NOT NULL,
  FOREIGN KEY (id_student) REFERENCES Student(id_student)
);

--  ADMIN STAFF
CREATE TABLE AdminStaff (
  id_admin INT PRIMARY KEY IDENTITY,
  name VARCHAR(50) NOT NULL,
  position VARCHAR(50) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  id_campus INT NOT NULL,
  FOREIGN KEY (id_campus) REFERENCES Campus(id_campus)
);

--  STUDENT_COURSE (PIVOT TABLE)
CREATE TABLE Student_Course (
  id_student INT NOT NULL,
  id_course INT NOT NULL,
  enrollment_date DATE NOT NULL,
  PRIMARY KEY (id_student, id_course),
  FOREIGN KEY (id_student) REFERENCES Student(id_student),
  FOREIGN KEY (id_course) REFERENCES Course(id_course)
);

--  TEACHER_COURSE (PIVOT TABLE)
CREATE TABLE Teacher_Course (
  id_teacher INT NOT NULL,
  id_course INT NOT NULL,
  id_classroom INT NOT NULL,
  id_schedule INT NOT NULL,
  PRIMARY KEY (id_teacher, id_course),
  FOREIGN KEY (id_teacher) REFERENCES Teacher(id_teacher),
  FOREIGN KEY (id_course) REFERENCES Course(id_course),
  FOREIGN KEY (id_classroom) REFERENCES Classroom(id_classroom),
  FOREIGN KEY (id_schedule) REFERENCES Schedule(id_schedule)
);


INSERT INTO Campus VALUES ('Main Campus', '10th Street #5-20', 'Bogotá');
INSERT INTO Campus VALUES ('North Campus', '15th Avenue #120-45', 'Medellín');

INSERT INTO Student VALUES ('Camilo', 'Suaza', 'camilo@mail.com', '3201112233', 1);
INSERT INTO Student VALUES ('Laura', 'Gómez', 'laura@mail.com', '3114455667', 2);

INSERT INTO Teacher VALUES ('Carlos Pérez', 'Mathematics', 'carlos@mail.com', 1);
INSERT INTO Teacher VALUES ('Ana Torres', 'English', 'ana@mail.com', 2);

INSERT INTO Course VALUES ('Basic Mathematics', 40, 'Beginner');
INSERT INTO Course VALUES ('Intermediate English', 60, 'Intermediate');

INSERT INTO Classroom VALUES ('A1', 30, 1);
INSERT INTO Classroom VALUES ('B2', 25, 2);

INSERT INTO Schedule VALUES ('Monday', '08:00', '10:00');
INSERT INTO Schedule VALUES ('Tuesday', '10:00', '12:00');

INSERT INTO AdminStaff VALUES ('Natalia Ruiz', 'Coordinator', 'natalia@mail.com', 1);
INSERT INTO AdminStaff VALUES ('José Díaz', 'Accountant', 'jose@mail.com', 2);

INSERT INTO Payment VALUES (1, 250000, '2025-11-10', 'Cash');
INSERT INTO Payment VALUES (2, 300000, '2025-11-11', 'Card');

INSERT INTO Student_Course VALUES (1, 1, '2025-11-01');
INSERT INTO Student_Course VALUES (2, 2, '2025-11-02');

INSERT INTO Teacher_Course VALUES (1, 1, 1, 1);
INSERT INTO Teacher_Course VALUES (2, 2, 2, 2);


UPDATE Student
SET phone = '3205558899'
WHERE first_name = 'Camilo';

UPDATE Classroom
SET capacity = 35
WHERE name = 'B2';

UPDATE Course
SET level = 'Advanced'
WHERE name = 'Intermediate English';


DELETE FROM Payment WHERE id_student = 2;
DELETE FROM Student_Course WHERE id_student = 2;
DELETE FROM Student WHERE id_student = 2;

DELETE FROM Teacher_Course WHERE id_course = 2;
DELETE FROM Student_Course WHERE id_course = 2;
DELETE FROM Course WHERE id_course = 2;


-- Students with courses
SELECT s.first_name AS Student, s.last_name, c.name AS Course, sc.enrollment_date
FROM Student s
JOIN Student_Course sc ON s.id_student = sc.id_student
JOIN Course c ON sc.id_course = c.id_course;

-- Teachers with campus
SELECT t.name AS Teacher, c.name AS Campus, c.city
FROM Teacher t
JOIN Campus c ON t.id_campus = c.id_campus;

-- Number of students per campus
SELECT c.name AS Campus, COUNT(s.id_student) AS TotalStudents
FROM Campus c
LEFT JOIN Student s ON c.id_campus = s.id_campus
GROUP BY c.name;

/*
Relaciones

Sede ↔ Estudiante (1:N)
Una sede puede tener muchos estudiantes, pero cada estudiante pertenece a una sola sede.

Sede ↔ Profesor (1:N)
Una sede puede tener muchos profesores, pero cada profesor pertenece a una sola sede.

Sede ↔ Administrativo (1:N)
Una sede puede tener varios administrativos, pero cada administrativo pertenece a una sola sede.

Sede ↔ Aula (1:N)
Una sede puede tener muchas aulas, pero cada aula pertenece a una sola sede.

Estudiante ↔ Pago (1:N)
Un estudiante puede realizar muchos pagos, pero cada pago pertenece a un solo estudiante.

Estudiante ↔ Curso (N:N)
Un estudiante puede inscribirse en varios cursos y un curso puede tener muchos estudiantes.
(Se maneja mediante la tabla pivote Estudiante_Curso).

Profesor ↔ Curso (N:N)
Un profesor puede dictar varios cursos y un curso puede tener varios profesores.
(Se maneja mediante la tabla pivote Profesor_Curso).

Profesor_Curso → Aula (1:1)
Cada relación profesor–curso se imparte en una sola aula.

Profesor_Curso → Horario (1:1)
Cada relación profesor–curso tiene asignado un solo horario.

*/

DROP TABLE IF EXISTS Profesor_Curso;
DROP TABLE IF EXISTS Estudiante_Curso;

-- LUEGO BORRAR TABLAS QUE DEPENDEN DE OTRAS
DROP TABLE IF EXISTS Pago;
DROP TABLE IF EXISTS Administrativo;
DROP TABLE IF EXISTS Profesor;
DROP TABLE IF EXISTS Estudiante;
DROP TABLE IF EXISTS Aula;
DROP TABLE IF EXISTS Horario;
DROP TABLE IF EXISTS Curso;

-- ÚLTIMA: SEDE
DROP TABLE IF EXISTS Sede;