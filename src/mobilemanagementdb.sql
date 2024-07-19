CREATE DATABASE `mobilemanagementdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `mobilemanagementdb`.`user` (
  `username` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` int NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `mobilemanagementdb`.`seller` (
  `username` varchar(45) NOT NULL,
  `company` varchar(45) NOT NULL,
  PRIMARY KEY (`username`),
  CONSTRAINT `seller_username` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `mobilemanagementdb`.`program` (
  `id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `minutes` int NOT NULL,
  `basecharge` double NOT NULL,
  `additionalcharge` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `mobilemanagementdb`.`phone_number` (
  `programid` int NOT NULL,
  `number` varchar(15) NOT NULL,
  PRIMARY KEY (`number`),
  KEY `program_idx` (`programid`),
  CONSTRAINT `programid` FOREIGN KEY (`programid`) REFERENCES `program` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `mobilemanagementdb`.`client` (
  `username` varchar(45) NOT NULL,
  `afm` varchar(12) NOT NULL,
  `balance` double NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `afm_UNIQUE` (`afm`),
  UNIQUE KEY `phone_number_UNIQUE` (`phone_number`),
  CONSTRAINT `phone_number` FOREIGN KEY (`phone_number`) REFERENCES `phone_number` (`number`),
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `mobilemanagementdb`.`call` (
  `caller` varchar(15) NOT NULL,
  `reciever` varchar(15) NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  PRIMARY KEY (`caller`,`reciever`),
  KEY `reciever_idx` (`reciever`),
  CONSTRAINT `caller` FOREIGN KEY (`caller`) REFERENCES `client` (`phone_number`),
  CONSTRAINT `reciever` FOREIGN KEY (`reciever`) REFERENCES `client` (`phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `mobilemanagementdb`.`bill` (
  `bill_id` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `billing_month` int NOT NULL,
  `number_of_calls` int NOT NULL,
  `paid` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`bill_id`),
  KEY `fk_user` (`username`),
  CONSTRAINT `fk_user` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `mobilemanagementdb`.`program` (`id`, `name`, `minutes`, `basecharge`, `additionalcharge`)
VALUES (0, 'null', 0, 0, 0);

