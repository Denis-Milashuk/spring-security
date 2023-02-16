
DROP TABLE IF EXISTS public.users;
CREATE TABLE public.users (
    id serial primary key,
    user_name varchar(45) not null unique,
    password varchar(45) not null,
    enabled int not null
);

DROP TABLE IF EXISTS authorities;
CREATE TABLE public.authorities (
    id serial primary key,
    user_name varchar(45) not null,
    authority varchar(45) not null
);