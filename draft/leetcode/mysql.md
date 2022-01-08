[TOC]
# 安装 
## win
>https://dev.mysql.com/downloads/mysql/
[http://mirrors.sohu.com/mysql/MySQL-8.0/](http://mirrors.sohu.com/mysql/MySQL-8.0/)
配置环境变量path
![](https://upload-images.jianshu.io/upload_images/18339009-6c5c4e1ade3e95ac.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/18339009-b6f751a6e0727c08.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
新建一个my.ini 用记事本打开
```js
[mysqld]
# 设置mysql的安装目录
basedir=D:\\software\\java\\mysql-5.7.28-winx64
# 切记此处一定要用双斜杠\\，单斜杠这里会出错。
# 设置mysql数据库的数据的存放目录
datadir=D:\\software\\java\\mysql-5.7.28-winx64\\Data
# 此处同上
# 允许最大连接数
max_connections=200
# 允许连接失败的次数。这是为了防止有人从该主机试图攻击数据库系统
max_connect_errors=10
# 服务端使用的字符集默认为UTF8
character-set-server=utf8
# 创建新表时将使用的默认存储引擎
default-storage-engine=INNODB
# 默认使用“mysql_native_password”插件认证
default_authentication_plugin=mysql_native_password
[mysql]
# 设置mysql客户端默认字符集
default-character-set=utf8
[client]
# 设置mysql客户端连接服务端时默认使用的端口
port=3306
default-character-set=utf8
```
**管理员权限**下运行cmd

```
mysqld -install
# 执行初始化代码（会在根目录创建data文件夹，并创建root用户）
mysqld --initialize-insecure --user=mysql
# 启动mysql服务
net start mysql
# 关闭服务
net stop mysql
```
>![](https://upload-images.jianshu.io/upload_images/18339009-3b3965a4ecafa1f0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
>


##  centos

```
下载命令：
wget https://dev.mysql.com/get/mysql80-community-release-el7-2.noarch.rpm
用 yum 命令安装下载好的 rpm 包.
yum -y install mysql80-community-release-el7-2.noarch.rpm
安装 MySQL 服务器.
yum -y install mysql-community-server

启动 MySQL
systemctl start  mysqld.service
查看 MySQL 运行状态, Active 后面的状态代表启功服务后为 active (running), 停止后为 inactive (dead), 运行状态如图：
systemctl status mysqld.service

重新启动 Mysql 和停止 Mysql 的命令如下：
service mysqld restart  #重新启动 Mysql
systemctl stop mysqld.service   #停止 Mysql

此时 MySQL 已经开始正常运行, 不过要想进入 MySQL 还得先找出此时 root 用户的密码, 通过如下命令可以在日志文件中找出密码： 为了加强安全性, MySQL8.0 为 root 用户随机生成了一个密码, 在 error log 中, 关于 error log 的位置, 如果安装的是 RPM 包, 则默认是/var/log/mysqld.log.  只有启动过一次 mysql 才可以查看临时密码
使用命令:
grep 'temporary password' /var/log/mysqld.log

登录 root 用户
mysql -u root -p
修改密码为"123", 注意结尾要有分号, 表示语句的结束.
ALTER USER 'root'@'localhost' IDENTIFIED BY '123';

MySQL 完整的初始密码规则可以通过如下命令查看：
SHOW VARIABLES LIKE 'validate_password%'
如果想要设置简单的密码必须要修改约束, 修改两个全局参数：
validate_password_policy代表密码策略, 默认是 1：符合长度, 且必须含有数字, 小写或大写字母, 特殊字符. 设置为 0 判断密码的标准就基于密码的长度了. 一定要先修改两个参数再修改密码
set global validate_password.policy=0;
validate_password_length代表密码长度, 最小值为 4
set global validate_password.length=4; 

但此时还有一个问题, 就是因为安装了 Yum Repository, 以后每次 yum 操作都会自动更新, 如不需要更新, 可以把这个 yum 卸载掉：
[root@localhost ~]# yum -y remove mysql80-community-release-el7-2.noarch

设置表名为大小写不敏感:
1.用root帐号登录, 使用命令
systemctl stop mysqld.service
停止MySQL数据库服务，修改vi /etc/my.cnf，在[mysqld]下面添加
 lower_case_table_names=1
这里的参数 0 表示区分大小写，1 表示不区分大小写. 2.做好数据备份，然后使用下述命令删除数据库数据(删除后, 数据库将恢复到刚安装的状态)
rm -rf /var/lib/mysql
3.重启数据库 ,此时数据库恢复到初始状态.
service mysql start
4.重复安装时的操作, 查找临时密码
grep 'temporary password' /var/log/mysqld.log
5.修改密码. 密码8位以上, 大小写符号数据. 如想要使用简单密码, 需按照上述安装流程中的步骤进行操作.
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '****';update user set host = "%" where user='root';
6.刷新MySQL的系统权限相关表
FLUSH PRIVILEGES;
此时, MySQL的表名的大小写不再敏感.

设置表名为大小写不敏感:
1.用root帐号登录后, 使用命令
systemctl stop mysqld.service
停止MySQL数据库服务，修改vi /etc/my.cnf，在[mysqld]下面添加
 lower_case_table_names=1
这里的参数 0 表示区分大小写，1 表示不区分大小写. 2.做好数据备份，然后使用下述命令删除数据库数据(删除后, 数据库将恢复到刚安装的状态)
rm -rf /var/lib/mysql
3.重启数据库 ,此时数据库恢复到初始状态.
service mysql start
4.重复安装时的操作, 查找临时密码
grep 'temporary password' /var/log/mysqld.log
5.修改密码. 密码8位以上, 大小写符号数据. 如想要使用简单密码, 需按照上述安装流程中的步骤进行操作.
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '****';update user set host = "%" where user='root';
6.刷新MySQL的系统权限相关表
FLUSH PRIVILEGES;
此时, MySQL的表名的大小写不再敏感.
```



## ubuntu
```
sudo apt-get install mysql-server
#sudo apt-get install mysql-client
```
**配置远程连接**

```
1.将bind-address = 127.0.0.1注释掉
sudo vim /etc/mysql/mysql.conf.d/mysqld.cnf
2.登录进数据库：
mysql -uroot -p123456
3.然后，切换到数据库mysql。SQL如下：
use mysql;
4.删除匿名用户。SQL如下：
delete from user where user='';
5.给root授予在任意主机（%）访问任意数据库的所有权限。SQL语句如下：
create user 'root'@'%' identified by '123456';
grant all privileges on *.* to 'root'@'%';
ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY '123456'; 
6.退出数据库
exit
7.重启数据库：
sudo service mysql restart
```

**删除 mysql**

```
sudo apt-get autoremove --purge mysql-server
sudo apt-get remove mysql-server
sudo apt-get autoremove mysql-server
sudo apt-get remove mysql-common
```
**清理残留数据**
```
dpkg -l |grep ^rc|awk '{print $2}' |sudo xargs dpkg -P
```

进入mysql
```
sudo mysql -uroot -p 
```
配置 MySQL 的管理员密码：
```
sudo mysqladmin -u root password newpassword
```

安装MySQL-workbench
```
sudo apt-get install mysql-workbench
```
一旦安装完成，MySQL 服务器应该自动启动。您可以在终端提示符后运行以下命令来检查 MySQL 服务器是否正在运行：
```
sudo netstat -tap | grep mysql
```
当您运行该命令时，您可以看到类似下面的行：
tcp 0 0 localhost.localdomain:mysql *:* LISTEN -
如果服务器不能正常运行，您可以通过下列命令启动它：
```
sudo /etc/init.d/mysql restart
```

# DBMS的种类

用来管理数据库的计算机系统称为数据库管理系统（Database Management System，DBMS）。

DBMS 主要通过数据的保存格式（数据库的种类）来进行分类，现阶段主要有以下 5 种类型.

- 层次数据库（Hierarchical Database，HDB）

- 关系数据库（Relational Database，RDB）

  - Oracle Database：甲骨文公司的RDBMS
  - SQL Server：微软公司的RDBMS
  - DB2：IBM公司的RDBMS
  - PostgreSQL：开源的RDBMS
  - MySQL：开源的RDBMS

  如上是5种具有代表性的RDBMS，其特点是由行和列组成的二维表来管理数据，这种类型的 DBMS 称为关系数据库管理系统（Relational Database Management System，RDBMS）。

- 面向对象数据库（Object Oriented Database，OODB）

- XML数据库（XML Database，XMLDB）

- 键值存储系统（Key-Value Store，KVS），举例：MongoDB



# 基本书写规则

- SQL语句要以分号（ ; ）结尾
- SQL 不区分关键字的大小写，但是插入到表中的数据是区分大小写的
- win 系统默认不区分表名及字段名的大小写
- linux / mac 默认严格区分表名及字段名的大小写 * 本教程已统一调整表名及字段名的为小写，以方便初学者学习使用。
- 注释是SQL语句中用来标识说明或者注意事项的部分。分为1行注释"-- "和多行注释两种"/* */"。


## 数据类型的指定

数据库创建的表，所有的列都必须指定数据类型，每一列都不能存储与该列数据类型不符的数据。

四种最基本的数据类型

- INTEGER 型

用来指定存储整数的列的数据类型（数字型），不能存储小数。

- CHAR 型

用来存储定长字符串，当列中存储的字符串长度达不到最大长度的时候，使用半角空格进行补足，由于会浪费存储空间，所以一般不使用。

- VARCHAR 型

用来存储可变长度字符串，定长字符串在字符数未达到最大长度时会用半角空格补足，但可变长字符串不同，即使字符数未达到最大长度，也不会用半角空格补足。

- DATE 型

用来指定存储日期（年月日）的列的数据类型（日期型）。

## 约束的设置

约束是除了数据类型之外，对列中存储的数据进行限制或者追加条件的功能。

`NOT NULL`是非空约束，即该列必须输入数据。

`PRIMARY KEY`是主键约束，代表该列是唯一值，可以通过该列取出特定的行的数据。

## 执行顺序

在加入窗口函数的基础上SQL的执行顺序也会发生变化，具体的执行顺序如下（window就是窗口函数）
![](https://upload-images.jianshu.io/upload_images/18339009-cd689024f076a36a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## DDL

DDL（Data Definition Language，数据定义语言） 用来**创建或者删除存储数据用的数据库以及数据库中的表**等对象。DDL 包含以下几种指令。

- CREATE ： 创建数据库和表等对象
- DROP ： 删除数据库和表等对象
- ALTER ： 修改数据库和表等对象的结构

## DML

DML（Data Manipulation Language，数据操纵语言） 用来**查询或者变更**表中的记录。DML 包含以下几种指令。

- SELECT ：查询表中的数据
- INSERT ：向表中插入新数据
- UPDATE ：更新表中的数据
- DELETE ：删除表中的数据

## DCL

DCL（Data Control Language，数据控制语言） 用来**确认或者取消对数据库中的数据进行的变更**。除此之外，还可以对 RDBMS 的用户是否有权限操作数据库中的对象（数据库表等）进行设定。DCL 包含以下几种指令。

- COMMIT ： 确认对数据库中的数据进行的变更
- ROLLBACK ： 取消对数据库中的数据进行的变更
- GRANT ： 赋予用户操作权限
- REVOKE ： 取消用户的操作权限



# 增删改查库

## 增加(CREATE)
```sql
CREATE DATABASE < 数据库名称 > default character utf8;
```
## 删除(DROP)
```sql
DROP DATABASE 库名 ;
```
## 修改(ALTER) 
```sql
ALTER DATABASE 库名  default character gbk;
```
## 查询(SHOW)
```sql
SHOW DATABASE 
```
## 选择数据库
```sql
USE 库名 ;
```

# 增删改查表


## 新建表(CREATE) 
```sql
CREATE TABLE < 表名 >
( < 列名 1> < 数据类型 > < 该列所需约束 > ,
  < 列名 2> < 数据类型 > < 该列所需约束 > ,
  < 列名 3> < 数据类型 > < 该列所需约束 > ,
  < 列名 4> < 数据类型 > < 该列所需约束 > ,
  < 该表的约束 1> , < 该表的约束 2> ,……);
  
CREATE TABLE 表名 (
  id int(10) unsigned NOT NULL COMMENT 'Id',
  username varchar(64) NOT NULL DEFAULT 'default' COMMENT '用户名',
  password varchar(64) NOT NULL DEFAULT 'default' COMMENT '密码',
  email varchar(64) NOT NULL DEFAULT 'default' COMMENT '邮箱'
) COMMENT='用户表';

```
**复制表**

```sql
CREATE TABLE 新表
AS
SELECT * FROM 旧表 
```

## 删除(DROP)

ALTER TABLE 语句和 DROP TABLE 语句一样，执行之后无法恢复。

```sql
DROP TABLE user;
```
## 修改表名称(ALTER  RENAME)
``` sql
ALTER TABLE user RENAME user_new;
```
# 增删改查字段

## 添加字段(ALTER ADD COLUMN)
```sql
ALTER TABLE user ADD COLUMN age int(3);
```

## 删除字段(ALTER DROP COLUMN)  
```sql
ALTER TABLE user DROP COLUMN age;
```
## 修改字段类型(ALTER MODIFY)
```sql
ALTER TABLE user MODIFY 字段名 新的字段类型;
```
mysql 设置字段 not null 变成可以null

```sql
ALTER TABLE 表名 MODIFY 字段名 VARCHAR(20) DEFAULT NULL
```
## 修改字段名称(ALTER CHANGE)
``` sql
ALTER TABLE user CHANGE 旧字段名 新字段名 字段类型;
```

## 显示表字段信息
```sql
DESC user;
```

# 管理数据
## 添加主键(ALTER ADD PRIMARY KEY)
```sql
ALTER TABLE user ADD PRIMARY KEY (id);
```
**删除主键**(ALTER DROP PRIMARY KEY)

```sql
ALTER TABLE user DROP PRIMARY KEY;
```
查询表的大小：
```sql
use information_schema;  
select data_length,index_length 
from tables 
where table_schema=库 and table_name = 表 ;
```

## 添加数据(INSERT INTO)
插入完整的行
```sql
INSERT INTO user VALUES (10, 'root', 'root', 'xxxx@163.com');

-- 多行INSERT （ DB2、SQL、SQL Server、 PostgreSQL 和 MySQL多行插入）
INSERT INTO productins VALUES ('0002', '打孔器', '办公用品', 500, 320, '2009-09-11'),
                              ('0003', '运动T恤', '衣服', 4000, 2800, NULL),
                              ('0004', '菜刀', '厨房用具', 3000, 2800, '2009-09-20');  
-- Oracle中的多行INSERT
INSERT ALL INTO productins VALUES ('0002', '打孔器', '办公用品', 500, 320, '2009-09-11')
           INTO productins VALUES ('0003', '运动T恤', '衣服', 4000, 2800, NULL)
           INTO productins VALUES ('0004', '菜刀', '厨房用具', 3000, 2800, '2009-09-20')
SELECT * FROM DUAL;  
-- DUAL是Oracle特有（安装时的必选项）的一种临时表A。因此“SELECT *FROM DUAL” 部分也只是临时性的，并没有实际意义。  
```
插入行的一部分
```sql
INSERT INTO user(username, password, email) VALUES ('admin', 'admin', 'xxxx@163.com');
```
**插入查询出来的数据**

```sql
INSERT INTO user(字段) SELECT 字段 FROM account;
```
```sql
SELECT * INTO new_表名 FROM 表名

// MySQL 数据库不支持 SELECT ... INTO 语句，但支持 INSERT INTO ... SELECT 。
INSERT INTO p1  SELECT * FROM product;
```

## 删除(DELETE FROM)
### DELETE FROM
1)可以**带条件删除**2）只能删除表的数据，**不能删除表的约束**3)删除的数据**可以回滚（事务）**
```sql
DELETE FROM user WHERE username='robot';
```
### truncate
1）不能带条件删除 2）可以删除表的数据，也可以删除表的约束 3）不能回滚

```sql
TRUNCATE TABLE user;
```

>总结：
>1、在速度上，一般来说，drop> truncate > delete。
>2、在使用drop和truncate时一定要注意，虽然可以恢复，但为了减少麻烦，还是要慎重。
>3、如果想删除部分数据用delete，注意带上where子句，回滚段要足够大；
>如果想**删除表**，当然用drop； 
>如果想**保留表而将所有数据删除**，如果**和事务无关**，用truncate即可；
>如果**和事务有关，或者想触发trigger**，还是用delete；
>如果是整理表内部的碎片，可以用truncate跟上reuse stroage，再重新导入/插入数据。
>

希望删除表结构时，用 drop;
希望保留表结构，但要删除所有记录时， 用 truncate;
希望保留表结构，但要删除部分记录时， 用 delete。

## 修改(UPDATE SET)
``` sql
UPDATE user
SET username='robot', password='robot'
WHERE username = 'root';

-- 合并后的写法
UPDATE product
   SET sale_price = sale_price * 10,
       purchase_price = purchase_price / 2
 WHERE product_type = '厨房用具';  
```
## 查询
所有字段：
``` sql
select * from 表;
```
指定字段：
``` sql
select 字段 from 表;
```
指定别名： 
```sql
select 字段1 as 别名 from 表;
```
合并列：
``` sql
select (字段1+字段2) as “和” from 表;
```
去重： 
```sql
select distinct 字段 from 表;
```

# 特殊字符
## 逻辑

**and、 or、in、not  in** 

```select * from 表 where 条件1 and/or 条件2```
```IN ("A", "B", "C")```     ––使用IN 和 NOT IN 时是无法选取出NULL数据的。

## 比较

 **> 、 <  、>= 、 <= 、 =、  !=、 <>、 between  and** 

```select * from 表 where 字段>=条件;```

## 模糊 

**like  、%（替换任意个字符）、  _（替换一个字符）**

```SELECT * FROM student WHERE NAME LIKE '李%';```

## 谓词

返回值为真值的函数。包括`TRUE / FALSE / UNKNOWN`。

谓词主要有以下几个：

- LIKE

- BETWEEN

- IS NULL、IS NOT NULL  

  **NULL 只能使用 IS NULL 和 IS NOT NULL 来进行判断。**

- IN

- EXISTS

  谓词的作用就是 **“判断是否存在满足某种条件的记录”**。如果存在这样的记录就返回真（TRUE），如果不存在就返回假（FALSE）。

##  判断

**case when 条件 then 真的操作 else 假的操作 end**

**if(条件, 真的操作, 假的操作)**

```sql
CASE WHEN product_type = '衣服' THEN CONCAT('A ： ',product_type)
 WHEN product_type = '办公用品'  THEN CONCAT('B ： ',product_type)
 WHEN product_type = '厨房用具'  THEN CONCAT('C ： ',product_type)
 ELSE NULL
END
```

## 聚合函数： 
聚合函数的使用前提是结果集已经确定，而WHERE还处于确定结果集的过程中，所以相互矛盾会引发错误。 如果**想指定条件，可以在SELECT，HAVING（下面马上会讲）以及ORDER BY子句中使用聚合函数。**

```sum()、avg() 、 max()  、min() 、 count()```

**count()**

> COUNT(常量) 和 COUNT(*)表示的是直接查询符合条件的数据库表的行数。而COUNT(列名)表示的是查询符合条件的列的值不为NULL的行数。

```SELECT SUM(servlet) AS 'servlet的总成绩' FROM student;```
```SELECT COUNT(*) FROM student;```

```sql
-- 计算去除重复数据后的数据行数
SELECT COUNT(DISTINCT product_type)
 FROM product;
```

```concat```将A和B按顺序连接在一起的字符串
```split(str, regex)```将string类型数据按regex提取，分隔后转换为array。
```substr（str,0,len) ```截取字符串从0位开始的长度为len个字符。
`SUBSTRING_INDEX (原始字符串， 分隔符，n)`获取原始字符串按照分隔符分割后，第 n 个分隔符之前（或之后）的子字符串，支持正向和反向索引，索引起始值分别为 1 和 -1。
```LOCATE()```查找某字符在长字符中的位置
```LEFT()、RIGHT()```左边或者右边的字符
```LOWER()、UPPER()```转换为小写或者大写
```LTRIM()、RTIM()```去除左边或者右边的空格
```LENGTH()```长度
```SOUNDEX()```转换为语音值
其中， SOUNDEX() 可以将一个字符串转换为描述其语音表示的字母数字模式。

```sql
SELECT *
FROM mytable
WHERE SOUNDEX(col1) = SOUNDEX('apple')
```

常用的日期提取函数包括 ```year()/month()/day()/hour()/minute()/second()```

CURRENT_DATE -- 获取当前日期
CURRENT_TIME -- 当前时间
CURRENT_TIMESTAMP -- 当前日期和时间
EXTRACT(日期元素 FROM 日期)-- 截取日期元素
```EXTRACT(YEAR   FROM CURRENT_TIMESTAMP) AS year```


```AddDate()```增加一个日期（天、周等）
```AddTime()```增加一个时间（时、分等）
```CurDate()```返回当前日期
```CurTime()```返回当前时间
```Date()```返回日期时间的日期部分
```to_date("1970-01-01 00:00:00")```把时间的字符串形式转化为时间类型，再进行后续的计算；
```datediff(enddate,stratdate) ```计算两个时间的时间差（day)；
```date_sub(stratdate,days) ```返回开始日期startdate减少days天后的日期。

```sql
SELECT ADDDATE("2017-06-15", INTERVAL 10 DAY);
```
```date_add(startdate,days) ```返回开始日期startdate增加days天后的日期。
```Date_Format()```返回一个格式化的日期或时间串
```date(paidTime).date_format(paidTime，%Y-%m-d%)```

`CAST（转换前的值 AS 想要转换的数据类型）` -- 将NULL转换为其他值

```
SELECT CAST('0001' AS SIGNED INTEGER) AS int_col;
```

`COALESCE(数据1，数据2，数据3……)` -- 返回可变参数 A 中左侧开始第 1个不是NULL的值



```SIN()```正弦
```COS()```余弦
```TAN()```正切
```ABS()```绝对值
```SQRT()```平方根
```MOD()```余数
```EXP()```指数
```PI()```圆周率
```RAND()```随机数
```percentile()``` 百分位函数



## 分组查询：group by 

使用COUNT等聚合函数时，**SELECT子句中如果出现列名，只能是GROUP BY子句中指定的列名**（也就是聚合键）。

SELECT子句中可以通过AS来指定别名，但在**GROUP BY中不能使用别名**。因为在DBMS中 ,SELECT子句在GROUP BY子句后执行。

**group by 后可加聚合函数，where 后不能加聚合函数**

```sql
-- 按照商品种类统计数据行数
SELECT product_type, COUNT(*)
  FROM product
 GROUP BY product_type;
--- error
SELECT product_name, COUNT(*)
  FROM product
 GROUP BY purchase_price;
```

## 分组后筛选： having 

WHERE子句只能指定记录（行）的条件，而不能用来指定组的条件（例如，“数据行数为 2 行”或者“平均值为 500”等）。

可以使用**数字、聚合函数和GROUP BY中指定的列名**（聚合键）。

```sql
SELECT product_type, COUNT(*)
  FROM product
 GROUP BY product_type
HAVING COUNT(*) = 2;

-- 错误形式（因为product_name不包含在GROUP BY聚合键中）
SELECT product_type, COUNT(*)
  FROM product
 GROUP BY product_type
HAVING product_name = '圆珠笔';
```

## DISTINCT 唯一

- 想要计算值的种类时，可以在COUNT函数的参数中使用DISTINCT。

```
SELECT DISTINCT Director FROM movies ASC;
```

## 分页查询：limit offset
起始行,查询行数起始行从0开始
把结果集分页，每页3条记录。要获取第1页的记录

```
SELECT * FROM student LIMIT 3 OFFSET 0;
```

## 排序： order by 

- ORDER BY中列名可使用别名

asc: 正序（默认）desc：反序
```sql
SELECT * 
FROM movies 
ORDER BY Director ASC,Year DESC 
LIMIT 10 OFFSET 0;
```

# 视图

视图是一个虚拟的表，不同于直接操作数据表，视图是依据SELECT语句来创建的

那既然已经有数据表了，为什么还需要视图呢？主要有以下几点原因：

1. 通过定义视图可以将频繁使用的SELECT语句保存以提高效率。
2. 通过定义视图可以使用户看到的数据更加清晰。
3. 通过定义视图可以不对外公开数据表全部字段，增强数据的保密性。
4. 通过定义视图可以降低数据的冗余。

创建视图的基本语法如下：

```sql
CREATE VIEW <视图名称>(<列名1>,<列名2>,...) AS <SELECT语句>
```

 需要注意的是**视图名在数据库中需要是唯一的，不能与其他视图和表重名。在创建视图时也尽量使用限制不允许通过视图来修改表**

视图不仅可以基于真实表，我们也可以在视图的基础上继续创建视图。虽然在视图上继续创建视图的语法没有错误，但是我们还是应该尽量避免这种操作。这是因为对多数 DBMS 来说， 多重视图会降低 SQL 的性能。

- 注意事项

需要注意的是在一般的DBMS中定义视图时不能使用ORDER BY语句。下面这样定义视图是错误的。

```sql
CREATE VIEW productsum (product_type, cnt_product)
AS
SELECT product_type, COUNT(*)
  FROM product
 GROUP BY product_type
 ORDER BY product_type;
```

为什么不能使用 ORDER BY 子句呢？这是**因为视图和表一样，数据行都是没有顺序的**。

*在 MySQL中视图的定义是允许使用 ORDER BY 语句的，但是若从特定视图进行选择，而该视图使用了自己的 ORDER BY 语句，则视图定义中的 ORDER BY 将被忽略。*

## 修改视图结构

```sql
ALTER VIEW <视图名> AS <SELECT语句>

ALTER VIEW productSum
    AS
        SELECT product_type, sale_price
          FROM Product
         WHERE regist_date > '2009-09-11';
```

其中视图名在数据库中需要是唯一的，不能与其他视图和表重名。 当然也可以通过将当前视图删除然后重新创建的方式达到修改的效果。（对于数据库底层是不是也是这样操作的呢，你可以自己探索一下。）

因为视图是一个虚拟表，所以对视图的操作就是对底层基础表的操作，所以在修改时只有满足底层基本表的定义才能成功修改。

对于一个视图来说，如果包含以下结构的任意一种都是不可以被更新的：

- 聚合函数 SUM()、MIN()、MAX()、COUNT() 等。
- DISTINCT 关键字。
- GROUP BY 子句。
- HAVING 子句。
- UNION 或 UNION ALL 运算符。
- FROM 子句中包含多个表。

视图归根结底还是从表派生出来的，因此，如果原表可以更新，那么 视图中的数据也可以更新。反之亦然，如果视图发生了改变，而原表没有进行相应更新的话，就无法保证数据的一致性了。

## 更新视图

因为我们刚刚修改的productSum视图不包括以上的限制条件，我们来尝试更新一下视图

```sql
UPDATE productsum
   SET sale_price = '5000'
 WHERE product_type = '办公用品';
```

## 删除视图

```sql
DROP VIEW <视图名1> [ , <视图名2> …]

DROP VIEW productSum;
```

注意：需要有相应的权限才能成功删除。



## 子查询和视图的关系

子查询就是将用来定义视图的 SELECT 语句直接用于 FROM 子句当中。其中AS studentSum可以看作是子查询的名称，而且由于子查询是一次性的，所以子查询不会像视图那样保存在存储介质中， 而是在 SELECT 语句执行之后就消失了。

### 标量子查询

标量就是单一的意思，那么标量子查询也就是单一的子查询，那什么叫做单一的子查询呢？

所谓单一就是要求我们执行的SQL语句只能返回一个值，也就是要返回表中具体的**某一行的某一列**。

### 关联子查询

关联子查询就是通过一些标志将内外两层的查询连接起来起到过滤数据的目的

`查询出销售单价高于平均销售单价的商品`，这个例子的SQL语句如下

```sql
SELECT product_id, product_name, sale_price
  FROM product
 WHERE sale_price > (SELECT AVG(sale_price) FROM product);
```

`选取出各商品种类中高于该商品种类的平均销售单价的商品`。SQL语句如下：

```sql
SELECT product_type, product_name, sale_price
  FROM product AS p1
 WHERE sale_price > (SELECT AVG(sale_price)
   FROM product AS p2
   WHERE p1.product_type = p2.product_type
   GROUP BY product_type);
```



# 连接查询（多表查询）JOIN  ON
把两个表中具有相同 主键ID的数据连接起来

**单纯的select * from a,b是笛卡尔乘积**。比如a表有5条数据，b表有3条数据，那么最后的结果有5*3=15条数据。但是如果对两个表进行关联:select * from a,b where a.id = b.id 意思就变了，此时就等价于：select * from a inner join b on a.id = b.id。即就是**内连接**。
![](https://upload-images.jianshu.io/upload_images/18339009-a79c08f891013866.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

INNER JOIN 产生的结果是AB的交集。
FULL [OUTER] JOIN 产生A和B的并集。
LEFT [OUTER] JOIN 产生表A的完全集，而B表中匹配的则有值，没有匹配的则以null值取代。
RIGHT [OUTER] JOIN 产生表B的完全集，而A表中匹配的则有值，没有匹配的则以null值取代CROSS JOIN 把表A和表B的数据进行一个N*M的组合，即笛卡尔积。
![](https://upload-images.jianshu.io/upload_images/18339009-93ca0a681c0411c1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/18339009-315255693cbe1041.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 内连接查询

**INNER JOIN：只保留两张表中完全匹配的结果集**

```
SELECT p.LastName, p.FirstName, o.OrderNo
FROM Persons p
INNER JOIN Orders o
ON p.Id_P=o.Id_P and 1=1　　
ORDER BY p.LastName
```
![](https://upload-images.jianshu.io/upload_images/18339009-798cff79aafdee78.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 左连接查询

**LEFT JOIN：返回左表所有的行，即使在右表中没有匹配的记录。**

```
SELECT p.LastName, p.FirstName, o.OrderNo
FROM Persons p
LEFT JOIN Orders o
ON p.Id_P=o.Id_P
ORDER BY p.LastName
```
![](https://upload-images.jianshu.io/upload_images/18339009-7951f83402cc6026.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
## 右连接

**RIGHT JOIN：返回右表所有的行，即使在左表中没有匹配的记录。**

```
SELECT p.LastName, p.FirstName, o.OrderNo
FROM Persons p
RIGHT JOIN Orders o
ON p.Id_P=o.Id_P
ORDER BY p.LastName
```
![](https://upload-images.jianshu.io/upload_images/18339009-fd7eaa8cc12522ed.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 全连接查询

**FULL JOIN ,返回左表和右表中所有的行。**

```
SELECT p.LastName, p.FirstName, o.OrderNo
FROM Persons p
FULL JOIN Orders o
ON p.Id_P=o.Id_P
ORDER BY p.LastName
```
![](https://upload-images.jianshu.io/upload_images/18339009-e11d28c6df0d7187.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# UNION 
将两个或更多查询的结果组合起来，并生成一个结果集
所有查询的**列数和列顺序**必须相同。
每个查询中涉及表的**列的数据类型**必须相同或兼容。
通常**返回的列名取自第一个查询。**
**默认会去除相同行**，如果需要保留相同行，使用 UNION ALL。
**只能包含一个 ORDER BY 子句，并且必须位于语句的最后。**

## JOIN vs UNION
JOIN 中连接表的列可能不同，但在 UNION 中，**所有查询的列数和列顺序必须相同。**

**UNION 将查询之后的行放在一起（垂直放置），但 JOIN 将查询之后的列放在一起（水平放置）**

## 应用场景
**在一个查询中从不同的表返回结构数据。**
**对一个表执行多个查询，按一个查询返回数据。
组合查询**

```python
SELECT cust_name, cust_contact, cust_email
FROM customers
WHERE cust_state IN ('IL', 'IN', 'MI')
UNION
SELECT cust_name, cust_contact, cust_email
FROM customers
WHERE cust_name = 'Fun4All';
````






# 远程连接
## 查看当前的用户
```USE mysql;```
```SELECT * FROM user;```

## 修改密码
@前用户名@后地址（ % 代表可以任意ip访问）
```mysql ALTER USER "root"@"localhost" IDENTIFIED  BY "root";```

## 创建新用户
```CREATE USER 'new_user'@'%' IDENTIFIED BY 'passwd';```
## 给用户赋权限
这里我赋的是全部的权限，*.* 表示数据库库的所有库和表，对应权限存储在mysql.user表中
```GRANT ALL ON *.* TO 'new_user'@'%';```

```GRANT SELECT, UPDATE ON day16.employee TO 'eric'@'localhost' IDENTIFIED BY '123456';```
## 刷新权限
```flush privileges;```


## 取消远程控制
```update user set host='localhost' where user='用户名';```

## 删除用户
```delete from user where user="用户名" and host='host权限（localhost/%）';```
>--开放3306端口--
1.控制面板—系统和安全—windows防火墙—攻击设置—入栈规则
2.新建规则—选择端口
![](https://upload-images.jianshu.io/upload_images/18339009-e649fed7e43e6b95.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
3.指定开放的端口
![](https://upload-images.jianshu.io/upload_images/18339009-a40b2644f02b64a3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
4.允许连接，一直点下一步即可
![](https://upload-images.jianshu.io/upload_images/18339009-4610a319e4a4f648.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)






# PyMySQL模块
是默认开启MySQL的事务功能的，
因此，进行 "增"、 "删"、"改"的时候，一定要使用db.commit()提交事务
一定要使用try…except…语句，因为万一没插入成功，其余代码都无法执行。当语句执行不成功，
我们就db.rollback()回滚到操作之前的状态；当语句执行成功，我们就db.commit()提交事务。

```python
import pymysql 
# 使用pymysql连接上mysql数据库服务器，创建了一个数据库对象；
db=pymysql.connect(host='localhost',user='root', password='',
                   port=3306, db='test', charset='utf8')
# 开启mysql的游标功能，创建一个游标对象；
cursor = db.cursor()

# 建表语句；
sql = """create table person(
        id int auto_increment primary key not null,
        name varchar(10) not null,
        age int not null) charset=utf8"""
# 执行sql语句；
cursor.execute(sql)

# 一次性插入一条数据；
name = "猪八戒"
age = 8000
sql = """
insert into person(name,age) values ("猪八戒",8000)
"""
try:
    cursor.execute(sql)
    db.commit()
    print("插入成功")
except:
    print("插入失败")
    db.rollback()

# 要执行的SQL语句；
sql = "select * from person"
# execute(query, args)：执行单条sql语句，接收的参数为sql语句本身和使用的参数列表，返回值为受影响的行数；
# executemany(query, args)：执行单条sql语句，但是重复执行参数列表里的参数，返回值为受影响的行数；
cursor.execute(sql)
# fetchone()：返回一条结果行；
# fetchmany(size)：接收size条返回结果行。如果size的值大于返回的结果行的数量，则会返回cursor.arraysize条数据；
# fetchall()：接收全部的返回结果行；
data = cursor.fetchone()
print(data)
#关闭游标
cursor.close()    
# 关闭数据库连接
db.close()
```
# 使用pandas
中的read_sql()方法，将提取到的数据直接转化为DataFrame，进行操作
```
df1 = pd.read_sql("select * from student where ssex='男'",db)
display(df1)
df2 = pd.read_sql("select * from student where ssex='女'",db)
display(df2)
```

# 数据约束（表约束）
```
默认值： default 默认值
非空：   not null
 唯一： unique
主键： primary key （非空+唯一）
 自增长： auto_increment
```
###### 外键： foreign key   约束两种表

```
CONSTRAINT emlyee_dept_fk 
FOREIGN KEY(deptId) 
REFERENCES dept(id)  
ON UPDATE CASCADE ON DELETE CASCADE  -- ON CASCADE
```
```
ALTER TABLE students
ADD CONSTRAINT fk_class_id
FOREIGN KEY (class_id)
REFERENCES classes (id);
```
其中，外键约束的名称fk_class_id可以任意，FOREIGN KEY (class_id)指定了class_id作为外键，REFERENCES classes (id)指定了这个外键将关联到classes表的id列（即classes表的主键）。
```
ALTER TABLE students
DROP FOREIGN KEY fk_class_id;
```
注意：删除外键约束并没有删除外键这一列。删除列是通过DROP COLUMN ...实现的。

###### UPDATE ：级联修改
外键名称                  外键               参考表(参考字段)
 注意：
            1）被约束的表称为副表，约束别人的表称为主表，外键设置在副表上的！！！
            2）主表的参考字段通用为主键！
            3）添加数据： 先添加主表，再添加副表
            4）修改数据： 先修改副表，再修改主表
            5）删除数据： 先删除副表，再删除主表
 当有了外键约束的时候，必须先修改或删除副表中的所有关联数据，才能修改或删除主表！但是，我们希望直接修改或删除主表数据，从而影响副表数据。可以使用级联操作实现！！！



存储过程
-- 创建存储过程
```
DELIMITER $       -- 声明存储过程的结束符
CREATE PROCEDURE pro_test()           --存储过程名称(参数列表)
BEGIN             -- 开始
    -- 可以写多个sql语句;          -- sql语句+流程控制
    SELECT * FROM employee;
END $            -- 结束 结束符
```
-- 执行存储过程
```
CALL pro_test();          -- CALL 存储过程名称(参数);
```
-- 删除存储过程
```
DROP PROCEDURE pro_testOut;
```
-  全局变量（内置变量）：mysql数据库内置的变量 （所有连接都起作用）
       -- 查看所有全局变量： show variables
       -- 查看某个全局变量： select @@变量名
       -- 修改全局变量： set 变量名=新值
   --  会话变量： 只存在于当前客户端与数据库服务器端的一次连接当中。如果连接断开，那么会话变量全部丢失！
       -- 定义会话变量: set @变量=值
       -- 查看会话变量： select @变量
       

-- 局部变量： 在存储过程中使用的变量就叫局部变量。只要存储过程执行完毕，局部变量就丢失！！

- 3.3 带有输入输出参数的存储过程
```
  DELIMITER $
  CREATE PROCEDURE pro_testInOut(INOUT n INT)  -- INOUT： 输入输出参数
  BEGIN
   SELECT n;   过程中的变量都是局部变量
   SET n =500;
  END $
  SET @n=10;
  CALL pro_testInOut(@n);
  SELECT @n;
IF num=1    THEN  SET str='星期一';
ELSEIF num=2 THEN  SET str='星期二';
ELSE               SET str='输入错误';
END IF;
```
-- 定义一个局部变量
```
    DECLARE i INT DEFAULT 1;
    DECLARE vsum INT DEFAULT 0;
    WHILE i<=num DO
          SET vsum = vsum+i;
          SET i=i+1;
    END WHILE;
    SET result=vsum;
```
-- 3.6 使用查询的结果赋值给变量（INTO）
```
DELIMITER $
CREATE PROCEDURE pro_findById2(IN eid INT,OUT vname VARCHAR(20) )
BEGIN
    SELECT empName INTO vname FROM employee WHERE id=eid;
END $
CALL pro_findById2(1,@NAME);
SELECT @NAME;
```
-- 创建触发器(添加)
```
CREATE TRIGGER tri_empAdd AFTER INSERT ON employee FOR EACH ROW    -- 当往员工表插入一条记录时
     INSERT INTO test_log(content) VALUES('员工表插入了一条记录');
```
-- 创建触发器(修改)
```
CREATE TRIGGER tri_empUpd AFTER UPDATE ON employee FOR EACH ROW    -- 当往员工表修改一条记录时
     INSERT INTO test_log(content) VALUES('员工表修改了一条记录');
```
-- 创建触发器(删除)
```
CREATE TRIGGER tri_empDel AFTER DELETE ON employee FOR EACH ROW    -- 当往员工表删除一条记录时
     INSERT INTO test_log(content) VALUES('员工表删除了一条记录');
```

# 窗口函数
**应用：1、topN问题或者组内排序问题2、连续登录问题**
**!!!!!!!!!!!!!窗口函数原则上只能写在select子句中**

## 窗口函数order by与group by的区别
窗口函数中的order by只是决定着**窗口里的数据的排序方式**，普通的order by决定**查询出的数据以什么样的方式整体排序**；

窗口函数可以在保留原表中的全部数据之后，对某些字段做分组排序或者计算，而group by只能保留与分组字段聚合的结果；

## 执行顺序

在加入窗口函数的基础上SQL的执行顺序也会发生变化，具体的执行顺序如下（window就是窗口函数）
![](https://upload-images.jianshu.io/upload_images/18339009-cd689024f076a36a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![专用窗口函数例如rank、row_number、lag和lead等，在窗口函数中有静态函数和动态函数的分类](https://upload-images.jianshu.io/upload_images/18339009-fbbe65cca6ca527b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```rank()```排序相同时会重复，总数不会变 ，意思是会出现1、1、3这样的排序结果；
```dense_rank()``` 排序相同时会重复，总数会减少，意思是会出现1、1、2这样的排序结果。
```row_number()``` 则在排序相同时不重复，会根据顺序排序。


>![](https://upload-images.jianshu.io/upload_images/18339009-96223baa7e580bc7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/18339009-95bdd2e203a8310e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
从上面的例子可以看出，在没有partition by 的情况下，是把整个表作为一个大的窗口，SUM（）相当于向下累加，AVG（）相当于求从第一行到当前行的平均值，**其他的聚合函数均是如此**。

注意点：
1 、在使用专用的窗口函数时，例如rank、lag等，rank（）括号里是不需要指定任何字段的，直接空着就可以；
2 、在使用聚合函数做窗口函数时，SUM（）括号里必须有字段，得指定对哪些字段执行聚合的操作。在学习的初期很容易弄混，不同函数括号里是否需写相应的字段名；


## 滑动窗口函数

### Preceding

![](https://upload-images.jianshu.io/upload_images/18339009-5f33b14e770215a7?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/18339009-c048cc9e17b7073b?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
Rows 2 preceding 中文的意思是之前的两行，preceding可以把它理解为不含当前行情况下截止到之前几行。根据上图可以看出在每一行，都会求出**当前行附近的3行(当前行+附近2行)数据的平均值**，这种方法也叫作**移动平均**。

### Following

Rows 2 following 中文意思是之后的两行，跟preceding正好相反，Preceding是向前，following是向后。
![](https://upload-images.jianshu.io/upload_images/18339009-93d5c51eb26202a6?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### preceding跟following相结合

![](https://upload-images.jianshu.io/upload_images/18339009-4b2be94447f8744d?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/18339009-85623bf00d8f8866?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

从以上的运行结果可以看出是把每一行（当前行）的前一行和后一行作为汇总的依据。







