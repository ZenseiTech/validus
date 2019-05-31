CREATE TABLE album (
    id   BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    year_released INTEGER NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE artist (
    id   BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE artist_albums (
    artist_id BIGINT,
    albums_id BIGINT,
    PRIMARY KEY (artist_id, albums_id)
);

CREATE TABLE song (
    id   BIGINT NOT NULL AUTO_INCREMENT,
    album_id BIGINT,
    track INTEGER NOT NULL,
    name VARCHAR(128) NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);