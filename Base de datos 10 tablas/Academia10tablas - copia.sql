
-- Crear base de datos
CREATE DATABASE TrainingCenterDB;
GO

-- Usar la base de datos
USE TrainingCenterDB;
GO



-- Tabla: Permission
CREATE TABLE permission (
    id_permission INT IDENTITY(1,1) PRIMARY KEY,
    permission_name NVARCHAR(255) NOT NULL
);

-- Tabla: ProgramType
CREATE TABLE program_type (
    id_program_type INT IDENTITY(1,1) PRIMARY KEY,
    type_name NVARCHAR(255) NOT NULL
);

-- Tabla: Teacher
CREATE TABLE teacher (
    id_teacher INT IDENTITY(1,1) PRIMARY KEY,
    teacher_name NVARCHAR(255) NOT NULL,
    expertise NVARCHAR(255)
);

-- Tabla: TrainingModule
CREATE TABLE training_module (
    id_training_module INT IDENTITY(1,1) PRIMARY KEY,
    module_title NVARCHAR(255) NOT NULL,
    module_cost DECIMAL(19,2)
);

-- Tabla: SystemUser
CREATE TABLE system_user (
    id_system_user INT IDENTITY(1,1) PRIMARY KEY,
    login_name NVARCHAR(255) NOT NULL UNIQUE
);



-- Tabla: Learner (depende de system_user)
CREATE TABLE learner (
    id_learner INT IDENTITY(1,1) PRIMARY KEY,
    learner_name NVARCHAR(255) NOT NULL,
    years_old INT,
    id_system_user INT UNIQUE,
    CONSTRAINT FK_learner_system_user FOREIGN KEY (id_system_user)
        REFERENCES system_user(id_system_user)
);

-- Tabla: Module_Assignment (depende de program_type y training_module)
CREATE TABLE module_assignment (
    id_module_assignment INT IDENTITY(1,1) PRIMARY KEY,
    id_program_type INT NOT NULL,
    id_training_module INT NOT NULL,
    CONSTRAINT FK_module_assignment_program_type FOREIGN KEY (id_program_type)
        REFERENCES program_type(id_program_type),
    CONSTRAINT FK_module_assignment_training_module FOREIGN KEY (id_training_module)
        REFERENCES training_module(id_training_module)
);

-- Tabla: Teacher_Module (depende de teacher y training_module)
CREATE TABLE teacher_module (
    id_teacher_module INT IDENTITY(1,1) PRIMARY KEY,
    id_teacher INT NOT NULL,
    id_training_module INT NOT NULL,
    CONSTRAINT FK_teacher_module_teacher FOREIGN KEY (id_teacher)
        REFERENCES teacher(id_teacher),
    CONSTRAINT FK_teacher_module_training_module FOREIGN KEY (id_training_module)
        REFERENCES training_module(id_training_module)
);

-- Tabla: User_Permission (depende de system_user y permission)
CREATE TABLE user_permission (
    id_user_permission INT IDENTITY(1,1) PRIMARY KEY,
    id_system_user INT NOT NULL,
    id_permission INT NOT NULL,
    CONSTRAINT FK_user_permission_system_user FOREIGN KEY (id_system_user)
        REFERENCES system_user(id_system_user),
    CONSTRAINT FK_user_permission_permission FOREIGN KEY (id_permission)
        REFERENCES permission(id_permission)
);

-- Tabla: Registration (depende de learner y training_module)
CREATE TABLE registration (
    id_registration INT IDENTITY(1,1) PRIMARY KEY,
    registered_on DATE NOT NULL,
    id_learner INT NOT NULL,
    id_training_module INT NOT NULL,
    CONSTRAINT FK_registration_learner FOREIGN KEY (id_learner)
        REFERENCES learner(id_learner),
    CONSTRAINT FK_registration_training_module FOREIGN KEY (id_training_module)
        REFERENCES training_module(id_training_module)
);

-- Tabla: FeePayment (depende de registration)
CREATE TABLE fee_payment (
    id_fee_payment INT IDENTITY(1,1) PRIMARY KEY,
    fee_amount DECIMAL(19,2) NOT NULL,
    paid_on DATE NOT NULL,
    id_registration INT NOT NULL,
    CONSTRAINT FK_fee_payment_registration FOREIGN KEY (id_registration)
        REFERENCES registration(id_registration)
);


-- Permission
INSERT INTO permission (permission_name) VALUES ('Admin'), ('User');

-- ProgramType
INSERT INTO program_type (type_name) VALUES ('Full-Time'), ('Part-Time');

-- Teacher
INSERT INTO teacher (teacher_name, expertise) VALUES ('John Doe', 'Math'), ('Jane Smith', 'Physics');

-- TrainingModule
INSERT INTO training_module (module_title, module_cost) VALUES ('Algebra', 200.00), ('Calculus', 300.00);

-- SystemUser
INSERT INTO system_user (login_name) VALUES ('user1'), ('user2');

-- Learner
INSERT INTO learner (learner_name, years_old, id_system_user) VALUES ('Alice', 20, 1), ('Bob', 22, 2);

-- Module_Assignment
INSERT INTO module_assignment (id_program_type, id_training_module) VALUES (1, 1), (2, 2);

-- Teacher_Module
INSERT INTO teacher_module (id_teacher, id_training_module) VALUES (1, 1), (2, 2);

-- User_Permission
INSERT INTO user_permission (id_system_user, id_permission) VALUES (1, 1), (2, 2);

-- Registration
INSERT INTO registration (registered_on, id_learner, id_training_module) VALUES ('2025-11-18', 1, 1), ('2025-11-18', 2, 2);

