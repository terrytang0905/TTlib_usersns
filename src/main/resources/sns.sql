create database lnsns character set utf8;
use lnsns;

/** ALL TABLES 13 **/

/*==============================================================*/
/* Table: sns_user                                              */
/*==============================================================*/
drop table if exists sns_user;
create table sns_user
(
   id                   int(20) auto_increment not null,
   loginName            varchar(60) not null,
   passWord             varchar(100),
   userType             int(1) not null,
   status               int(1) not null,
   shortID              varchar(10),
   nickName             varchar(60),
   name                 varchar(60),
   gender               char(1),
   birthday             datetime,
   email                varchar(60),
   qq                   varchar(15),
   msn                  varchar(60),
   phone                varchar(20),
   zip                  varchar(10),
   address              varchar(200),
   hometown             varchar(200),
   location             varchar(200),
   bloodType            char(1),
   juniorHighSchool     varchar(100),
   seniorHighSchool     varchar(100),
   college              varchar(100),
   workUnit             varchar(100),
   description          varchar(200),
   photo                varchar(100),
   lastLoginTime        datetime,
   lastOperateTime      datetime,
   primary key (id)
) ENGINE=InnoDB;
alter table sns_user comment 'supernote sns user info';


/*==============================================================*/
/* Table: sns_account                                           */
/*==============================================================*/
drop table if exists sns_account;
create table sns_account
(
   id                   int(20) auto_increment not null,
   userID               int(20) not null,
   account              varchar(100) not null,
   status               int(1) not null,
   source               int(1),
   createTime           datetime,
   subSource 			varchar(50),
   UNIQUE KEY sns_account.account (account,source),
   primary key (id)
) ENGINE=InnoDB;
alter table sns_account comment 'supernote sns account';


/*==============================================================*/
/* Table: sns_friend_req                                        */
/*==============================================================*/
drop table if exists sns_friend_req;
create table sns_friend_req
(
   id                   int(20) auto_increment not null,
   userID               int(20) not null,
   type                 int(1),
   account              varchar(60),
   status               int(1),
   requestTime          datetime,
   responseTime         datetime,
   primary key (id)
) ENGINE=InnoDB;
alter table sns_friend_req comment 'supernote friend req';


/*==============================================================*/
/* Table: sns_friend_group                                      */
/*==============================================================*/
drop table if exists sns_friend_group;
create table sns_friend_group
(
   id                   int(20) auto_increment not null,
   userID               int(20) not null,
   name                 varchar(60),
   status               int(1),
   createTime           datetime,
   primary key (id)
) ENGINE=InnoDB;
alter table sns_friend_group comment 'supernote friend group';


/*==============================================================*/
/* Table: sns_friend_group_user                                 */
/*==============================================================*/
drop table if exists sns_friend_group_user;
create table sns_friend_group_user
(
   id                   int(20) auto_increment not null,
   groupID              int(20) not null,
   friendID             int(20) not null,
   userID               int(20),
   status               int(1),
   createTime           datetime,
   primary key (id)
) ENGINE=InnoDB;
alter table sns_friend_group_user comment 'supernote friend group user';


/*==============================================================*/
/* Table: sns_friend                                            */
/*==============================================================*/
drop table if exists sns_friend;
create table sns_friend
(
   id                   int(20) auto_increment not null,
   userID1              int(20) not null,
   userID2              int(20) not null,
   status               int(1) not null,
   startTime            datetime,
   endTime              datetime,
   primary key (id)
) ENGINE=InnoDB;
alter table sns_friend comment 'supernote sns friend info';


/*==============================================================*/
/* Table: sns_attention                                         */
/*==============================================================*/
drop table if exists sns_attention;
create table sns_attention
(
   id                   int(20) auto_increment not null,
   userID               int(20) not null,
   attendedUserID       int(20) not null,
   status               int(1) not null,
   startTime            datetime,
   endTime              datetime,
   primary key (id)
) ENGINE=InnoDB;
alter table sns_attention comment 'supernote user attention';


/*==============================================================*/
/* Table: sns_group                                             */
/*==============================================================*/
drop table if exists sns_group;
create table sns_group
(
   id                   int(20) auto_increment not null,
   name                 varchar(60) not null,
   status               int(1),
   type                 int(1),
   needAuth             int(1),
   description          varchar(200),
   notice               varchar(500),
   icon                 varchar(200),
   creator              int(20),
   createTime           datetime,
   primary key (id)
) ENGINE=InnoDB;
alter table sns_group comment 'supernote sns group';


/*==============================================================*/
/* Table: sns_group_req                                         */
/*==============================================================*/
drop table if exists sns_group_req;
create table sns_group_req
(
   id                   int(20) auto_increment not null,
   groupID              int(20) not null,
   type                 int(1) not null,
   account              varchar(60) not null,
   status               int(1) not null,
   direction            int(1),
   validateText         varchar(200),
   requestTime          datetime,
   responseTime         datetime,
   responseUserID       int(20),
   primary key (id)
) ENGINE=InnoDB;
alter table sns_group_req comment 'supernote sns group  req';


/*==============================================================*/
/* Table: sns_group_user                                        */
/*==============================================================*/
drop table if exists sns_group_user;
create table sns_group_user
(
   id                   int(20) auto_increment not null,
   groupID              int(20) not null,
   userID               int(20) not null,
   role                 int(1),
   displayName          varchar(60),
   gender               int(1),
   phone                varchar(20),
   email                varchar(60),
   description          varchar(200),
   joinTime             datetime,
   primary key (id)
) ENGINE=InnoDB;
alter table sns_group_user comment 'supernote sns group user';



