INSERT  INTO public.security_user (id, user_name, password, algorithm)
VALUES ('1', 'john', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'BCRYPT');

INSERT INTO public.authority (id, name, user_id) VALUES (1, 'READ', '1');
INSERT INTO public.authority (id, name, user_id) VALUES (2,'WRITE', '1');
INSERT INTO public.product (id, name, price, currency) VALUES (1, 'Chocolate', '10', 'USD');