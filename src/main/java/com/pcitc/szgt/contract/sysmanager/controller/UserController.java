package com.pcitc.szgt.contract.sysmanager.controller;

import com.fasterxml.jackson.core.filter.TokenFilterContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.config.oauthconfig.UserInfo;
import com.pcitc.szgt.contract.share.entity.SysOrganization;
import com.pcitc.szgt.contract.share.entity.SysUserinfo;
import com.pcitc.szgt.contract.share.model.SysOrganizationTree;
import com.pcitc.szgt.contract.share.request.OrganizationRequest;
import com.pcitc.szgt.contract.util.CurrentUserUtil;
import com.pcitc.szgt.contract.util.RestTemplateUtil;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CurrentUserUtil currentUserUtil;

    @Autowired
    private OrganizationRequest orgRequest;

    @GetMapping("currentUser")
    public DataResult<UserInfo> currentUser(){
        return DataResult.success(currentUserUtil.currentUserInfo());
    }

    @GetMapping("aaa")
    public String aaa(){
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            String token = request.getHeader("Authorization");
            return token;
        }

        return "";
    }

    @GetMapping("subOrgs")
    public DataResult subOrgs(){
        List<SysOrganization> sysOrganizations = orgRequest.queryAllSubOrgs(1);
        return DataResult.success(sysOrganizations);
    }

}
