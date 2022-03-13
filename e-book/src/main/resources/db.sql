select * from users;
insert into users(username, password,email)
values('admin','admin','admin@gmail.com', 0776161331,'adminov'),
      ('user','user','user@gmail.com', 0777777777, 'userov'),
       ('vendor', 'vendor', 'vendor@gmail.com', 09988888888, 'vendorov');




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
select * from vendors
insert into vendors(vendor_email,vendor_last_name, vendor_number,password, vendor_name)
values ('vendor111@gmail.com', 'vendorov', '8888888888', 'vendor', 'vendor')

