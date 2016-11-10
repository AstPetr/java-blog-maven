postgresql duombazes uzklausos

CREATE TABLE users (id SERIAL primary key, name varchar(20), email varchar(20), password varchar(20));
CREATE TABLE articles (ID SERIAL primary key, title varchar(3000), body varchar(10000), created_at date, updated_at date, userId integer REFERENCES users(id));
CREATE TABLE comments (id SERIAL primary key, name varchar(50), body varchar(600), parentID integer REFERENCES articles(ID));

Nuoroda i heroku: https://evening-wildwood-53003.herokuapp.com/