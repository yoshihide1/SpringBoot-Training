drop database if exists training;

create database training;

use training;

CREATE TABLE admins (
id INT AUTO_INCREMENT,
name VARCHAR(128),
password VARCHAR(256),
PRIMARY KEY (id),
UNIQUE(name)
);

CREATE TABLE members (
id INT AUTO_INCREMENT,
email VARCHAR(128),
password VARCHAR(256),
zip_code VARCHAR(12),
address VARCHAR(256),
phone_number VARCHAR(12),
lastUpdatedBy VARCHAR(128),
status VARCHAR(32), -- 未承認:unapproved, 承認:approval --
PRIMARY KEY (id),
UNIQUE(email)
);

CREATE TABLE category(
id INT AUTO_INCREMENT,
name VARCHAR(16),
PRIMARY KEY (id)
);

CREATE TABLE product(
id INT AUTO_INCREMENT,
category_id INT,
name VARCHAR(128),
price INT,
image_path VARCHAR(20),
description TEXT,
PRIMARY KEY (id)
);

CREATE TABLE order_items (
  id INT(11) NOT NULL AUTO_INCREMENT,
  order_id INT(11),
  name VARCHAR(128),
  price INT(11),
  image_path VARCHAR(20),
  description TEXT,
  quantity INT(11),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE orders (
  id INT(11) NOT NULL AUTO_INCREMENT,
  member_id INT(11) NOT NULL,
  name VARCHAR(12),
  email VARCHAR(256),
  phone VARCHAR(12),
  zip_code VARCHAR(12),
  address VARCHAR(256),
  price INT(12),
  date DATE,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
