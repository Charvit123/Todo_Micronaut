CREATE TABLE todo (
	task varchar(100) NOT NULL,
	id INT auto_increment NOT NULL,
	createdAt DATETIME NOT NULL,
	updatedAt DATETIME NOT NULL,
	status varchar(100) NOT NULL,
	CONSTRAINT todo_PK PRIMARY KEY (id)
)