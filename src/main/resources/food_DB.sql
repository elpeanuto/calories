drop table IF EXISTS food;

create TABLE food(
	f_id SERIAL PRIMARY KEY,
	f_name VARCHAR(20) UNIQUE,
	f_calories INTEGER
);

insert into food(f_name, f_calories) values ('Хлеб', 100),
('Рис', 120), ('Макароны', 10),
('Хрен', 32), ('Буряк', 44);