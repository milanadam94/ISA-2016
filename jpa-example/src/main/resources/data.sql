insert into Sys_User(id, email, password, user_type) values('1', 'admin', 'admin', 'SYSTEMMANAGER')

insert into Sys_User(id, email, password, user_type, name, last_name) values('2', 'borossasa@gmail.com', '1100rh18', 'GUEST', 'Sasa', 'Boros')
insert into Sys_User(id, email, password, user_type, name, last_name) values('3', 'djole', '1100rh18', 'GUEST', 'Kesa', 'Kesic')
insert into Sys_User(id, email, password, user_type, name, last_name) values('4', 'mitar', '1100rh18', 'GUEST', 'Mitar', 'Miric')
insert into Sys_User(id, email, password, user_type, name, last_name) values('5', 'jeca', '1100rh18', 'GUEST', 'Jelena', 'Boros')
insert into Sys_User(id, email, password, user_type, name, last_name) values('6', 'krepa', '1100rh18', 'GUEST', 'Krepa', 'Krepic')

insert into Guest(id, user_id, address, city, visits) values('1', '2', 'Lole Ribara 14', 'Sremska Kamenica', 4)
insert into Guest(id, user_id, address, city, visits) values('2', '3', 'Gavrila Principa 15', 'Sremska Kamenica', 5)
insert into Guest(id, user_id, address, city, visits) values('3', '4', 'Balzakova 12 stan 32', 'Novi Sad', 7)
insert into Guest(id, user_id, address, city, visits) values('4', '5', 'Lole Ribara 14', 'Sremska Kamenica', 1)
insert into Guest(id, user_id, address, city, visits) values('5', '6', 'Lole Ribara 14', 'Sremska Kamenica', 1)


insert into Friendship(guest, friend) values('1', '3')
insert into Friendship(guest, friend) values('1', '4')
insert into Friendship(guest, friend) values('3', '1')
insert into Friendship(guest, friend) values('4', '1')
insert into Friendship(guest, friend) values('5', '1')
insert into Friendship(guest, friend) values('1', '5')

insert into Restaurant(id, name, description, category) values('1', 'Banjalucki', 'rostilj', 'domaca kuhinja')
insert into Restaurant(id, name, description, category) values('2', 'Burek ispoda saca', 'dobar burek', 'domaca kuhinja')
insert into Restaurant(id, name, description, category) values('3', 'Stapic', 'kinezi', 'kineski')

insert into Sys_User(id, email, first_login, last_name, name, password, user_type) values('7', 'adam94@gmail.com', FALSE, 'Mitic', 'Petar', 'milanadam', 'WAITER')
insert into Waiter(id, birthday, shoe_size, suit_size, restaurant_id, user_id) values('1', '20.04.1994.', '56', 'M', '1', '7')

insert into Sys_User(id, email, first_login, last_name, name, password, user_type) values('8','adamovic@gmail.com', FALSE, 'Peric','Luka','milanadam','BARTENDER')
insert into Bartender(id, birthday, shoe_size, suit_size, restaurant_id, user_id) values('1','20.04.1994.', '56', 'M', '1', '8')

insert into Sys_User(id, email, first_login, last_name, name, password, user_type) values('9','milan@gmail.com', FALSE,'Petrovic','Petar','milanadam','COOK')
insert into Cook(id, birthday, cook_type, shoe_size, suit_size, restaurant_id, user_id) values('1','20.04.1994.', 'SALAD', '56', 'M', '1', '9')

insert into Sys_User(id, email, first_login, last_name, name, password, user_type) values('10','mile@gmail.com', FALSE,'Matic','Milan','milanadam','COOK')
insert into Cook(id, birthday, cook_type, shoe_size, suit_size, restaurant_id, user_id) values('2','20.04.1994.', 'COOKEDMEAL', '56', 'M', '1', '10')

