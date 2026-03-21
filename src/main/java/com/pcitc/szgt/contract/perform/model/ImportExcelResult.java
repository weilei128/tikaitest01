package com.pcitc.szgt.contract.perform.model;

import lombok.Builder;

import java.util.List;

/**
 * @author: hanyafei
 * @date: 2020-06-12 16:42
 */
@Builder
public class ImportExcelResult<T> {
    private List<String> msgs;

    private List<T> result;

    public ImportExcelResult() {
    }

    public ImportExcelResult(List<String> msgs, List<T> result) {
        this.msgs = msgs;
        this.result = result;
    }

    public List<String> getMsgs() {
        return msgs;
    }

    public void setMsgs(List<String> msgs) {
        this.msgs = msgs;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
