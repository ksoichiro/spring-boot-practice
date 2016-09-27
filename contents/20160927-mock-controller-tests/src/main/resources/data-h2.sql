truncate table `user`;
truncate table `project`;
truncate table `project_user`;

insert into `user` (id, name) values
  (1, 'foo'),
  (2, 'bar');

insert into `project` (id, name) values
  (1, 'a'),
  (2, 'b');

insert into `project_user` (id, user_id, project_id) values
  (1, 1, 1),
  (2, 1, 2);
