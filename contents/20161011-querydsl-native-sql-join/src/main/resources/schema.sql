create table `a` (
    `id` int primary key auto_increment,
    `name` varchar(255) not null
);
create table `b` (
    `id` int primary key auto_increment,
    `a_id` int not null,
    `name` varchar(255) not null
);
create table `c` (
    `id` int primary key auto_increment,
    `b_id` int not null,
    `d_id` int,
    `name` varchar(255) not null
);
create table `d` (
    `id` int primary key auto_increment,
    `name` varchar(255) not null
);
create table `e` (
    `id` int primary key auto_increment,
    `d_id` int not null,
    `sub_id` int not null,
    `name` varchar(255) not null
);
