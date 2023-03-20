create table if not exists public.security_user (
    id serial primary key,
    user_name varchar not null unique ,
    password text not null,
    algorithm varchar not null
);

create table if not exists public.authority (
    id serial primary key,
    name varchar(45) not null,
    user_id bigint not null,
    constraint authority_to_user_fk foreign key (user_id) references public.security_user (id) on delete cascade
);

create table if not exists public.product (
    id serial primary key,
    name varchar not null,
    price varchar not null,
    currency varchar not null
)
