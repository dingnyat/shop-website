create table if not exists account
(
  id         int auto_increment
    primary key,
  username   varchar(128)               not null,
  password   varchar(512)               not null,
  name       varchar(128) charset utf8  not null,
  address    varchar(1024) charset utf8 not null,
  phone      varchar(32)                not null,
  email      varchar(128)               null,
  avatar_url varchar(1024)              null,
  constraint account_email_uindex
    unique (email),
  constraint account_phone_uindex
    unique (phone),
  constraint account_username_uindex
    unique (username)
);

create table if not exists category
(
  id   int auto_increment
    primary key,
  code varchar(50)              not null,
  name varchar(70) charset utf8 not null,
  constraint category_code_uindex
    unique (code),
  constraint category_name_uindex
    unique (name)
);

create table if not exists parent_category
(
  id        int auto_increment
    primary key,
  parent_id int not null,
  child_id  int not null,
  constraint parent_category_category_id_fk
    foreign key (parent_id) references category (id),
  constraint parent_category_category_id_fk_2
    foreign key (child_id) references category (id)
);

create table if not exists persistent_login
(
  series    varchar(512) not null,
  username  varchar(128) not null,
  token     varchar(512) not null,
  last_used timestamp    not null,
  primary key (series)
);

create table if not exists product
(
  id                int auto_increment
    primary key,
  name              varchar(100) charset utf8 not null,
  quantity          int                       not null,
  price             double                    not null,
  description       varchar(100) charset utf8 null,
  product_file_name varchar(100) charset utf8 not null,
  constraint product_name_uindex
    unique (name)
);

create table if not exists role
(
  id        int auto_increment
    primary key,
  role_name varchar(32) not null,
  constraint role_role_name_uindex
    unique (role_name)
);

create table if not exists account_role
(
  id         int auto_increment
    primary key,
  account_id int not null,
  role_id    int not null,
  constraint account_role_unique_constraint
    unique (account_id, role_id),
  constraint account_role_account_id_fk
    foreign key (account_id) references account (id),
  constraint account_role_role_id_fk
    foreign key (role_id) references role (id)
);

