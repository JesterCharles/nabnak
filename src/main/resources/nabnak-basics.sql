

create table members(
	email varchar(40) primary key,
	full_name varchar(20) not null,
	experience_months numeric check ( experience_months >= 0),
	registration_date date not null,
	"password" varchar(32) not null
);
-- drop for members
--drop table members cascade;
--
--

create table card(
	card_id serial primary key,
	description text not null,
	points int,
	tech varchar(20),
	status varchar(20),
	members_email varchar(20)
);
-- drop for card
--drop table card;
--
--

-- alter card for FK
alter table card 
add constraint fk_members_card
foreign key (members_email) references members(email) on delete cascade;


-- Select and view tables
select * from members;
select * from card;

-- Create some dummy data
insert into members 
values
('cj@mail.com', 'Charles Jester', 18, '2022-01-01', 'pass'),
('ar@mail.com', 'Adam Ranieri', 2400, '2022-07-06', 'gatorFan'),
('bp@mail.com', 'Ben Petruzziello', 24, '2022-07-31', 'ooBen'),
('po@mail.com', 'Patrick Orwin', 12, '2022-07-22', 'notThatPatrick'),
('ab@mail.com', 'Ashwin Bharath', 180, '2022-06-18', 'coolBoss');

insert into card 
values
(default, 'Show off some cool JDBC', 2, 'Java', 'In-progress', 'cj@mail.com'),
(default, 'Show them maven', 2, 'Java', 'Completed', 'cj@mail.com'),
(default, 'Show them JankUnit & Reflections', 2, 'Java', 'In-progress', 'cj@mail.com'),
(default, 'Show off Spring Boot', 2, 'Java', 'In-progress', 'ar@mail.com'),
(default, 'Show off Multiplicities', 2, 'SQL', 'Completed', 'po@mail.com'),
(default, 'Show off a button with JS', 2, 'JavaScript', 'In-progress', 'bp@mail.com');

-- Read some data
select * from members m; -- findAll members
select * from card; -- findAll cards

select * from members where email = 'cj@mail.com'; -- find a member by identity 
select * from card where card_id = 1; --  find a card by id
select * from card where members_email = 'cj@mail.com'; -- find all cards made by user

-- Update some data
update members set experience_months = 24 where email = 'cj@mail.com'; -- update member
update card set status = 'Completed' where card_id = 1; -- update card
update members 
set email = 'cj@mail.com', full_name = 'Charles Jester', experience_months = 18, registration_date = '2022-01-01', "password" = 'pass' 
where email = 'cj@mail.com'; -- complete update 

-- Delete some data
delete from members where email = 'valid'; -- delete by id
delete from card where status = 'Completed'; -- delete by status


