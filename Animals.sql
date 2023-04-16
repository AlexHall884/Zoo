/* создать базу данных “Друзья
человека” */

DROP DATABASE IF EXISTS Human_friends;
CREATE DATABASE Human_friends;

USE Human_friends;

/* Создать таблицы с иерархией из диаграммы в БД */

# Родителский классс
DROP TABLE IF EXISTS Animals;
CREATE TABLE Animals(
	ID INT AUTO_INCREMENT PRIMARY KEY,
    Type_name VARCHAR(20)
    );

INSERT INTO Animals (Type_name)
	VALUES 
		('Pets'), 
        ('Pack_animals');

# Домашние животные 
DROP TABLE IF EXISTS Pets;
CREATE TABLE Pets(
	ID INT AUTO_INCREMENT PRIMARY KEY,
    Genus_name VARCHAR(20),
    Id_type INT,
    FOREIGN KEY (Id_type) REFERENCES Animals (ID) ON DELETE CASCADE ON UPDATE CASCADE
    );
    
INSERT INTO Pets (Genus_name, Id_type)
	VALUES 
		('Cats', 1),
		('Dogs', 1),
		('Hamsters', 1);
    
# Вьючные животные 
DROP TABLE IF EXISTS Pack_animals;
CREATE TABLE Pack_animals(
	ID INT AUTO_INCREMENT PRIMARY KEY,
    Genus_name VARCHAR(20),
    Id_type INT,
    FOREIGN KEY (Id_type) REFERENCES Animals (ID) ON DELETE CASCADE ON UPDATE CASCADE
    );
    
INSERT INTO Pack_animals (Genus_name, Id_type)
	VALUES 
		('Horses', 2),
		('Camels', 2),
		('Donkeys', 2);

# Кошки 
DROP TABLE IF EXISTS Cats;
CREATE TABLE Cats(
	ID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(30),
    Birthday DATE,
    Commands VARCHAR(40),
    Genus_id INT,
    Foreign KEY (Genus_id) REFERENCES Pets (Id) ON DELETE CASCADE ON UPDATE CASCADE
    );
    
# Собаки 
DROP TABLE IF EXISTS Dogs;
CREATE TABLE Dogs(
	ID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(30),
    Birthday DATE,
    Commands VARCHAR(40),
    Genus_id INT,
    Foreign KEY (Genus_id) REFERENCES Pets (Id) ON DELETE CASCADE ON UPDATE CASCADE
    );
    
# Хомяки
DROP TABLE IF EXISTS Hamsters;
CREATE TABLE Hamsters(
	ID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(30),
    Birthday DATE,
    Commands VARCHAR(40),
    Genus_id INT,
    Foreign KEY (Genus_id) REFERENCES Pets (Id) ON DELETE CASCADE ON UPDATE CASCADE
    );
  
# Лошади 
DROP TABLE IF EXISTS Horses;
CREATE TABLE Horses(
	ID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(30),
    Birthday DATE,
    Commands VARCHAR(40),
    Genus_id INT,
    Foreign KEY (Genus_id) REFERENCES Pack_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
    );
 
# Верблюды 
DROP TABLE IF EXISTS Camels;
CREATE TABLE Camels(
	ID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(30),
    Birthday DATE,
    Commands VARCHAR(40),
    Genus_id INT,
    Foreign KEY (Genus_id) REFERENCES Pack_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
    );
    
# Ослы 
DROP TABLE IF EXISTS Donkeys;
CREATE TABLE Donkeys(
	ID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(30),
    Birthday DATE,
    Commands VARCHAR(40),
    Genus_id INT,
    Foreign KEY (Genus_id) REFERENCES Pack_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
    );
    
/* Заполнить низкоуровневые таблицы именами(животных), командами
которые они выполняют и датами рождения */

INSERT INTO Cats (Name, Birthday, Commands, Genus_id)
	VALUES 
		('Snowball', '2017-01-03', 'Kitty-Kitty', 1),
        ('Laffe', '2021-01-01', 'Kitty-Kitty, Homewards', 1),
        ('Tom', '2022-02-05', 'Kitty-Kitty, Jump', 1);
        

INSERT INTO Dogs (Name, Birthday, Commands, Genus_id)
	VALUES 
		('Lord', '2019-01-01', 'Voice, Sit, Lay ', 2),
        ('Pirate', '2022-04-01', 'Sit, High five, Greetings ', 2),
        ('Lucky', '2023-01-02', 'Lay, Voice', 2);
        
