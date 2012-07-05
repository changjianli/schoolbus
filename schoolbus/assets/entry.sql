/*bus_type的值
0 一直运行
1 工作日
2 非工作日
*/

/* 1 */
insert into summer_time values(1,'中心校区','兴隆山校区','7:10',null,'图书馆',null,0,null);
insert into summer_time values(2,'中心校区','兴隆山校区','13:20',null,'图书馆',null,0,null);
insert into summer_time values(3,'兴隆山校区','中心校区','12:05',null,null,'图书馆',0,null);
insert into summer_time values(4,'兴隆山校区','中心校区','17:50',null,null,'图书馆',0,null);

/* 2 */
insert into summer_time values(5,'中心校区','软件园校区','7:20',null,'图书馆',null,0,null);
insert into summer_time values(6,'中心校区','软件园校区','13:25',null,'图书馆',null,0,null);
insert into summer_time values(7,'软件园校区','中心校区','12:10',null,null,'图书馆',0,null);
insert into summer_time values(8,'软件园校区','中心校区','17:30',null,null,'图书馆',0,null);

insert into summer_time_between values(5,'洪家楼校区','南门外');
insert into summer_time_between values(5,'五宿舍',null);
insert into summer_time_between values(6,'洪家楼校区','南门外');
insert into summer_time_between values(6,'五宿舍',null);
insert into summer_time_between values(7,'洪家楼校区','南门外');
insert into summer_time_between values(7,'五宿舍',null);
insert into summer_time_between values(8,'洪家楼校区','南门外');
insert into summer_time_between values(8,'五宿舍',null);

/* 3 */
insert into summer_time values(9,'洪家楼校区','兴隆山校区','10:10',null,'南门内',null,0,'教师专用');
insert into summer_time values(10,'兴隆山校区','洪家楼校区','11:10',null,null,'南门内',0,'教师专用');

insert into summer_time_between values(9,'中心校区','信息楼');
insert into summer_time_between values(10,'中心校区','信息楼');

/* 4 */
insert into summer_time values(11,'五宿舍','趵突泉校区','7:25',null,null,'停车场',0,null);
insert into summer_time values(12,'趵突泉校区','五宿舍','17:30',null,'停车场',null,0,null);

insert into summer_time_between values(11,'洪家楼','路口南');
insert into summer_time_between values(11,'中心校区','南门外');
insert into summer_time_between values(11,'千佛山校区',null);
insert into summer_time_between values(12,'洪家楼','路口南');
insert into summer_time_between values(12,'中心校区','南门外');
insert into summer_time_between values(12,'千佛山校区',null);

/* 5 */
insert into summer_time values(13,'五宿舍','兴隆山校区','7:10',null,null,null,0,null);
insert into summer_time values(14,'五宿舍','兴隆山校区','13:20',null,null,null,0,null);
insert into summer_time values(15,'兴隆山校区','五宿舍','12:05',null,null,null,0,null);
insert into summer_time values(16,'兴隆山校区','五宿舍','17:50',null,null,null,0,null);

insert into summer_time_between values(13,'洪家楼校区','南门外');
insert into summer_time_between values(14,'洪家楼校区','南门外');
insert into summer_time_between values(15,'洪家楼校区','南门外');
insert into summer_time_between values(16,'洪家楼校区','南门外');

/* 6 */
insert into summer_time values(17,'五宿舍','中心校区','7:40',null,null,'图书馆',0,null);
insert into summer_time values(18,'五宿舍','中心校区','13:40',null,null,'图书馆',0,null);
insert into summer_time values(19,'中心校区','五宿舍','12:00',null,'图书馆',null,0,null);
insert into summer_time values(20,'中心校区','五宿舍','17:30',null,'图书馆',null,0,null);


/* 7 */
insert into summer_time values(21,'三宿舍','中心校区','7:40',null,null,'图书馆',0,null);
insert into summer_time values(22,'三宿舍','中心校区','13:40',null,null,'图书馆',0,null);
insert into summer_time values(23,'中心校区','三宿舍','12:00',null,'图书馆',null,0,null);
insert into summer_time values(24,'中心校区','三宿舍','17:30',null,'图书馆',null,0,null);

