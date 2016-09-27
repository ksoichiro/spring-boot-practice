create table if not exists `user` (
    `id` int primary key auto_increment,
    `name` varchar(255) not null
);

create table if not exists `project` (
    `id` int primary key auto_increment,
    `name` varchar(255) not null
);

create table if not exists `project_user` (
    `id` int primary key auto_increment,
    `project_id` int not null,
    `user_id` int not null
);
