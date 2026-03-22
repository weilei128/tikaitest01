package com.example.qrcode.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class QrCodeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 测试用例1: POST请求生成二维码 - 正常场景
     * 覆盖接口: POST /api/qrcode/generate
     * 场景描述: 使用有效的组织和用户名参数，通过POST请求生成二维码
     */
    @Test
    public void testGenerateQrCodePost_Success() throws Exception {
        QrCodeController.QrCodeRequest request = new QrCodeController.QrCodeRequest();
        request.setOrganization("测试公司");
        request.setUsername("张三");

        mockMvc.perform(post("/api/qrcode/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("二维码生成成功"))
                .andExpect(jsonPath("$.data").exists());
    }

    /**
     * 测试用例2: GET请求生成二维码 - 正常场景
     * 覆盖接口: GET /api/qrcode/generate
     * 场景描述: 使用有效的组织和用户名参数，通过GET请求生成二维码
     */
    @Test
    public void testGenerateQrCodeGet_Success() throws Exception {
        mockMvc.perform(get("/api/qrcode/generate")
                        .param("organization", "测试部门")
                        .param("username", "李四"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("二维码生成成功"))
                .andExpect(jsonPath("$.data").exists());
    }

    /**
     * 测试用例3: POST请求生成二维码 - 组织参数为空
     * 覆盖接口: POST /api/qrcode/generate
     * 场景描述: 组织参数为空字符串，验证参数校验逻辑
     */
    @Test
    public void testGenerateQrCodePost_EmptyOrganization() throws Exception {
        QrCodeController.QrCodeRequest request = new QrCodeController.QrCodeRequest();
        request.setOrganization("");
        request.setUsername("王五");

        mockMvc.perform(post("/api/qrcode/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    /**
     * 测试用例4: GET请求生成二维码 - 用户名为空
     * 覆盖接口: GET /api/qrcode/generate
     * 场景描述: 用户名为空字符串，验证参数校验逻辑
     */
    @Test
    public void testGenerateQrCodeGet_EmptyUsername() throws Exception {
        mockMvc.perform(get("/api/qrcode/generate")
                        .param("organization", "测试团队")
                        .param("username", ""))
                .andExpect(status().isBadRequest());
    }

    /**
     * 测试用例5: POST请求保存用户记录 - 正常场景
     * 覆盖接口: POST /api/qrcode/record
     * 场景描述: 使用有效的组织和用户名参数，保存用户记录到CSV文件
     */
    @Test
    public void testSaveUserRecordPost_Success() throws Exception {
        QrCodeController.QrCodeRequest request = new QrCodeController.QrCodeRequest();
        request.setOrganization("研发中心");
        request.setUsername("赵六");

        mockMvc.perform(post("/api/qrcode/record")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("用户记录保存成功"));
    }

    /**
     * 测试用例6: GET请求保存用户记录 - 正常场景
     * 覆盖接口: GET /api/qrcode/record
     * 场景描述: 使用有效的组织和用户名参数，通过GET请求保存用户记录
     */
    @Test
    public void testSaveUserRecordGet_Success() throws Exception {
        mockMvc.perform(get("/api/qrcode/record")
                        .param("organization", "产品部")
                        .param("username", "钱七"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("用户记录保存成功"));
    }

    /**
     * 测试用例7: 获取小时级统计信息
     * 覆盖接口: GET /api/qrcode/statistics
     * 场景描述: 获取过去1小时内的用户记录统计信息
     */
    @Test
    public void testGetHourlyStatistics_Success() throws Exception {
        mockMvc.perform(get("/api/qrcode/statistics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("获取统计信息成功"))
                .andExpect(jsonPath("$.data.count").exists())
                .andExpect(jsonPath("$.data.timeRange").exists())
                .andExpect(jsonPath("$.data.timestamp").exists());
    }

    /**
     * 测试用例8: 生成二维码 - 参数包含特殊字符
     * 覆盖接口: POST /api/qrcode/generate
     * 场景描述: 组织和用户名包含非法字符，验证字符过滤逻辑
     */
    @Test
    public void testGenerateQrCode_WithSpecialCharacters() throws Exception {
        QrCodeController.QrCodeRequest request = new QrCodeController.QrCodeRequest();
        request.setOrganization("测试/公司:名称*");
        request.setUsername("用户<名>|测试");

        mockMvc.perform(post("/api/qrcode/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("二维码生成成功"))
                .andExpect(jsonPath("$.data").exists());
    }
}