insert into summer_time_between values(21,'二宿舍',null);
insert into summer_time_between values(21,'中心校区','北门');
insert into summer_time_between values(22,'二宿舍',null);
insert into summer_time_between values(22,'中心校区','北门');
insert into summer_time_between values(23,'二宿舍',null);
insert into summer_time_between values(23,'中心校区','北门');
insert into summer_time_between values(24,'二宿舍',null);
insert into summer_time_between values(24,'中心校区','北门');

/* 8 */
insert into summer_time values(25,'趵突泉校区','中心校区','7:40',null,'停车场','图书馆',0,null);
insert into summer_time values(26,'趵突泉校区','中心校区','13:40',null,'停车场','图书馆',0,null);
insert into summer_time values(27,'中心校区','趵突泉校区','12:00',null,'图书馆','停车场',0,null);
insert into summer_time values(28,'中心校区','趵突泉校区','17:30',null,'图书馆','停车场',0,null);

insert into summer_time_between values(25,'和平路与山师东路交叉口',null);
insert into summer_time_between values(26,'和平路与山师东路交叉口',null);
insert into summer_time_between values(27,'和平路与山师东路交叉口',null);
insert into summer_time_between values(28,'和平路与山师东路交叉口',null);

/* 9 */
insert into summer_time values(29,'趵突泉校区','兴隆山校区','7:10',null,'南门外',null,0,null);
insert into summer_time values(30,'趵突泉校区','兴隆山校区','13:20',null,'南门外',null,0,null);
insert into summer_time values(31,'兴隆山校区','趵突泉校区','12:05',null,null,'南门外',0,null);
insert into summer_time values(32,'兴隆山校区','趵突泉校区','17:50',null,null,'南门外',0,null);

insert into summer_time_between values(29,'千佛山校区','南院北门外');
insert into summer_time_between values(29,'燕山小区',null);
insert into summer_time_between values(29,'马家庄',null);
insert into summer_time_between values(30,'千佛山校区','南院北门外');
insert into summer_time_between values(30,'燕山小区',null);
insert into summer_time_between values(30,'马家庄',null);

insert into summer_time_between values(31,'社会科学院',null);
insert into summer_time_between values(32,'社会科学院',null);
/* 备注 */

/* 10 */
insert into summer_time values(33,'舜玉小区','中心校区','7:20',null,null,'图书馆',0,null);
insert into summer_time values(34,'中心校区','舜玉小区','17:30',null,'图书馆',null,0,null);

insert into summer_time_between values(33,'阳光舜城',null);
insert into summer_time_between values(33,'燕山立交桥北',null);
insert into summer_time_between values(34,'阳光舜城',null);
insert into summer_time_between values(34,'燕山立交桥北',null);

/*11*/
insert into summer_time values(35,'千佛山校区','中心校区','7:40',null,'北院南门','图书馆',0,null);
insert into summer_time values(36,'千佛山校区','中心校区','13:40',null,'北院南门','图书馆',0,null);
insert into summer_time values(37,'中心校区','千佛山校区','12:00',null,'图书馆','北院南门',0,null);
insert into summer_time values(38,'中心校区','千佛山校区','17:30',null,'图书馆','北院南门',0,null);

insert into summer_time_between values(36,'千佛山校区','东院');

/*12*/
insert into summer_time values(39,'千佛山校区','洪家楼校区','7:10',null,'北院南门','南门',0,null);
insert into summer_time values(40,'千佛山校区','洪家楼校区','13:10',null,'北院南门','南门',0,null);

insert into summer_time_between values(39,'趵突泉校区','北门');
insert into summer_time_between values(39,'文化路',null);
insert into summer_time_between values(39,'中心校区','南门');
insert into summer_time_between values(40,'趵突泉校区','北门');
insert into summer_time_between values(40,'文化路',null);
insert into summer_time_between values(40,'中心校区','南门');

/*13*/
insert into summer_time values(41,'千佛山校区','中心校区','7:40',null,'南院北门','图书馆',0,null);

/*14*/
insert into summer_time values(42,'千佛山校区','兴隆山校区','7:20',null,'南院北门',null,0,null);
insert into summer_time values(43,'千佛山校区','兴隆山校区','13:20',null,'南院北门',null,0,null);
insert into summer_time values(44,'兴隆山校区','千佛山校区','12:05',null,null,'南院北门',0,null);
insert into summer_time values(45,'兴隆山校区','千佛山校区','17:50',null,null,'南院北门',0,null);

