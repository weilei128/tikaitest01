package com.pcitc.szgt.contract.make.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.make.entity.CrProjectinfo;
import com.pcitc.szgt.contract.make.modelEx.ProjectInfo;

import javax.print.DocFlavor;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 项目管理
 * </p>
 *
 * @author ziran.zhou
 * @since 2020-02-18
 */
public interface ICrProjectinfoService extends IService<CrProjectinfo> {
    /*
     * 新增项目
     * */
    boolean addProject(ProjectInfo projectInfo);

    /*
     * 修改项目信息
     * */
    boolean updateProject(ProjectInfo projectInfo);

    /*
     * 项目信息删除
     * */
    boolean delProject(String projectId);

    /*
     * 根据ID获取项目信息
     * */
    DataResult getProjectInfoById(String projectId);

    /*
     * 项目信息查询
     * */
    DataResult selectProjectInfo(String projectName, String atYear, Integer pageNum, Integer pageSize, Integer isValid);
}
