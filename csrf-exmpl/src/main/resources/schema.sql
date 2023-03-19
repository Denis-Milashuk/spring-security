create database csrf;

create table if not exists public.custom_csrf_token (
    id serial primary key,
    identifier varchar not null,
    token text null
)