select * from users;
insert into users(username,password,email, number)
values('admin','admin','admin@gmail.com', 0776161331),
      ('user','user','user@gmail.com', 0555555555),
       ('vendor', 'vendor', 'vendor@gmail.com', 0777777777);


select * from roles;
insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN'),
       ('ROLE_VENDOR');

select * from users_roles;
insert into users_roles(user_id,role_id)
values (2,1),
       (1,2),
       (3,3);