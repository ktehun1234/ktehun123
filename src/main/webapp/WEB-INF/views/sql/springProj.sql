-- 게시판 테이블 생성
create table tbl_board
(
no int not null auto_increment,
title varchar(100) not null,
content text null,
writer varchar(50) not null,
regdate timestamp not null default now(),
viewcnt int default 0,
primary key(no)
)

use test;
-- 댓글 테이블 생성
create table tbl_reply
(
no int not null auto_increment,
bno int not null default 0,
replytext varchar(500) not null,
replyer varchar(50) not null,
regdate timestamp default now(),
updatedate timestamp default now(),
primary key(no)
)

alter table tbl_reply
add constraint fk_board
foreign key (bno) references tbl_board(no);

-- 회원 테이블 생성
create table tbl_user
(
uid varchar(50) not null,
upw varchar(50) not null,
uname varchar(100), 
upoint int(11),
primary key(uid)
)

alter table tbl_user
modify upoint int default 0;

-- message 테이블 생성
create table tbl_message
(
mid int(11) not null auto_increment,
targetid varchar(50),
sender varchar(50),
message varchar(100),
opendate timestamp,
senddate timestamp,
primary key(mid)
)

alter table tbl_message 
add constraint fk_targetid
foreign key(targetid) references tbl_user(uid);

alter table tbl_message 
add constraint fk_sender
foreign key(sender) references tbl_user(uid);

select distinct writer from tbl_board;
select * from tbl_user;

insert into tbl_user(uid, upw, uname)
values('webshjin', '1234', 'iron man');

insert into tbl_user(uid, upw, uname)
values('aaa', '1234', 'captin ame');

insert into tbl_user(uid, upw, uname)
values('bbb', '1234', 'hulk');

insert into tbl_user(uid, upw, uname)
values('ccc', '1234', 'thor');


select distinct replyer from tbl_reply;
select * from tbl_user;


insert into tbl_user(uid, upw, uname)
values('ddd', '1234', '완전');

insert into tbl_user(uid, upw, uname)
values('admin', '1234', 'admin');

delete from tbl_reply where replyer in('도연', 'jwy', 'ktehun', '영광', '박종진', '효어어어어엉');

alter table tbl_board
add constraint fk_writer_tbl
foreign key(writer) references tbl_user(uid);

alter table tbl_reply
add constraint fk_reply_replyer
foreign key(replyer) references tbl_user(uid);

 