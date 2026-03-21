//package com.pcitc.szgt.contract.config.oauthconfig;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.pcitc.szgt.contract.share.entity.SysOrganization;
//import com.pcitc.szgt.contract.share.entity.SysRoleinfo;
//import com.pcitc.szgt.contract.share.entity.SysUserinfo;
//import com.pcitc.szgt.contract.sysmanager.mapper.SysOrganizationMapper;
//import com.pcitc.szgt.contract.sysmanager.mapper.SysRoleinfoMapper;
//import com.pcitc.szgt.contract.sysmanager.mapper.SysUserinfoMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class CustomUserDetailService implements UserDetailsService {
//
//    @Autowired
//    private SysUserinfoMapper userinfoMapper;
//
//    @Autowired
//    private SysOrganizationMapper organizationMapper;
//
//    @Autowired
//    private SysRoleinfoMapper roleinfoMapper;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//
//        QueryWrapper<SysUserinfo> userQueryWrapper = new QueryWrapper<SysUserinfo>();
//        userQueryWrapper.lambda().eq(SysUserinfo::getfAccount, s);
//
//        List<SysUserinfo> sysUsers = userinfoMapper.selectList(userQueryWrapper);
//        if(sysUsers == null || sysUsers.size() == 0){
//            throw new RuntimeException("用户不存在");
//        }
//
//        SysUserinfo sysUser = sysUsers.get(0);
//        String passWord = sysUser.getfPassword();
//
//        WrapUser wrapUser = new WrapUser(s, passWord, true, true,
//                true, true, getGrantedAuthorities());
//        UserInfo userInfo = new UserInfo();
//        userInfo.setSysUser(sysUser);
//
//        List<SysOrganization> orgs = organizationMapper.selectByUserId(sysUser.getfId());
//        List<SysRoleinfo> roles = roleinfoMapper.selectByUserId(sysUser.getfId());
////        SysOrganiseunit sysOrganiseunit = sysOrgMapper.selectById(sysUser.getOulabel());
//
//        userInfo.setSysOrgList(orgs);
//        userInfo.setSysRoleList(roles);
//        userInfo.setUnitName("石化盈科有限责任公司");
//        userInfo.setUnitId(1);
//
//        wrapUser.setUserInfo(userInfo);
//
//        return wrapUser;
//
//    }
//
//    private List<GrantedAuthority> getGrantedAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ADMIN"));
//        return authorities;
//    }
//}
