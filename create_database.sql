CREATE DATABASE IF NOT EXISTS restaurant_manager;

USE restaurant_manager;

CREATE TABLE Address (
    id 				BIGINT AUTO_INCREMENT PRIMARY KEY,
    postalCode 		VARCHAR(9) NOT NULL,
    city 			VARCHAR(255) NOT NULL,
    state 			VARCHAR(2) NOT NULL,
    neighborhood 	VARCHAR(255) NOT NULL,
    street 			VARCHAR(80) NOT NULL,
    number 			VARCHAR(50) NOT NULL
);

CREATE TABLE OperatingHours (
    id 				BIGINT AUTO_INCREMENT PRIMARY KEY,
    dayOfWeek 		VARCHAR(15) NOT NULL,
    startTime 		DATETIME NOT NULL,
    endTime 		DATETIME NOT NULL
);

CREATE TABLE Restaurant (
    id 				BIGINT AUTO_INCREMENT PRIMARY KEY,
    name 			VARCHAR(255) NOT NULL,
    addressId 		BIGINT NOT NULL,
    kitchenType 	ENUM('ITALIAN', 'MEXICAN', 'INDIAN', 'JAPANESE', 'VEGAN') NOT NULL,
    cnpj 			VARCHAR(18) NOT NULL,
    capacity 		BIGINT NOT NULL,
    FOREIGN KEY (addressId) REFERENCES Address (id)
);

CREATE TABLE User (
    id 				BIGINT AUTO_INCREMENT PRIMARY KEY,
    name 			VARCHAR(255) NOT NULL,
    email 			VARCHAR(255) NOT NULL UNIQUE,
    cpf 			VARCHAR(14) NOT NULL UNIQUE
);

CREATE TABLE Booking (
    id 				BIGINT AUTO_INCREMENT PRIMARY KEY,
    restaurantId 	BIGINT NOT NULL,
    userId 			BIGINT NOT NULL,
    bookingDate 	DATETIME NOT NULL,
	peopleQuantity 	BIGINT NOT NULL,
    status 			ENUM('PENDANT', 'CANCELED', 'CONFIRMED') NOT NULL,
    FOREIGN KEY (restaurantId) REFERENCES Restaurant (id),
    FOREIGN KEY (userId) REFERENCES User (id)
);