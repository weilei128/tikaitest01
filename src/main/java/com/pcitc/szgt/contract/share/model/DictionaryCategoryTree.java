package com.pcitc.szgt.contract.share.model;

import com.pcitc.szgt.contract.share.entity.SysDictionarycategory;

import java.util.List;

public class DictionaryCategoryTree extends SysDictionarycategory {
    private List<DictionaryCategoryTree> childNodeList;

    public List<DictionaryCategoryTree> getChildNodeList() {
        return childNodeList;
    }

    public void setChildNodeList(List<DictionaryCategoryTree> childNodeList) {
        this.childNodeList = childNodeList;
    }
}
