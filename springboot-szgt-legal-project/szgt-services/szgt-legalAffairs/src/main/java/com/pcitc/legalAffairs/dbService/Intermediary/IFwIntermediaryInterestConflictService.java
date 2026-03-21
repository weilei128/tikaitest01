package com.pcitc.legalAffairs.dbService.Intermediary;

import java.util.List;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.Intermediary.ConflictLogBo;
import com.pcitc.legalAffairs.bo.Intermediary.ConflictQueryBo;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryInterestConflict;
import com.pcitc.szgt.legalAffairs.base.IBaseService;

/**
 * 利益冲突名单
 * 
 * @author meihongli
 *
 */
public interface IFwIntermediaryInterestConflictService extends IBaseService<FwIntermediaryInterestConflict> {
	
	/**
	 * 保存利益冲突信息
	 * @param uscCode 统一社会信用代码
	 * @param name
	 * @param type
	 */
	public void save(String uscCode, String name, String type);
	public void saveList(List<FwIntermediaryInterestConflict> entities);

	/**
	 * 删除利益冲突信息
	 * @param uscCode
	 * @return
	 */
	public Result delete(String uscCode);

	/**
	 * 检查中介是否被禁用
	 * @param uscCode 统一社会信用代码
	 * @return
	 */
	public boolean isBanned(String uscCode);
	
	/**
	 * 禁用
	 * @param uscCode 统一社会信用代码
	 * @return
	 */
	public Result ban(ConflictLogBo bo);
	
	/**
	 * 启用
	 * @param uscCode 统一社会信用代码
	 * @return
	 */
	public Result unban(ConflictLogBo bo);
	
	/**
	 * 分页查询数据
	 * @param bo
	 * @return
	 */
	public Result pageData(ConflictQueryBo bo);

	/**
	 * 删除没有案件对应的利益冲突信息
	 * 该方法目前不实现, 视需求决定是否实现
	 */
	public void removeNoCase();
}
