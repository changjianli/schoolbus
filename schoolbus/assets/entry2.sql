/*bus_type的值
0 一直运行
1 工作日
2 非工作日
*/

/* 1 */
insert into winter_time values(1,'中心校区','兴隆山校区','7:10',null,'图书馆',null,0,null);
insert into winter_time values(2,'中心校区','兴隆山校区','12:50',null,'图书馆',null,0,null);
insert into winter_time values(3,'兴隆山校区','中心校区','12:05',null,null,'图书馆',0,null);
insert into winter_time values(4,'兴隆山校区','中心校区','17:20',null,null,'图书馆',0,null);

/* 2 */
insert into winter_time values(5,'中心校区','软件园校区','7:20',null,'图书馆',null,0,null);
insert into winter_time values(6,'中心校区','软件园校区','12:55',null,'图书馆',null,0,null);
insert into winter_time values(7,'软件园校区','中心校区','12:10',null,null,'图书馆',0,null);
insert into winter_time values(8,'软件园校区','中心校区','17:00',null,null,'图书馆',0,null);

insert into winter_time_between values(5,'洪家楼校区','南门外');
insert into winter_time_between values(5,'五宿舍',null);
insert into winter_time_between values(6,'洪家楼校区','南门外');
insert into winter_time_between values(6,'五宿舍',null);
insert into winter_time_between values(7,'洪家楼校区','南门外');
insert into winter_time_between values(7,'五宿舍',null);
insert into winter_time_between values(8,'洪家楼校区','南门外');
insert into winter_time_between values(8,'五宿舍',null);

/* 3 */
insert into winter_time values(9,'洪家楼校区','兴隆山校区','10:10',null,'南门内',null,0,'教师专用');
insert into winter_time values(10,'兴隆山校区','洪家楼校区','11:10',null,null,'南门内',0,'教师专用');

insert into winter_time_between values(9,'中心校区','信息楼');
insert into winter_time_between values(10,'中心校区','信息楼');

/* 4 */
insert into winter_time values(11,'五宿舍','趵突泉校区','7:25',null,null,'停车场',0,null);
insert into winter_time values(12,'趵突泉校区','五宿舍','17:00',null,'停车场',null,0,null);

insert into winter_time_between values(11,'洪家楼','路口南');
insert into winter_time_between values(11,'中心校区','南门外');
insert into winter_time_between values(11,'千佛山校区',null);
insert into winter_time_between values(12,'洪家楼','路口南');
insert into winter_time_between values(12,'中心校区','南门外');
insert into winter_time_between values(12,'千佛山校区',null);

/* 5 */
insert into winter_time values(13,'五宿舍','兴隆山校区','7:10',null,null,null,0,null);
insert into winter_time values(14,'五宿舍','兴隆山校区','12:50',null,null,null,0,null);
insert into winter_time values(15,'兴隆山校区','五宿舍','12:05',null,null,null,0,null);
insert into winter_time values(16,'兴隆山校区','五宿舍','17:20',null,null,null,0,null);

insert into winter_time_between values(13,'洪家楼校区','南门外');
insert into winter_time_between values(14,'洪家楼校区','南门外');
insert into winter_time_between values(15,'洪家楼校区','南门外');
insert into winter_time_between values(16,'洪家楼校区','南门外');

/* 6 */
insert into winter_time values(17,'五宿舍','中心校区','7:40',null,null,'图书馆',0,null);
insert into winter_time values(18,'五宿舍','中心校区','13:10',null,null,'图书馆',0,null);
insert into winter_time values(19,'中心校区','五宿舍','12:00',null,'图书馆',null,0,null);
insert into winter_time values(20,'中心校区','五宿舍','17:00',null,'图书馆',null,0,null);


/* 7 */
insert into winter_time values(21,'三宿舍','中心校区','7:40',null,null,'图书馆',0,null);
insert into winter_time values(22,'三宿舍','中心校区','13:10',null,null,'图书馆',0,null);
insert into winter_time values(23,'中心校区','三宿舍','12:00',null,'图书馆',null,0,null);
insert into winter_time values(24,'中心校区','三宿舍','17:00',null,'图书馆',null,0,null);

insert into winter_time_between values(21,'二宿舍',null);
insert into winter_time_between values(21,'中心校区','北门');
insert into winter_time_between values(22,'二宿舍',null);
insert into winter_time_between values(22,'中心校区','北门');
insert into winter_time_between values(23,'二宿舍',null);
insert into winter_time_between values(23,'中心校区','北门');
insert into winter_time_between values(24,'二宿舍',null);
insert into winter_time_between values(24,'中心校区','北门');

