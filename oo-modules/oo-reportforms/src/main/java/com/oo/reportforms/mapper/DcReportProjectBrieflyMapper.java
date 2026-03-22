package com.oo.reportforms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.DcReportProjectBriefly;
import com.oo.reportforms.domain.vo.DcMdOrganizationVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DcReportProjectBrieflyMapper extends BaseMapper<DcReportProjectBriefly> {

    public List<String> selectDistinctYear();

    public List<DcMdOrganizationVo> groupByYear(String year);

    public void updateDelete(String[] Id);
}
