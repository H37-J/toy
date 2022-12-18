CREATE TABLE Customers
(
    CustomerID   int primary key auto_increment,
    CustomerName varchar(50),
    ContactName  varchar(50),
    Address      varchar(50),
    City         varchar(50),
    PostalCode   varchar(50),
    Country      varchar(50)
);

INSERT INTO Customers(CustomerName, ContactName, Address, City, PostalCode, Country) VALUES ("TestName1", "TestContact1", "TestAddress1", "TestCity1", "TestPostalCode1", "Mexico");
INSERT INTO Customers(CustomerName, ContactName, Address, City, PostalCode, Country) VALUES ("TestName2", "TestContact2", "TestAddress2", "TestCity2", "TestPostalCode2", "Mexico");
INSERT INTO Customers(CustomerName, ContactName, Address, City, PostalCode, Country) VALUES ("TestName3", "TestContact3", "TestAddress3", "TestCity3", "TestPostalCode3", "Mexico");
INSERT INTO Customers(CustomerName, ContactName, Address, City, PostalCode, Country) VALUES ("TestName4", "TestContact4", "TestAddress4", "TestCity4", "TestPostalCode4", "Mexico");
INSERT INTO Customers(CustomerName, ContactName, Address, City, PostalCode, Country) VALUES ("TestName5", "TestContact5", "TestAddress5", "TestCity5", "TestPostalCode5", "Mexico");
INSERT INTO Customers(CustomerName, ContactName, Address, City, PostalCode, Country) VALUES ("TestName6", "TestContact6", "TestAddress6", "TestCity6", "TestPostalCode6", "Mexico");


INSERT INTO Customers(CustomerName, ContactName, Address, City, PostalCode, Country) VALUES ("TestName7", "TestContact7", "TestAddress7", "gasan", "TestPostalCode7", "Korea");
INSERT INTO Customers(CustomerName, ContactName, Address, City, PostalCode, Country) VALUES ("TestName8", "TestContact8", "TestAddress8", "gasan", "TestPostalCode8", "Korea");
INSERT INTO Customers(CustomerName, ContactName, Address, City, PostalCode, Country) VALUES ("TestName9", "TestContact9", "TestAddress9", "gasan", "TestPostalCode9", "Korea");
INSERT INTO Customers(CustomerName, ContactName, Address, City, PostalCode, Country) VALUES ("TestName10", "TestContact10", "TestAddress10", "gasan", "TestPostalCode10", "Korea");
INSERT INTO Customers(CustomerName, ContactName, Address, City, PostalCode, Country) VALUES ("TestName11", "TestContact11", "TestAddress11", "gasan", "TestPostalCode11", "Korea");

INSERT INTO Customers(CustomerName, ContactName, Address, City, PostalCode, Country) VALUES ("TestName12", "TestContact12", "TestAddress12", "sinrim", "TestPostalCode12", "Korea");
INSERT INTO Customers(CustomerName, ContactName, Address, City, PostalCode, Country) VALUES ("TestName13", "TestContact13", "TestAddress13", "sinrim", "TestPostalCode13", "Korea");
INSERT INTO Customers(CustomerName, ContactName, Address, City, PostalCode, Country) VALUES ("TestName14", "TestContact14", "TestAddress14", "sinrim", "TestPostalCode14", "Korea");
INSERT INTO Customers(CustomerName, ContactName, Address, City, PostalCode, Country) VALUES ("TestName15", "TestContact15", "TestAddress15", "sinrim", "TestPostalCode15", "Korea");

INSERT INTO Customers(CustomerName, ContactName, Address, City, PostalCode, Country) VALUES ("TestName16", "TestContact16", "TestAddress16", "Tokyo", "TestPostalCode16", "Japn");
INSERT INTO Customers(CustomerName, ContactName, Address, City, PostalCode, Country) VALUES ("TestName17", "TestContact17", "TestAddress17", "Tokyo", "TestPostalCode17", "Japn");
INSERT INTO Customers(CustomerName, ContactName, Address, City, PostalCode, Country) VALUES ("TestName18", "TestContact18", "TestAddress18", "Tokyo", "TestPostalCode18", "Japn");
INSERT INTO Customers(CustomerName, ContactName, Address, City, PostalCode, Country) VALUES ("TestName19", "TestContact19", "TestAddress19", "Tokyo", "TestPostalCode19", "Japn");

SELECT C.Country, C.City, O.*
FROM Orders AS O INNER JOIN Customers AS C ON O.CustomerId = C.CustomerID
WHERE C.Country = "Mexico";

SELECT Country, city, count(*) as NumberOfCustomers
FROM Customers
WHERE Country
IN(SELECT Country FROM Customers GROUP BY Country HAVING count(*) >= 5)
GROUP BY City ORDER BY NumberOfCustomers DESC;

SELECT DATE_FORMAT(orderDate, "%y-%m") AS YYMM, count(distinct CustomerId) as CustomerCnt, count(*) AS OrderCnt
FROM Orders
WHERE orderDate BETWEEN "2022-01-01" AND "2022-04-31" AND ShipperID = 3
GROUP BY YYMM;

CREATE TABLE Orders
(
    OrderId int primary key,
    CustomerId int,
    EmployeeId int,
    orderDate varchar(50),
    ShipperID int,
    FOREIGN KEY (CustomerId) REFERENCES Customers(CustomerID),
    FOREIGN KEY (ShipperID) REFERENCES Shippers(ShipperID)
);


INSERT INTO Orders(OrderId, CustomerId, EmployeeId, orderDate, ShipperID) VALUES(10000, 1, 11, "2022-01-01", 21);
INSERT INTO Orders(OrderId, CustomerId, EmployeeId, orderDate, ShipperID) VALUES(10001, 7, 11, "2022-01-01", 21);
INSERT INTO Orders(OrderId, CustomerId, EmployeeId, orderDate, ShipperID) VALUES(10002, 12, 11, "2022-01-01", 21);
INSERT INTO Orders(OrderId, CustomerId, EmployeeId, orderDate, ShipperID) VALUES(10003, 13, 11, "2022-01-01", 21);
INSERT INTO Orders(OrderId, CustomerId, EmployeeId, orderDate, ShipperID) VALUES(10005, 13, 11, "2022-05-01", 3);
INSERT INTO Orders(OrderId, CustomerId, EmployeeId, orderDate, ShipperID) VALUES(10004, 13, 11, "2022-03-01", 3);

CREATE TABLE Shippers
(
    ShipperID int primary key,
    ShipperName varchar(50)
);

INSERT INTO Shippers(ShipperID, ShipperName) VALUES (3, "ShipperName0");
INSERT INTO Shippers(ShipperID, ShipperName) VALUES (21, "ShipperName1");