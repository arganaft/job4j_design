
create table type
(
    id    serial primary key,
    type_name  text
);
create table product
(
    id    serial primary key,
    product_name  text,
    type_id int references type,
    expired_date date,
    price numeric
);

insert into type(type_name) values ('сыр'), ('молоко'), ('мука');

insert into product(product_name, type_id, expired_date, price)
 values ('моцарелла', 1, '17.04.2024', 559.49), ('сулугуни', 1, '26.08.2024', 259.49),
        ('бри', 1, '01.11.2024', 2361.49), ('брюнуст', 1, '15.01.2025', 1399.99),
        ('гауда', 1, '07.01.2024', 399.28), ('камамбер', 1, '28.07.2025', 749.99),
        ('бри', 1, '01.11.2024', 859.49), ('сыр козий', 1, '03.03.2023', 2361.49),
        ('пармезан', 1, '17.12.2022', 1831.49), ('пекорино романо', 1, '29.06.2024', 469.15),
        ('сыр овечий', 1, '04.04.2025', 2361.49), ('пор-салю', 1, '29.06.2024', 1399.99);

insert into product(product_name, type_id, expired_date, price)
 values ('мороженное', 2, '17.04.2024', 559.49), ('сгущенка', 2, '26.08.2024', 259.49),
        ('кефир', 2, '01.11.2024', 2361.49), ('Шоколадное мороженное', 2, '15.01.2025', 1399.99),
        ('ацидофилин', 2, '07.01.2024', 399.28), ('йогурт', 2, '28.07.2025', 749.99),
        ('весовое мороженное', 2, '01.11.2024', 859.49), ('тан', 2, '03.03.2023', 2361.49),
        ('масло сливочное', 2, '17.12.2022', 1831.49), ('творог', 2, '29.06.2024', 469.15);

insert into product(product_name, type_id, expired_date, price)
 values ('блинчик', 3, '17.04.2024', 559.49), ('булка', 3, '26.08.2024', 259.49),
        ('хлеб', 3, '01.11.2024', 2361.49), ('кекс', 3, '15.01.2025', 1399.99),
        ('печенье', 3, '07.01.2024', 399.28), ('крекер', 3, '28.07.2025', 749.99),
        ('креньдель', 3, '01.11.2024', 859.49), ('лаваш', 3, '03.03.2023', 2361.49);

