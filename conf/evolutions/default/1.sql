# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table sendnum (
  id                        integer not null,
  stotal                    bigint,
  stoday                    bigint,
  sday_max                  bigint,
  sday                      varchar(255),
  constraint pk_sendnum primary key (id))
;

create table sendrecord (
  id                        bigint not null,
  remote_ip                 varchar(255),
  email                     varchar(255),
  status                    boolean,
  create_at                 varchar(255),
  constraint pk_sendrecord primary key (id))
;

create sequence sendnum_seq;

create sequence sendrecord_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists sendnum;

drop table if exists sendrecord;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists sendnum_seq;

drop sequence if exists sendrecord_seq;

