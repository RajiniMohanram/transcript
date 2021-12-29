delimiter $$

CREATE DATABASE `dmisjbu` /*!40100 DEFAULT CHARACTER SET utf8 */$$

delimiter $$

CREATE TABLE `transcript` (
  `study_center` varchar(25) NOT NULL,
  `programme` varchar(255) NOT NULL,
  `registration_no` varchar(15) NOT NULL,
  `stud_name` varchar(255) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `dob` date NOT NULL,
  `doe` varchar(45) NOT NULL,
  `cgpa` decimal(5,2) NOT NULL,
  `class_of_award` varchar(255) NOT NULL,
  `last_appear` varchar(45) NOT NULL,
  `doi` date NOT NULL,
  `semester` int(2) NOT NULL,
  `module_order` int(2) NOT NULL,
  `module_code` varchar(45) NOT NULL,
  `module_name` varchar(255) NOT NULL,
  `grade` decimal(5,2) DEFAULT NULL,
  `appeared` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

