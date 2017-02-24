insert into Sys_User(id, email, password, user_type) values(null, 'admin', 'admin', 'SYSTEMMANAGER')

insert into Sys_User(id, email, password, user_type) values(null, 'gost', 'gost', 'GUEST')

insert into Sys_User(id, email, last_name, name, password, user_type) values(null, 'adam94@gmail.com', 'Adamovic','Milan','adam','WAITER')
insert into Waiter(id, birthday, shoe_size, suit_size, user_id) values('1','20.04.1994.','42','L','3')

insert into Restaurant(id, name, description, category) values('1', 'Banjalucki', 'rostilj', 'domaca kuhinja')
insert into Restaurant(id, name, description, category) values('2', 'Burek ispoda saca', 'dobar burek', 'domaca kuhinja')
insert into Restaurant(id, name, description, category) values('3', 'Stapic', 'kinezi', 'kineski')

insert into Sys_User(id, email, last_name, name, password, user_type) values(null, 'adam1994@gmail.com', 'Adamovic','Milan','adam','RESTAURANTMANAGER')
insert into Restaurant_Manager(id, user_id) values('4', '4')
<<<<<<< HEAD
insert into Restaurant_Manager(id, email, restaurant_id, user_id) values ('1','1','1','1')
insert into Drink(id, description, grade, name, price) values('1', 'tamno pivo', '4', 'Lav', '125')
insert into Drink(id, description, grade, name, price) values('2', 'bezalkoholno pivo', '4', 'Jelen', '120')
insert into Drink(id, description, grade, name, price) values('3', 'domaca', '5', 'Rakija', '80')
insert into Drink(id, description, grade, name, price) values('4', 'svetlo belo vino', '5', 'Vino', '150')

insert into Food(id, description, grade, name, price) values('1', 'gurmanska, ljuta', '5', 'Pljeskavica', '200')
insert into Food(id, description, grade, name, price) values('2', 'specijalitet', '5', 'Karadjordjeva', '250')
=======
>>>>>>> 2b65de13be28ef693124c735de68c0cc25501f77
insert into Restaurant_Manager(id, email, restaurant_id, user_id) values ('1','1','1','1')