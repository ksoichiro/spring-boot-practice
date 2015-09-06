create table role (
    id int primary key auto_increment,
    name varchar(255) not null,
    deleted_at datetime
);
create table role_permission (
    id int primary key auto_increment,
    role_id int not null,
    permission_id int not null,
    deleted_at datetime
);
create table permission (
    id int primary key auto_increment,
    name varchar(255) not null,
    deleted_at datetime
);
