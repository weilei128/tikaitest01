package com.pcitc.legalAffairs.dbService.client;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.legalAffairs.mapper.client.ClientMapper;
import com.pcitc.legalAffairs.po.Client;
import org.springframework.stereotype.Service;

/***
 * @description
 * @author leigang
 * @date 2019年10月22日 16:47:48
 *
 */
@Service
public class IClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements IClientService {


}
