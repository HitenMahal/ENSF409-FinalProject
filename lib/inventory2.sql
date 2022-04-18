/* ENSF 409 W22 Project Example Database
 2022 Barcomb and Marasco
 
 Each time this file is executed, it will reset the database to the original state defined below.
 
 */

DROP DATABASE IF EXISTS FOOD_INVENTORY;
CREATE DATABASE FOOD_INVENTORY; 
USE FOOD_INVENTORY;


DROP TABLE IF EXISTS DAILY_CLIENT_NEEDS;
CREATE TABLE DAILY_CLIENT_NEEDS (
	ClientID		int not null AUTO_INCREMENT,
	Client			varchar(25),
	WholeGrains		int,
	FruitVeggies	int,
	Protein			int,
	Other			int,
	Calories		int,
	primary key (ClientID)
);

INSERT INTO DAILY_CLIENT_NEEDS (Client, WholeGrains, FruitVeggies, Protein, Other, Calories)
VALUES
('Adult Male',	16,	28,	26,	30,	2500/7),
('Adult Female', 16, 28, 26, 30, 2000/7),
('Child over 8', 21, 33, 31, 15, 2200/7),
('Child under 8', 21, 33, 31, 15, 1400/7);

DROP TABLE IF EXISTS AVAILABLE_FOOD;
CREATE TABLE AVAILABLE_FOOD (
	ItemID			int not null AUTO_INCREMENT,
	Name			varchar(50),
	GrainContent	int,
	FVContent		int,
	ProContent		int,
	Other			int,
	Calories		int,
	primary key (ItemID)
);

INSERT INTO AVAILABLE_FOOD (Name, GrainContent, FVContent, ProContent, Other, Calories)
VALUES
/*Over by 0 calories*/ 
('Mineral Water', 0, 0, 0, 100, 750), 
('Ham', 0, 0, 100, 0, 250), 
('Tuna', 50, 0, 50, 0, 800), 
('Apple, dozen', 0, 100, 0, 0, 700), 

/*Over by 100 calories*/ 
('Granola Bar, box', 50, 0, 0, 50, 640), 
('Chicken broth, can', 0, 50, 50, 0, 1040), 
('Wheat bread, loaf', 0, 40, 0, 60, 300), 
('Tomato Sauce, jar', 0, 0, 0, 100, 120), 

/*Over by 137 calories*/ 
('Orange, dozen', 100, 0, 0, 0, 462), 
('Eggs, dozen', 0, 100, 0, 0, 826), 
('Chicken breast, pound', 0, 0, 100, 0, 682), 
('Pasta, dry, pound', 0, 0, 0, 100, 367),

/*Over by 87 calories*/ 
('Saltines', 100, 0, 0, 0, 294), 
('Instant ramen, package 20', 0, 100, 0, 0, 469), 
('Frozen pizza, pepperoni', 0, 0, 100, 0, 434), 
('Banana, bunch 5', 0, 0, 0, 100, 290), 

/*Random*/ 
('Banana, bunch 6', 0, 97, 3, 0, 1210), 
('Black olives, large tin', 23, 0, 0, 77, 2200), 
('Coconut milk, 1 liter', 4, 0, 0, 96, 1960), 
('Avocado, 1 pound', 19, 76, 5, 0, 725);