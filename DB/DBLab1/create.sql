CREATE TYPE star_colors AS ENUM ('коричневый', 'красный', 'оранжевый', 'жёлтый', 'жёлто-белый', 'белый', 'бело-голубой', 'голубой', 'звезда Вольфа-Райе');
CREATE TYPE star_types AS ENUM ('карлик', 'гигант', 'сверхгигант', 'сверхновая');
CREATE TABLE human (passport_id CHAR(6) PRIMARY KEY, name VARCHAR(50) NOT NULL, surname VARCHAR(50) NOT NULL, last_name VARCHAR(50), birth_date TIMESTAMP NOT NULL);
CREATE TABLE galaxy (galaxy_name VARCHAR(50) PRIMARY KEY, galaxy_size VARCHAR(10) NOT NULL);
CREATE TABLE nebula (nebula_name VARCHAR(50) PRIMARY KEY, galaxy_name VARCHAR(50) REFERENCES galaxy(galaxy_name), nebula_size VARCHAR(10) NOT NULL);
CREATE TABLE star (star_name VARCHAR(50) PRIMARY KEY, nebula_name VARCHAR(50) REFERENCES nebula(nebula_name), star_type star_types NOT NULL DEFAULT 'карлик', star_color star_colors NOT NULL DEFAULT 'красный');
CREATE TABLE planet (planet_name VARCHAR(50) PRIMARY KEY, star_name VARCHAR(50) REFERENCES star(star_name), planet_size REAL NOT NULL, planet_atmosphere BOOL, planet_life BOOL);
CREATE TABLE star_collision (id SERIAL PRIMARY KEY, star_name VARCHAR(50) REFERENCES star(star_name), human CHAR(6) REFERENCES human(passport_id), collision_time TIMESTAMP NOT NULL);
CREATE TABLE frame (frame_id SERIAL PRIMARY KEY, human CHAR(6) REFERENCES human(passport_id), galaxy_name VARCHAR(50) REFERENCES galaxy(galaxy_name), creation_time DATE NOT NULL);
