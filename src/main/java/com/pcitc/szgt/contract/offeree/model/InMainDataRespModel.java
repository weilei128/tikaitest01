package com.pcitc.szgt.contract.offeree.model;

import java.util.Objects;

/**
 * 内部单位model
 */
public class InMainDataRespModel {
    private String c_001;
    private String c_002;
    private String c_003;
    private String c_004;
    private String c_005;
    private String c_016;
    private String c_008;
    private String c_008text;
    private String c_006;
    private String c_007;
    private String c_009;
    private String c_010;
    private String c_010text;
    private String c_011;
    private String c_011text;
    private String c_012;
    private String c_012text;
    private String c_013;
    private String c_018;
    private String c_014;
    private String c_015;
    private String c_020;
    private String c_017;
    private String createrTime;
    private String status;

    //extention
    private boolean isExist;

    public String getC_001() {
        return c_001;
    }

    public void setC_001(String c_001) {
        this.c_001 = c_001;
    }

    public String getC_002() {
        return c_002;
    }

    public void setC_002(String c_002) {
        this.c_002 = c_002;
    }

    public String getC_003() {
        return c_003;
    }

    public void setC_003(String c_003) {
        this.c_003 = c_003;
    }

    public String getC_004() {
        return c_004;
    }

    public void setC_004(String c_004) {
        this.c_004 = c_004;
    }

    public String getC_005() {
        return c_005;
    }

    public void setC_005(String c_005) {
        this.c_005 = c_005;
    }

    public String getC_016() {
        return c_016;
    }

    public void setC_016(String c_016) {
        this.c_016 = c_016;
    }

    public String getC_008() {
        return c_008;
    }

    public void setC_008(String c_008) {
        this.c_008 = c_008;
    }

    public String getC_008text() {
        return c_008text;
    }

    public void setC_008text(String c_008text) {
        this.c_008text = c_008text;
    }

    public String getC_006() {
        return c_006;
    }

    public void setC_006(String c_006) {
        this.c_006 = c_006;
    }

    public String getC_007() {
        return c_007;
    }

    public void setC_007(String c_007) {
        this.c_007 = c_007;
    }

    public String getC_009() {
        return c_009;
    }

    public void setC_009(String c_009) {
        this.c_009 = c_009;
    }

    public String getC_010() {
        return c_010;
    }

    public void setC_010(String c_010) {
        this.c_010 = c_010;
    }

    public String getC_010text() {
        return c_010text;
    }

    public void setC_010text(String c_010text) {
        this.c_010text = c_010text;
    }

    public String getC_011() {
        return c_011;
    }

    public void setC_011(String c_011) {
        this.c_011 = c_011;
    }

    public String getC_011text() {
        return c_011text;
    }

    public void setC_011text(String c_011text) {
        this.c_011text = c_011text;
    }

    public String getC_012() {
        return c_012;
    }

    public void setC_012(String c_012) {
        this.c_012 = c_012;
    }

    public String getC_012text() {
        return c_012text;
    }

    public void setC_012text(String c_012text) {
        this.c_012text = c_012text;
    }

    public String getC_013() {
        return c_013;
    }

    public void setC_013(String c_013) {
        this.c_013 = c_013;
    }

    public String getC_018() {
        return c_018;
    }

    public void setC_018(String c_018) {
        this.c_018 = c_018;
    }

    public String getC_014() {
        return c_014;
    }

    public void setC_014(String c_014) {
        this.c_014 = c_014;
    }

    public String getC_015() {
        return c_015;
    }

    public void setC_015(String c_015) {
        this.c_015 = c_015;
    }

    public String getC_020() {
        return c_020;
    }

    public void setC_020(String c_020) {
        this.c_020 = c_020;
    }

    public String getC_017() {
        return c_017;
    }

    public void setC_017(String c_017) {
        this.c_017 = c_017;
    }

    public String getCreaterTime() {
        return createrTime;
    }

    public void setCreaterTime(String createrTime) {
        this.createrTime = createrTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InMainDataRespModel that = (InMainDataRespModel) o;
        return c_001.equals(that.c_001);
    }

    @Override
    public int hashCode() {
        return Objects.hash(c_001);
    }
}
