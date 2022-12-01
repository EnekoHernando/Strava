/* DELETE 'auctions_user' database*/
DROP SCHEMA IF EXISTS auctionsdb;
/* DELETE USER 'auctions_user' AT LOCAL SERVER*/
DROP USER IF EXISTS 'auctions_user'@'%';

/* CREATE ''auctionsdb' DATABASE */
CREATE SCHEMA auctionsdb;
/* CREATE THE USER 'auctions_user' AT LOCAL SERVER WITH PASSWORD 'auctions_user' */
CREATE USER 'auctions_user'@'%' IDENTIFIED BY 'password';
/* GRANT FULL ACCESS TO THE DATABASE 'auctionsdb' FOR THE USER 'auctions_user' AT LOCAL SERVER*/
GRANT ALL ON auctionsdb.* TO 'auctions_user'@'%';
