create table if not exists public.product (
    id serial primary key,
    name varchar null,
    owner varchar null
);