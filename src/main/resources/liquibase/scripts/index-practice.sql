-- liquibase formatted sql

-- changeset sdudin: 1
CREATE INDEX name_index ON student (name);

-- changeset sdudin: 2
CREATE INDEX name_color_index ON faculty (name, color);