insert into authors (`name`) values ('Толстой Лев Николаевич');
insert into authors (`name`) values ('Грибоедов Александр Сергеевич');

insert into genres (`name`) values ('Роман');
insert into genres (`name`) values ('Комедия');

insert into books (`name`, author_id, genre_id) values ('Война и мир', 1, 1);
insert into books (`name`, author_id, genre_id) values ('Горе от ума', 2, 2);