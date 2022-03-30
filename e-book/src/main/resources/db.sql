
insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN'),
       ('ROLE_VENDOR');

select * from users_roles;
insert into users_roles(user_id,role_id)
values (2,1),
       (1,2),
       (3,3);

INSERT INTO genres(name)
values('Education'),
    ('Artistic literature'),
    ('Books for children'),
    ('Science and technology'),
    ('Society'),
    ('Business Literature'),
    ('Beauty. Health. Sport'),
    ('Hobbies'),
    ('Psychology');

INSERT INTO types(name)
values('Audio Book'),
    ('E-book'),
    ('Paper Book');

INSERT INTO languages(name)
values ('English'),
       ('Kyrgyz'),
       ('Russian');

select * from books