insert into summer_time_between values(42,'舜玉小区',null);
insert into summer_time_between values(42,'社会科学院',null);
insert into summer_time_between values(43,'舜玉小区',null);
insert into summer_time_between values(43,'社会科学院',null);
insert into summer_time_between values(44,'舜玉小区',null);
insert into summer_time_between values(44,'社会科学院',null);
insert into summer_time_between values(45,'舜玉小区',null);
insert into summer_time_between values(45,'社会科学院',null);

/*15*/
insert into summer_time values(46,'趵突泉校区','软件园校区','7:15',null,'北门',null,0,null);
insert into summer_time values(47,'趵突泉校区','软件园校区','9:15',null,'北门',null,0,null);
insert into summer_time values(48,'趵突泉校区','软件园校区','13:20',null,'北门',null,0,null);
insert into summer_time values(49,'软件园校区','趵突泉校区','12:10',null,null,'北门',0,null);
insert into summer_time values(50,'软件园校区','趵突泉校区','11:00',null,null,'北门',0,null);
insert into summer_time values(51,'软件园校区','趵突泉校区','17:30',null,null,'北门',0,null);

insert into summer_time_between values(46,'千佛山校区','南院北门外');
insert into summer_time_between values(47,'千佛山校区','南院北门外');
insert into summer_time_between values(48,'千佛山校区','南院北门外');
insert into summer_time_between values(49,'千佛山校区','南院北门外');
insert into summer_time_between values(50,'千佛山校区','南院北门外');
insert into summer_time_between values(51,'千佛山校区','南院北门外');

/*16*/
insert into summer_time values(52,'千佛山校区','兴隆山校区','10:10',null,'南院北门',null,0,'教师专用');
insert into summer_time values(53,'兴隆山校区','千佛山校区','11:10',null,null,'南院北门',0,'教师专用');

/*17*/
insert into summer_time values(54,'千佛山校区','中心校区','7:40',null,'东院','图书馆',0,null);
insert into summer_time values(55,'中心校区','千佛山校区','17:30',null,'图书馆','东院',0,null);

/*18*/
insert into summer_time values(56,'燕山宿舍','千佛山校区','7:40',null,null,'北院南门',0,null);
insert into summer_time values(57,'燕山宿舍','千佛山校区','13:40',null,null,'北院南门',0,null);
insert into summer_time values(58,'千佛山校区','燕山宿舍','12:00',null,'北院南门',null,0,null);
insert into summer_time values(59,'千佛山校区','燕山宿舍','17:30',null,'北院南门',null,0,null);

/*19*/
insert into summer_time values(60,'马家庄','千佛山校区','7:20',null,null,'北院南门',0,null);
insert into summer_time values(61,'马家庄','千佛山校区','7:30',null,null,'北院南门',0,null);
insert into summer_time values(62,'马家庄','趵突泉校区','7:40',null,null,null,0,null);
insert into summer_time values(63,'马家庄','千佛山校区','13:30',null,null,'北院南门',0,null);
insert into summer_time values(64,'千佛山校区','马家庄','12:00',null,'北院南门',null,0,null);
insert into summer_time values(65,'千佛山校区','马家庄','17:30',null,'北院南门',null,0,null);

insert into summer_time_between values(62,'千佛山校区',null);

/*20*/
insert into summer_time values(66,'阳光舜城','趵突泉校区','7:30',null,null,'停车场',0,null);
insert into summer_time values(67,'趵突泉校区','阳光舜城','17:30',null,'停车场',null,0,null);

/*21*/
insert into summer_time values(68,'阳光舜城','千佛山校区','7:30',null,null,'北院南门',0,null);
insert into summer_time values(69,'千佛山校区','阳光舜城','17:30',null,'北院南门',null,0,null);

/*22*/
insert into summer_time values(70,'南山小区','中心校区','7:30',null,null,'图书馆',0,null);
insert into summer_time values(71,'中心校区','南山小区','17:30',null,'图书馆',null,0,null);

/*23*/
insert into summer_time values(72,'南山小区','洪家楼校区','7:30',null,null,'南门内',0,null);
insert into summer_time values(73,'洪家楼校区','南山小区','17:30',null,'南门内',null,0,null);

