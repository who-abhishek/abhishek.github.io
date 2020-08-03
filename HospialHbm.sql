/*
SQLyog Community v9.30 
MySQL - 5.6.25-log : Database - Hospitalhbm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`Hospitalhbm` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `Hospitalhbm`;

/*Table structure for table `H_APPOINTMENT` */

DROP TABLE IF EXISTS `H_APPOINTMENT`;

CREATE TABLE `H_APPOINTMENT` (
  `ID` bigint(20) NOT NULL,
  `FIRSTNAME` varchar(255) DEFAULT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  `DOCTORNAME` varchar(255) DEFAULT NULL,
  `DECEASED` varchar(255) DEFAULT NULL,
  `TIME` varchar(255) DEFAULT NULL,
  `APPDATE` datetime DEFAULT NULL,
  `MOBILENO` varchar(255) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `MODIFIED_BY` varchar(255) DEFAULT NULL,
  `CREATEDDATETIME` datetime DEFAULT NULL,
  `MODIFIEDDATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `H_APPOINTMENT` */

/*Table structure for table `H_PATIENT` */

DROP TABLE IF EXISTS `H_PATIENT`;

CREATE TABLE `H_PATIENT` (
  `ID` bigint(20) NOT NULL,
  `FIRSTNAME` varchar(255) DEFAULT NULL,
  `EMAILID` varchar(255) DEFAULT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  `DOB` datetime DEFAULT NULL,
  `MOBILENO` varchar(255) DEFAULT NULL,
  `GENDER` varchar(255) DEFAULT NULL,
  `AGE` varchar(255) DEFAULT NULL,
  `BLOODGROUP` varchar(255) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `CITY` varchar(255) DEFAULT NULL,
  `CNIC` varchar(255) DEFAULT NULL,
  `MARITIALSTATUS` varchar(255) DEFAULT NULL,
  `JOININGDATE` datetime DEFAULT NULL,
  `DOCTORNAME` varchar(255) DEFAULT NULL,
  `DECEASED` varchar(255) DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `MODIFIED_BY` varchar(255) DEFAULT NULL,
  `CREATEDDATETIME` datetime DEFAULT NULL,
  `MODIFIEDDATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `H_PATIENT` */

/*Table structure for table `H_ROLE` */

DROP TABLE IF EXISTS `H_ROLE`;

CREATE TABLE `H_ROLE` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `MODIFIED_BY` varchar(255) DEFAULT NULL,
  `CREATEDDATETIME` datetime DEFAULT NULL,
  `MODIFIEDDATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `H_ROLE` */

insert  into `H_ROLE`(`ID`,`NAME`,`DESCRIPTION`,`CREATED_BY`,`MODIFIED_BY`,`CREATEDDATETIME`,`MODIFIEDDATETIME`) values (1,'Admin','Administration',NULL,NULL,'2019-12-11 12:27:07','2019-12-11 12:27:10'),(2,'Doctor','Doctor',NULL,NULL,'2019-12-11 12:28:51','2019-12-11 12:28:54'),(3,'Receptionist','Receptionist',NULL,NULL,'2019-12-11 12:29:12','2019-12-11 12:29:16');

/*Table structure for table `H_USER` */

DROP TABLE IF EXISTS `H_USER`;

CREATE TABLE `H_USER` (
  `ID` bigint(20) NOT NULL,
  `FIRSTNAME` varchar(255) DEFAULT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  `LOGIN` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `DOB` datetime DEFAULT NULL,
  `MOBILENO` varchar(255) DEFAULT NULL,
  `GENDER` varchar(255) DEFAULT NULL,
  `AGE` varchar(255) DEFAULT NULL,
  `SPCIALIZATION` varchar(255) DEFAULT NULL,
  `BLOODGROUP` varchar(255) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `CITY` varchar(255) DEFAULT NULL,
  `CNIC` varchar(255) DEFAULT NULL,
  `MARITIALSTATUS` varchar(255) DEFAULT NULL,
  `JOININGDATE` datetime DEFAULT NULL,
  `QUALIFICATION` varchar(255) DEFAULT NULL,
  `EMAILID` varchar(255) DEFAULT NULL,
  `ROLE_ID` bigint(20) DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `MODIFIED_BY` varchar(255) DEFAULT NULL,
  `CREATEDDATETIME` datetime DEFAULT NULL,
  `MODIFIEDDATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `H_USER` */

insert  into `H_USER`(`ID`,`FIRSTNAME`,`LASTNAME`,`LOGIN`,`PASSWORD`,`DOB`,`MOBILENO`,`GENDER`,`AGE`,`SPCIALIZATION`,`BLOODGROUP`,`ADDRESS`,`CITY`,`CNIC`,`MARITIALSTATUS`,`JOININGDATE`,`QUALIFICATION`,`EMAILID`,`ROLE_ID`,`CREATED_BY`,`MODIFIED_BY`,`CREATEDDATETIME`,`MODIFIEDDATETIME`) values (1,'Admin','Admin','Admin@gmail.com','Admin@123','2019-12-11 12:58:30','7585654555','Male','25',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,'2019-12-11 12:59:00','2019-12-11 12:59:03'),(2,'Doctor','Doctor','Doctor@gmail.com','Doc@123','1997-10-06 00:00:00','7595654855','Male','21','ssgfg','B+','sfvfdv','Indore','Ducimus cum sunt is',NULL,'2010-10-06 00:00:00','Cillum natus quo ani','Doctor@gmail.com',2,'Admin@gmail.com','Admin@gmail.com','2019-12-11 13:00:48','2019-12-11 13:00:48'),(3,'Receptionist','Receptionist','Receptionist@gmail.com','Rep@123','1997-10-06 00:00:00','9565856655','Male','21',NULL,'B-','sfverge','Bhopal','Repudiandae in ipsa',NULL,'2010-10-06 00:00:00','Cillum natus quo ani','Receptionist@gmail.com',3,'Admin@gmail.com','Admin@gmail.com','2019-12-11 13:04:07','2019-12-11 13:04:07');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