/*==============================================================*/
/* Table: sns_message                                           */
/*==============================================================*/
drop table if exists sns_message;
create table sns_message
(
   id                   int(20) auto_increment not null,
   userID               int(20) not null,
   receiverID           int(20) not null, 
   title                varchar(200),
   content              varchar(2000),
   scope                int(1),
   sendTime             datetime,
   validTime            datetime,
   primary key (id)
) ENGINE=InnoDB;
alter table sns_message comment 'supernote sns system message';


/*==============================================================*/
/* Table: sns_message_read                                      */
/*==============================================================*/
drop table if exist ssns_message_read;
create table sns_message_read
(
   id                   int(20) auto_increment not null,
   userID               int(20) not null,
   messageID            int(20) not null,
   readTime             datetime,
   primary key (id)
) ENGINE=InnoDB;
alter table sns_message_read comment 'supernote sns message read';


/*==============================================================*/
/* Table: sns_message_delete                                    */
/*==============================================================*/
drop table if exist ssns_message_delete;
create table sns_message_delete
(
   id                   int(20) auto_increment not null,
   userID               int(20) not null,
   messageID            int(20) not null,
   deleteTime           datetime,
   primary key (id)
) ENGINE=InnoDB;
alter table sns_message_delete comment 'supernote sns message delete';


/*==============================================================*/
/* Table: sns_secure_app										*/
/*==============================================================*/
drop table if exists sns_secure_app;
create table sns_secure_app
(
   id                   int(8) auto_increment not null,
   name                 varchar(200),
   description          varchar(1000),
   status               int(1),   
   create_user          int(8),
   create_time          datetime,
   update_user          int(8),
   update_time          datetime,
   primary key (id)
);
alter table sns_secure_app comment 'secure app';
insert into sns_secure_app values (1, "乐云记事", "乐云记事", 1, 1, now(), null, null);

/*==============================================================*/
/* Table: sns_secure_app_client									*/
/*==============================================================*/
drop table if exists sns_secure_app_client;
create table sns_secure_app_client
(
   id                   int(8) auto_increment not null,
   app_id               int(8),
   group_id             int(8),
   name                 varchar(200),
   description          varchar(1000),
   client_key           varchar(200),
   secret               varchar(200),
   status               int(1),
   create_user          int(8),
   create_time          datetime,
   update_user          int(8),
   update_time          datetime,
   primary key (id)
);
alter table sns_secure_app_client comment 'secure app client';
insert into sns_secure_app_client values(1, 1, 1, "乐云记事SNS系统", "乐云记事SNS系统", "lenote-sns", "100ef58e179c85ddf4dc686a475d1880", 1, 1, now(),null, null);
insert into sns_secure_app_client values(2, 1, 1, "乐云记事File系统", "乐云记事File系统", "lenote-file", "8be028a20339997d1062efc1539a6b0e", 1, 1, now(),null, null);
insert into sns_secure_app_client values(3, 1, 1, "乐云记事Search系统", "乐云记事Search系统", "lenote-search", "d40562a226681b0f291ad6848022e5d8", 1, 1, now(),null, null);
insert into sns_secure_app_client values(4, 1, 1, "Android Phone", "Android Phone", "lenote-androidPhone", "fe84eed777e1a9b7ceda6cf6908f7924", 1, 1, now(),null, null);
insert into sns_secure_app_client values(5, 1, 1, "Android Pad", "Android Pad", "lenote-androidPad", "bb2b25861d197393d39a5bd16cefbd8d", 1, 1, now(),null, null);
insert into sns_secure_app_client values(6, 1, 1, "PC", "PC", "lenote-pc", "b052658dd4949aac64fdee3f69004a65", 1, 1, now(),null, null);
insert into sns_secure_app_client values(7, 1, 1, "Iphone", "Iphone", "lenote-iphone", "a81a0107ad607364bc2139487e08ee69", 1, 1, now(),null, null);
insert into sns_secure_app_client values(8, 1, 1, "Ipad", "lenote-ipad", "lenote-ipad", "c3669073739fe786b444a24fcc5d3f24", 1, 1, now(),null, null);
insert into sns_secure_app_client values(9, 1, 1, "一键保存", "lenote-clickSave", "lenote-clickSave", "9eb4ee1e614eb2aad4323a82b59314fd", 1, 1, now(),null, null);


/*==============================================================*/
/* Table: sns_secure_group										*/
/*==============================================================*/
drop table if exists sns_secure_group;
create table sns_secure_group
(
   id                   int(8) auto_increment not null,
   name                 varchar(200),
   status               int(1),
   type                 int(1),
   create_user		    int(8),
   create_time          datetime,
   update_user          int(8),
   update_time          datetime,
   primary key (id)
);
alter table sns_secure_group comment 'secure client group';

insert sns_secure_group values(1, "全部权限", 0, 0, 1, now(), null, null);


/*==============================================================*/
/* Table: sns_secure_group_access								*/
/*==============================================================*/
drop table if exists sns_secure_group_access;
create table sns_secure_group_access
(
   id                   int(8) auto_increment not null,
   group_id             int(8),
   name                 varchar(200),
   url                  varchar(1000),
   service              varchar(200),
   status               int(1),  
   create_user          int(8),
   create_time          datetime,
   update_user          int(8),
   update_time          datetime,
   primary key (id)
);
alter table sns_secure_group_access comment 'secure client group access';
