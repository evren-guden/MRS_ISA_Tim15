insert into rentacar (id,name, address, description, rating) values (101,'Atlas servis', 'Novi sad', 'Najbolji rent-a-car servis',5 );
insert into rentacar (id,name, address, description, rating) values (202,'Uni Line', 'Beograd', 'Povoljno izdavanje vozila', 4);
insert into rentacar (id,name, address, description, rating) values (303,'DDM Rent-a-car', 'Novi Sad', 'Veliki izbor vozila', 4);
insert into rentacar (id,name, address, description, rating) values (404,'Inex', 'Novi Sad', 'Izdavanje vozila', 4);

insert into airline (id,name, address, description, rating) values (1,'Aeroflot Russian Airlines', 'Russia', '', 1);
insert into airline (id,name, address, description, rating) values (2,'Norwegian', 'Norway', '', 1);
insert into airline (id,name, address, description, rating) values (3,'Ethiopian Airlines', 'Ethiopia ', '', 1);
insert into airline (id,name, address, description, rating) values (4,'KLM Royal Dutch Airlines', 'Germany', '', 1);
insert into airline (id,name, address, description, rating) values (5,'Air France', 'France', '', 1);
insert into airline (id,name, address, description, rating) values (6,'Austrian Airlines', 'Austria', '', 1);
insert into airline (id,name, address, description, rating) values (7,'Swiss International Air', 'Switzerland', '', 1);
insert into airline (id,name, address, description, rating) values (9,'Turkish Airlines', 'Turkey', '', 1);
insert into airline (id,name, address, description, rating) values (10,'Air Serbia', 'Serbia', '', 1);
insert into flight (id, date_fligh, date_land, finald, information_luggage, length_travel, number, price_ticket, startd, time_travel, airline_id, final_id, start_id) values (11,'2019-04-30','2019-05-30','bec','da',5,5,5,'bg',1,1,1,1);

insert into flight (id, date_fligh, date_land, finald, information_luggage, length_travel, number, price_ticket, startd, time_travel, airline_id, final_id, start_id) values (12,'2019-09-30','2019-06-30','ue','ne',3,3,3,'nis',2,2,2,2);



insert into branch (id, rac_id, name, address) values (1, 101,'My branch 1', 'Novi Sad');
insert into branch (id, rac_id, name, address) values (2, 303,'My branch 1', 'Novi Sad');
insert into branch (id, rac_id, name, address) values (3, 101,'My branch 2', 'Zlatibor');
insert into branch (id, rac_id, name, address) values (4, 101,'My branch 3', 'Kraljevo');

insert into vehicle (id, registration_number, type, gear, color, branch_id, price_per_day) values (1, 'AA-11', 'neki_tip', 'automatic', 'red', 1, 400);
insert into vehicle (id, registration_number, type, gear, color, branch_id, price_per_day) values (2, 'BB-11', 'neki_tip2', 'manual', 'green',1, 350);
insert into vehicle (id, registration_number, type, gear, color, branch_id, price_per_day) values (3, 'CC-11', 'neki_tip3', 'automatic', 'black', 1, 100);
insert into vehicle (id, registration_number, type, gear, color, branch_id, price_per_day) values (4, 'DD-11', 'neki_tip2', 'manual', 'green',1, 700);
insert into vehicle (id, registration_number, type, gear, color, branch_id, price_per_day) values (5, 'EE-11', 'neki_tip3', 'automatic', 'black', 1, 560);
insert into vehicle (id, registration_number, type, gear, color, branch_id) values (6, 'BB-11', 'neki_tip2', 'manual', 'green',3);
insert into vehicle (id, registration_number, type, gear, color, branch_id) values (7, 'CC-11', 'neki_tip3', 'automatic', 'black', 3);
insert into vehicle (id, registration_number, type, gear, color, branch_id) values (8, 'BB-11', 'neki_tip2', 'manual', 'green',4);
insert into vehicle (id, registration_number, type, gear, color, branch_id) values (9, 'CC-11', 'neki_tip3', 'automatic', 'black', 4);

insert into hotel (id, name, address, description, rating, stars) values (100, 'Park', 'Novi Sad', '',4.5, 5);
insert into hotel (id, name, address, description, rating, service_type_prices_id,stars) values (200, 'Palisad', 'Zlatibor', '',4.2, 100, 3);
insert into hotel (id, name, address, description, rating, stars) values (300, 'Park 2', 'Novi Sad', '',5, 5);
insert into hotel (id, name, address, description, rating, stars) values (400, 'Palisad 2', 'Zlatibor', '',4, 3);


insert into room (id, room_number, floor_number, capacity, rating, price_per_nigth, hotel_id) values (100, 101, 1, 2, 5, 45, 100);
insert into room (id, room_number, floor_number, capacity, rating, price_per_nigth, hotel_id) values (101, 102, 1, 2, 5, 45, 100);
insert into room (id, room_number, floor_number, capacity, rating, price_per_nigth, hotel_id) values (102, 103, 1, 2, 5, 45, 200);
insert into room (id, room_number, floor_number, capacity, rating, price_per_nigth, hotel_id) values (103, 104, 1, 2, 5, 45, 300);
insert into room (id, room_number, floor_number, capacity, rating, price_per_nigth, hotel_id) values (104, 201, 3, 2, 5, 55, 100);
insert into room (id, room_number, floor_number, capacity, rating, price_per_nigth, hotel_id) values (105, 202, 2, 2, 5, 55, 100);

