insert into rentacar (id,name, address, description, rating) values (1,'rac1', 'addr', 'automatic', 1);
insert into rentacar (id,name, address, description, rating) values (2,'rac2', 'addr', 'automatic', 1);
insert into rentacar (id,name, address, description, rating) values (3,'rac3', 'addr', 'automatic', 1);

insert into airline (id,name, address, description, rating) values (1,'Aeroflot Russian Airlines', 'Russia', '', 1);
insert into airline (id,name, address, description, rating) values (2,'Norwegian', 'Norway', '', 1);
insert into airline (id,name, address, description, rating) values (3,'Ethiopian Airlines', 'Ethiopia ', '', 1);
insert into airline (id,name, address, description, rating) values (4,'KLM Royal Dutch Airlines', 'Germany', '', 1);
insert into airline (id,name, address, description, rating) values (5,'Air France', 'France', '', 1);
insert into airline (id,name, address, description, rating) values (6,'Austrian Airlines', 'Austria', '', 1);
insert into airline (id,name, address, description, rating) values (7,'Swiss International Air', 'Switzerland', '', 1);
insert into airline (id,name, address, description, rating) values (9,'Turkish Airlines', 'Turkey', '', 1);
insert into airline (id,name, address, description, rating) values (10,'Air Serbia', 'Serbia', '', 1);





insert into branch (id, rac_id, name, address) values (1, 1,'ime', 'adresa');
insert into branch (id, rac_id) values (2, 2);
insert into branch (id, rac_id) values (3, 1);
insert into branch (id, rac_id) values (4, 1);

insert into vehicle (id, registration_number, type, gear, color, branch_id) values (1, 'AA-11', 'neki_tip', 'automatic', 'red', 1);
insert into vehicle (id, registration_number, type, gear, color) values (2, 'BB-11', 'neki_tip2', 'manual', 'green');
insert into vehicle (id, registration_number, type, gear, color, branch_id) values (3, 'CC-11', 'neki_tip3', 'automatic', 'black', 1);

insert into hotel (id, name, address, description, rating) values (100, 'Park', 'Novi Sad', '',5);
insert into hotel (id, name, address, description, rating) values (200, 'Palisad', 'Zlatibor', '',5);
insert into hotel (id, name, address, description, rating) values (300, 'Park 2', 'Novi Sad', '',5);
insert into hotel (id, name, address, description, rating) values (400, 'Palisad 2', 'Zlatibor', '',5);

INSERT INTO admin (id, username, password, first_name, last_name, email, enabled) VALUES (1, 'user', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', 'user@example.com', true);
INSERT INTO registered_user (id, username, password, first_name, last_name, email, enabled) VALUES (2, 'admin', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikola', 'Nikolic', 'admin@example.com', true);
INSERT INTO admin (id, username, password, first_name, last_name, email, enabled, company_id) VALUES (3, 'adminrac', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Bojana', 'Zoric', 'admin@example.com', true, 1);
INSERT INTO admin (id, username, password, first_name, last_name, email, enabled) VALUES (4, 'adminhotel', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Ana', 'Mijailovic', 'admin@example.com', true);
INSERT INTO admin (id, username, password, first_name, last_name, email, enabled) VALUES (5, 'adminairline', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marina', 'Simovic', 'admin@example.com', true);

INSERT INTO authority (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO authority (id, name) VALUES (2, 'ROLE_ADMIN');
INSERT INTO authority (id, name) VALUES (3, 'ROLE_ADMINRAC');
INSERT INTO authority (id, name) VALUES (4, 'ROLE_ADMINHOTEL');
INSERT INTO authority (id, name) VALUES (5, 'ROLE_ADMINAIRLINE');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (3, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (4, 4);
INSERT INTO user_authority (user_id, authority_id) VALUES (5, 5);
