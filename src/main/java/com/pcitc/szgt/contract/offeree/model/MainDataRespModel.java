package com.pcitc.szgt.contract.offeree.model;

import java.util.Objects;

public class MainDataRespModel {
    private String c_001;
    private String c_002;
    private String c_003;
    private String c_004;
    private String c_005;
    private String c_006;
    private String c_007;
    private String c_008;
    private String c_009;
    private String c_010;
    private String c_011;
    private String c_012;
    private String c_013;
    private String c_014;
    private String c_015;
    private String c_016;
    private String c_017;
    private String c_020;
    private String c_021;
    private String c_022;
    private String c_023;
    private String c_037;

    private String bc_001;
    private String bc_002;
    private String bc_003;
    private String bc_006;
    private String bc_007;
    private String bc_008;
    private String bc_009;

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

    public String getC_008() {
        return c_008;
    }

    public void setC_008(String c_008) {
        this.c_008 = c_008;
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

    public String getC_011() {
        return c_011;
    }

    public void setC_011(String c_011) {
        this.c_011 = c_011;
    }

    public String getC_012() {
        return c_012;
    }

    public void setC_012(String c_012) {
        this.c_012 = c_012;
    }

    public String getC_013() {
        return c_013;
    }

    public void setC_013(String c_013) {
        this.c_013 = c_013;
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

    public String getC_016() {
        return c_016;
    }

    public void setC_016(String c_016) {
        this.c_016 = c_016;
    }

    public String getC_017() {
        return c_017;
    }

    public void setC_017(String c_017) {
        this.c_017 = c_017;
    }

    public String getC_020() {
        return c_020;
    }

    public void setC_020(String c_020) {
        this.c_020 = c_020;
    }

    public String getC_021() {
        return c_021;
    }

    public void setC_021(String c_021) {
        this.c_021 = c_021;
    }

    public String getC_022() {
        return c_022;
    }

    public void setC_022(String c_022) {
        this.c_022 = c_022;
    }

    public String getC_023() {
        return c_023;
    }

    public void setC_023(String c_023) {
        this.c_023 = c_023;
    }

    public String getC_037() {
        return c_037;
    }

    public void setC_037(String c_037) {
        this.c_037 = c_037;
    }

    public String getBc_001() {
        return bc_001;
    }

    public void setBc_001(String bc_001) {
        this.bc_001 = bc_001;
    }

    public String getBc_002() {
        return bc_002;
    }

    public void setBc_002(String bc_002) {
        this.bc_002 = bc_002;
    }

    public String getBc_003() {
        return bc_003;
    }

    public void setBc_003(String bc_003) {
        this.bc_003 = bc_003;
    }

    public String getBc_006() {
        return bc_006;
    }

    public void setBc_006(String bc_006) {
        this.bc_006 = bc_006;
    }

    public String getBc_007() {
        return bc_007;
    }

    public void setBc_007(String bc_007) {
        this.bc_007 = bc_007;
    }

    public String getBc_008() {
        return bc_008;
    }

    public void setBc_008(String bc_008) {
        this.bc_008 = bc_008;
    }

    public String getBc_009() {
        return bc_009;
    }

    public void setBc_009(String bc_009) {
        this.bc_009 = bc_009;
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
        MainDataRespModel that = (MainDataRespModel) o;
        return c_001.equals(that.c_001);
    }

    @Override
    public int hashCode() {
        return Objects.hash(c_001);
    }
}