insert into price (id, price, start_date, end_date) values (100, 20, '2019-06-01 00:00:00', '2019-07-01 00:00:00');
insert into price (id, price, start_date, end_date) values (101, 25, '2019-07-01 00:00:00', '2019-08-01 00:00:00');
insert into price (id, price, start_date, end_date) values (102, 27, '2019-08-01 00:00:00', '2019-09-01 00:00:00');
insert into price (id, price, start_date, end_date) values (103, 20, '2019-09-01 00:00:00', '2019-10-01 00:00:00');

insert into price (id, price, start_date, end_date) values (104, 20, '2019-06-01 00:00:00', '2019-07-01 00:00:00');
insert into price (id, price, start_date, end_date) values (105, 25, '2019-07-01 00:00:00', '2019-08-01 00:00:00');
insert into price (id, price, start_date, end_date) values (106, 27, '2019-08-01 00:00:00', '2019-09-01 00:00:00');
insert into price (id, price, start_date, end_date) values (107, 20, '2019-09-01 00:00:00', '2019-10-01 00:00:00');

insert into room_prices (room_id, prices_id) values (100, 100);
insert into room_prices (room_id, prices_id) values (100, 101);
insert into room_prices (room_id, prices_id) values (100, 102);
insert into room_prices (room_id, prices_id) values (100, 103);

insert into room_prices (room_id, prices_id) values (101, 104);
insert into room_prices (room_id, prices_id) values (101, 105);
insert into room_prices (room_id, prices_id) values (102, 106);
insert into room_prices (room_id, prices_id) values (102, 107);

insert into hotel_special_offer (id, name, description, price) values (100, 'Wellness','', 10);
insert into hotel_special_offer (id, name, description, price) values (101, 'Wellness','', 12);
insert into hotel_special_offer (id, name, description, price) values (102, 'Wellness','', 11);
insert into hotel_special_offer (id, name, description, price) values (103, 'Wellness','', 13);

insert into hotel_special_offer (id, name, description, price) values (104, 'Gym','', 9);
insert into hotel_special_offer (id, name, description, price) values (105, 'Gym','', 10);
insert into hotel_special_offer (id, name, description, price) values (106, 'Gym','', 10);
insert into hotel_special_offer (id, name, description, price) values (107, 'Gym','', 12);

insert into hotel_special_offers (hotel_id, special_offers_id) values (100, 100);
insert into hotel_special_offers (hotel_id, special_offers_id) values (200, 101);
insert into hotel_special_offers (hotel_id, special_offers_id) values (300, 102);
insert into hotel_special_offers (hotel_id, special_offers_id) values (400, 103);

insert into hotel_special_offers (hotel_id, special_offers_id) values (100, 104);
insert into hotel_special_offers (hotel_id, special_offers_id) values (200, 105);
insert into hotel_special_offers (hotel_id, special_offers_id) values (300, 106);
insert into hotel_special_offers (hotel_id, special_offers_id) values (400, 107);

insert into hotel_service_type_prices (id,bed_and_breakfast_enabled, bed_and_breakfast_price,half_board_enabled, half_board_price,full_board_enabled, full_board_price,all_inclusive_enabled, all_inclusive_price) values (100, true, 10,  false, 0, false, 0, false, 0);

insert into room_reservation(id, room_id, user_id, check_in_date, check_out_date, reservation_date, total_price ) values (100, 102, 100, '2019-11-01 00:00:00', '2019-11-15 00:00:00', '2019-05-15 00:00:00', 200);
insert into room_reservation(id, room_id, user_id, check_in_date, check_out_date, reservation_date, total_price ) values (101, 101, 100, '2019-11-01 00:00:00', '2019-11-15 00:00:00', '2019-05-15 00:00:00', 200);

