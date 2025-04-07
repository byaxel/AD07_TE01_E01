-- Inserta categorías
insert into category (id, name) values (1, 'Queer');
insert into category (id, name) values (2, 'Ficción');
insert into category (id, name) values (3, 'Historia Ficción');

-- Inserta libros con sus respectivas categorías
insert into book (id, title, author, read_date, rating, status, category_id) values
(1, 'Nadar en la oscuridad', 'Tomasz Jedrowski', '2025-03-19', 5, 'READ', 1),
(2, 'Rebelión en la granja', 'George Orwell', null, null, 'CURRENTLY_READING', 3),
(3, 'El dios en llamas', 'Kiang R. F.', null, null, 'TO_READ', 3),
(4, 'Amanecer en la cosecha (Los juegos del hambre)', 'Suzanne Collins', '2023-04-18', 4, 'READ', 2),
(5, 'Un rey de verano', 'Juan Arcones', '2024-09-05', 5, 'READ', 1);