CREATE DATABASE personal_info;
USE personal_info;
CREATE TABLE users(
                      userId INT(255) AUTO_INCREMENT PRIMARY KEY ,
                      email VARCHAR(255) NOT NULL UNIQUE KEY ,
                      passcode VARCHAR(255) NOT NULL ,
                      fullName VARCHAR(255) ,
                      sex BOOLEAN ,
                      birthday DATE ,
                      phoneNumber VARCHAR(10) ,
                      address VARCHAR(255) ,
                      idNumber VARCHAR(12) ,
                      avatar VARCHAR(255)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;