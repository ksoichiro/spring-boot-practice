create table `department` (
    id int primary key auto_increment,
    name varchar(255) not null
);

create table `employee` (
    id int primary key auto_increment,
    department_id int not null,
    name varchar(255) not null
);
