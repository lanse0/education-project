
#教师信息表
create table teacher(
	id int primary key auto_increment comment '主键',
	email varchar(30) unique not null comment '教师邮箱',
	name varchar(10) not null comment '姓名',
	sex tinyint comment '性别',
	birthday date comment '生日',
	header text comment '头像',
	info text comment '个人简介',
	create_time datetime comment '创建时间',
	update_time datetime comment '最后修改时间',
	status tinyint comment '状态',
	del_flag tinyint comment '删除标识'
) comment '教师表';