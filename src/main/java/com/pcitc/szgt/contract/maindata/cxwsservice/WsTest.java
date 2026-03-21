package com.pcitc.szgt.contract.maindata.cxwsservice;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WsTest {

    /**
     * 主数据接口
     */
    public static void maindata(){
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        Client client = factory.createClient("http://116.253.211.133:33201/dmodel/webservices/CxWsService?wsdl");
        try{
            Object[] objects = new Object[0];
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("RevicesForcxWs", "T_2082", "INSYS_OUTUNIT", "2020-05-10 10:33:20", "2020-05-19 10:33:20", "100", "1");
            System.out.println("返回数据:" + objects[0]);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 财务系统接口
     */
    public static void financial(){
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        Client client = factory.createClient("http://10.100.1.3/cwbase/JTGL/PubWebService.asmx?wsdl");
        try{
            Object[] objects = new Object[0];
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("SyncContractInfo", "MdmSys", "{}", "");
            System.out.println("返回数据:" + objects[0]);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 财务系统密文
     * @return
     */
    public static String f_secretText(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHH");
        String format = simpleDateFormat.format(new Date());
        String raw = "MdmSys" + "GXTZFSSC" + format + "{}";

        return DigestUtils.md5Hex(raw);
    }

    public static void main(String[] args) {
//        financial();
//        System.out.println(f_secretText());
        maindata();
    }
}
