<<<<<<< HEAD
CREATE DATABASE geek_collaborators;
\c geek_collaborators

CREATE TABLE jobs (
id SERIAL PRIMARY KEY,
company VARCHAR,
title VARCHAR,
description VARCHAR,
duration VARCHAR,
languages VARCHAR
);

CREATE DATABASE geek_collaborators_test WITH TEMPLATE geek_collaborators;
=======
CREATE DATABASE fullstack_collaborators;
\c fullstack_collaborators;
CREATE TABLE fullstack_collaborators (name varchar, language varchar, contact int, role varchar, id PRIMARY KEY;

CREATE DATABASE fullstack;
\c fullstack;
CREATE TABLE fullstack (name varchar, owner varchar, owner_contact varchar, id PRIMARY KEY;

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

CREATE TABLE contacts(
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR,
    email VARCHAR,
    subject VARCHAR,
    message VARCHAR
);

CREATE DATABASE geek_collaborators_test WITH TEMPLATE geek_collaborators;

>>>>>>> b1b7f4c45133ef6171e84127d0dabd1ce20e7123
