package com.oo.system.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.oo.common.core.annotation.MethodTranslate;
import com.oo.system.domain.SysPost;
import com.oo.system.mapper.SysPostMapper;
import com.oo.system.service.ISysPostService;
import com.oo.system.service.ISysTranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oo.common.core.constant.UserConstants;
import com.oo.common.core.exception.ServiceException;
import com.oo.common.core.utils.StringUtils;
import com.oo.system.mapper.SysUserPostMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * 岗位信息 服务层处理
 * 
 * @author ruoyi
 */
@Service
public class SysPostServiceImpl implements ISysPostService
{
    @Autowired
    private SysPostMapper postMapper;

    @Autowired
    private SysUserPostMapper userPostMapper;

    @Autowired
    private ISysTranslateService sysTranslateService;

    /**
     * 查询岗位信息集合
     * 
     * @param post 岗位信息
     * @return 岗位信息集合
     */
    @Override
    @MethodTranslate(dtoClass = SysPost.class)
    public List<SysPost> selectPostList(SysPost post)
    {
        return postMapper.selectPostList(post);
    }

    /**
     * 查询所有岗位
     * 
     * @return 岗位列表
     */
    @Override
    @MethodTranslate(dtoClass = SysPost.class)
    public List<SysPost> selectPostAll()
    {
        return postMapper.selectPostAll();
    }

    /**
     * 通过岗位ID查询岗位信息
     * 
     * @param postId 岗位ID
     * @return 角色对象信息
     */
    @Override
    @MethodTranslate(dtoClass = SysPost.class)
    public SysPost selectPostById(Long postId)
    {
        return postMapper.selectPostById(postId);
    }

    /**
     * 根据用户ID获取岗位选择框列表
     * 
     * @param userId 用户ID
     * @return 选中岗位ID列表
     */
    @Override
    public List<Long> selectPostListByUserId(Long userId)
    {
        return postMapper.selectPostListByUserId(userId);
    }

    /**
     * 校验岗位名称是否唯一
     * 
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public String checkPostNameUnique(SysPost post)
    {
        Long postId = StringUtils.isNull(post.getPostId()) ? -1L : post.getPostId();
        SysPost info = postMapper.checkPostNameUnique(post.getPostName());
        if (StringUtils.isNotNull(info) && info.getPostId().longValue() != postId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验岗位编码是否唯一
     * 
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public String checkPostCodeUnique(SysPost post)
    {
        Long postId = StringUtils.isNull(post.getPostId()) ? -1L : post.getPostId();
        SysPost info = postMapper.checkPostCodeUnique(post.getPostCode());
        if (StringUtils.isNotNull(info) && info.getPostId().longValue() != postId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 通过岗位ID查询岗位使用数量
     * 
     * @param postId 岗位ID
     * @return 结果
     */
    @Override
    public int countUserPostById(Long postId)
    {
        return userPostMapper.countUserPostById(postId);
    }

    /**
     * 删除岗位信息
     * 
     * @param postId 岗位ID
     * @return 结果
     */
    @Override
    public int deletePostById(Long postId)
    {
        return postMapper.deletePostById(postId);
    }

    /**
     * 批量删除岗位信息
     * 
     * @param postIds 需要删除的岗位ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deletePostByIds(Long[] postIds)
    {
        for (Long postId : postIds)
        {
            SysPost post = selectPostById(postId);
            if (countUserPostById(postId) > 0)
            {
                throw new ServiceException(String.format("%1$s已分配,不能删除", post.getPostName()));
            }
        }
        int result = postMapper.deletePostByIds(postIds);
        if (result <= 0) {
            return result;
        }
        //删除多语言
        List<String> postIdList = Arrays.stream(postIds).map(Object::toString).collect(Collectors.toList());
        boolean transResult = sysTranslateService.remove(postIdList);
        return transResult ? result : 0;
    }

    /**
     * 新增保存岗位信息
     * 
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertPost(SysPost post)
    {
        int result = postMapper.insertPost(post);
        if (result <= 0) {
            return result;
        }
        //保存多语言
        boolean transResult = sysTranslateService.submit(post.getPostId().toString(), post.getTransList());
        return transResult ? result : 0;
    }

    /**
     * 修改保存岗位信息
     * 
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updatePost(SysPost post)
    {
        int result = postMapper.updatePost(post);
        if (result <= 0) {
            return result;
        }
        //保存多语言
        boolean transResult = sysTranslateService.submit(post.getPostId().toString(), post.getTransList());
        return transResult ? result : 0;
    }
}
