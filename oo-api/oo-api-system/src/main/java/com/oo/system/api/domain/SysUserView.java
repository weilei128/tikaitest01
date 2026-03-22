package com.oo.system.api.domain;

import com.oo.common.core.annotation.Excel;
import com.oo.common.core.annotation.Excels;
import com.oo.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class SysUserView extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "用户序号", cellType = Excel.ColumnType.NUMERIC, prompt = "用户编号")
    private Long userId;

    /** 部门ID */
    @Excel(name = "部门编号", type = Excel.Type.IMPORT)
    private Long deptId;

    /** 用户账号 */
    @Excel(name = "登录名称")
    private String userName;

    /** 用户昵称 */
    @Excel(name = "用户名称")
    private String nickName;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String email;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phonenumber;

    /** 用户性别 */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 用户头像 */
    private String avatar;

    /** 密码 */
    private String password;

    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 最后登录IP */
    @Excel(name = "最后登录IP", type = Excel.Type.EXPORT)
    private String loginIp;

    /** 最后登录时间 */
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginDate;

/*    *//** 创建人 *//*
    private String createBy;

    *//** 最后登录时间 *//*
    private Date createTime;

    *//** 修改人 *//*
    private String updateBy;

    *//** 修改时间 *//*
    private Date updateTime;*/

    /** 最高学历 */
    @Excel(name = "最高学历")
    private String degreeMax;

    /** 工作地点 */
    @Excel(name = "工作地点")
    private String workAddress;

    /** 当前国外派国家 */
    @Excel(name = "工作地点")
    private String expatriateCountry;

    /** 最高学位 */
    @Excel(name = "工作地点")
    private String graduateMax;

    /** 当前位置-国家，省市，三级联动 */
    @Excel(name = "国家省市")
    private String address;

    /** 当前位置-详细地址 */
    @Excel(name = "详细地址")
    private String addressDetails;

    /** 当前国家外派年限 */
    @Excel(name = "当前国家外派年限")
    private double expatriateYear;

    /** 职称 */
    @Excel(name = "职称")
    private String professional;

    /** 毕业院校 */
    @Excel(name = "毕业院校")
    private String draguateSchool;

    /** 负责人-直属上级 */
    private Long parentId;//关联

    /** 家庭详细住址 */
    @Excel(name = "毕业院校")
    private String familyAddress;

    /** KPI近三年考核结果 */
    @Excel(name = "KPI近三年考核结果")
    private String kpiResultRecent;

    /** 政治面貌 */
    @Excel(name = "政治面貌")
    private String politicalLandscape;

    /** 中海油入职时间 */
    @Excel(name = "中海油入职时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date onboardDateCn;

    /** 座机号 */
    @Excel(name = "座机号")
    private String landlineNumber;

    /** 工资级别 */
    @Excel(name = "工资级别")
    private String salaryScale;

    /** 出生日期 */
    @Excel(name = "出生日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    /** 转正日期 */
    @Excel(name = "转正日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date positiveDate;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 累计外派时间 */
    @Excel(name = "累计外派时间")
    private BigDecimal outsiteDateSum;

    /** 入党时间 */
    @Excel(name = "入党时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date partyDate;

    /** 海油国际入职时间 */
    @Excel(name = "海油国际入职时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date onboardDateWorld;

    /** 所有外派国家 */
    @Excel(name = "所有外派国家")
    private String outsiteCountryAll;

    /** 当前位置-经度 */
    @Excel(name = "经度")
    private BigDecimal localLongitude;

    /** 当前位置-纬度 */
    @Excel(name = "纬度")
    private BigDecimal localLatitude;

    private String remark;


    /** 部门对象 */
    @Excels({
            @Excel(name = "部门名称", targetAttr = "deptName", type = Excel.Type.EXPORT),
            @Excel(name = "部门负责人", targetAttr = "leader", type = Excel.Type.EXPORT)
    })

    private SysDept dept;
    private SysUserInfo userInfo;

    /** 角色对象 */
    private List<SysRole> roles;

    /** 角色组 */
    private Long[] roleIds;

    /** 岗位组 */
    private Long[] postIds;

    /** 角色ID */
    private Long roleId;
 /*   *//** 祖级列表 *//*
    private String ancestors;

    *//** 部门名称 *//*
    private String deptName;

    *//** 显示顺序 *//*
    private String orderNum;

    *//** 负责人 *//*
    private String leader;

    *//** 角色ID *//*
    @Excel(name = "角色序号", cellType = Excel.ColumnType.NUMERIC)
    private Long roleId;

    *//** 角色名称 *//*
    @Excel(name = "角色名称")
    private String roleName;

    *//** 角色权限 *//*
    @Excel(name = "角色权限")
    private String roleKey;

    *//** 角色排序 *//*
    @Excel(name = "角色排序")
    private String roleSort;

    *//** 数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限；5：仅本人数据权限） *//*
    @Excel(name = "数据范围", readConverterExp = "1=所有数据权限,2=自定义数据权限,3=本部门数据权限,4=本部门及以下数据权限,5=仅本人数据权限")
    private String dataScope;*/

}
