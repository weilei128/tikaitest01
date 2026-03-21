package com.pcitc.szgt.contract.interactive.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-06-04
 */
public class FinancialLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 合同id
     */
    @TableField("contractId")
    private String contractId;

    /**
     * 合同编码
     */
    @TableField("contractCode")
    private String contractCode;

    /**
     * 合同名称
     */
    @TableField("contractName")
    private String contractName;

    /**
     * 发送数据
     */
    private String jsondata;

    /**
     * 时间
     */
    private String time;

    /**
     * sysCode
     */
    private String syscode;

    /**
     * key
     */
    private String syskey;

    /**
     * 密文
     */
    private String md5;

    /**
     * 结果字段
     */
    private String falg;

    /**
     * 结果json
     */
    private String result;

    /**
     * 当前状态
     */
    private Integer status;

    /**
     * 调用时间
     */
    private LocalDateTime createtime;

    /**
     * 异常信息
     */
    private String exception;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }
    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }
    public String getJsondata() {
        return jsondata;
    }

    public void setJsondata(String jsondata) {
        this.jsondata = jsondata;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getSyscode() {
        return syscode;
    }

    public void setSyscode(String syscode) {
        this.syscode = syscode;
    }

    public String getSyskey() {
        return syskey;
    }

    public void setSyskey(String syskey) {
        this.syskey = syskey;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
    public String getFalg() {
        return falg;
    }

    public void setFalg(String falg) {
        this.falg = falg;
    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }
    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FinancialLog{");
        sb.append("id=").append(id);
        sb.append(", contractId='").append(contractId).append('\'');
        sb.append(", contractCode='").append(contractCode).append('\'');
        sb.append(", contractName='").append(contractName).append('\'');
        sb.append(", jsondata='").append(jsondata).append('\'');
        sb.append(", time='").append(time).append('\'');
        sb.append(", syscode='").append(syscode).append('\'');
        sb.append(", syskey='").append(syskey).append('\'');
        sb.append(", md5='").append(md5).append('\'');
        sb.append(", falg='").append(falg).append('\'');
        sb.append(", result='").append(result).append('\'');
        sb.append(", status=").append(status);
        sb.append(", createtime=").append(createtime);
        sb.append(", exception='").append(exception).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
