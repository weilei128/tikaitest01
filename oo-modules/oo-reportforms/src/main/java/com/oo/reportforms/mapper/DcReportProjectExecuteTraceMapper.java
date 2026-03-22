package com.oo.reportforms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportProjectExecuteTrace;
import com.oo.reportforms.domain.vo.DcReportProjectExecuteTraceYearVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DcReportProjectExecuteTraceMapper extends BaseMapper<DcReportProjectExecuteTrace> {


    /**
     * 通过id查询DcReportProjectExecuteTraceYearVo
     *
     */
    public List<DcReportProjectExecuteTraceYearVo> selectDcReportProjectExecuteTraceYear(@Param("organizationId") String organizationId);


    public void delectDcReportProjectExecuteTraceYear(String[] Id);

    public void updateDelete(String[] Id);

}
