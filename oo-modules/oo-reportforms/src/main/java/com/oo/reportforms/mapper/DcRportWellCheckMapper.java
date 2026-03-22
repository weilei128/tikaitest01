package com.oo.reportforms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.reportforms.domain.vo.DcAllWellVo;
import com.oo.reportforms.domain.DcRportWellCheck;
import com.oo.reportforms.domain.vo.CountryByWellVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DcRportWellCheckMapper extends BaseMapper<DcRportWellCheck> {

    public void updateDelete(String[] Id);


    public List<DcAllWellVo> selectAllWell();

    public List<DcAllWellVo> selectAllCountry();


    public CountryByWellVo selectCountryByWell(String id);

}