/*24*/
insert into summer_time values(74,'南山小区','千佛山校区','7:30',null,null,'北院南门',0,null);
insert into summer_time values(75,'千佛山校区','南山小区','17:30',null,'北院南门',null,0,null);

/*25*/
insert into summer_time values(76,'南山小区','趵突泉校区','7:30',null,null,'停车场',0,null);
insert into summer_time values(77,'趵突泉校区','南山小区','17:30',null,'停车场',null,0,null);

/*26*/
insert into summer_time values(78,'南山小区','洪家楼校区','9:00',null,null,null,0,null);

insert into summer_time_between values(78,'趵突泉校区',null);
insert into summer_time_between values(78,'千佛山校区',null);
insert into summer_time_between values(78,'中心校区',null);


/*-----------------------------分割线-----------------------------------*/
/*1*/
insert into summer_time values(101,'五宿舍','趵突泉校区','7:20',null,null,'停车场',1,null);

insert into summer_time values(102,'洪家楼校区','趵突泉校区','8:30',null,'南门内','停车场',1,null);
insert into summer_time values(103,'洪家楼校区','趵突泉校区','9:20',null,'南门内','停车场',1,null);
insert into summer_time values(104,'洪家楼校区','趵突泉校区','10:30',null,'南门内','停车场',1,null);
insert into summer_time values(105,'洪家楼校区','趵突泉校区','11:00',null,'南门内','停车场',1,null);
insert into summer_time values(106,'洪家楼校区','趵突泉校区','12:00',null,'南门内','停车场',1,null);

insert into summer_time values(107,'五宿舍','趵突泉校区','13:30',null,null,'停车场',1,null);

insert into summer_time values(108,'洪家楼校区','趵突泉校区','14:30',null,'南门内','停车场',1,null);
insert into summer_time values(109,'洪家楼校区','趵突泉校区','16:00',null,'南门内','停车场',1,null);
insert into summer_time values(110,'洪家楼校区','趵突泉校区','17:00',null,'南门内','停车场',1,null);
insert into summer_time values(111,'洪家楼校区','趵突泉校区','17:30',null,'南门内','停车场',1,null);


insert into summer_time values(112,'趵突泉校区','洪家楼校区','7:20',null,'停车场','南门内',1,null);
insert into summer_time values(113,'趵突泉校区','洪家楼校区','8:30',null,'停车场','南门内',1,null);
insert into summer_time values(114,'趵突泉校区','洪家楼校区','9:20',null,'停车场','南门内',1,null);
insert into summer_time values(115,'趵突泉校区','洪家楼校区','10:30',null,'停车场','南门内',1,null);
insert into summer_time values(116,'趵突泉校区','洪家楼校区','11:00',null,'停车场','南门内',1,null);

insert into summer_time values(117,'趵突泉校区','五宿舍','12:00',null,'停车场',null,1,null);

insert into summer_time values(118,'趵突泉校区','洪家楼校区','13:30',null,'停车场','南门内',1,null);
insert into summer_time values(119,'趵突泉校区','洪家楼校区','14:30',null,'停车场','南门内',1,null);
insert into summer_time values(120,'趵突泉校区','洪家楼校区','16:00',null,'停车场','南门内',1,null);

insert into summer_time values(121,'趵突泉校区','五宿舍','17:00',null,'停车场',null,1,null);

insert into summer_time values(122,'趵突泉校区','洪家楼校区','17:30',null,'停车场','南门内',1,null);

insert into summer_time_between values(101,'洪家楼校区','南门外');
insert into summer_time_between values(101,'中心校区','北门');
insert into summer_time_between values(101,'中心校区','信息楼');
insert into summer_time_between values(101,'燕山',null);
insert into summer_time_between values(101,'马家庄',null);
insert into summer_time_between values(101,'千佛山校区',null);

insert into summer_time_between values(102,'中心校区','北门');
insert into summer_time_between values(102,'中心校区','信息楼');
insert into summer_time_between values(102,'燕山',null);
insert into summer_time_between values(102,'马家庄',null);
insert into summer_time_between values(102,'千佛山校区',null);

insert into summer_time_between values(103,'中心校区','北门');
insert into summer_time_between values(103,'中心校区','信息楼');
insert into summer_time_between values(103,'燕山',null);
insert into summer_time_between values(103,'马家庄',null);
insert into summer_time_between values(103,'千佛山校区',null);

