insert into RentACar (id,name, address, description, rating) values (100,'AA-11', 'Type1', 'automatic', 1);
insert into RentACar (id,name, address, description, rating) values (200,'AA-11', 'Type1', 'automatic', 1);
insert into RentACar (id,name, address, description, rating) values (300,'AA-11', 'Type1', 'automatic', 1);

insert into Branch (rac_id) values (1);
insert into Branch (rac_id) values (2);
insert into Branch (rac_id) values (1);

insert into Vehicle (registration_number, type, gear, color, rentacar_id) values ('AA-11', 'neki_tip', 'automatic', 'red', 1);