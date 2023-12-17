CREATE TABLE Product (
    ProductID INT PRIMARY KEY,
    ProductName VARCHAR(255) NOT NULL,
    Description VARCHAR(255),
    Category VARCHAR(50),
    Price DECIMAL(10, 2),
    StockQuantity INT
);

CREATE TABLE Supplier (
    SupplierID INT PRIMARY KEY,
    SupplierName VARCHAR(255) NOT NULL,
    Place VARCHAR(255)
);

ALTER TABLE Product MODIFY ProductID INT PRIMARY KEY AUTO_INCREMENT;

CREATE TABLE PurchaseOrder (
  OrderId INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  SupplierId INTEGER NOT NULL,
  OrderDate DATETIME NOT NULL,
  ExpDeliveryDate DATETIME NOT NULL,
  Status VARCHAR(45) NOT NULL,
  CONSTRAINT FK_purchaseOrder_1 FOREIGN KEY FK_purchaseOrder_1 (SupplierId)
  REFERENCES supplier (SupplierID)
);

CREATE TABLE  salesorder (
  saleId int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  saleProduct varchar(255) NOT NULL,
  salePrice decimal(10,2) NOT NULL,
  saleQuantity int(11) NOT NULL
);

CREATE TABLE orderitem (
  ItemId int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ProductId int(11) NOT NULL,
  PurchaseOrderId int(10) NOT NULL,
  SalesOrderId int(11) NOT NULL,
  Quantity int(10) NOT NULL,
  KEY FK_product (ProductId),
  KEY FK_purchaseOrder (PurchaseOrderId),
  KEY FK_salesOrder (SalesOrderId),
  CONSTRAINT FK_product FOREIGN KEY (ProductId) REFERENCES product (ProductID) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_purchaseOrder FOREIGN KEY (PurchaseOrderId) REFERENCES purchaseorder (OrderId),
  CONSTRAINT FK_salesOrder FOREIGN KEY (SalesOrderId) REFERENCES salesorder (saleId)
);