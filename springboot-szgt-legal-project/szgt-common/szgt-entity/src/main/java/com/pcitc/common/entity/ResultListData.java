package com.pcitc.common.entity;

import lombok.Data;

import java.util.List;


@Data
public class ResultListData<T, W> {

    private T data;

    private ResultList<W> resultList;

}
