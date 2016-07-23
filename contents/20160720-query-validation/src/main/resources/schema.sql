create table `department` (
    id int primary key auto_increment,
    name varchar(255) not null
);

create table `employee` (
    id int primary key auto_increment,
    department_id int not null,
    manager tinyint(1) not null default 0,
    name varchar(255) not null,
    defined_column int,
    undefined_column int
);
