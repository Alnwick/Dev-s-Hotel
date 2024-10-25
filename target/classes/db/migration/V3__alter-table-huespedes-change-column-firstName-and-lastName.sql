ALTER TABLE huespedes CHANGE COLUMN firstName first_Name  varchar(100) not null unique;
ALTER TABLE huespedes CHANGE COLUMN lastName last_Name  varchar(100) not null unique;