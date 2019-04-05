insert into RentACar (id,name, address, description, rating) values (100,'AA-11', 'Type1', 'automatic', 1);
insert into RentACar (id,name, address, description, rating) values (200,'AA-11', 'Type1', 'automatic', 1);
insert into RentACar (id,name, address, description, rating) values (300,'AA-11', 'Type1', 'automatic', 1);

insert into Branch (rac_id) values (1);
insert into Branch (rac_id) values (2);
insert into Branch (rac_id) values (1);

insert into Vehicle (registration_number, type, gear, color) values ('AA-11', 'neki_tip', 'automatic', 'red');
insert into Vehicle (registration_number, type, gear, color) values ('BB-11', 'neki_tip2', 'manual', 'green');
insert into Vehicle (registration_number, type, gear, color) values ('CC-11', 'neki_tip3', 'automatic', 'black');

insert into Hotel (id, name, address, description, rating) values (100, 'Park', 'Novi Sad', '',5);
insert into Hotel (id, name, address, description, rating) values (200, 'Palisad', 'Zlatibor', '',5);

INSERT INTO admin (id, username, password, first_name, last_name, email, enabled) VALUES (1, 'user', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', 'user@example.com', true);
INSERT INTO registered_user (id, username, password, first_name, last_name, email, enabled) VALUES (2, 'admin', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikola', 'Nikolic', 'admin@example.com', true);
INSERT INTO admin (id, username, password, first_name, last_name, email, enabled) VALUES (3, 'adminrac', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Bojana', 'Zoric', 'admin@example.com', true);
INSERT INTO admin (id, username, password, first_name, last_name, email, enabled) VALUES (4, 'adminhotel', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Ana', 'Mijailovic', 'admin@example.com', true);
INSERT INTO admin (id, username, password, first_name, last_name, email, enabled) VALUES (5, 'adminairline', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marina', 'Simovic', 'admin@example.com', true);

INSERT INTO AUTHORITY (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO AUTHORITY (id, name) VALUES (2, 'ROLE_ADMIN');
INSERT INTO AUTHORITY (id, name) VALUES (3, 'ROLE_ADMINRAC');
INSERT INTO AUTHORITY (id, name) VALUES (4, 'ROLE_ADMINHOTEL');
INSERT INTO AUTHORITY (id, name) VALUES (5, 'ROLE_ADMINAIRLINE');


INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 2);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (3, 3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (4, 4);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (5, 5);
