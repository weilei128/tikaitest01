/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.20-log : Database - federation
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`federation` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `federation`;

/*Table structure for table `t_client` */

DROP TABLE IF EXISTS `t_client`;

CREATE TABLE `t_client` (
  `f_Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `f_client_id` varchar(30) DEFAULT NULL COMMENT '认证ID',
  `f_client_des` varchar(30) DEFAULT NULL COMMENT '认证描述...',
  `f_client_Secret` varchar(30) DEFAULT NULL COMMENT '认证密钥..',
  `f_client_Acctoken_Seconds` int(11) DEFAULT NULL COMMENT 'token过期时间...',
  `f_client_Reftoken_Seconds` int(11) DEFAULT NULL COMMENT '刷新token时间...',
  `f_CreateUser` varchar(50) DEFAULT NULL COMMENT '创建用户账号',
  `f_CreateName` varchar(50) DEFAULT NULL COMMENT '创建用户姓名',
  `f_CreateTime` datetime DEFAULT NULL COMMENT '创建时间日期',
  `f_UpdateUser` varchar(50) DEFAULT NULL COMMENT '修改用户账号',
  `f_UpdateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `f_UpdateName` varchar(50) DEFAULT NULL COMMENT '修改用户姓名',
  `f_Type` int(11) DEFAULT NULL COMMENT '类型',
  `f_State` int(11) DEFAULT NULL COMMENT '状态',
  `f_Sort` int(11) DEFAULT NULL COMMENT '排序字段 ',
  `f_IsDel` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除(逻辑删除标记0没有删除1删除)',
  PRIMARY KEY (`f_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='授权认证Client';

/*Data for the table `t_client` */

insert  into `t_client`(`f_Id`,`f_client_id`,`f_client_des`,`f_client_Secret`,`f_client_Acctoken_Seconds`,`f_client_Reftoken_Seconds`,`f_CreateUser`,`f_CreateName`,`f_CreateTime`,`f_UpdateUser`,`f_UpdateTime`,`f_UpdateName`,`f_Type`,`f_State`,`f_Sort`,`f_IsDel`) values (1,'federation_client_id','联合会客服端认证ID','123456',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(3,'ces','测试认证',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(4,'ces','测试认证',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(5,'ces','测试认证 -- 更新',NULL,NULL,NULL,'11','测试用户','2020-02-11 17:56:52','12','2020-02-12 11:17:17','测试更新用户',NULL,NULL,NULL,0),(6,'ces','测试认证1212',NULL,NULL,NULL,'11','测试用户','2020-02-13 10:59:35',NULL,NULL,NULL,NULL,NULL,NULL,0);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `f_Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `fk_Image_Id` int(11) DEFAULT NULL COMMENT '图片关联Id',
  `f_User_Id` varchar(50) DEFAULT NULL COMMENT '用户Id',
  `f_Phone` varchar(50) NOT NULL COMMENT '手机号码',
  `fk_Customer_Id` int(11) DEFAULT NULL COMMENT '关联客服id(邀请码关联)',
  `f_Department` varchar(20) DEFAULT NULL COMMENT '部门',
  `f_Position` varchar(20) DEFAULT NULL COMMENT '职务',
  `f_User_Mail` varchar(30) DEFAULT NULL COMMENT '用户邮箱',
  `f_Company_Name` varchar(50) DEFAULT NULL COMMENT '公司名称',
  `f_UserName` varchar(50) DEFAULT NULL COMMENT '用户姓名',
  `f_Password` varchar(50) NOT NULL COMMENT '用户密码',
  `f_User_Type` int(11) DEFAULT NULL COMMENT '用户类型(0管理员、1专家、2供应商、3采购商等等)',
  `f_User_Image` varchar(80) DEFAULT NULL COMMENT '用户头像',
  `f_User_Type_Des` varchar(10) DEFAULT NULL COMMENT '用户类型(专家、供应商、采购商等等)描述',
  `f_Invitation_Code` varchar(50) DEFAULT NULL COMMENT '邀请码',
  `f_Verification_Code` varchar(50) DEFAULT NULL COMMENT '验证码',
  `f_Is_Agree` int(11) DEFAULT NULL COMMENT '用户注册协议',
  `f_State` int(11) DEFAULT '0' COMMENT '状态(0没有认证,1认证通过)',
  `f_Type` int(11) DEFAULT NULL COMMENT '数据来源（1是管理员添加，0是用户注册）',
  `f_IsDel` int(11) DEFAULT '0' COMMENT '是否删除(逻辑删除标记0没有删除1删除)',
  `f_Sort` int(11) DEFAULT NULL COMMENT '排序字段 ',
  `f_UpdateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `f_CreateTime` datetime DEFAULT NULL COMMENT '创建时间',
  `f_ParentId` int(11) DEFAULT NULL COMMENT '账号管理父账号id',
  PRIMARY KEY (`f_Id`),
  UNIQUE KEY `f_Phone` (`f_Phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=218 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `t_user` */

insert  into `t_user`(`f_Id`,`fk_Image_Id`,`f_User_Id`,`f_Phone`,`fk_Customer_Id`,`f_Department`,`f_Position`,`f_User_Mail`,`f_Company_Name`,`f_UserName`,`f_Password`,`f_User_Type`,`f_User_Image`,`f_User_Type_Des`,`f_Invitation_Code`,`f_Verification_Code`,`f_Is_Agree`,`f_State`,`f_Type`,`f_IsDel`,`f_Sort`,`f_UpdateTime`,`f_CreateTime`,`f_ParentId`) values (1,NULL,'1','15808472688',4,NULL,NULL,NULL,NULL,'admin','admin',2,NULL,'供应商','3396493123dasd',NULL,NULL,1,NULL,0,NULL,NULL,'2019-10-29 15:20:41',NULL),(2,NULL,'2','15808472689',2,NULL,NULL,NULL,NULL,'expert','expert',1,NULL,'专家',NULL,NULL,NULL,1,NULL,0,NULL,NULL,'2019-10-29 17:57:54',NULL),(3,NULL,'3','15808472619',NULL,NULL,NULL,NULL,NULL,'buyers','buyers',3,NULL,'采购商',NULL,NULL,NULL,1,NULL,0,NULL,NULL,'2019-11-02 10:30:34',NULL),(4,NULL,'4','15808472612',NULL,NULL,NULL,NULL,NULL,'SuperAdmin','123456',0,NULL,'系统管理员',NULL,NULL,NULL,1,NULL,0,NULL,NULL,'2019-11-02 15:19:29',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


CREATE TABLE `t_lawyers_basic_info` (
  `f_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '法律人员基本信息主键',
  `f_Name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `f_Account_Number` varchar(32) DEFAULT NULL COMMENT '账号',
  `fk_Org_ID` bigint(20) DEFAULT NULL COMMENT '组织机构ID',
  `fk_Org_Name` varchar(32) DEFAULT NULL COMMENT '组织机构名称',
  `f_Sex` tinyint(1) DEFAULT NULL COMMENT '0：男，1：女',
  `f_Nationality` varchar(20) DEFAULT NULL COMMENT '国籍',
  `f_Birthday` date DEFAULT NULL COMMENT '出生日期',
  `f_Age` int(3) DEFAULT NULL COMMENT '年龄',
  `f_Duty` varchar(20) DEFAULT NULL COMMENT '职务',
  `f_Position` varchar(20) DEFAULT NULL COMMENT '岗位',
  `f_Position_Level` varchar(20) DEFAULT NULL COMMENT '职务级别',
  `f_Title_Level` varchar(20) DEFAULT NULL COMMENT '职称级别',
  `f_Graduate_School` varchar(20) DEFAULT NULL COMMENT '毕业院校',
  `f_Graduate_Time` date DEFAULT NULL COMMENT '毕业时间',
  `f_Highest_School_Record` varchar(10) DEFAULT NULL COMMENT '最高学历',
  `f_Highest_Degree` varchar(10) DEFAULT NULL COMMENT '最高学位',
  `f_If_Full_Legal_EDU` tinyint(1) DEFAULT NULL COMMENT '是否有全日制法律教育背景，0：无；1：有',
  `f_Major` varchar(20) DEFAULT NULL COMMENT '所学专业',
  `f_Work_Time` date DEFAULT NULL COMMENT '参加工作时间',
  `f_Law_Work_Time` date DEFAULT NULL COMMENT '从事法律工作时间',
  `f_Politics_Status` varchar(20) DEFAULT NULL COMMENT '政治面貌',
  `f_Email_Address` varchar(32) DEFAULT NULL COMMENT '电子邮件',
  `f_Office_Phone_Number` varchar(20) DEFAULT NULL COMMENT '办公电话',
  `f_Phone_Number` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `f_Enable` tinyint(1) DEFAULT NULL COMMENT '是否启用，0：不启用；1：启用',
  `f_Is_Del` tinyint(1) DEFAULT NULL COMMENT '是否删除，0：未删除；1：删除',
  `f_Create_ID` varchar(32) DEFAULT NULL COMMENT '创建人ID',
  `f_Create_Account_Number` varchar(32) DEFAULT NULL COMMENT '创建人账号',
  `f_Create_Name` varchar(20) DEFAULT NULL COMMENT '创建人姓名',
  `f_Create_Time` datetime DEFAULT NULL COMMENT '创建时间',
  `f_Update_ID` varchar(32) DEFAULT NULL COMMENT '修改人ID',
  `f_Update_Account_Number` varchar(32) DEFAULT NULL COMMENT '修改人账号',
  `f_Update_Name` varchar(20) DEFAULT NULL COMMENT '修改人姓名',
  `f_Update_Time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`f_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='法律人员基本信息表'