insert into summer_time_between values(104,'中心校区','北门');
insert into summer_time_between values(104,'中心校区','信息楼');
insert into summer_time_between values(104,'燕山',null);
insert into summer_time_between values(104,'马家庄',null);
insert into summer_time_between values(104,'千佛山校区',null);

insert into summer_time_between values(105,'中心校区','北门');
insert into summer_time_between values(105,'中心校区','信息楼');
insert into summer_time_between values(105,'燕山',null);
insert into summer_time_between values(105,'马家庄',null);
insert into summer_time_between values(105,'千佛山校区',null);

insert into summer_time_between values(106,'中心校区','北门');
insert into summer_time_between values(106,'中心校区','信息楼');
insert into summer_time_between values(106,'燕山',null);
insert into summer_time_between values(106,'马家庄',null);
insert into summer_time_between values(106,'千佛山校区',null);

insert into summer_time_between values(107,'洪家楼校区','南门外');
insert into summer_time_between values(107,'中心校区','北门');
insert into summer_time_between values(107,'中心校区','信息楼');
insert into summer_time_between values(107,'燕山',null);
insert into summer_time_between values(107,'马家庄',null);
insert into summer_time_between values(107,'千佛山校区',null);

insert into summer_time_between values(108,'中心校区','北门');
insert into summer_time_between values(108,'中心校区','信息楼');
insert into summer_time_between values(108,'燕山',null);
insert into summer_time_between values(108,'马家庄',null);
insert into summer_time_between values(108,'千佛山校区',null);

insert into summer_time_between values(109,'中心校区','北门');
insert into summer_time_between values(109,'中心校区','信息楼');
insert into summer_time_between values(109,'燕山',null);
insert into summer_time_between values(109,'马家庄',null);
insert into summer_time_between values(109,'千佛山校区',null);

insert into summer_time_between values(110,'中心校区','北门');
insert into summer_time_between values(110,'中心校区','信息楼');
insert into summer_time_between values(110,'燕山',null);
insert into summer_time_between values(110,'马家庄',null);
insert into summer_time_between values(110,'千佛山校区',null);

insert into summer_time_between values(111,'中心校区','北门');
insert into summer_time_between values(111,'中心校区','信息楼');
insert into summer_time_between values(111,'燕山',null);
insert into summer_time_between values(111,'马家庄',null);
insert into summer_time_between values(111,'千佛山校区',null);

insert into summer_time_between values(112,'中心校区','北门');
insert into summer_time_between values(112,'中心校区','信息楼');
insert into summer_time_between values(112,'燕山',null);
insert into summer_time_between values(112,'马家庄',null);
insert into summer_time_between values(112,'千佛山校区',null);

insert into summer_time_between values(113,'中心校区','北门');
insert into summer_time_between values(113,'中心校区','信息楼');
insert into summer_time_between values(113,'燕山',null);
insert into summer_time_between values(113,'马家庄',null);
insert into summer_time_between values(113,'千佛山校区',null);

insert into summer_time_between values(114,'中心校区','北门');
insert into summer_time_between values(114,'中心校区','信息楼');
insert into summer_time_between values(114,'燕山',null);
insert into summer_time_between values(114,'马家庄',null);
insert into summer_time_between values(114,'千佛山校区',null);

insert into summer_time_between values(115,'中心校区','北门');
insert into summer_time_between values(115,'中心校区','信息楼');
insert into summer_time_between values(115,'燕山',null);
insert into summer_time_between values(115,'马家庄',null);
insert into summer_time_between values(115,'千佛山校区',null);

insert into summer_time_between values(116,'中心校区','北门');
insert into summer_time_between values(116,'中心校区','信息楼');
insert into summer_time_between values(116,'燕山',null);
insert into summer_time_between values(116,'马家庄',null);
insert into summer_time_between values(116,'千佛山校区',null);

insert into summer_time_between values(117,'洪家楼校区',null);
insert into summer_time_between values(117,'中心校区','北门');
insert into summer_time_between values(117,'中心校区','信息楼');
insert into summer_time_between values(117,'燕山',null);
insert into summer_time_between values(117,'马家庄',null);
insert into summer_time_between values(117,'千佛山校区',null);

