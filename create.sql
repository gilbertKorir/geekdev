CREATE DATABASE geek_collaborators;
\c geek_collaborators;

CREATE TABLE students (
id SERIAL PRIMARY KEY,
name VARCHAR,
email VARCHAR,
password VARCHAR
);

CREATE TABLE developers (
id SERIAL PRIMARY KEY,
name VARCHAR,
email VARCHAR,
password VARCHAR,
resume VARCHAR
);

CREATE TABLE collaboration (
id SERIAL PRIMARY KEY,
username VARCHAR,
project_code VARCHAR
);


CREATE DATABASE geek_collaborators_test WITH TEMPLATE geek_collaborators;