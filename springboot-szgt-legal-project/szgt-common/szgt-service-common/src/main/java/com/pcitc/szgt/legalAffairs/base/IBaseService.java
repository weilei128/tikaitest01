package com.pcitc.szgt.legalAffairs.base;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.UpdateChainWrapper;

public interface IBaseService<T> extends IService<T> {

    default QueryWrapper<T> queryWrapper() {
        return new QueryWrapper<>();
    }

    default LambdaQueryWrapper<T> lambdaQueryWrapper() {
        return new LambdaQueryWrapper<>();
    }

    default LambdaUpdateWrapper<T> lambdaUpdateWrapper() {
        return new LambdaUpdateWrapper<>();
    }


}
