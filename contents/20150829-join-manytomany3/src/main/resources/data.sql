insert into role (id, name) values (1, 'ADMIN');
insert into role (id, name) values (2, 'MANAGER');
insert into role (id, name) values (3, 'USER');

insert into permission (id, name) values (1, 'CREATE_USER');
insert into permission (id, name) values (2, 'READ_USER');
insert into permission (id, name) values (3, 'UPDATE_USER');
insert into permission (id, name) values (4, 'DELETE_USER');
insert into permission (id, name) values (5, 'CREATE_TEAM');
insert into permission (id, name) values (6, 'READ_TEAM');
insert into permission (id, name) values (7, 'UPDATE_TEAM');
insert into permission (id, name) values (8, 'DELETE_TEAM');

insert into role_permission (role_id, permission_id) values (1, 1);
insert into role_permission (role_id, permission_id) values (1, 2);
insert into role_permission (role_id, permission_id) values (1, 3);
insert into role_permission (role_id, permission_id) values (1, 4);
insert into role_permission (role_id, permission_id) values (1, 5);
insert into role_permission (role_id, permission_id) values (1, 6);
insert into role_permission (role_id, permission_id) values (1, 7);
insert into role_permission (role_id, permission_id) values (1, 8);
insert into role_permission (role_id, permission_id) values (2, 1);
insert into role_permission (role_id, permission_id) values (2, 2);
insert into role_permission (role_id, permission_id) values (2, 3);
insert into role_permission (role_id, permission_id) values (2, 4);
insert into role_permission (role_id, permission_id) values (2, 6);
