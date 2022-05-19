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