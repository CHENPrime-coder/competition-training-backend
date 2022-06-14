/*
SQLyog Professional v12.09 (64 bit)
MySQL - 8.0.26 : Database - competitiontraining
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`competitiontraining` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `competitiontraining`;

/*Table structure for table `daily` */

DROP TABLE IF EXISTS `daily`;

CREATE TABLE `daily` (
  `dname` varchar(20) NOT NULL,
  `did` bigint NOT NULL AUTO_INCREMENT,
  `ddate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `reporter` varchar(20) NOT NULL,
  `dbody` text NOT NULL,
  PRIMARY KEY (`did`)
) ENGINE=InnoDB AUTO_INCREMENT=100007 DEFAULT CHARSET=utf8mb3;

/*Data for the table `daily` */

insert  into `daily`(`dname`,`did`,`ddate`,`reporter`,`dbody`) values ('redis工作',100005,'2022-03-15 17:35:11','admin','今天把redis打卡搞完了'),('今天把接口文档写完了',100006,'2022-03-16 18:27:45','admin','接口文档');

/*Table structure for table `files` */

DROP TABLE IF EXISTS `files`;

CREATE TABLE `files` (
  `name` varchar(50) NOT NULL,
  `url` varchar(100) NOT NULL,
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `oldname` varchar(50) NOT NULL,
  `fid` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=100011 DEFAULT CHARSET=utf8mb3;

/*Data for the table `files` */

insert  into `files`(`name`,`url`,`type`,`oldname`,`fid`) values ('default.jpg','/springbootUPLOAD/default.jpg','image/jpeg','MESA.jpg',100004),('项目架构-后端1647426650511.png','/springbootUPLOAD/项目架构-后端1647426650511.png','image/png','项目架构-后端.png',100009),('admin1647427290808.jpg','/springbootUPLOAD/admin1647427290808.jpg','image/jpeg','admin.jpg',100010);

/*Table structure for table `performance` */

DROP TABLE IF EXISTS `performance`;

CREATE TABLE `performance` (
  `uid` bigint NOT NULL,
  `score` varchar(255) NOT NULL,
  `info` varchar(255) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `pfmid` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`pfmid`)
) ENGINE=InnoDB AUTO_INCREMENT=100008 DEFAULT CHARSET=utf8mb3;

/*Data for the table `performance` */

insert  into `performance`(`uid`,`score`,`info`,`date`,`pfmid`) values (0,'{\"redis\":\"100\"}','redis考核','2022-03-16 15:41:16',100007);

/*Table structure for table `plans` */

DROP TABLE IF EXISTS `plans`;

CREATE TABLE `plans` (
  `pid` bigint NOT NULL AUTO_INCREMENT,
  `pname` varchar(50) NOT NULL,
  `pbody` text NOT NULL,
  `ptype` varchar(20) NOT NULL,
  `planner` varchar(50) NOT NULL,
  `pdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=100012 DEFAULT CHARSET=utf8mb3;

/*Data for the table `plans` */

insert  into `plans`(`pid`,`pname`,`pbody`,`ptype`,`planner`,`pdate`) values (100008,'完善后端','实现增加的一些功能','周计划','admin','2022-03-15 17:25:17'),(100010,'完善项目','android+web+后端完成','学期计划','admin','2022-03-15 17:28:01'),(100011,'完善前端','至少要构思好','月计划','admin','2022-03-15 17:31:31');

/*Table structure for table `progress` */

DROP TABLE IF EXISTS `progress`;

CREATE TABLE `progress` (
  `tid` int NOT NULL,
  `proid` int NOT NULL AUTO_INCREMENT,
  `progress` int NOT NULL,
  `prodate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remarks` text NOT NULL,
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`proid`)
) ENGINE=InnoDB AUTO_INCREMENT=100008 DEFAULT CHARSET=utf8mb3;

/*Data for the table `progress` */

