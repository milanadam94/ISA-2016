
insert into Sys_User(id, email, password, user_type) values('1', '1', '1', 'RESTAURANTMANAGER')

insert into Sys_User(id, email, password, user_type) values('2', '2', '2', 'OFFERER')



insert into Restaurant(id, name, description, category) values('1', 'Banjalucki', 'rostilj', 'domaca kuhinja')
insert into Restaurant(id, name, description, category) values('2', 'Burek ispoda saca', 'dobar burek', 'domaca kuhinja')
insert into Restaurant(id, name, description, category) values('3', 'Stapic', 'kinezi', 'kineski')


insert into Restaurant_Manager(id, restaurant_id, user_id) values ('1','1','1')
insert into Offerer(id,user_id) values('1','2')


insert into Menu(id,restaurant_id) values ('1','1')

insert into Active_User(id,email,user_type,user_id) values ('1','1','RESTAURANTMANAGER','1')
insert into Active_User(id,email,user_type,user_id) values ('2','2','OFFERER','2')


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



















