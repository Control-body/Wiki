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