/* 8 */
insert into winter_time values(25,'趵突泉校区','中心校区','7:40',null,'停车场','图书馆',0,null);
insert into winter_time values(26,'趵突泉校区','中心校区','13:10',null,'停车场','图书馆',0,null);
insert into winter_time values(27,'中心校区','趵突泉校区','12:00',null,'图书馆','停车场',0,null);
insert into winter_time values(28,'中心校区','趵突泉校区','17:00',null,'图书馆','停车场',0,null);

insert into winter_time_between values(25,'和平路与山师东路交叉口',null);
insert into winter_time_between values(26,'和平路与山师东路交叉口',null);
insert into winter_time_between values(27,'和平路与山师东路交叉口',null);
insert into winter_time_between values(28,'和平路与山师东路交叉口',null);

/* 9 */
insert into winter_time values(29,'趵突泉校区','兴隆山校区','7:10',null,'南门外',null,0,null);
insert into winter_time values(30,'趵突泉校区','兴隆山校区','12:50',null,'南门外',null,0,null);
insert into winter_time values(31,'兴隆山校区','趵突泉校区','12:05',null,null,'南门外',0,null);
insert into winter_time values(32,'兴隆山校区','趵突泉校区','17:20',null,null,'南门外',0,null);

insert into winter_time_between values(29,'千佛山校区','南院北门外');
insert into winter_time_between values(29,'燕山小区',null);
insert into winter_time_between values(29,'马家庄',null);
insert into winter_time_between values(30,'千佛山校区','南院北门外');
insert into winter_time_between values(30,'燕山小区',null);
insert into winter_time_between values(30,'马家庄',null);

insert into winter_time_between values(31,'社会科学院',null);
insert into winter_time_between values(32,'社会科学院',null);
/* 备注 */

/* 10 */
insert into winter_time values(33,'舜玉小区','中心校区','7:20',null,null,'图书馆',0,null);
insert into winter_time values(34,'中心校区','舜玉小区','17:00',null,'图书馆',null,0,null);

insert into winter_time_between values(33,'阳光舜城',null);
insert into winter_time_between values(33,'燕山立交桥北',null);
insert into winter_time_between values(34,'阳光舜城',null);
insert into winter_time_between values(34,'燕山立交桥北',null);

/*11*/
insert into winter_time values(35,'千佛山校区','中心校区','7:40',null,'北院南门','图书馆',0,null);
insert into winter_time values(36,'千佛山校区','中心校区','13:10',null,'北院南门','图书馆',0,null);
insert into winter_time values(37,'中心校区','千佛山校区','12:00',null,'图书馆','北院南门',0,null);
insert into winter_time values(38,'中心校区','千佛山校区','17:00',null,'图书馆','北院南门',0,null);

insert into winter_time_between values(36,'千佛山校区','东院');

/*12*/
insert into winter_time values(39,'千佛山校区','洪家楼校区','7:10',null,'北院南门','南门',0,null);
insert into winter_time values(40,'千佛山校区','洪家楼校区','12:40',null,'北院南门','南门',0,null);

insert into winter_time_between values(39,'趵突泉校区','北门');
insert into winter_time_between values(39,'文化路',null);
insert into winter_time_between values(39,'中心校区','南门');
insert into winter_time_between values(40,'趵突泉校区','北门');
insert into winter_time_between values(40,'文化路',null);
insert into winter_time_between values(40,'中心校区','南门');

/*13*/
insert into winter_time values(41,'千佛山校区','中心校区','7:40',null,'南院北门','图书馆',0,null);

/*14*/
insert into winter_time values(42,'千佛山校区','兴隆山校区','7:20',null,'南院北门',null,0,null);
insert into winter_time values(43,'千佛山校区','兴隆山校区','12:50',null,'南院北门',null,0,null);
insert into winter_time values(44,'兴隆山校区','千佛山校区','12:05',null,null,'南院北门',0,null);
insert into winter_time values(45,'兴隆山校区','千佛山校区','17:20',null,null,'南院北门',0,null);

insert into winter_time_between values(42,'舜玉小区',null);
insert into winter_time_between values(42,'社会科学院',null);
insert into winter_time_between values(43,'舜玉小区',null);
insert into winter_time_between values(43,'社会科学院',null);
insert into winter_time_between values(44,'舜玉小区',null);
insert into winter_time_between values(44,'社会科学院',null);
insert into winter_time_between values(45,'舜玉小区',null);
insert into winter_time_between values(45,'社会科学院',null);

