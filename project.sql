
-- 教师信息表
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

-- 课程分类表
CREATE TABLE course_type (
	id INT PRIMARY KEY auto_increment COMMENT '主键',
	pid INT COMMENT '父分类id',
	tname VARCHAR ( 20 ) UNIQUE NOT NULL COMMENT '分类名称',
	tag VARCHAR ( 100 ) COMMENT '分类标签 存储上面级分类的id 如：00010002',
	create_time datetime COMMENT '创建时间',
	update_time datetime COMMENT '最后修改时间',
	status TINYINT COMMENT '状态',
	del_flag TINYINT COMMENT '删除标识'
) COMMENT '课程分类表';
-- 课程分类和规格关联表
create table course_type_guige(
	tid int comment '课程分类ID',
	gid int comment '分类规格ID',
	create_time datetime COMMENT '创建时间',
	update_time datetime COMMENT '最后修改时间',
	status TINYINT COMMENT '状态',
	del_flag TINYINT COMMENT '删除标识',
	primary key(tid,gid)
) comment '课程分类和规格关联表';
-- 课程规格表
CREATE TABLE course_guige (
	id INT PRIMARY KEY auto_increment COMMENT '主键',
	gname VARCHAR ( 20 ) NOT NULL COMMENT '规格名称',
	info VARCHAR ( 50 ) COMMENT '规格备注',
	create_time datetime COMMENT '创建时间',
	update_time datetime COMMENT '最后修改时间',
	status TINYINT COMMENT '状态',
	del_flag TINYINT COMMENT '删除标识'
) COMMENT '课程分类的规格表';
-- 具体规格可选项表
CREATE TABLE course_guige_val (
	id INT PRIMARY KEY auto_increment COMMENT '主键',
	gid INT NOT NULL COMMENT '规格id',
	val VARCHAR ( 50 ) NOT NULL COMMENT '规格值',
	create_time datetime COMMENT '创建时间',
	update_time datetime COMMENT '最后修改时间',
	status TINYINT COMMENT '状态',
	del_flag TINYINT COMMENT '删除标识'
) COMMENT '具体规格可选项表';

-- 课程信息表
create table course_info(
	id int primary key auto_increment comment '主键',
	teacher_id int not null comment '申请教师的ID - 外键(teacher表id)',
	tid int not null comment '课程分类的id - 外键(course_type表id)',
	subject varchar(100) not null comment '课程标题',
	fengmian varchar(100) not null comment '课程封面',
	info text comment '课程简介',
	price decimal(10,2) not null comment '课程金额 -1 为免费',  #最长10尾 小数点后2位
	begin_time datetime comment '课程开始i时间',
	end_time datetime comment '课程结束时间',
	create_time datetime COMMENT '创建时间',
	update_time datetime COMMENT '最后修改时间',
	status TINYINT COMMENT '状态 0-待审核 1-审核通过 2-审核驳回',
	del_flag TINYINT COMMENT '删除标识'
) comment '课程信息表';
-- 课程详细计划表(课程小节)
create table course_desc_info(
	id int primary key auto_increment comment '主键',
	cid int not null comment '所属课程的外键',
	title varchar(20) not null comment '课程详情标题',
	begin_time datetime comment '课程开始时间',
	end_time datetime comment '课程结束时间',
	type tinyint not null comment '章节类型 0-收费 1-免费试听',
	create_time datetime COMMENT '创建时间',
	update_time datetime COMMENT '最后修改时间',
	status TINYINT COMMENT '状态 0-待审核 1-审核通过 2-审核驳回',
	del_flag TINYINT COMMENT '删除标识'
) comment '课程详细计划表(小节)';
-- 课程信息规格关联信息
create table course_info_guige(
	cid int not null comment '课程id',
	cgid int not null comment '规格id',
	cgvid int not null comment '规格具体的值的id',
	create_time datetime COMMENT '创建时间',
	update_time datetime COMMENT '最后修改时间',
	status TINYINT COMMENT '状态 0-待审核 1-审核通过 2-审核驳回',
	del_flag TINYINT COMMENT '删除标识',
	PRIMARY key (cid,cgid,cgvid)
) comment '课程信息和规格关联表';

-- 小程序端的用户表信息
create table wx_user(
	id int primary key auto_increment comment '主键',
	openid varchar(50) unique not null comment '小程序端的开发id',
	unionid varchar(50) unique comment '小程序端的用户在开放平台下的唯一id',
	session_key varchar(50) not null comment '会话密钥',
	nickname varchar(20) comment '小程序端的用户昵称',
	header text comment '小程序端的头像',
	create_time datetime COMMENT '创建时间',
	update_time datetime COMMENT '最后修改时间',
	status TINYINT COMMENT '状态',
	del_flag TINYINT COMMENT '删除标识'
) comment '小程序端的用户表';

-- 给微信用户表添加一个字段 积分
alter table wx_user add column score int not null default 0 comment '用户积分';

-- 积分流水表
create table wx_score_details(
	id int primary key auto_increment comment '主键',
	uid int not null comment '用户id',
	source_score int not null comment '原始积分',
	action_score int not null comment '操作积分',
	target tinyint not null comment '积分来源 0-发红包 1-抢红包 2-充值 3-活动 ... ',
	busid int comment '业务id',
	create_time datetime COMMENT '创建时间',
	update_time datetime COMMENT '最后修改时间',
	status TINYINT COMMENT '状态',
	del_flag TINYINT COMMENT '删除标识'
) comment '积分流水表';

-- 红包表
create table red_envelopes(
	id int primary key auto_increment comment '红包id',
	uid int not null comment '发送者id',
	scount int not null comment '红包的积分总额 - 红包有多少积分',
	number int not null comment '红包的份数',
	type tinyint not null comment '红包的类型 0-固定 1-随机',
	info varchar(20) comment '红包信息',
	timeout datetime comment '红包过期时间',
	create_time datetime COMMENT '创建时间',
	update_time datetime COMMENT '最后修改时间',
	status TINYINT COMMENT '状态 0-待领取 1-领取完 2-红包过期',
	del_flag TINYINT COMMENT '删除标识'
) comment '红包表';

-- 红包详情表
create table red_envelopes_details(
	id int primary key auto_increment comment '红包明细id',
	redid int not null comment '红包的id',
	uid int not null comment '领取者id',
	score int not null comment '抢到的积分值',
	type tinyint not null comment '领取类型 0-抢红包 1-红包回退',
	create_time datetime COMMENT '创建时间',
	update_time datetime COMMENT '最后修改时间',
	status TINYINT COMMENT '状态',
	del_flag TINYINT COMMENT '删除标识'
) comment '红包明细表';


-- 回滚日志表
-- 注意此处0.7.0+ 增加字段 context
CREATE TABLE `undo_log` (
	`id` BIGINT ( 20 ) NOT NULL auto_increment,
	`branch_id` BIGINT ( 20 ) NOT NULL,
	`xid` VARCHAR ( 100 ) NOT NULL,
	`context` VARCHAR ( 128 ) NOT NULL,
	`rollback_info` LONGBLOB NOT NULL,
	`log_status` INT ( 11 ) NOT NULL,
	`log_created` datetime NOT NULL,
	`log_modified` datetime NOT NULL,
	PRIMARY KEY ( `id` ),
UNIQUE KEY `ux_undo_log` ( `xid`, `branch_id` )
) ENGINE = INNODB auto_increment = 1 DEFAULT charset = utf8;