INSERT INTO registered_user (id, username, password, first_name, last_name, email, enabled) VALUES (100, 'user', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', 'user@example.com', true);
INSERT INTO admin (id, username, password, first_name, last_name, email, enabled, type) VALUES (200, 'admin', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikola', 'Nikolic', 'admin@example.com', true, 0);
INSERT INTO admin (id, username, password, first_name, last_name, email, enabled, company_id, type) VALUES (300, 'adminrac', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Bojana', 'Zoric', 'admin@example.com', true, 101, 3);
INSERT INTO admin (id, username, password, first_name, last_name, email, enabled, company_id, type) VALUES (400, 'adminhotel', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Ana', 'Mijailovic', 'admin@example.com', true, 200, 2);
INSERT INTO admin (id, username, password, first_name, last_name, email, enabled, type) VALUES (500, 'adminairline', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marina', 'Simovic', 'admin@example.com', true, 1);
INSERT INTO admin (id, username, password, first_name, last_name, email, enabled, company_id, type) VALUES (600, 'adminrac2', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Bojana', 'Zoric', 'admin@example.com', true, 101, 3);
INSERT INTO admin (id, username, password, first_name, last_name, email, enabled, company_id, type) VALUES (700, 'adminhotel2', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Ana', 'Mijailovic', 'admin@example.com', true, 300, 2);
INSERT INTO admin (id, username, password, first_name, last_name, email, enabled, type) VALUES (800, 'adminairline2', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marina', 'Simovic', 'admin@example.com', true, 1);
INSERT INTO admin (id, username, password, first_name, last_name, email, enabled, company_id, type) VALUES (900, 'adminrac3', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Bojana', 'Zoric', 'admin@example.com', true, 101, 3);
INSERT INTO admin (id, username, password, first_name, last_name, email, enabled, company_id, type) VALUES (1000, 'adminhotel3', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Ana', 'Mijailovic', 'admin@example.com', true, 200, 2);
INSERT INTO admin (id, username, password, first_name, last_name, email, enabled, type) VALUES (1100, 'adminairline3', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marina', 'Simovic', 'admin@example.com', true, 1);

INSERT INTO authority (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO authority (id, name) VALUES (2, 'ROLE_ADMIN');
INSERT INTO authority (id, name) VALUES (3, 'ROLE_ADMINRAC');
INSERT INTO authority (id, name) VALUES (4, 'ROLE_ADMINHOTEL');
INSERT INTO authority (id, name) VALUES (5, 'ROLE_ADMINAIRLINE');

INSERT INTO user_authority (user_id, authority_id) VALUES (100, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (200, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (300, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (400, 4);
INSERT INTO user_authority (user_id, authority_id) VALUES (500, 5);
INSERT INTO user_authority (user_id, authority_id) VALUES (600, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (700, 4);
INSERT INTO user_authority (user_id, authority_id) VALUES (800, 5);
INSERT INTO user_authority (user_id, authority_id) VALUES (900, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (1000, 4);
INSERT INTO user_authority (user_id, authority_id) VALUES (1100, 5);


insert into vehicle_reservation (id, vehicle_id, vehicle_user_id, check_in_date, check_out_date, total_price) values(1,1, 100,'2018-11-01 00:00:00', '2018-11-02 00:00:00', 100);
insert into vehicle_reservation (id, vehicle_id, vehicle_user_id, check_in_date, check_out_date, total_price) values(2,1, 100,'2019-01-01 00:00:00', '2019-01-02 00:00:00', 200);
insert into vehicle_reservation (id, vehicle_id, vehicle_user_id, check_in_date, check_out_date, total_price) values(3,1, 100,'2019-02-01 00:00:00', '2019-02-02 00:00:00', 105);
insert into vehicle_reservation (id, vehicle_id, vehicle_user_id, check_in_date, check_out_date, total_price) values(4,1, 100,'2019-04-01 00:00:00', '2019-04-02 00:00:00', 150);
insert into vehicle_reservation (id, vehicle_id, vehicle_user_id, check_in_date, check_out_date, total_price) values(5,1, 100,'2019-03-01 00:00:00', '2019-03-02 00:00:00', 250);
insert into RACSpecial_Offer (id, name, racservice_id, price) values (1,"Special offer", 101, 10);




INSERT INTO friend_request (sender_id, reciever_id, date_created) VALUES (100, 101, '2019-06-01 00:00:00');
INSERT INTO friend_request (sender_id, reciever_id, date_created) VALUES (102, 100, '2019-06-01 00:00:00');
INSERT INTO friend_request (sender_id, reciever_id, date_created) VALUES (101, 103, '2019-06-01 00:00:00');

insert into seat (flight_id, seat_number, type, available) values (11,  1, 1, true);
insert into seat (flight_id, seat_number, type, available) values (11,  2, 1, true);
insert into seat (flight_id, seat_number, type, available) values (11,  3, 1, true);
insert into seat (flight_id, seat_number, type, available) values (11,  4, 2, true);
insert into seat (flight_id, seat_number, type, available) values (11,  5, 2, true);
insert into seat (flight_id, seat_number, type, available) values (11,  6, 2, true);
insert into seat (flight_id, seat_number, type, available) values (11,  7, 0, true);
insert into seat (flight_id, seat_number, type, available) values (11,  8, 1, false);
insert into seat (flight_id, seat_number, type, available) values (11,  9, 2, true);
insert into seat (flight_id, seat_number, type, available) values (11, 10, 2, true);
insert into seat (flight_id, seat_number, type, available) values (11, 11, 2, true);
insert into seat (flight_id, seat_number, type, available) values (11, 12, 1, true);
insert into seat (flight_id, seat_number, type, available) values (11, 13, 1, true);
insert into seat (flight_id, seat_number, type, available) values (11, 14, 1, true);
insert into seat (flight_id, seat_number, type, available) values (11, 15, 0, true);
insert into seat (flight_id, seat_number, type, available) values (11, 16, 0, true);
insert into seat (flight_id, seat_number, type, available) values (11, 17, 1, false);
insert into seat (flight_id, seat_number, type, available) values (11, 18, 1, false);