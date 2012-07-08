create table summer_time(
	id integer not null PRIMARY KEY autoincrement,
	bus_from varchar(10) not null,
	bus_to varchar(10) not null,
	start_time varchar(6) not null,
	end_time varchar(6),
	from_desc text,
	to_desc text,
	bus_type int(1) not null,
	remark text
);

create table summer_time_between(
	id integer not null,
	bus_between	varchar(10) not null,
	between_desc text
);

create table winter_time(
	id integer not null PRIMARY KEY autoincrement,
	bus_from varchar(10) not null,
	bus_to varchar(10) not null,
	start_time varchar(6) not null,
	end_time varchar(6),
	from_desc text,
	to_desc text,
	bus_type int(1) not null,
	remark text
);

create table winter_time_between(
	id integer not null,
	bus_between	varchar(10) not null,
	between_desc text
);
/*
id = 1 ÏÄ¼¾Ê±¿Ì
id = 2 ¶¬¼¾Ê±¿Ì
select id from summer_winter where 
	start_month <= $today_month 
		and start_day <= $today_day
			and end_day >= $today_day
				and end_month >= $today_month;
*/
create table summer_winter(
	id integer not null,
	start_month integer not null,
	start_day integer not null,
	end_month integer not null,
	end_day integer not null
);

insert into summer_winter values(1,5,4,10,7);
insert into summer_winter values(2,10,8,12,31);
insert into summer_winter values(2,1,1,5,3);
create table sum_or_win(
	id integer not null,
	tstart integer not null,
	tend	integer not null
);
insert into sum_or_win values(1,0504,1007);
insert into sum_or_win values(2,1008,1231);
insert into sum_or_win values(2,0101,0503);