insert into Sys_User(id, email, first_login, last_name, name, password, user_type) values('11','adam@gmail.com', FALSE,'Petrovic','Marko','milanadam','COOK')
insert into Cook(id, birthday, cook_type, shoe_size, suit_size, restaurant_id, user_id) values('3','20.04.1994.', 'FRIEDMEAL', '56', 'M', '1', '11')

insert into Sys_User(id, email, first_login, last_name, name, password, user_type) values('12', 'marko@gmail.com', FALSE, 'Matic', 'Nemanja', 'milanadam', 'WAITER')
insert into Waiter(id, birthday, shoe_size, suit_size, restaurant_id, user_id) values('2', '20.04.1994.', '56', 'M', '1', '12')

insert into Sys_User(id, email, first_login, last_name, name, password, user_type) values('13', 'mirkossxs@gmail.com', FALSE, 'Markovic', 'Mirko', 'milanadam', 'WAITER')
insert into Waiter(id, birthday, shoe_size, suit_size, restaurant_id, user_id) values('3', '20.04.1994.', '56', 'M', '1', '13')

insert into Sys_User(id, email, first_login, last_name, name, password, user_type) values('14','milance@gmail.com', FALSE, 'Peric','Marko','milanadam','BARTENDER')
insert into Bartender(id, birthday, shoe_size, suit_size, restaurant_id, user_id) values('2','20.04.1994.', '56', 'M', '1', '14')

insert into Sys_User(id, email, first_login, last_name, name, password, user_type) values('15','luka@gmail.com', FALSE, 'Peric','Luka','milanadam','BARTENDER')
insert into Bartender(id, birthday, shoe_size, suit_size, restaurant_id, user_id) values('3','20.04.1994.', '56', 'M', '1', '15')


insert into Sys_User(id, email, first_login, last_name, name, password, user_type) values('16','sasa@gmail.com', FALSE, 'Peric','Djole','123','RESTAURANTMANAGER')
insert into Restaurant_Manager(id, restaurant_id, user_id) values ('1','1','16')
insert into Sys_User(id, email, first_login, last_name, name, password, user_type) values('17','offerer@gmail.com', FALSE, 'Kesic','Luka','123','OFFERER')
insert into Offerer(id,user_id) values('1','17')

insert into Segment(id, restaurant_id, smoking, name) values('1', '1', true, 'Pusacki segment')
insert into Segment(id, restaurant_id, smoking, name) values('2', '1', false, 'Nepusacki segment')
insert into Segment(id, restaurant_id, smoking, name) values('3', '2', false, 'Segment za plakanje')

insert into Guest_Table(id, seat_count, tag, description, x_coord, y_coord, segment_id, restaurant_id) values('1', '5' , 't1', null, '5', '3', '1', '1')
insert into Guest_Table(id, seat_count, tag, description, x_coord, y_coord, segment_id, restaurant_id) values('2', '6' , 't2', 'lep onako astal', '1', '1', '1', '1')
insert into Guest_Table(id, seat_count, tag, description, x_coord, y_coord, segment_id, restaurant_id) values('3', '2' , 't3', null, '7', '4', '3', '2')

insert into Drink(id, description, grade, name, price) values ('1', 'tamno', '5', 'Lav', '125')
insert into Drink(id, description, grade, name, price) values ('2', 'psenicno', '5', 'Jelen', '170')
insert into Drink(id, description, grade, name, price) values ('3', 'svetlo', '5', 'Vino','150')

insert into Food(id, description, grade, name, price, food_type) values('1', 'ljuta kobasica', '4', 'Kobasica', '150', 'COOKEDMEAL')
insert into Food(id, description, grade, name, price, food_type) values('2', 'fino zacinjena', '4', 'Karadjordjeva', '250', 'COOKEDMEAL')
insert into Food(id, description, grade, name, price, food_type) values('3', 'govedji', '5', 'Gulas', '200', 'COOKEDMEAL')