insert into summer_time_between values(118,'中心校区','北门');
insert into summer_time_between values(118,'中心校区','信息楼');
insert into summer_time_between values(118,'燕山',null);
insert into summer_time_between values(118,'马家庄',null);
insert into summer_time_between values(118,'千佛山校区',null);

insert into summer_time_between values(119,'中心校区','北门');
insert into summer_time_between values(119,'中心校区','信息楼');
insert into summer_time_between values(119,'燕山',null);
insert into summer_time_between values(119,'马家庄',null);
insert into summer_time_between values(119,'千佛山校区',null);

insert into summer_time_between values(120,'中心校区','北门');
insert into summer_time_between values(120,'中心校区','信息楼');
insert into summer_time_between values(120,'燕山',null);
insert into summer_time_between values(120,'马家庄',null);
insert into summer_time_between values(120,'千佛山校区',null);

insert into summer_time_between values(121,'洪家楼校区',null);
insert into summer_time_between values(121,'中心校区','北门');
insert into summer_time_between values(121,'中心校区','信息楼');
insert into summer_time_between values(121,'燕山',null);
insert into summer_time_between values(121,'马家庄',null);
insert into summer_time_between values(121,'千佛山校区',null);

insert into summer_time_between values(122,'中心校区','北门');
insert into summer_time_between values(122,'中心校区','信息楼');
insert into summer_time_between values(122,'燕山',null);
insert into summer_time_between values(122,'马家庄',null);
insert into summer_time_between values(122,'千佛山校区',null);

/*2*/
insert into summer_time values(123,'五宿舍','兴隆山校区','9:20',null,null,null,1,null);
insert into summer_time values(124,'中心校区','兴隆山校区','15:20',null,'信息楼',null,1,null);

insert into summer_time values(125,'洪家楼校区','兴隆山校区','21:30',null,null,null,1,null);

insert into summer_time values(126,'兴隆山校区','五宿舍','10:00',null,null,null,1,null);
insert into summer_time values(127,'兴隆山校区','五宿舍','16:00',null,null,null,1,null);
insert into summer_time values(128,'兴隆山校区','中心校区','17:00',null,null,'信息楼',1,null);
insert into summer_time values(129,'兴隆山校区','洪家楼校区','22:10',null,null,null,1,null);

insert into summer_time_between values(123,'洪家楼校区','南门外');
insert into summer_time_between values(123,'中心校区','信息楼');
insert into summer_time_between values(124,'洪家楼校区','南门外');
insert into summer_time_between values(124,'中心校区','信息楼');

insert into summer_time_between values(126,'中心校区','信息楼');
insert into summer_time_between values(126,'洪家楼校区',null);
insert into summer_time_between values(127,'中心校区','信息楼');
insert into summer_time_between values(127,'洪家楼校区',null);

insert into summer_time_between values(125,'中心校区','信息楼');
insert into summer_time_between values(129,'中心校区','信息楼');

/*3*/
insert into summer_time values(130,'五宿舍','兴隆山校区','7:10',null,null,null,2,null);
insert into summer_time_between values(130,'洪家楼校区','南门外');
insert into summer_time_between values(130,'中心校区',null);

insert into summer_time values(131,'洪家楼校区','兴隆山校区','8:00',null,'南门内',null,2,null);
insert into summer_time values(132,'洪家楼校区','兴隆山校区','9:20',null,'南门内',null,2,null);

insert into summer_time_between values(131,'中心校区','信息楼');
insert into summer_time_between values(132,'中心校区','信息楼');

insert into summer_time values(133,'五宿舍','兴隆山校区','10:30',null,null,null,2,null);
insert into summer_time_between values(133,'洪家楼校区','南门外');
insert into summer_time_between values(133,'中心校区',null);

insert into summer_time values(134,'洪家楼校区','兴隆山校区','11:10',null,'南门内',null,2,null);
insert into summer_time values(135,'洪家楼校区','兴隆山校区','12:10',null,'南门内',null,2,null);
insert into summer_time values(136,'洪家楼校区','兴隆山校区','13:20',null,'南门内',null,2,null);
insert into summer_time values(137,'洪家楼校区','兴隆山校区','16:00',null,'南门内',null,2,null);
insert into summer_time values(138,'洪家楼校区','兴隆山校区','17:00',null,'南门内',null,2,null);