insert  into `progress`(`tid`,`proid`,`progress`,`prodate`,`remarks`,`username`) values (100009,100000,20,'2022-03-15 14:11:58','更新20%的任务进度，现在的任务进度是20%','admin'),(100009,100001,50,'2022-03-15 14:12:40','更新50%的任务进度，现在的任务进度是70%','admin'),(100010,100002,30,'2022-03-20 18:11:30','完成30%的内容，更新任务进度','student1'),(100010,100003,20,'2022-03-20 18:11:42','完成50%的内容，更新任务进度','student1'),(100010,100004,40,'2022-03-20 18:11:54','完成90%的内容，更新任务进度','student1'),(100010,100005,10,'2022-03-20 18:12:06','完成100%的内容，更新任务进度','student1'),(100011,100006,10,'2022-03-21 10:35:32','测试更新任务进度','admin'),(100011,100007,20,'2022-03-21 11:07:24','修复不刷新bug','admin');

/*Table structure for table `relation` */

DROP TABLE IF EXISTS `relation`;

CREATE TABLE `relation` (
  `sid` varchar(20) NOT NULL,
  `uid` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `relation` */

insert  into `relation`(`sid`,`uid`) values ('1','0'),('9','100001'),('9','100002');

/*Table structure for table `studios` */

DROP TABLE IF EXISTS `studios`;

CREATE TABLE `studios` (
  `sid` bigint NOT NULL AUTO_INCREMENT,
  `smembers` varchar(200) DEFAULT NULL,
  `sheadicon` varchar(100) NOT NULL DEFAULT 'default.jpg',
  `sname` varchar(20) NOT NULL,
  `teacher` varchar(20) NOT NULL DEFAULT 'admin',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;

/*Data for the table `studios` */

insert  into `studios`(`sid`,`smembers`,`sheadicon`,`sname`,`teacher`) values (1,'[[,admin','项目架构-后端1647426650511.png','admin','admin'),(9,'[[,student1,student2','default.jpg','云计算工作室','admin');

/*Table structure for table `task` */

DROP TABLE IF EXISTS `task`;

CREATE TABLE `task` (
  `tid` bigint NOT NULL AUTO_INCREMENT,
  `publisher` varchar(20) NOT NULL,
  `tbody` text NOT NULL,
  `tname` varchar(20) NOT NULL,
  `tfiles` varchar(100) DEFAULT NULL,
  `progress` varchar(10) NOT NULL,
  `startdate` date NOT NULL,
  `enddate` date NOT NULL,
  `tmembers` varchar(100) NOT NULL,
  `tdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=100014 DEFAULT CHARSET=utf8mb3;

/*Data for the table `task` */

insert  into `task`(`tid`,`publisher`,`tbody`,`tname`,`tfiles`,`progress`,`startdate`,`enddate`,`tmembers`,`tdate`) values (100009,'admin','213123123132123131231231','任务名字23131312',NULL,'70','2022-01-21','2022-01-25','admin','2022-03-15 14:12:40'),(100010,'admin','根据后端提供的接口文档和设计稿完成开发','安卓端开发任务',NULL,'100','2022-03-16','2022-04-16','admin','2022-03-20 18:12:06'),(100011,'admin','根据需求文档完成开发。长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本','后端端开发任务',NULL,'30','2022-03-22','2022-04-01','admin','2022-03-21 11:07:24'),(100012,'admin','根据需求文档完成开发，毕业设计级。','全栈开发任务',NULL,'0','2022-03-22','2024-03-22','admin','2022-03-17 17:59:42'),(100013,'admin','根据需求文档完成开发。多人协作','多人开发任务',NULL,'0','2022-03-22','2022-04-11','admin,student1,student2','2022-03-18 08:31:38');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  `headicon` varchar(50) NOT NULL DEFAULT 'default.jpg',
  `telnumber` varchar(11) NOT NULL,
  `role` varchar(20) NOT NULL,
  `uid` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=100003 DEFAULT CHARSET=utf8mb3;

/*Data for the table `users` */

insert  into `users`(`username`,`password`,`headicon`,`telnumber`,`role`,`uid`) values ('admin','admin','admin1647427290808.jpg','','administrator',0),('student1','student1','default.jpg','18382020121','学生',100001),('student2','student2','default.jpg','82922020231','学生',100002);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
