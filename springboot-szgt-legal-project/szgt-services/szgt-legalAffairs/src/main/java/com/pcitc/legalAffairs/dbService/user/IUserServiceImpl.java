package com.pcitc.legalAffairs.dbService.user;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.legalAffairs.mapper.user.UserMapper;
import com.pcitc.legalAffairs.po.User;
import org.springframework.stereotype.Service;

/***
 * @description
 * @author leigang
 * @date 2019年10月22日 16:47:48
 *
 */
@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


}