insert into Menu(id, restaurant_id) values ('1','1')

insert into Menu_Foods(menu_id, foods_id) values ('1', '1')
insert into Menu_Foods(menu_id, foods_id) values ('1', '2')
insert into Menu_Foods(menu_id, foods_id) values ('1', '3')

insert into Menu_Drinks(menu_id, drinks_id) values ('1', '1')
insert into Menu_Drinks(menu_id, drinks_id) values ('1', '2')
insert into Menu_Drinks(menu_id, drinks_id) values ('1', '3')

-- SASA 1 GORE
insert into Guest_Order(id, order_date, prepared, restaurant_id, waiter_id)values('1', '2014-02-28', FALSE, '1', '1')
insert into Guest_Order(id, order_date, prepared, restaurant_id, waiter_id)values('2', '2014-02-28', FALSE, '1', '1')

insert into Drink_Order(id, prepared, quantity, drink_id) values('1', FALSE, '2', '1')
insert into Drink_Order(id, prepared, quantity, drink_id) values('2', FALSE, '2', '3')
insert into Drink_Order(id, prepared, quantity, drink_id) values('3', FALSE, '4', '1')
insert into Drink_Order(id, prepared, quantity, drink_id) values('4', FALSE, '4', '3')

insert into Food_Order(id, prepared, quantity, started, food_id) values('1', FALSE, '2', FALSE, '3')
insert into Food_Order(id, prepared, quantity, started, food_id) values('2', FALSE, '2', FALSE, '1')
insert into Food_Order(id, prepared, quantity, started, food_id) values('3', FALSE, '4', FALSE, '3')
insert into Food_Order(id, prepared, quantity, started, food_id) values('4', FALSE, '4', FALSE, '1')

insert into Guest_Order_Drink_Orders(guest_order_id, drink_orders_id) values ('1', '1')
insert into Guest_Order_Drink_Orders(guest_order_id, drink_orders_id) values ('1', '2')
insert into Guest_Order_Drink_Orders(guest_order_id, drink_orders_id) values ('2', '3')
insert into Guest_Order_Drink_Orders(guest_order_id, drink_orders_id) values ('2', '4')

insert into Guest_Order_Food_Orders(guest_order_id, food_orders_id) values ('1', '1')
insert into Guest_Order_Food_Orders(guest_order_id, food_orders_id) values ('1', '2')
insert into Guest_Order_Food_Orders(guest_order_id, food_orders_id) values ('2', '3')
insert into Guest_Order_Food_Orders(guest_order_id, food_orders_id) values ('2', '4')

insert into Schedule(id, end_date, shift, start_date, segment_id) values('1', '2017-04-28', '0','2017-03-18','1')
insert into Schedule(id, end_date, shift, start_date, segment_id) values('2', '2017-04-28', '1','2017-03-18','1')
insert into Schedule(id, end_date, shift, start_date, segment_id) values('3', '2017-05-14', '2','2017-04-20','1')
insert into Schedule(id, end_date, shift, start_date, segment_id) values('4', '2017-06-25', '1','2017-05-18','1')
insert into Schedule(id, end_date, shift, start_date, segment_id) values('5', '2017-09-28', '0','2017-06-25','1')
insert into Schedule(id, end_date, shift, start_date, segment_id) values('6', '2017-05-12', '2','2017-03-25','1')

insert into Worker_Schedule(id, schedule_id, worker_id) values ('1','1','3')
insert into Worker_Schedule(id, schedule_id, worker_id) values ('2','2','3')
insert into Worker_Schedule(id, schedule_id, worker_id) values ('3','3','3')
insert into Worker_Schedule(id, schedule_id, worker_id) values ('4','1','2')
insert into Worker_Schedule(id, schedule_id, worker_id) values ('5','2','2')
insert into Worker_Schedule(id, schedule_id, worker_id) values ('6','3','1')