insert into summer_time_between values(134,'中心校区','信息楼');
insert into summer_time_between values(135,'中心校区','信息楼');
insert into summer_time_between values(136,'中心校区','信息楼');
insert into summer_time_between values(137,'中心校区','信息楼');
insert into summer_time_between values(138,'中心校区','信息楼');

insert into summer_time values(139,'五宿舍','兴隆山校区','17:30',null,null,null,2,null);
insert into summer_time_between values(139,'洪家楼校区','南门外');
insert into summer_time_between values(139,'中心校区',null);

insert into summer_time values(140,'洪家楼校区','兴隆山校区','19:30',null,'南门内',null,2,null);
insert into summer_time values(141,'洪家楼校区','兴隆山校区','21:30',null,'南门内',null,2,null);

insert into summer_time_between values(140,'中心校区','信息楼');
insert into summer_time_between values(141,'中心校区','信息楼');

insert into summer_time values(142,'兴隆山校区','五宿舍','7:10',null,null,null,2,null);
insert into summer_time_between values(142,'中心校区',null);
insert into summer_time_between values(142,'洪家楼校区','南门外');

insert into summer_time values(143,'兴隆山校区','洪家楼校区','8:00',null,null,'南门内',2,null);
insert into summer_time values(144,'兴隆山校区','洪家楼校区','9:20',null,null,'南门内',2,null);
insert into summer_time values(145,'兴隆山校区','洪家楼校区','10:30',null,null,'南门内',2,null);

insert into summer_time_between values(143,'中心校区','信息楼');
insert into summer_time_between values(144,'中心校区','信息楼');
insert into summer_time_between values(145,'中心校区','信息楼');

insert into summer_time values(146,'兴隆山校区','五宿舍','11:10',null,null,null,2,null);
insert into summer_time_between values(146,'中心校区',null);
insert into summer_time_between values(146,'洪家楼校区','南门外');

insert into summer_time values(147,'兴隆山校区','洪家楼校区','12:10',null,null,'南门内',2,null);

insert into summer_time_between values(147,'中心校区','信息楼');

insert into summer_time values(148,'兴隆山校区','五宿舍','13:20',null,null,null,2,null);
insert into summer_time_between values(148,'中心校区',null);
insert into summer_time_between values(148,'洪家楼校区','南门外');

insert into summer_time values(149,'兴隆山校区','五宿舍','16:00',null,null,null,2,null);
insert into summer_time_between values(149,'中心校区',null);
insert into summer_time_between values(149,'洪家楼校区','南门外');

insert into summer_time values(150,'兴隆山校区','洪家楼校区','17:00',null,null,'南门内',2,null);

insert into summer_time_between values(150,'中心校区','信息楼');

insert into summer_time values(151,'兴隆山校区','五宿舍','17:45',null,null,null,2,null);
insert into summer_time_between values(151,'中心校区',null);
insert into summer_time_between values(151,'洪家楼校区','南门外');

insert into summer_time values(152,'兴隆山校区','洪家楼校区','20:10',null,null,'南门内',2,null);
insert into summer_time values(153,'兴隆山校区','洪家楼校区','22:10',null,null,'南门内',2,null);

insert into summer_time_between values(152,'中心校区','信息楼');
insert into summer_time_between values(153,'中心校区','信息楼');

/*4*/

insert into summer_time values(154,'趵突泉校区','兴隆山校区','9:15',null,'南门外',null,1,null);
insert into summer_time_between values(154,'千佛山校区','南院北门外');
insert into summer_time_between values(154,'燕山小区',null);
insert into summer_time_between values(154,'马家庄',null);

insert into summer_time values(155,'千佛山校区','兴隆山校区','15:10',null,'南院北门',null,1,null);
insert into summer_time_between values(155,'燕山小区',null);
insert into summer_time_between values(155,'马家庄',null);

insert into summer_time values(156,'千佛山校区','兴隆山校区','16:00',null,'南院北门',null,1,null);

insert into summer_time values(157,'趵突泉校区','兴隆山校区','21:30',null,'南门外',null,1,null);
insert into summer_time_between values(157,'千佛山校区','南院北门外');

insert into summer_time values(158,'兴隆山校区','千佛山校区','10:00',null,null,'南院北门',1,null);
insert into summer_time values(159,'兴隆山校区','千佛山校区','16:00',null,null,'南院北门',1,null);
insert into summer_time values(160,'兴隆山校区','千佛山校区','17:00',null,null,'南院北门',1,null);

