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
);

CREATE TABLE collaboration (
id SERIAL PRIMARY KEY,
username VARCHAR,
resume VARCHAR,
project_code VARCHAR,
date date
);


CREATE DATABASE geek_collaborators_test WITH TEMPLATE geek_collaborators;