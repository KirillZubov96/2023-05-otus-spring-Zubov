insert into authors (id, `name`) values (1, 'Толстой Лев Николаевич');
insert into authors (id, `name`) values (2, 'Грибоедов Александр Сергеевич');

insert into genres (id, `name`) values (1, 'Роман');
insert into genres (id, `name`) values (2, 'Комедия');

insert into books (id, `name`, author_id, genre_id) values ( 1, 'Война и мир', 1, 1);
insert into books (id, `name`, author_id, genre_id) values ( 2, 'Горе от ума', 2, 2);