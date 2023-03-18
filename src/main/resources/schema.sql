DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS genres;

create table authors(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name varchar(255)
);
create table genres(
    id bigserial,
    name varchar(255),
    primary key (id)
);
create table books(
    id bigserial,
    name varchar(255),
    author_id bigint references authors(id) on delete cascade,
    genre_id bigint references genres(id) on delete cascade,
    primary key (id)
);
create table book_comment(
    id bigserial,
    book_id bigint references books(id) on delete cascade,
    comment varchar(255),
    primary key (id)
);