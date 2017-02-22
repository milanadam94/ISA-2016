insert into Sys_User(id, email, password, user_type) values(null, 'admin', 'admin', 'SYSTEMMANAGER')

insert into Sys_User(id, email, password, user_type) values(null, 'gost', 'gost', 'GUEST')

insert into Sys_User(id, email, last_name, name, password, user_type) values('2', 'adam94@gmail.com', 'Adamovic','Milan','adam','WAITER')
insert into Waiter(id, birthday, shoe_size, suit_size, user_id) values('1','20.04.1994.','42','L','2')
insert into Sys_User(id, email, password, user_type) values(null, 'gost', 'gost', 'GUEST')

insert into Restaurant(id, name, description, category) values('1', 'Banjalucki', 'rostilj', 'domaca kuhinja')
insert into Restaurant(id, name, description, category) values('2', 'Burek ispoda saca', 'dobar burek', 'domaca kuhinja')
insert into Restaurant(id, name, description, category) values('3', 'Stapic', 'kinezi', 'kineski')

insert into Sys_User(id, email, last_name, name, password, user_type) values('3', 'adam1994@gmail.com', 'Adamovic','Milan','adam','RESTAURANTMANAGER')
insert into Restaurant_Manager(id, user_id) values('1', '3')
