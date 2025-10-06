CREATE TABLE IF NOT EXISTS users (
  email varchar(255) NOT NULL,
  username varchar(255) NOT NULL,
  date_of_Birth DATE NOT NULL,
  role varchar(255) NOT NULL,
  hashed_password varchar(255) NOT NULL
);