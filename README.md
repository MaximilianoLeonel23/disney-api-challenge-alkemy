# Disney API

The "Disney API" is an API designed for managing characters, movies, series and genres from the universe of Disney.
This document presents a general view of the app's functionalities and how to use them.

## Table of Contents

- [Technologies](#technologies)
- [Requirements](#requirements)
- [Instalation](#instalation)
- [Environment configuration](#environment-configuration)
- [Endpoints](#endpoints)
  - [Authentication](#authentication)
      - [Sign Up](#sign-up)
      - [Sign In](#sign-in)
  - [Character](#character)
      - [Create a new character](#create-a-new-character)
      - [Get all characters](#get-all-characters)
      - [Get a character details](#get-a-character-details)
      - [Update a character](#update-a-character)
      - [Delete a character](#delete-a-character)
  - [Movies](#movies)
      - [Create a new movie](#create-a-new-movie)
      - [Get all movies](#get-all-movies)
      - [Get a movie details](#get-a-movie-details)
      - [Update a movie](#update-a-movie)
      - [Delete a movie](#delete-a-movie)
  - [Series](#series)
      - [Create a new series](#create-a-new-series)
      - [Get all series](#get-all-series)
      - [Get a series details](#get-a-series-details)
      - [Update a series](#update-a-series)
      - [Delete a series](#delete-a-series)
  - [Genres](#genres)
      - [Get all genres](#get-all-genres)
      - [Get a genre details](#get-a-genre-details)

## Technologies

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- JPA (Java Persistence API)
- MySQL
- Flyway
- Spring Mail

## Requirements

- Java 17
- Maven
- MySQL
- IDE como IntelliJ IDEA o Eclipse

## Instalation

1. Clone the repository:

```shell
git clone https://github.com/usuario/disney-api.git
```

2. Go to the directory project

```shell
cd disney-api
```

3. Install the dependencies

```shell
mvn install
```

4. Configure database and environment variables. Create application.properties file

## Environment configuration

Create a file `.env` in the root of your project with the following variables:

```env
DB_HOST=db host (e.i. localhost)
DB_PORT=db port (e.i. 3306)
DB_NAME=db name (e.i. disnet_db)
DB_USER=db user (e.i. username)
DB_PASS=db password (e.i. password)
JWT_SECRET=secret key (e.i. secret1234)
```

## Endpoints

### Authentication

#### Sign Up

**Endpoint**: `POST /api/auth/signup`

**Description**: Register a new user and send a confirmation email.

**Request**:

```json
{
  "email": "user@example.com",
  "password": "password",
  "name": "User Name"
}
```

**Response**:

```json
{
  "user": {
    "id": 1,
    "username": "User Name",
    "email": "user@example.com",
    "createdAt": "2024-08-02T21:10:42.292Z"
  },
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
}
```

#### Sign In

**Endpoint**: `POST /api/auth/signin`

**Description**: Login with an existed user and receive a valid token to access all routes.

**Request**:

```json
{
  "email": "user@example.com",
  "password": "password"
}
```

**Response**:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
}
```

### Character

#### Create a new character

**Endpoint**: `POST /api/characters`

**Description**: Create a new character.

**Request**:

```json
{
  "name": "string",
  "image": "string",
  "age": 0,
  "weight": 0,
  "history": "string"
}
```

**Response**:

```json
{
  "id": 0,
  "name": "string",
  "image": "string",
  "age": 0,
  "weight": 0,
  "history": "string"
}
```

#### Get all characters

**Endpoint**: `GET /api/characters`

**Description**: Get a list of characters with filters.

**Query Parameters in url** (query parameters):
- `name` (optional)
- `age` (optional)
- `weight` (optional)
- `movies` (optional) ( i.e.`movies=1,2`).
- `series` (optional) ( i.e.`movies=1,2`).


**Response**:

```json
[
  {
  "id": 0,
  "name": "string",
  "image": "string",
  "age": 0,
  "weight": 0,
  "history": "string"
  },
  {}
]
```

#### Get a character details

**Endpoint**: `GET /api/characters/{id}`

**Description**: Get a character details.

**Query Parameters in url** (query parameters):
- `id` (i.e. `/api/characters/4`)

**Response**:

```json
[
  {
  "id": 0,
  "name": "string",
  "image": "string",
  "age": 0,
  "weight": 0,
  "history": "string"
  }
]
```

#### Update a character

**Endpoint**: `PUT /api/characters/{id}`

**Description**: Update a character.

**Query Parameters in url** (query parameters):
- `id` (i.e. `/api/characters/4`)

**Request**:

```json
{
  "name": "string",
  "image": "string",
  "age": 0,
  "weight": 0,
  "history": "string"
}
```

**Response**:

```json
{
  "id": 0,
  "name": "string",
  "image": "string",
  "age": 0,
  "weight": 0,
  "history": "string"
}
```

#### Delete a character

**Endpoint**: `DELETE /api/characters/{id}`

**Description**: Delete a character.

**Query Parameters in url** (query parameters):
- `id` (i.e. `/api/characters/4`)


**Response**:

```json
{
  
}
```

### Movies

#### Create a new movie

**Endpoint**: `POST /api/movies`

**Description**: Create a new movie.

**Request**:

```json
{
  "title": "string",
  "image": "string",
  "creationDate": "2020-01-01",
  "rating": 0.0
}
```

**Response**:

```json
{
  "id": 1,
  "title": "string",
  "image": "string",
  "creationDate": "2020-01-01",
  "rating": 0.0
}
```

#### Get all movies

**Endpoint**: `GET /api/movies`

**Description**: Get a list of movies with filters.

**Query Parameters in url** (query parameters):
- `name` (optional) 
- `genre` (optional) (i.e. `genres=1,3`)
- `order` (optional) (options: `ASC` or `DESC`)

**Response**:

```json
[
  {
    "id": 1,
    "title": "string",
    "image": "string",
    "creationDate": "2020-01-01",
    "rating": 0.0
  },
  {}
]
```

#### Get a movie details

**Endpoint**: `GET /api/movies/{id}`

**Description**: Get a movie details.

**Query Parameters in url** (query parameters):
- `id` (i.e. `/api/movies/4`)

**Response**:

```json

  {
    "id": 1,
    "title": "string",
    "image": "string",
    "creationDate": "2020-01-01",
    "rating": 0.0,
    "genres": [],
    "characters": []
  }

```

#### Update a movie

**Endpoint**: `PUT /api/movies/{id}`

**Description**: Update a movie.

**Query Parameters in url** (query parameters):
- `id` (i.e. `/api/movies/4`)

**Request**:

```json
{
  "title": "string",
  "image": "string",
  "creationDate": "2020-01-01",
  "rating": 0.0
}
```

**Response**:

```json
{
  "id": 1,
  "title": "string",
  "image": "string",
  "creationDate": "2020-01-01",
  "rating": 0.0
}
```

#### Delete a movie

**Endpoint**: `DELETE /api/movies/{id}`

**Description**: Delete a movie.

**Query Parameters in url** (query parameters):
- `id` (i.e. `/api/movies/4`)


**Response**:

```json
{
  
}
```

### Series

#### Create a new series

**Endpoint**: `POST /api/series`

**Description**: Create a new series.

**Request**:

```json
{
  "title": "string",
  "image": "string",
  "creationDate": "2020-01-01",
  "rating": 0.0,
  "seasons": 0,
  "episodes": 0
}
```

**Response**:

```json
{
  "id": 1,
  "title": "string",
  "image": "string",
  "creationDate": "2020-01-01",
  "rating": 0.0,
  "seasons": 0,
  "episodes": 0
}
```

#### Get all series

**Endpoint**: `GET /api/series`

**Description**: Get a list of series with filters.

**Query Parameters in url** (query parameters):
- `name` (optional)
- `genre` (optional) (i.e. `genres=1,3`)
- `order` (optional) (options: `ASC` or `DESC`)

**Response**:

```json
[
  {
    "id": 1,
    "title": "string",
    "image": "string",
    "creationDate": "2020-01-01",
    "rating": 0.0,
    "seasons": 0,
    "episodes": 0
  },
  {}
]
```

#### Get a series details

**Endpoint**: `GET /api/series/{id}`

**Description**: Get a series details.

**Query Parameters in url** (query parameters):
- `id` (i.e. `/api/series/4`)

**Response**:

```json
  {
  "id": 1,
  "title": "string",
  "image": "string",
  "creationDate": "2020-01-01",
  "rating": 0.0,
  "seasons": 0,
  "episodes": 0,
  "genres": [],
  "characters": []
}

```

#### Update a series

**Endpoint**: `PUT /api/series/{id}`

**Description**: Update a series.

**Query Parameters in url** (query parameters):
- `id` (i.e. `/api/series/4`)

**Request**:

```json
{
  "title": "string",
  "image": "string",
  "creationDate": "2020-01-01",
  "rating": 0.0,
  "seasons": 0,
  "episodes": 0
}
```

**Response**:

```json
{
  "id": 1,
  "title": "string",
  "image": "string",
  "creationDate": "2020-01-01",
  "rating": 0.0,
  "seasons": 0,
  "episodes": 0
}
```

#### Delete a series

**Endpoint**: `DELETE /api/series/{id}`

**Description**: Delete a series.

**Query Parameters in url** (query parameters):
- `id` (i.e. `/api/series/4`)


**Response**:

```json
{
  
}
```

### Genres

#### Get all genres

**Endpoint**: `GET /api/genres`

**Description**: Get a list of genres.

**Response**:

```json
[
  {
    "id": 1,
    "name": "string",
    "image": "string"
  },
  {}
]
```

#### Get a genre details

**Endpoint**: `GET /api/genres/{id}`

**Description**: Get a genre details.

**Query Parameters in url** (query parameters):
- `id` (i.e. `/api/genres/4`)

**Response**:

```json
{
  "id": 1,
  "name": "string",
  "image": "string",
  "movies": [],
  "series": []
}

```