/*15*/
insert into winter_time values(46,'趵突泉校区','软件园校区','7:15',null,'北门',null,0,null);
insert into winter_time values(47,'趵突泉校区','软件园校区','9:15',null,'北门',null,0,null);
insert into winter_time values(48,'趵突泉校区','软件园校区','12:50',null,'北门',null,0,null);
insert into winter_time values(49,'软件园校区','趵突泉校区','12:10',null,null,'北门',0,null);
insert into winter_time values(50,'软件园校区','趵突泉校区','11:00',null,null,'北门',0,null);
insert into winter_time values(51,'软件园校区','趵突泉校区','17:00',null,null,'北门',0,null);

insert into winter_time_between values(46,'千佛山校区','南院北门外');
insert into winter_time_between values(47,'千佛山校区','南院北门外');
insert into winter_time_between values(48,'千佛山校区','南院北门外');
insert into winter_time_between values(49,'千佛山校区','南院北门外');
insert into winter_time_between values(50,'千佛山校区','南院北门外');
insert into winter_time_between values(51,'千佛山校区','南院北门外');

/*16*/
insert into winter_time values(52,'千佛山校区','兴隆山校区','10:10',null,'南院北门',null,0,'教师专用');
insert into winter_time values(53,'兴隆山校区','千佛山校区','11:10',null,null,'南院北门',0,'教师专用');

/*17*/
insert into winter_time values(54,'千佛山校区','中心校区','7:40',null,'东院','图书馆',0,null);
insert into winter_time values(55,'中心校区','千佛山校区','17:00',null,'图书馆','东院',0,null);

/*18*/
insert into winter_time values(56,'燕山宿舍','千佛山校区','7:20',null,null,'北院南门',0,null);
insert into winter_time values(57,'燕山宿舍','千佛山校区','13:10',null,null,'北院南门',0,null);
insert into winter_time values(58,'千佛山校区','燕山宿舍','12:00',null,'北院南门',null,0,null);
insert into winter_time values(59,'千佛山校区','燕山宿舍','17:00',null,'北院南门',null,0,null);

/*19*/
insert into winter_time values(60,'马家庄','千佛山校区','7:20',null,null,'北院南门',0,null);
insert into winter_time values(61,'马家庄','千佛山校区','7:30',null,null,'北院南门',0,null);
insert into winter_time values(62,'马家庄','趵突泉校区','7:40',null,null,null,0,null);
insert into winter_time values(63,'马家庄','千佛山校区','13:00',null,null,'北院南门',0,null);
insert into winter_time values(64,'千佛山校区','马家庄','12:00',null,'北院南门',null,0,null);
insert into winter_time values(65,'千佛山校区','马家庄','17:00',null,'北院南门',null,0,null);

insert into winter_time_between values(62,'千佛山校区',null);

/*20*/
insert into winter_time values(66,'阳光舜城','趵突泉校区','7:30',null,null,'停车场',0,null);
insert into winter_time values(67,'趵突泉校区','阳光舜城','17:00',null,'停车场',null,0,null);

/*21*/
insert into winter_time values(68,'阳光舜城','千佛山校区','7:30',null,null,'北院南门',0,null);
insert into winter_time values(69,'千佛山校区','阳光舜城','17:00',null,'北院南门',null,0,null);

/*22*/
insert into winter_time values(70,'南山小区','中心校区','7:10',null,null,'图书馆',0,null);
insert into winter_time values(71,'中心校区','南山小区','17:00',null,'图书馆',null,0,null);

/*23*/
insert into winter_time values(72,'南山小区','洪家楼校区','7:10',null,null,'南门内',0,null);
insert into winter_time values(73,'洪家楼校区','南山小区','17:00',null,'南门内',null,0,null);

/*24*/
insert into winter_time values(74,'南山小区','千佛山校区','7:10',null,null,'北院南门',0,null);
insert into winter_time values(75,'千佛山校区','南山小区','17:00',null,'北院南门',null,0,null);

/*25*/
insert into winter_time values(76,'南山小区','趵突泉校区','7:10',null,null,'停车场',0,null);
insert into winter_time values(77,'趵突泉校区','南山小区','17:00',null,'停车场',null,0,null);

/*26*/
insert into winter_time values(78,'南山小区','洪家楼校区','9:00',null,null,null,0,null);

insert into winter_time_between values(78,'趵突泉校区',null);
insert into winter_time_between values(78,'千佛山校区',null);
insert into winter_time_between values(78,'中心校区',null);