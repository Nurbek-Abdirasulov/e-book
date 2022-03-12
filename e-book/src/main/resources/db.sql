select * from users;
insert into users(username,password,email)
values('admin','admin','admin@gmail.com'),
      ('user','user','user@gmail.com'),
       ('vendor', 'vendor', 'vendor@gmail.com');


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

select *from vendors;