-- FeePayment
INSERT INTO fee_payment (fee_amount, paid_on, id_registration) VALUES (200.00, '2025-11-18', 1), (300.00, '2025-11-18', 2);

-- Permission
UPDATE permission SET permission_name = 'SuperAdmin' WHERE id_permission = 1;
UPDATE permission SET permission_name = 'Guest' WHERE id_permission = 2;

-- ProgramType
UPDATE program_type SET type_name = 'Evening' WHERE id_program_type = 1;
UPDATE program_type SET type_name = 'Weekend' WHERE id_program_type = 2;

-- Teacher
UPDATE teacher SET teacher_name = 'John Updated', expertise='Algebra' WHERE id_teacher=1;
UPDATE teacher SET teacher_name = 'Jane Updated', expertise='Calculus' WHERE id_teacher=2;

-- TrainingModule
UPDATE training_module SET module_title='Linear Algebra', module_cost=220.00 WHERE id_training_module=1;
UPDATE training_module SET module_title='Advanced Calculus', module_cost=330.00 WHERE id_training_module=2;

-- SystemUser
UPDATE system_user SET login_name='user1_updated' WHERE id_system_user=1;
UPDATE system_user SET login_name='user2_updated' WHERE id_system_user=2;

-- Learner
UPDATE learner SET learner_name='Alice Updated', years_old=21 WHERE id_learner=1;
UPDATE learner SET learner_name='Bob Updated', years_old=23 WHERE id_learner=2;

-- Module_Assignment
UPDATE module_assignment SET id_program_type=2, id_training_module=1 WHERE id_module_assignment=1;
UPDATE module_assignment SET id_program_type=1, id_training_module=2 WHERE id_module_assignment=2;

-- Teacher_Module
UPDATE teacher_module SET id_teacher=2, id_training_module=1 WHERE id_teacher_module=1;
UPDATE teacher_module SET id_teacher=1, id_training_module=2 WHERE id_teacher_module=2;

-- User_Permission
UPDATE user_permission SET id_system_user=2, id_permission=1 WHERE id_user_permission=1;
UPDATE user_permission SET id_system_user=1, id_permission=2 WHERE id_user_permission=2;

-- Registration
UPDATE registration SET registered_on='2025-12-01', id_learner=2, id_training_module=1 WHERE id_registration=1;
UPDATE registration SET registered_on='2025-12-02', id_learner=1, id_training_module=2 WHERE id_registration=2;

-- FeePayment
UPDATE fee_payment SET fee_amount=250.00, paid_on='2025-12-01', id_registration=1 WHERE id_fee_payment=1;
UPDATE fee_payment SET fee_amount=350.00, paid_on='2025-12-02', id_registration=2 WHERE id_fee_payment=2;


-- FeePayment
DELETE FROM fee_payment WHERE id_fee_payment IN (1,2);

-- Registration
DELETE FROM registration WHERE id_registration IN (1,2);

-- User_Permission
DELETE FROM user_permission WHERE id_user_permission IN (1,2);

-- Teacher_Module
DELETE FROM teacher_module WHERE id_teacher_module IN (1,2);

-- Module_Assignment
DELETE FROM module_assignment WHERE id_module_assignment IN (1,2);

-- Learner
DELETE FROM learner WHERE id_learner IN (1,2);

-- SystemUser
DELETE FROM system_user WHERE id_system_user IN (1,2);

-- TrainingModule
DELETE FROM training_module WHERE id_training_module IN (1,2);

-- Teacher
DELETE FROM teacher WHERE id_teacher IN (1,2);

-- ProgramType
DELETE FROM program_type WHERE id_program_type IN (1,2);

-- Permission
DELETE FROM permission WHERE id_permission IN (1,2);


/*
Relaciones

SystemUser ↔ Learner (1:1)
Cada usuario del sistema puede tener un solo estudiante asociado, y cada estudiante pertenece a un único usuario.

SystemUser ↔ User_Permission (1:N)
Un usuario puede tener muchos permisos, pero cada registro en User_Permission pertenece a un solo usuario.

Permission ↔ User_Permission (1:N)
Un permiso puede aplicarse a muchos usuarios, pero cada registro de User_Permission apunta a un solo permiso.

User_Permission → SystemUser & Permission (N:M)
La tabla pivote User_Permission permite la relación muchos a muchos entre usuarios y permisos.

Learner ↔ Registration (1:N)
Un estudiante puede registrarse en muchos módulos, pero cada inscripción pertenece a un único estudiante.

TrainingModule ↔ Registration (1:N)
Un módulo puede tener muchos estudiantes inscritos, pero cada registro pertenece a un único módulo.

Registration ↔ FeePayment (1:N)
Cada inscripción puede tener muchos pagos, pero cada pago pertenece a una sola inscripción.

ProgramType ↔ Module_Assignment (1:N)
Cada tipo de programa puede tener muchos módulos asignados, pero cada asignación pertenece a un solo programa.

TrainingModule ↔ Module_Assignment (1:N)
Cada módulo puede estar asignado a muchos programas, pero cada asignación apunta a un único módulo.

Module_Assignment → ProgramType & TrainingModule (N:M)
La tabla pivote Module_Assignment permite la relación muchos a muchos entre programas y módulos.

Teacher ↔ Teacher_Module (1:N)
Un profesor puede enseñar muchos módulos, pero cada registro de Teacher_Module pertenece a un solo profesor.

TrainingModule ↔ Teacher_Module (1:N)
Un módulo puede ser impartido por muchos profesores, pero cada registro de Teacher_Module pertenece a un único módulo.

Teacher_Module → Teacher & TrainingModule (N:M)
La tabla pivote Teacher_Module permite la relación muchos a muchos entre profesores y módulos.
/*