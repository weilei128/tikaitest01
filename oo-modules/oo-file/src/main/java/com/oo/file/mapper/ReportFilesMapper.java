package com.oo.file.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oo.file.domain.ReportFiles;

import java.util.List;

/**
 * 文件记录Mapper接口
 * 
 * @author oo
 * @date 2023-09-26
 */
public interface ReportFilesMapper extends BaseMapper<ReportFiles>
{

    /**
     * 查询文件记录列表
     * 
     * @param reportFiles 文件记录
     * @return 文件记录集合
     */
    public List<ReportFiles> selectReportFilesList(ReportFiles reportFiles);

    /**
     * 物理删除
     *
     * @param ids
     * @return
     */
    boolean deleteItemsByIds(String[] ids);

    /**
     * 查询文件记录浏览列表（文件最新版本）
     *
     * @param reportFiles
     * @return
     */
    List<ReportFiles> selectReportFilesLatestList(ReportFiles reportFiles);
}
