-- noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE phrases(
  id BIGSERIAL PRIMARY KEY,
  phrase VARCHAR
);

CREATE TABLE totals(
  id VARCHAR PRIMARY KEY,
  total INTEGER
);