insert into summer_time values(161,'兴隆山校区','千佛山校区','22:10',null,null,'南院北门',1,null);
insert into summer_time_between values(161,'趵突泉校区',null);

/*5*/
insert into summer_time values(162,'趵突泉校区','兴隆山校区','7:10',null,'南门',null,2,null);
insert into summer_time_between values(162,'千佛山校区','南院北门');
insert into summer_time_between values(162,'燕山小区',null);
insert into summer_time_between values(162,'马家山',null);

insert into summer_time values(163,'千佛山校区','兴隆山校区','8:40',null,'南院北门',null,2,null);

insert into summer_time values(164,'趵突泉校区','兴隆山校区','9:20',null,'南门',null,2,null);

insert into summer_time_between values(164,'千佛山校区','南院北门');
insert into summer_time_between values(164,'燕山小区',null);
insert into summer_time_between values(164,'马家山',null);

insert into summer_time values(165,'千佛山校区','兴隆山校区','10:30',null,'南院北门',null,2,null);
insert into summer_time values(166,'千佛山校区','兴隆山校区','11:10',null,'南院北门',null,2,null);
insert into summer_time values(167,'千佛山校区','兴隆山校区','12:10',null,'南院北门',null,2,null);

insert into summer_time values(168,'趵突泉校区','兴隆山校区','13:20',null,'南门',null,2,null);

insert into summer_time_between values(168,'千佛山校区','南院北门');
insert into summer_time_between values(168,'燕山小区',null);
insert into summer_time_between values(168,'马家山',null);

insert into summer_time values(169,'千佛山校区','兴隆山校区','16:00',null,'南院北门',null,2,null);

insert into summer_time values(170,'趵突泉校区','兴隆山校区','17:00',null,'南门',null,2,null);

insert into summer_time_between values(170,'千佛山校区','南院北门');
insert into summer_time_between values(170,'燕山小区',null);
insert into summer_time_between values(170,'马家山',null);

insert into summer_time values(171,'千佛山校区','兴隆山校区','17:30',null,'南院北门',null,2,null);
insert into summer_time values(172,'趵突泉校区','兴隆山校区','19:30',null,null,null,2,null);
insert into summer_time_between values(172,'千佛山校区',null);
insert into summer_time values(173,'趵突泉校区','兴隆山校区','21:30',null,null,null,2,null);
insert into summer_time_between values(173,'千佛山校区',null);

insert into summer_time values(175,'兴隆山校区','千佛山校区','7:10',null,null,'南院北门',2,null);

insert into summer_time values(176,'兴隆山校区','千佛山校区','8:40',null,null,'南院北门',2,null);
insert into summer_time_between values(176,'趵突泉校区',null);

insert into summer_time values(177,'兴隆山校区','千佛山校区','9:20',null,null,'南院北门',2,null);
insert into summer_time values(178,'兴隆山校区','千佛山校区','10:30',null,null,'南院北门',2,null);
insert into summer_time values(179,'兴隆山校区','千佛山校区','11:10',null,null,'南院北门',2,null);
insert into summer_time_between values(179,'趵突泉校区',null);
insert into summer_time values(180,'兴隆山校区','千佛山校区','12:10',null,null,'南院北门',2,null);
insert into summer_time values(181,'兴隆山校区','千佛山校区','13:20',null,null,'南院北门',2,null);
insert into summer_time values(182,'兴隆山校区','千佛山校区','16:00',null,null,'南院北门',2,null);
insert into summer_time_between values(182,'趵突泉校区',null);
insert into summer_time values(183,'兴隆山校区','千佛山校区','17:00',null,null,'南院北门',2,null);
insert into summer_time values(184,'兴隆山校区','千佛山校区','17:45',null,null,'南院北门',2,null);
insert into summer_time_between values(184,'趵突泉校区',null);
insert into summer_time values(185,'兴隆山校区','千佛山校区','20:10',null,null,'南院北门',2,null);
insert into summer_time_between values(185,'趵突泉校区',null);
insert into summer_time values(186,'兴隆山校区','千佛山校区','22:10',null,null,'南院北门',2,null);
insert into summer_time_between values(186,'趵突泉校区',null);

















