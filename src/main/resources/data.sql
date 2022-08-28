insert into drone(model, serial_number, weight, battery_percentage, state)
values ('Cruiserweight', '12qw2222211', 154.5, 100.0, 'LOADING');
insert into drone(model, serial_number, weight, battery_percentage, state)
values ('Heavyweight', 'whih70989898', 0.00, 23.4, 'IDLE');
insert into drone(model, serial_number, weight, battery_percentage, state)
values ('Cruiserweight', '843792knksldnkas', 0.00, 100.0, 'IDLE');
insert into drone(model, serial_number, weight, battery_percentage, state)
values ('Middleweight ', '65875yyuu6565', 149.5, 100.0, 'LOADING');
insert into drone(model, serial_number, weight, battery_percentage, state)
values ('Cruiserweight', '878f7sdf8s7d', 0.00, 100.0, 'IDLE');
insert into drone(model, serial_number, weight, battery_percentage, state)
values ('Middleweight', '889ew7f8sdfwcdsc', 0.00, 100.0, 'IDLE');
insert into drone(model, serial_number, weight, battery_percentage, state)
values ('Cruiserweight', '43hj42jjjjjj', 118, 100.0, 'DELIVERING');
insert into drone(model, serial_number, weight, battery_percentage, state)
values ('Middleweight', 'e2r2r3444eeef', 0.00, 100.0, 'IDLE');
insert into drone(model, serial_number, weight, battery_percentage, state)
values ('Cruiserweight', '968d9f9sd8ds', 0.00, 100.0, 'IDLE');
insert into drone(model, serial_number, weight, battery_percentage, state)
values ('Heavyweight', '87c87xcsc7dc', 149, 100.0, 'LOADING');


insert into medication(name, weight, code, image)
values ('Benadryl', 10.0, 'NDC111899', '/meds/benadryl.jpg');
insert into medication(name, weight, code, image)
values ('Band-Aid  Anti-Itch', 136.0, 'ANT327899', '/meds/anti-itch.jpg');
insert into medication(name, weight, code, image)
values ('Lipitor', 30.0, 'NDC227699', '/meds/lipitor.jpg');
insert into medication(name, weight, code, image)
values ('Nexium', 45.6, 'NDC456456', '/meds/nexium.jpg');
insert into medication(name, weight, code, image)
values ('Plavix', 30.5, 'NDC159753', '/meds/plavix.jpg');
insert into medication(name, weight, code, image)
values ('Singulair', 25.5, 'NDC852654', '/meds/singulair.jpg');
insert into medication(name, weight, code, image)
values ('Crestor', 15.5, 'NDC456959', '/meds/crestor.jpg');
insert into medication(name, weight, code, image)
values ('Actos', 20.0, 'NDC998555', '/meds/actos.jpg');
insert into medication(name, weight, code, image)
values ('Epogen', 57.0, 'NDC118555', '/meds/epogen.jpg');
insert into medication(name, weight, code, image)
values ('Amoxicillin', 67.0, 'NDC123555', '/meds/amoxicillin.jpg');

insert into drone_medication (drone_id, code, loaded_date_time)
values (1, 'NDC118555', '2022-08-27 09:29:36');
insert into drone_medication (drone_id, code, loaded_date_time)
values (1, 'NDC123555', '2022-08-27 08:29:36');
insert into drone_medication (drone_id, code, loaded_date_time)
values (1, 'NDC159753', '2022-08-27 10:19:36');
insert into drone_medication (drone_id, code, loaded_date_time)
values (4, 'NDC456959', '2022-08-27 12:09:36');
insert into drone_medication (drone_id, code, loaded_date_time)
values (4, 'NDC111899', '2022-08-27 14:29:36');
insert into drone_medication (drone_id, code, loaded_date_time)
values (4, 'NDC123555', '2022-08-27 17:29:36');
insert into drone_medication (drone_id, code, loaded_date_time)
values (4, 'NDC118555', '2022-08-27 19:29:36');
insert into drone_medication (drone_id, code, loaded_date_time)
values (7, 'NDC852654', '2022-08-27 13:29:36');
insert into drone_medication (drone_id, code, loaded_date_time)
values (7, 'NDC123555', '2022-08-28 15:29:36');
insert into drone_medication (drone_id, code, loaded_date_time)
values (7, 'NDC456959', '2022-08-27 08:29:36');
insert into drone_medication (drone_id, code, loaded_date_time)
values (7, 'NDC111899', '2022-08-27 09:29:36');
insert into drone_medication (drone_id, code, loaded_date_time)
values (10, 'NDC456959', '2022-08-27 11:29:36');
insert into drone_medication (drone_id, code, loaded_date_time)
values (10, 'NDC123555', '2022-08-28 12:29:36');
insert into drone_medication (drone_id, code, loaded_date_time)
values (10, 'NDC852654', '2022-08-27 15:29:36');
insert into drone_medication (drone_id, code, loaded_date_time)
values (10, 'NDC852654', '2022-08-27 18:29:36');
insert into drone_medication (drone_id, code, loaded_date_time)
values (10, 'NDC456959', '2022-08-28 15:29:36');