INSERT INTO Hamsters (Name, Birthday, Commands, Genus_id)
	VALUES 
		('Busy', '2020-02-02', NULL, 3),
        ('Pie', '2022-04-04', 'High five', 3),
        ('Lusy', '2021-03-02', 'Greetings', 3);

INSERT INTO Horses (Name, Birthday, Commands, Genus_id)
	VALUES 
		('Glory', '2020-01-02', 'Golop, Walk', 1),
        ('Merlin', '2018-05-05', 'Golop, Walk, Halt', 1),
        ('Golden', '2017-03-03', 'Golop, Walk, Rear', 1);

INSERT INTO Camels (Name, Birthday, Commands, Genus_id)
	VALUES 
		('Sahara', '2021-05-02', 'Halt, Walk', 2),
        ('Bombay', '2018-06-07', 'Golop, Walk, Halt', 2),
        ('Vizier', '2018-03-03', 'Golop, Walk', 2);
        
INSERT INTO Donkeys (Name, Birthday, Commands, Genus_id)
	VALUES 
		('Duncan', '2017-05-02', 'Halt, Walk', 3),
        ('Berry', '2018-08-09', 'Walk, Halt', 3),
        ('Amanda', '2021-02-02', 'Walk', 3);
        
/* Удалить из таблицы верблюдов, т.к. верблюдов решили перевезти в другой
питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу. */

SET SQL_SAFE_UPDATES = 0;
DELETE FROM Camels;

SELECT Name, Birthday, Commands FROM Horses
UNION SELECT  Name, Birthday, Commands FROM Donkeys;

/* Создать новую таблицу “молодые животные” в которую попадут все
животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью
до месяца подсчитать возраст животных в новой таблице */ 

CREATE TEMPORARY TABLE Temp AS
	SELECT *, 'Horses' as genus FROM Horses
	UNION SELECT *, 'Donkeys' AS genus FROM Donkeys
	UNION SELECT *, 'Dogs' AS genus FROM Dogs
	UNION SELECT *, 'Cats' AS genus FROM Cats
	UNION SELECT *, 'Hamsters' AS genus FROM Hamsters;

SELECT * FROM Temp;

DROP TABLE IF EXISTS Young_animals;
CREATE TABLE Young_animals AS
	SELECT Name, Birthday, Commands, genus, TIMESTAMPDIFF(MONTH, Birthday, CURDATE()) AS Age_in_month
	FROM Temp WHERE Birthday BETWEEN ADDDATE(curdate(), INTERVAL -3 YEAR) AND ADDDATE(CURDATE(), INTERVAL -1 YEAR);

SELECT * FROM Young_animals;

/* Объединить все таблицы в одну, при этом сохраняя поля, указывающие на
прошлую принадлежность к старым таблицам.*/

SELECT h.Name, h.Birthday, h.Commands, pa.Genus_name, ya.Age_in_month 
FROM Horses h
LEFT JOIN Young_animals ya ON ya.Name = h.Name
LEFT JOIN Pack_animals pa ON pa.Id = h.Genus_id
UNION 
SELECT d.Name, d.Birthday, d.Commands, pa.Genus_name, ya.Age_in_month 
FROM Donkeys d 
LEFT JOIN Young_animals ya ON ya.Name = d.Name
LEFT JOIN Pack_animals pa ON pa.Id = d.Genus_id
UNION
SELECT c.Name, c.Birthday, c.Commands, p.Genus_name, ya.Age_in_month 
FROM Cats c
LEFT JOIN Young_animals ya ON ya.Name = c.Name
LEFT JOIN Pets p ON p.Id = c.Genus_id
UNION
SELECT d.Name, d.Birthday, d.Commands, p.Genus_name, ya.Age_in_month 
FROM Dogs d
LEFT JOIN Young_animals ya ON ya.Name = d.Name
LEFT JOIN Pets p ON p.Id = d.Genus_id
UNION
SELECT hm.Name, hm.Birthday, hm.Commands, p.Genus_name, ya.Age_in_month 
FROM Hamsters hm
LEFT JOIN Young_animals ya ON ya.Name = hm.Name
LEFT JOIN Pets p ON p.Id = hm.Genus_id;
