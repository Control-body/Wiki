create table `test`(
                       `id` bigint not null comment 'id',
                       `name` varchar(50) comment '名称',
                       `password` varchar(50) comment '密码',
                       primary key (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment ='测试';
select * from test;

create table `demo`(
                       `id` bigint not null comment 'id',
                       `name` varchar(50) comment '名称',
                       primary key (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment ='测试';

create table `ebook`(
    `id` bigint not null comment 'id',
    `name` varchar(50) comment '名称',
    `category1_id` bigint  comment '分类1',
    `category2_id` bigint  comment '分类2',
    `description` varchar(200) comment '描述',
    `cover` varchar(200) comment '封面',
    `doc_count` int comment '文档数',
    `view_count` int comment '阅读数',
    `vote_count` int comment '点赞数',
    primary key (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment ='电子书';

insert into `ebook` (id,name,description) values (1,'ebookExampleBoot 入门教程','零基础入门WEB 必备');
insert into `ebook` (id,name,description) values (2,'Vue 入门教程','零基础入门Vue 必备');
insert into `ebook` (id,name,description) values (3,'pythoon入门教程','零基础入门pythoon 必备');
insert into `ebook` (id,name,description) values (4,'Mysql 入门教程','零基础入门Mysql 必备');
insert into `ebook` (id,name,description) values (5,'Orical 入门教程','零基础入门Orical 必备');