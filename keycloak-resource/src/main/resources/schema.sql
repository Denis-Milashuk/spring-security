create table if not exists public.workout (
    id serial primary key,
    user_name varchar null,
    start_time timestamp null,
    end_time timestamp null,
    difficulty int null
);