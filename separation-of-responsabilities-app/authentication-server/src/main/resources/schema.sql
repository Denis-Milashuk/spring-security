create table if not exists public.auth_user (
    user_name varchar primary key null ,
    password  text null
);

create table if not exists public.otp (
    user_name varchar primary key,
    code varchar null
)