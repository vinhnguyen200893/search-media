/*
SQLyog Enterprise - MySQL GUI v7.02 
MySQL - 5.0.22 : Database - nhatviet_rental_properties
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`nhatviet_rental_properties` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `nhatviet_rental_properties`;

/*Table structure for table `access_key` */

DROP TABLE IF EXISTS `access_key`;

CREATE TABLE `access_key` (
  `access_key` varchar(50) collate utf8_unicode_ci NOT NULL default '',
  `object_name` varchar(50) collate utf8_unicode_ci default NULL,
  `order` int(10) default NULL,
  `status` varchar(50) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`access_key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `access_key` */

/*Table structure for table `account_group` */

DROP TABLE IF EXISTS `account_group`;

CREATE TABLE `account_group` (
  `group_id` int(10) NOT NULL default '0',
  `group_name` int(10) default NULL,
  `posted_date` datetime default NULL,
  `posted_by` int(10) default NULL,
  `modified_date` datetime default NULL,
  `mnodified_by` int(10) default NULL,
  PRIMARY KEY  (`group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `account_group` */

/*Table structure for table `accounting` */

DROP TABLE IF EXISTS `accounting`;

CREATE TABLE `accounting` (
  `account_id` int(10) NOT NULL default '0',
  `account_name` varchar(50) collate utf8_unicode_ci default NULL,
  `agency_id` int(10) default NULL,
  `status` varchar(50) collate utf8_unicode_ci default NULL,
  `posted_date` datetime default NULL,
  `posted_by` int(10) default NULL,
  `modified_date` datetime default NULL,
  `modified_by` int(10) default NULL,
  PRIMARY KEY  (`account_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `accounting` */

/*Table structure for table `bill` */

DROP TABLE IF EXISTS `bill`;

CREATE TABLE `bill` (
  `bill_no` int(10) NOT NULL default '0',
  `vendor_id` int(10) default NULL,
  `agency_id` int(10) default NULL,
  `bill_date` datetime default NULL,
  `due_date` datetime default NULL,
  `bill_type` int(10) default NULL,
  `invoice_number` varchar(50) collate utf8_unicode_ci default NULL,
  `bill_description` varchar(50) collate utf8_unicode_ci default NULL,
  `total_amount` int(10) default NULL,
  `status` varchar(50) collate utf8_unicode_ci default NULL,
  `posted_date` datetime default NULL,
  `posted_by` int(10) default NULL,
  `modified_date` datetime default NULL,
  `modified_by` int(10) default NULL,
  PRIMARY KEY  (`bill_no`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `bill` */

/*Table structure for table `bill_detail` */

DROP TABLE IF EXISTS `bill_detail`;

CREATE TABLE `bill_detail` (
  `item_id` int(10) NOT NULL default '0',
  `bill_no` int(10) default NULL,
  `building_id` int(10) default NULL,
  `unit_id` int(10) default NULL,
  `description` varchar(50) collate utf8_unicode_ci default NULL,
  `account_id` int(10) default NULL,
  `amount` int(10) default NULL,
  `paid_amount` int(10) default NULL,
  `balance` int(10) default NULL,
  `status` varchar(50) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `bill_detail` */

/*Table structure for table `building` */

DROP TABLE IF EXISTS `building`;

CREATE TABLE `building` (
  `building_id` int(6) NOT NULL default '0',
  `building_type` int(5) NOT NULL default '0',
  `agency_id` int(5) NOT NULL default '0',
  `builidng_name` varchar(150) collate utf8_unicode_ci NOT NULL default '',
  `country` int(3) NOT NULL default '0',
  `state` int(6) default '0',
  `city` int(6) NOT NULL default '0',
  `zip` varchar(20) collate utf8_unicode_ci NOT NULL default '0',
  `address` varchar(100) collate utf8_unicode_ci NOT NULL default '',
  `owner_id` int(5) NOT NULL default '0',
  `manager_id` int(5) NOT NULL default '0',
  `area` varchar(50) collate utf8_unicode_ci NOT NULL default '0',
  `bar_clup` char(1) collate utf8_unicode_ci NOT NULL default 'N',
  `swimming_pool` char(1) collate utf8_unicode_ci NOT NULL default 'N',
  `garden` char(1) collate utf8_unicode_ci NOT NULL default 'N',
  `near_airport` char(1) collate utf8_unicode_ci NOT NULL default 'N',
  `near_train_station` char(1) collate utf8_unicode_ci NOT NULL default 'N',
  `near_supper_market` char(1) collate utf8_unicode_ci NOT NULL default 'N',
  `near_hospital` char(1) collate utf8_unicode_ci NOT NULL default 'N',
  `near_church` char(1) collate utf8_unicode_ci NOT NULL default 'N',
  `description` longtext collate utf8_unicode_ci,
  `total_floor` int(3) NOT NULL default '1',
  `total_unit` int(3) NOT NULL default '1',
  `vacant_unit` int(3) NOT NULL default '1',
  `occupied_unit` int(3) NOT NULL default '0',
  `built_date` date default NULL,
  `status` char(1) collate utf8_unicode_ci NOT NULL default 'N' COMMENT 'A: Active - I: Inactive - R: Repairing',
  `deleted` char(1) collate utf8_unicode_ci NOT NULL default 'N',
  `posted_date` datetime NOT NULL default '0000-00-00 00:00:00',
  `posted_by` int(5) NOT NULL default '0',
  `modified_date` datetime default NULL,
  `modified_by` int(5) NOT NULL default '0',
  PRIMARY KEY  (`building_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `building` */

/*Table structure for table `building_type` */

DROP TABLE IF EXISTS `building_type`;

CREATE TABLE `building_type` (
  `ID` int(5) NOT NULL default '0',
  `name` varchar(100) character set latin1 NOT NULL default '',
  `description` varchar(250) character set latin1 default '',
  `status` char(1) character set latin1 default 'A',
  `posted_date` datetime NOT NULL default '0000-00-00 00:00:00',
  `last_modified` datetime default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `building_type` */

insert  into `building_type`(`ID`,`name`,`description`,`status`,`posted_date`,`last_modified`) values (0,'test','test','A','2009-05-27 14:48:29','2007-05-27 14:48:29'),(0,'test2','test 2','A','2001-05-21 13:48:29','2008-05-27 14:48:29'),(0,'test 3','test 3','A','2000-04-03 09:48:29','2006-05-27 14:48:29'),(0,'test 4','test 4','A','2009-01-27 15:48:29','2037-05-27 14:48:29'),(0,'test 5','test 6','A','2003-05-12 12:48:29','2006-05-27 14:48:29'),(0,'test 6','test 6','A','2009-02-20 11:58:29','1997-05-27 14:48:29'),(0,'test 7','test 7','A','2009-03-12 11:58:29','1999-05-27 14:48:29'),(0,'test 8','test 8','A','2003-11-23 11:58:29','2006-05-27 14:48:29'),(0,'test 9','test 9','A','2003-04-25 11:58:29','2001-05-27 14:48:29');

/*Table structure for table `building_unit` */

DROP TABLE IF EXISTS `building_unit`;

CREATE TABLE `building_unit` (
  `unit_id` int(6) NOT NULL default '0',
  `building_id` int(6) NOT NULL default '0',
  `group_id` int(6) NOT NULL default '0',
  `unit_type` int(5) NOT NULL default '0',
  `unit_name` varchar(150) collate utf8_unicode_ci default NULL,
  `floor` int(2) NOT NULL default '1',
  `area` decimal(10,2) NOT NULL default '0.00',
  `wide` decimal(10,2) NOT NULL default '0.00',
  `long` decimal(10,2) NOT NULL default '0.00',
  `bedroom` int(2) NOT NULL default '0',
  `bathroom` int(2) NOT NULL default '0',
  `livingroom` char(1) collate utf8_unicode_ci NOT NULL default 'Y',
  `diningroom` char(1) collate utf8_unicode_ci NOT NULL default 'N',
  `family` char(1) collate utf8_unicode_ci NOT NULL default 'Y',
  `air_conditioning` char(1) collate utf8_unicode_ci NOT NULL default 'N',
  `furnished` char(1) collate utf8_unicode_ci NOT NULL default 'N',
  `fireplace` char(1) collate utf8_unicode_ci NOT NULL default 'N',
  `microwave` char(1) collate utf8_unicode_ci NOT NULL default 'N',
  `washer` char(1) collate utf8_unicode_ci NOT NULL default 'N',
  `dish_washer` char(1) collate utf8_unicode_ci NOT NULL default 'N',
  `refrigerator` char(1) collate utf8_unicode_ci NOT NULL default 'N',
  `description` longtext collate utf8_unicode_ci,
  `price` decimal(10,2) NOT NULL default '0.00',
  `price_type` int(5) NOT NULL default '0',
  `status` char(1) collate utf8_unicode_ci NOT NULL default 'V' COMMENT 'V: Vacant ; O: Occupied ; D: Damaged',
  `deleted` char(1) collate utf8_unicode_ci NOT NULL default 'N' COMMENT 'N: Not deleted ; Y: Deleted',
  `posted_date` datetime NOT NULL default '0000-00-00 00:00:00',
  `posted_by` int(5) NOT NULL default '0',
  `modified_date` datetime default NULL,
  `modified_by` int(5) NOT NULL default '0',
  PRIMARY KEY  (`unit_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `building_unit` */

/*Table structure for table `building_unit_type` */

DROP TABLE IF EXISTS `building_unit_type`;

CREATE TABLE `building_unit_type` (
  `ID` int(5) NOT NULL auto_increment,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) default NULL,
  `status` char(1) NOT NULL default 'A',
  `posted_date` datetime NOT NULL,
  `last_modified` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `building_unit_type` */

/*Table structure for table `business_area` */

DROP TABLE IF EXISTS `business_area`;

CREATE TABLE `business_area` (
  `type_id` int(10) NOT NULL default '0',
  `contractor_id` int(10) NOT NULL default '0',
  PRIMARY KEY  (`contractor_id`,`type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `business_area` */

/*Table structure for table `city` */

DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
  `city_id` int(6) NOT NULL default '0',
  `country_id` int(6) NOT NULL default '0',
  `city_name` varchar(50) default NULL,
  `status` char(1) NOT NULL default 'A' COMMENT 'A: Active - I: Inactive',
  `posted_date` datetime NOT NULL default '0000-00-00 00:00:00',
  `posted_by` int(5) NOT NULL default '0',
  PRIMARY KEY  (`city_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

/*Data for the table `city` */

/*Table structure for table `contractor` */

DROP TABLE IF EXISTS `contractor`;

CREATE TABLE `contractor` (
  `contractor_id` int(10) NOT NULL default '0',
  `description` varchar(50) collate utf8_unicode_ci default NULL,
  `public` bit(1) NOT NULL,
  PRIMARY KEY  (`contractor_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `contractor` */

/*Table structure for table `country` */

DROP TABLE IF EXISTS `country`;

CREATE TABLE `country` (
  `country_id` int(6) NOT NULL auto_increment,
  `country_name` varchar(50) default NULL,
  `status` char(1) NOT NULL default 'A' COMMENT 'A: Active - I: Inactive',
  `posted_date` datetime NOT NULL default '0000-00-00 00:00:00',
  `posted_by` int(5) NOT NULL default '0',
  `last_modified` datetime default NULL,
  `modified_by` tinyint(5) default NULL,
  PRIMARY KEY  (`country_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `country` */

insert  into `country`(`country_id`,`country_name`,`status`,`posted_date`,`posted_by`,`last_modified`,`modified_by`) values (1,'Australia','A','2009-05-28 11:11:11',0,NULL,NULL),(2,'aaa','A','2009-05-29 17:55:04',0,NULL,NULL),(3,'bbb','A','2009-05-29 17:58:22',0,NULL,NULL),(4,'aaaaaa','A','2009-05-29 18:00:49',0,NULL,NULL),(5,'asdasdsad','A','2009-05-29 18:01:03',0,NULL,NULL);

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `customer_id` int(6) NOT NULL default '0',
  `agency_id` int(5) NOT NULL default '0',
  `first_name` char(40) collate utf8_unicode_ci default NULL,
  `middle_name` char(40) collate utf8_unicode_ci default NULL,
  `last_name` char(40) collate utf8_unicode_ci default NULL,
  `company_name` varchar(100) collate utf8_unicode_ci default NULL,
  `DOB` date NOT NULL default '0000-00-00',
  `picture` varchar(50) collate utf8_unicode_ci default NULL COMMENT 'picture of customer',
  `email_address` varchar(60) collate utf8_unicode_ci NOT NULL default '',
  `id_code` varchar(30) collate utf8_unicode_ci NOT NULL default '0' COMMENT 'username',
  `password` varchar(30) collate utf8_unicode_ci NOT NULL default '0',
  `country` int(3) NOT NULL default '0',
  `state` int(6) NOT NULL default '0',
  `city` int(6) NOT NULL default '0',
  `address` varchar(100) collate utf8_unicode_ci default NULL,
  `zip` varchar(20) collate utf8_unicode_ci default NULL,
  `phone` varchar(15) collate utf8_unicode_ci default NULL,
  `hand_phone` varchar(15) collate utf8_unicode_ci default NULL,
  `posted_date` datetime NOT NULL default '0000-00-00 00:00:00',
  `posted_by` int(5) NOT NULL default '0',
  `modified_date` datetime default NULL,
  `modified_by` int(5) default NULL,
  `status` char(1) collate utf8_unicode_ci default 'A' COMMENT 'A: active - I: Inactive',
  `deleted` char(3) collate utf8_unicode_ci NOT NULL default 'N' COMMENT 'Y: deleted ; N: not deleted',
  PRIMARY KEY  (`customer_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `customer` */

insert  into `customer`(`customer_id`,`agency_id`,`first_name`,`middle_name`,`last_name`,`company_name`,`DOB`,`picture`,`email_address`,`id_code`,`password`,`country`,`state`,`city`,`address`,`zip`,`phone`,`hand_phone`,`posted_date`,`posted_by`,`modified_date`,`modified_by`,`status`,`deleted`) values (1,1,'Nguyen','Van','A','AAA','0000-00-00',NULL,'','0','0',0,0,0,NULL,NULL,NULL,NULL,'0000-00-00 00:00:00',0,NULL,NULL,'A','N'),(2,1,'Le','Thi','B','BBB','0000-00-00',NULL,'','0','0',0,0,0,NULL,NULL,NULL,NULL,'0000-00-00 00:00:00',0,NULL,NULL,'A','N'),(3,1,'Tran','Van','C','CCC','0000-00-00',NULL,'','0','0',0,0,0,NULL,NULL,NULL,NULL,'0000-00-00 00:00:00',0,NULL,NULL,'A','N');

/*Table structure for table `customer_contact` */

DROP TABLE IF EXISTS `customer_contact`;

CREATE TABLE `customer_contact` (
  `contact_id` int(6) NOT NULL default '0',
  `customer_id` int(6) NOT NULL default '0',
  `address` varchar(100) collate utf8_unicode_ci default '',
  `city` int(6) NOT NULL default '0',
  `state` int(6) NOT NULL default '0',
  `zip` varchar(20) collate utf8_unicode_ci default NULL,
  `country` int(6) NOT NULL default '0',
  `address_phone` varchar(15) collate utf8_unicode_ci default NULL,
  `posted_date` datetime NOT NULL default '0000-00-00 00:00:00',
  `posted_by` int(5) NOT NULL default '0',
  `modified_date` datetime default NULL,
  `modified_by` int(5) default NULL,
  `status` char(1) collate utf8_unicode_ci NOT NULL default 'A' COMMENT 'A: Active  - I: Inactive',
  PRIMARY KEY  (`contact_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Other contact info of customer';

/*Data for the table `customer_contact` */

/*Table structure for table `document` */

DROP TABLE IF EXISTS `document`;

CREATE TABLE `document` (
  `document_id` int(10) NOT NULL default '0',
  `title` varchar(50) collate utf8_unicode_ci default NULL,
  `description` varchar(50) collate utf8_unicode_ci default NULL,
  `author` varchar(50) collate utf8_unicode_ci default NULL,
  `file_name` varchar(50) collate utf8_unicode_ci default NULL,
  `folder` varchar(50) collate utf8_unicode_ci default NULL,
  `document_type` varchar(50) collate utf8_unicode_ci default NULL,
  `status` varchar(50) collate utf8_unicode_ci default NULL,
  `posted_date` datetime default NULL,
  `posted_by` int(10) default NULL,
  `modified_date` datetime default NULL,
  `modified_by` int(10) default NULL,
  PRIMARY KEY  (`document_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `document` */

/*Table structure for table `equipment` */

DROP TABLE IF EXISTS `equipment`;

CREATE TABLE `equipment` (
  `equipment_id` int(10) NOT NULL default '0',
  `building_id` int(10) default NULL,
  `unit_id` int(10) default NULL,
  `category` int(10) default NULL,
  `serial_no` varchar(50) collate utf8_unicode_ci default NULL,
  `model` varchar(50) collate utf8_unicode_ci default NULL,
  `brand` varchar(50) collate utf8_unicode_ci default NULL,
  `purchase_date` datetime default NULL,
  `purchase_price` int(10) default NULL,
  `purchase_from` varchar(50) collate utf8_unicode_ci default NULL,
  `picture` varchar(50) collate utf8_unicode_ci default NULL,
  `folder` varchar(50) collate utf8_unicode_ci default NULL,
  `status` varchar(50) collate utf8_unicode_ci default NULL,
  `posted_date` datetime default NULL,
  `modified_date` datetime default NULL,
  `modified_by` int(10) default NULL,
  PRIMARY KEY  (`equipment_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `equipment` */

/*Table structure for table `invoice` */

DROP TABLE IF EXISTS `invoice`;

CREATE TABLE `invoice` (
  `invoice_id` int(10) NOT NULL default '0',
  `contact_id` int(10) default NULL,
  `transaction_date` datetime default NULL,
  `payment_due_date` datetime default NULL,
  `transaction_description` varchar(50) collate utf8_unicode_ci default NULL,
  `total_amount` int(10) default NULL,
  `paid_amount` int(10) default NULL,
  `balance` int(10) default NULL,
  `note` varchar(50) collate utf8_unicode_ci default NULL,
  `posted_date` datetime default NULL,
  `posted_by` int(10) default NULL,
  `modified_date` datetime default NULL,
  `modified_by` int(10) default NULL,
  `status` varchar(50) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`invoice_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `invoice` */

/*Table structure for table `invoice_detail` */

DROP TABLE IF EXISTS `invoice_detail`;

CREATE TABLE `invoice_detail` (
  `charge_id` int(10) NOT NULL default '0',
  `invoice_id` int(10) default NULL,
  `account_id` int(10) default NULL,
  `amount` int(10) default NULL,
  `description` varchar(50) collate utf8_unicode_ci default NULL,
  `paid_amount` int(10) default NULL,
  `balance` int(10) default NULL,
  `posted_date` datetime default NULL,
  `posted_by` int(10) default NULL,
  `modified_date` datetime default NULL,
  `modified_by` int(10) default NULL,
  `status` varchar(50) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`charge_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `invoice_detail` */

/*Table structure for table `log_month` */

DROP TABLE IF EXISTS `log_month`;

CREATE TABLE `log_month` (
  `log_id` varchar(50) collate utf8_unicode_ci NOT NULL default '',
  `type_id` int(10) default NULL,
  `object_id` int(10) default NULL,
  `log_url` varchar(50) collate utf8_unicode_ci default NULL,
  `log_date` datetime default NULL,
  PRIMARY KEY  (`log_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `log_month` */

/*Table structure for table `log_table` */

DROP TABLE IF EXISTS `log_table`;

CREATE TABLE `log_table` (
  `log_month` datetime NOT NULL default '0000-00-00 00:00:00',
  `log_table` varchar(50) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`log_month`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `log_table` */

/*Table structure for table `log_type` */

DROP TABLE IF EXISTS `log_type`;

CREATE TABLE `log_type` (
  `type_id` int(10) NOT NULL default '0',
  `log_type` varchar(50) collate utf8_unicode_ci default NULL,
  `sort_key` int(10) default NULL,
  `table` varchar(50) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `log_type` */

/*Table structure for table `lost_item` */

DROP TABLE IF EXISTS `lost_item`;

CREATE TABLE `lost_item` (
  `lost_id` int(10) NOT NULL default '0',
  `customer_id` int(10) default NULL,
  `item_name` varchar(50) collate utf8_unicode_ci default NULL,
  `description` varchar(50) collate utf8_unicode_ci default NULL,
  `lost_date` datetime default NULL,
  `lost_description` varchar(50) collate utf8_unicode_ci default NULL,
  `found_date` datetime default NULL,
  `found_description` varchar(50) collate utf8_unicode_ci default NULL,
  `status` varchar(50) collate utf8_unicode_ci default NULL,
  `posted_date` datetime default NULL,
  `posted_by` int(10) default NULL,
  `modified_date` datetime default NULL,
  `modified_by` int(10) default NULL,
  PRIMARY KEY  (`lost_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `lost_item` */

/*Table structure for table `maintenance` */

DROP TABLE IF EXISTS `maintenance`;

CREATE TABLE `maintenance` (
  `maintenance_id` int(10) NOT NULL default '0',
  `building_id` int(10) default NULL,
  `unit_id` int(10) default NULL,
  `maintenence_title` varchar(50) collate utf8_unicode_ci default NULL,
  `maintenance_type` int(10) default NULL,
  `contractor_id` int(10) default NULL,
  `priority` varchar(50) collate utf8_unicode_ci default NULL,
  `detail` varchar(50) collate utf8_unicode_ci default NULL,
  `total_amount` int(10) default NULL,
  `paid_amount` int(10) default NULL,
  `balance_amount` int(10) default NULL,
  `posted_date` datetime default NULL,
  `started_date` datetime default NULL,
  `completed_date` datetime default NULL,
  `status` varchar(50) collate utf8_unicode_ci default NULL,
  `bill_no` int(10) default NULL,
  PRIMARY KEY  (`maintenance_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `maintenance` */

/*Table structure for table `maintenance_note` */

DROP TABLE IF EXISTS `maintenance_note`;

CREATE TABLE `maintenance_note` (
  `note_id` int(10) NOT NULL default '0',
  `maintenance_id` int(10) default NULL,
  `detail` varchar(50) collate utf8_unicode_ci default NULL,
  `public` bit(1) NOT NULL,
  `posted_date` datetime default NULL,
  `contractor_id` int(10) default NULL,
  `agent_id` int(10) default NULL,
  PRIMARY KEY  (`note_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `maintenance_note` */

/*Table structure for table `maintenance_type` */

DROP TABLE IF EXISTS `maintenance_type`;

CREATE TABLE `maintenance_type` (
  `type_id` int(10) NOT NULL default '0',
  `type_name` varchar(50) collate utf8_unicode_ci default NULL,
  `deleted` bit(1) NOT NULL,
  `status` varchar(50) collate utf8_unicode_ci default NULL,
  `posted_date` datetime default NULL,
  `posted_by` int(10) default NULL,
  `modified_date` datetime default NULL,
  `modified_by` int(10) default NULL,
  PRIMARY KEY  (`type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `maintenance_type` */

/*Table structure for table `media` */

DROP TABLE IF EXISTS `media`;

CREATE TABLE `media` (
  `media_id` int(10) NOT NULL default '0',
  `building_id` int(10) default NULL,
  `unit_id` int(10) default NULL,
  `filename` varchar(50) collate utf8_unicode_ci default NULL,
  `folder` varchar(50) collate utf8_unicode_ci default NULL,
  `description` varchar(50) collate utf8_unicode_ci default NULL,
  `media_type` varchar(50) collate utf8_unicode_ci default NULL,
  `public` bit(1) NOT NULL,
  `status` varchar(50) collate utf8_unicode_ci default NULL,
  `posted_date` datetime default NULL,
  `posted_by` int(10) default NULL,
  PRIMARY KEY  (`media_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `media` */

/*Table structure for table `object_type` */

DROP TABLE IF EXISTS `object_type`;

CREATE TABLE `object_type` (
  `type_id` int(10) NOT NULL default '0',
  `type_name` varchar(50) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `object_type` */

/*Table structure for table `occupant` */

DROP TABLE IF EXISTS `occupant`;

CREATE TABLE `occupant` (
  `occupant_id` int(6) NOT NULL default '0',
  `contract_id` int(6) NOT NULL default '0',
  `occupant_name` varchar(100) collate utf8_unicode_ci default NULL,
  `DOB` date default NULL,
  `relashionship` int(5) NOT NULL default '0',
  `note` varchar(255) collate utf8_unicode_ci default NULL,
  `posted_date` datetime NOT NULL default '0000-00-00 00:00:00',
  `posted_by` int(5) NOT NULL default '0',
  `modified_date` datetime default NULL,
  `modified_by` int(5) default NULL,
  `deleted` char(1) collate utf8_unicode_ci NOT NULL default 'N',
  `status` char(1) collate utf8_unicode_ci NOT NULL default 'S' COMMENT 'S: stay; N: not stay',
  PRIMARY KEY  (`occupant_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `occupant` */

/*Table structure for table `paid_history` */

DROP TABLE IF EXISTS `paid_history`;

CREATE TABLE `paid_history` (
  `ID` int(10) NOT NULL default '0',
  `bill_detail` int(10) default NULL,
  `paid_amount` int(10) default NULL,
  `paid_date` datetime default NULL,
  `paid_by` int(10) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `paid_history` */

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `payment_id` int(10) NOT NULL default '0',
  `pre_paid_id` int(10) default NULL,
  `contract_id` int(10) default NULL,
  `payment_date` datetime default NULL,
  `description` varchar(50) collate utf8_unicode_ci default NULL,
  `payment_method` int(10) default NULL,
  `amount_recieved` int(10) default NULL,
  `amount_paid` int(10) default NULL,
  `posted_date` datetime default NULL,
  `posted_by` int(10) default NULL,
  PRIMARY KEY  (`payment_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `payment` */

/*Table structure for table `payment_detail` */

DROP TABLE IF EXISTS `payment_detail`;

CREATE TABLE `payment_detail` (
  `ID` int(10) NOT NULL default '0',
  `charge_id` int(10) default NULL,
  `payment_id` int(10) default NULL,
  `payment_amount` int(10) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `payment_detail` */

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `group_id` int(10) NOT NULL default '0',
  `access_key` varchar(50) collate utf8_unicode_ci NOT NULL default '',
  `r_read` bit(1) NOT NULL,
  `r_write` bit(1) NOT NULL,
  `r_full` bit(1) NOT NULL,
  PRIMARY KEY  (`group_id`,`access_key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `permission` */

/*Table structure for table `pre_paid` */

DROP TABLE IF EXISTS `pre_paid`;

CREATE TABLE `pre_paid` (
  `ID` int(6) NOT NULL default '0',
  `contract_id` int(6) NOT NULL default '0',
  `payment_id` int(10) default NULL,
  `amount` decimal(10,2) NOT NULL default '0.00',
  `used_amount` decimal(10,2) NOT NULL default '0.00',
  `prepaid_date` date NOT NULL default '0000-00-00',
  `prepaid_type` char(1) collate utf8_unicode_ci NOT NULL default 'D',
  `status` char(1) collate utf8_unicode_ci NOT NULL default 'N',
  `posted_date` datetime NOT NULL default '0000-00-00 00:00:00',
  `modified_date` datetime default NULL,
  `modified_by` int(10) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `pre_paid` */

/*Table structure for table `price_type` */

DROP TABLE IF EXISTS `price_type`;

CREATE TABLE `price_type` (
  `ID` int(5) NOT NULL default '0',
  `name` varchar(100) character set latin1 NOT NULL default '',
  `description` varchar(250) character set latin1 default '',
  `status` char(1) character set latin1 default 'A',
  `posted_date` datetime NOT NULL default '0000-00-00 00:00:00',
  `last_modified` datetime default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `price_type` */

insert  into `price_type`(`ID`,`name`,`description`,`status`,`posted_date`,`last_modified`) values (0,'test','test','A','2009-05-27 14:48:29',NULL);

/*Table structure for table `rating` */

DROP TABLE IF EXISTS `rating`;

CREATE TABLE `rating` (
  `rating_id` int(10) NOT NULL default '0',
  `customer_id` int(10) default NULL,
  `rating` varchar(50) collate utf8_unicode_ci default NULL,
  `detail` varchar(50) collate utf8_unicode_ci default NULL,
  `agency_id` int(10) default NULL,
  `posted_date` datetime default NULL,
  `posted_by` int(10) default NULL,
  PRIMARY KEY  (`rating_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `rating` */

/*Table structure for table `rental_contract` */

DROP TABLE IF EXISTS `rental_contract`;

CREATE TABLE `rental_contract` (
  `contract_id` int(6) NOT NULL default '0',
  `building_id` int(6) NOT NULL default '0',
  `unit_id` int(6) default '0',
  `tenant_id` int(6) NOT NULL default '0',
  `lease_type` int(5) NOT NULL default '0',
  `price` decimal(10,2) NOT NULL default '0.00',
  `price_type` int(5) NOT NULL default '0',
  `deposit` decimal(10,2) NOT NULL default '0.00',
  `created_date` date NOT NULL default '0000-00-00',
  `lease_start_date` date NOT NULL default '0000-00-00',
  `lease_end_date` date default NULL,
  `move_in_date` date default NULL,
  `move_out_date` date default NULL,
  `description` longtext collate utf8_unicode_ci,
  `deleted` char(1) collate utf8_unicode_ci NOT NULL default 'N',
  `status` char(1) collate utf8_unicode_ci NOT NULL default 'O' COMMENT 'O: Open ; C: Close ; D: Cancel ; N: Confirm info',
  `posted_date` datetime NOT NULL default '0000-00-00 00:00:00',
  `posted_by` int(5) NOT NULL default '0',
  `modified_date` datetime default NULL,
  `modified_by` int(5) default NULL,
  PRIMARY KEY  (`contract_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `rental_contract` */

/*Table structure for table `rental_contract_status` */

DROP TABLE IF EXISTS `rental_contract_status`;

CREATE TABLE `rental_contract_status` (
  `ID` int(5) NOT NULL auto_increment,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) default NULL,
  `status` char(1) NOT NULL default 'A',
  `posted_date` datetime NOT NULL,
  `last_modified` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

/*Data for the table `rental_contract_status` */

/*Table structure for table `selected_contractor` */

DROP TABLE IF EXISTS `selected_contractor`;

CREATE TABLE `selected_contractor` (
  `invitation` int(10) NOT NULL default '0',
  `maintenance_id` int(10) default NULL,
  `constructor_id` int(10) default NULL,
  `invited_date` datetime default NULL,
  `replied_date` datetime default NULL,
  `cost` int(10) default NULL,
  `note` varchar(50) collate utf8_unicode_ci default NULL,
  `status` varchar(50) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`invitation`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `selected_contractor` */

/*Table structure for table `sharing` */

DROP TABLE IF EXISTS `sharing`;

CREATE TABLE `sharing` (
  `document_id` int(10) NOT NULL default '0',
  `object_id` int(10) NOT NULL default '0',
  `object_type` int(10) NOT NULL default '0',
  PRIMARY KEY  (`document_id`,`object_id`,`object_type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `sharing` */

/*Table structure for table `state` */

DROP TABLE IF EXISTS `state`;

CREATE TABLE `state` (
  `state_id` int(6) NOT NULL default '0',
  `country_id` int(6) NOT NULL default '0',
  `state_name` varchar(50) default NULL,
  `status` char(1) NOT NULL default 'A' COMMENT 'A: Active - I: Inactive',
  `posted_date` datetime NOT NULL default '0000-00-00 00:00:00',
  `posted_by` int(5) NOT NULL default '0',
  PRIMARY KEY  (`state_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

/*Data for the table `state` */

/*Table structure for table `suggestion` */

DROP TABLE IF EXISTS `suggestion`;

CREATE TABLE `suggestion` (
  `suggestion_id` int(10) NOT NULL default '0',
  `contract_id` int(10) default NULL,
  `detail` text collate utf8_unicode_ci,
  `public` bit(1) NOT NULL,
  `posted_date` datetime default NULL,
  `agent_id` int(10) default NULL,
  `customer_id` int(10) default NULL,
  PRIMARY KEY  (`suggestion_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `suggestion` */

/*Table structure for table `task` */

DROP TABLE IF EXISTS `task`;

CREATE TABLE `task` (
  `task_id` int(10) NOT NULL default '0',
  `agency_id` int(10) default NULL,
  `task_type` int(10) default NULL,
  `assignee_id` int(10) default NULL,
  `unit_id` int(10) default NULL,
  `customer_id` int(10) default NULL,
  `contractor_id` int(10) default NULL,
  `owner_id` int(10) default NULL,
  `task_title` varchar(50) collate utf8_unicode_ci default NULL,
  `task_description` varchar(50) collate utf8_unicode_ci default NULL,
  `status` varchar(50) collate utf8_unicode_ci default NULL,
  `posted_date` datetime default NULL,
  `posted_by` int(10) default NULL,
  PRIMARY KEY  (`task_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `task` */

/*Table structure for table `task_type` */

DROP TABLE IF EXISTS `task_type`;

CREATE TABLE `task_type` (
  `tasktype_id` int(10) NOT NULL default '0',
  `type_name` varchar(50) collate utf8_unicode_ci default NULL,
  `property_link` bit(1) NOT NULL,
  `customer_link` bit(1) NOT NULL,
  `contractor_link` bit(1) NOT NULL,
  `status` varchar(50) collate utf8_unicode_ci default NULL,
  `posted_date` datetime default NULL,
  `posted_by` int(10) default NULL,
  `modified_date` datetime default NULL,
  `modified_by` int(10) default NULL,
  PRIMARY KEY  (`tasktype_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `task_type` */

/*Table structure for table `tenant` */

DROP TABLE IF EXISTS `tenant`;

CREATE TABLE `tenant` (
  `customer_id` int(10) NOT NULL default '0',
  `tenant_id` varchar(50) collate utf8_unicode_ci default NULL,
  `tenant_password` varchar(50) collate utf8_unicode_ci default NULL,
  `creditcar_type` varchar(50) collate utf8_unicode_ci default NULL,
  `creditcard_number` varchar(50) collate utf8_unicode_ci default NULL,
  `creditcard_expier_date` datetime default NULL,
  `income` varchar(50) collate utf8_unicode_ci default NULL,
  `bank_name` varchar(50) collate utf8_unicode_ci default NULL,
  `bank_branch` varchar(50) collate utf8_unicode_ci default NULL,
  `account_number` varchar(50) collate utf8_unicode_ci default NULL,
  `posted_date` datetime default NULL,
  `modified_date` datetime default NULL,
  `status` varchar(50) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`customer_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `tenant` */

insert  into `tenant`(`customer_id`,`tenant_id`,`tenant_password`,`creditcar_type`,`creditcard_number`,`creditcard_expier_date`,`income`,`bank_name`,`bank_branch`,`account_number`,`posted_date`,`modified_date`,`status`) values (1,'1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `tenant_pet` */

DROP TABLE IF EXISTS `tenant_pet`;

CREATE TABLE `tenant_pet` (
  `pet_id` int(10) NOT NULL default '0',
  `construct_id` int(10) default NULL,
  `pet_name` varchar(50) collate utf8_unicode_ci default NULL,
  `pet` varchar(50) collate utf8_unicode_ci default NULL,
  `species` varchar(50) collate utf8_unicode_ci default NULL,
  `note` varchar(50) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`pet_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `tenant_pet` */

/*Table structure for table `tenants_verhicle` */

DROP TABLE IF EXISTS `tenants_verhicle`;

CREATE TABLE `tenants_verhicle` (
  `verhical_id` int(10) NOT NULL default '0',
  `construct_id` int(10) default NULL,
  `brand` varchar(50) collate utf8_unicode_ci default NULL,
  `model` varchar(50) collate utf8_unicode_ci default NULL,
  `serial_number` varchar(50) collate utf8_unicode_ci default NULL,
  `note` varchar(50) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`verhical_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `tenants_verhicle` */

/*Table structure for table `test` */

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `Id` int(11) NOT NULL auto_increment,
  `agent_name` varchar(255) default NULL,
  `DOB` date default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `test` */

insert  into `test`(`Id`,`agent_name`,`DOB`) values (1,'Nguyen Van A','1999-01-01'),(2,'Tran Van Ty','2009-12-02');

/*Table structure for table `unit_group` */

DROP TABLE IF EXISTS `unit_group`;

CREATE TABLE `unit_group` (
  `group_id` int(10) NOT NULL default '0',
  `building_id` int(10) default NULL,
  `floor` int(10) default NULL,
  `group_name` varchar(50) collate utf8_unicode_ci default NULL,
  `area` varchar(50) collate utf8_unicode_ci default NULL,
  `wide` varchar(50) collate utf8_unicode_ci default NULL,
  `long` varchar(50) collate utf8_unicode_ci default NULL,
  `bedroom` int(10) default NULL,
  `bathroom` int(10) default NULL,
  `livingroom` bit(1) NOT NULL,
  `dining` bit(1) NOT NULL,
  `family` bit(1) NOT NULL,
  `air_conditioning` bit(1) NOT NULL,
  `furnished` bit(1) NOT NULL,
  `fireplace` bit(1) NOT NULL,
  `microwave` bit(1) NOT NULL,
  `washer` bit(1) NOT NULL,
  `dish_washer` bit(1) NOT NULL,
  `refrigerator` bit(1) NOT NULL,
  `description` varchar(50) collate utf8_unicode_ci default NULL,
  `unit_type` int(10) default NULL,
  `price` int(10) default NULL,
  `price_type` int(10) default NULL,
  `total_unit` int(10) default NULL,
  `vacant_unit` int(10) default NULL,
  `occupied_unit` int(10) default NULL,
  `status` varchar(50) collate utf8_unicode_ci default NULL,
  `posted_date` datetime default NULL,
  `modified_date` datetime default NULL,
  `modified_by` int(10) default NULL,
  PRIMARY KEY  (`group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `unit_group` */

/*Table structure for table `user_account` */

DROP TABLE IF EXISTS `user_account`;

CREATE TABLE `user_account` (
  `account_id` int(10) NOT NULL default '0',
  `group_id` int(10) default NULL,
  `user_name` varchar(50) collate utf8_unicode_ci default NULL,
  `password` varchar(50) collate utf8_unicode_ci default NULL,
  `account_name` varchar(50) collate utf8_unicode_ci default NULL,
  `contact_name` varchar(50) collate utf8_unicode_ci default NULL,
  `country` int(10) default NULL,
  `city` int(10) default NULL,
  `state` int(10) default NULL,
  `zip` varchar(50) collate utf8_unicode_ci default NULL,
  `phone` varchar(50) collate utf8_unicode_ci default NULL,
  `fax` varchar(50) collate utf8_unicode_ci default NULL,
  `email` varchar(50) collate utf8_unicode_ci default NULL,
  `parent_acount` int(10) default NULL,
  `account_type` int(10) default NULL,
  `status` varchar(50) collate utf8_unicode_ci default NULL,
  `posted_date` datetime default NULL,
  `posted_by` int(10) default NULL,
  `modified_date` datetime default NULL,
  `modified_by` int(10) default NULL,
  PRIMARY KEY  (`account_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user_account` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
