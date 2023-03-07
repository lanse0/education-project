
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

# 课程分类表
CREATE TABLE course_type (
	id INT PRIMARY KEY auto_increment COMMENT '主键',
	pid INT COMMENT '父分类id',
	tname VARCHAR ( 20 ) UNIQUE NOT NULL COMMENT '分类名称',
	tag VARCHAR ( 100 ) COMMENT '分类标签 存储上面级分类的id 如：00010002',
	create_time datetime COMMENT '创建时间',
	update_time datetime COMMENT '最后修改时间',
	STATUS TINYINT COMMENT '状态',
	del_flag TINYINT COMMENT '删除标识'
) COMMENT '课程分类表';
#课程分类和规格关联表
create table course_type_guige(
	tid int comment '课程分类ID',
	gid int comment '分类规格ID',
	create_time datetime COMMENT '创建时间',
	update_time datetime COMMENT '最后修改时间',
	STATUS TINYINT COMMENT '状态',
	del_flag TINYINT COMMENT '删除标识',
	primary key(tid,gid)
) comment '课程分类和规格关联表';
#课程规格表
CREATE TABLE course_guige (
	id INT PRIMARY KEY auto_increment COMMENT '主键',
	gname VARCHAR ( 20 ) NOT NULL COMMENT '规格名称',
	info VARCHAR ( 50 ) COMMENT '规格备注',
	create_time datetime COMMENT '创建时间',
	update_time datetime COMMENT '最后修改时间',
	STATUS TINYINT COMMENT '状态',
	del_flag TINYINT COMMENT '删除标识'
) COMMENT '课程分类的规格表';
#具体规格可选项表
CREATE TABLE course_guige_val (
	id INT PRIMARY KEY auto_increment COMMENT '主键',
	gid INT NOT NULL COMMENT '规格id',
	val VARCHAR ( 50 ) NOT NULL COMMENT '规格值',
	create_time datetime COMMENT '创建时间',
	update_time datetime COMMENT '最后修改时间',
	STATUS TINYINT COMMENT '状态',
	del_flag TINYINT COMMENT '删除标识'
) COMMENT '具体规格可选项表';