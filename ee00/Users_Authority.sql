create table users(
    username varchar2(50) not null primary key,
    password varchar2(50) not null,
    enabled char(1) default('1')
);

create table authorities(
    username varchar2(50) not null,
    authority varchar2(50) not null,
    CONSTRAINT fk_authorities_users FOREIGN key(username) references users(username)
);

create unique index ix_auth_username on authorities(username, authority);

insert into users(username, password) values('user00', 'pw00');
insert into users(username, password) values('member00', 'pw00');
insert into users(username, password) values('admin00', 'pw00');

insert into AUTHORITIES(username, authority) values('user00', 'ROLE_USER');
insert into AUTHORITIES(username, authority) values('member00', 'ROLE_MEMBER');
insert into AUTHORITIES(username, authority) values('admin00', 'ROLE_ADMIN');
insert into AUTHORITIES(username, authority) values('admin00', 'ROLE_MEMBER');
commit;




create table tbl_member(
      userid varchar2(50) not null primary key,
      userpw varchar2(100) not null,
      username varchar2(100) not null,
      regdate date default sysdate, 
      updatedate date default sysdate,
      enabled char(1) default '1');


create table tbl_member_auth(
     userid varchar2(50) not null,
     auth varchar2(50) not null,
     constraint fk_member_auth foreign key(userid) references tbl_member(userid)
);