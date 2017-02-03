# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table contact (
  id                            bigint auto_increment not null,
  first_name                    varchar(255),
  last_name                     varchar(255),
  occupation                    varchar(255),
  company                       varchar(255),
  home_number                   varchar(255),
  mobile_number                 varchar(255),
  relationship                  varchar(255),
  status                        varchar(255),
  comment                       varchar(255),
  constraint pk_contact primary key (id)
);

create table student (
  id                            bigint auto_increment not null,
  first_name                    varchar(255),
  middle_name                   varchar(255),
  last_name                     varchar(255),
  address                       varchar(255),
  dob                           varchar(255),
  birth_certificate             longblob,
  profile_pic                   longblob,
  constraint pk_student primary key (id)
);

create table user (
  id                            bigint auto_increment not null,
  username                      varchar(255),
  password                      varchar(255),
  constraint pk_user primary key (id)
);


# --- !Downs

drop table if exists contact;

drop table if exists student;

drop table if exists user;

