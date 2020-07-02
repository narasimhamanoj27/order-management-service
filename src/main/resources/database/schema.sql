CREATE TABLE IF NOT EXISTS `orders` ( 
   `customerId` INT AUTO_INCREMENT, 
   `customerName` VARCHAR(20) NOT NULL, 
   `orderDate` DATE NOT NULL, 
   `address` VARCHAR(255) NOT NULL,
   `total` DOUBLE NOT NUll,
   PRIMARY KEY (customerId)
);

CREATE TABLE IF NOT EXISTS `orderitem` (
	`productCode` INT PRIMARY KEY AUTO_INCREMENT,
	`productName` VARCHAR(20) NOT NULL,
	`quantity` INT NOT NULL
);