insert into Worker_Schedule(id, schedule_id, worker_id) values ('7','4','1')
insert into Worker_Schedule(id, schedule_id, worker_id) values ('8','3','2')
insert into Worker_Schedule(id, schedule_id, worker_id) values ('9','5','3')
insert into Worker_Schedule(id, schedule_id, worker_id) values ('10','6','2')
insert into Worker_Schedule(id, schedule_id, worker_id) values ('11','4','1')

insert into Worker_Schedule(id, schedule_id, worker_id) values ('12','1','1')
insert into Worker_Schedule(id, schedule_id, worker_id) values ('13','3','2')
insert into Worker_Schedule(id, schedule_id, worker_id) values ('14','4','3')
insert into Worker_Schedule(id, schedule_id, worker_id) values ('15','5','2')
insert into Worker_Schedule(id, schedule_id, worker_id) values ('16','6','1')

--SASA 2 DOLE

insert into Tender(id, description, expired, end_date, start_date, restaurant_id) values ('1','To je taj bre', FALSE, '2018-02-21','2015-02-02','1')
insert into Tender(id, description, expired, end_date, start_date, restaurant_id) values ('2','To je taj bre', FALSE, '2017-01-21','2016-06-06','2')
insert into Tender(id, description, expired, end_date, start_date, restaurant_id) values ('3','To je taj bre', FALSE, '2019-11-21','2017-01-02','1')
insert into Tender(id, description, expired, end_date, start_date, restaurant_id) values ('4','To je taj bre', FALSE, '2020-02-21','2015-06-02','2')
insert into Tender(id, description, expired, end_date, start_date, restaurant_id) values ('5','To je taj bre', FALSE, '2018-03-21','2014-01-02','3')

insert into Offerings(id, delivery_date, description, price, offerer_id, tender_id) values('1','2017-02-28','NekiTekst1', '120', '1', '3')
insert into Offerings(id, delivery_date, description, price, offerer_id, tender_id) values('2','2018-02-28','NekiTekst2', '120', '1', '3')
insert into Offerings(id, delivery_date, description, price, offerer_id, tender_id) values('3','2019-02-28','NekiTekst3', '120', '1', '1')
insert into Offerings(id, delivery_date, description, price, offerer_id, tender_id) values('4','2014-02-28','NekiTekst4', '120', '1', '1')
insert into Offerings(id, delivery_date, description, price, offerer_id, tender_id) values('5','2012-02-28','NekiTekst5', '120', '1', '1')
insert into Offerings(id, delivery_date, description, price, offerer_id, tender_id) values('6','2014-02-28','NekiTekst6', '120', '1', '3')
insert into Offerings(id, delivery_date, description, price, offerer_id, tender_id) values('7','2015-02-28','NekiTekst7', '120', '1', '3')


insert into Guest_Table(id, tag, x_coord,seat_count, y_coord, restaurant_id, segment_id) values('4','A','2','2','3','1','1')

insert into Reservation(id, reservation_date_time, guest_id, restaurant_id) values ('1','2010-01-01T00:01:00.000Z','1','1')

insert into Reservation_Tables(reservation_id,tables_id) values('1','1')

insert into Restaurant_Recension(id, grade, guest_id, restaurant_id) values ('1','4','1','1')
insert into Restaurant_Recension(id, grade, guest_id, restaurant_id) values ('2','6','1','1')
insert into Restaurant_Recension(id, grade, guest_id, restaurant_id) values ('3','1','1','1')
insert into Restaurant_Recension(id, grade, guest_id, restaurant_id) values ('4','9','1','1')

insert into Invite(id,accepted, friend_id, guest_id, reservation_id) values('1',TRUE, '2','1','1')
insert into Invite(id,accepted, friend_id, guest_id, reservation_id) values('2',TRUE, '3','1','1')
insert into Invite(id,accepted, friend_id, guest_id, reservation_id) values('3',TRUE, '4','1','1')

