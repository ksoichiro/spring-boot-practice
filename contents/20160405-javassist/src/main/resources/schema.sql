create table os (
    id int primary key auto_increment,
    foo varchar(255),
    name varchar(255) not null
);

create table foo (
    id int primary key auto_increment,
    -- bar varchar(255),
    name varchar(255) not null
);
