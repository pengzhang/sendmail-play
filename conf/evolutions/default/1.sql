# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table sendnum (
  id                        integer auto_increment not null,
  stotal                    bigint,
  stoday                    bigint,
  sday_max                  bigint,
  sday                      varchar(255),
  constraint pk_sendnum primary key (id))
;

create table sendrecord (
  id                        bigint auto_increment not null,
  remote_ip                 varchar(255),
  email                     varchar(255),
  status                    tinyint(1) default 0,
  create_at                 varchar(255),
  constraint pk_sendrecord primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table sendnum;

drop table sendrecord;

SET FOREIGN_KEY_CHECKS=1;

