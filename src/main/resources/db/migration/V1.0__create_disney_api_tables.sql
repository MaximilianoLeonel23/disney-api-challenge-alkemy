-- Movies
CREATE TABLE movies (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    image varchar(255),
    title varchar(255) NOT NULL,
    creation_date DATE,
    rating double
);

-- Series
CREATE TABLE series (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    image varchar(255),
    title varchar(255) NOT NULL,
    creation_date DATE,
    rating double,
    seasons integer,
    episodes integer
);

-- Character
CREATE TABLE characters (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    image varchar(255),
    name varchar(100) NOT NULL,
    age integer,
    weight double,
    history text
);

-- Genres
CREATE TABLE genres (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    image VARCHAR(255)
);

-- Users
CREATE TABLE users (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    username varchar(100),
    email varchar(100) NOT NULL,
    password varchar(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Relation Character - Movies
CREATE TABLE character_movies (
    character_id bigint,
    movie_id bigint,
    PRIMARY KEY(character_id, movie_id),
    FOREIGN KEY(character_id) REFERENCES characters(id),
    FOREIGN KEY(movie_id) REFERENCES movies(id)
);

-- Relation Character - Series
CREATE TABLE character_series (
    character_id bigint,
    series_id bigint,
    PRIMARY KEY(character_id, series_id),
    FOREIGN KEY(character_id) REFERENCES characters(id),
    FOREIGN KEY(series_id) REFERENCES series(id)
);

-- Relation Genres - Movies
CREATE TABLE genre_movies (
    genre_id bigint,
    movie_id bigint,
    PRIMARY KEY(genre_id, movie_id),
    FOREIGN KEY(genre_id) REFERENCES genres(id),
    FOREIGN KEY(movie_id) REFERENCES movies(id)
);

-- Relation Genres - Series
CREATE TABLE genre_series (
    genre_id bigint,
    series_id bigint,
    PRIMARY KEY(genre_id, series_id),
    FOREIGN KEY(genre_id) REFERENCES genres(id),
    FOREIGN KEY(series_id) REFERENCES series(id)
);