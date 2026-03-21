package com.pcitc.legalAffairs.easypoi.model;

import cn.afterturn.easypoi.handler.inter.IExcelDataModel;
import cn.afterturn.easypoi.handler.inter.IExcelModel;

/**
 * @author: hanyafei
 * @date: 2020-06-12 09:58
 */
public class ExcelModel implements IExcelDataModel, IExcelModel {
    /**
     * 行号
     */
    private int rowNum;

    /**
     * 错误消息
     */
    private String errorMsg;

    @Override
    public int getRowNum() {
        return rowNum;
    }

    @Override
    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
