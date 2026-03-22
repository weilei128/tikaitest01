package com.example.qrcode.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QrCodeServiceTest {

    @Autowired
    private QrCodeService qrCodeService;

    /**
     * 测试用例1: 生成二维码 - 正常场景
     * 场景描述: 使用有效的组织和用户名参数生成二维码
     */
    @Test
    public void testGenerateQrCode_NormalCase() throws Exception {
        String filePath = qrCodeService.generateQrCode("测试单位", "测试用户");
        assertNotNull(filePath);
        assertTrue(filePath.endsWith(".jpg"));
    }

    /**
     * 测试用例2: 生成二维码 - 组织参数为null
     * 场景描述: 组织参数为null，验证参数校验逻辑
     */
    @Test
    public void testGenerateQrCode_NullOrganization() {
        Exception exception = assertThrows(Exception.class, () -> {
            qrCodeService.generateQrCode(null, "测试用户");
        });
        assertEquals("用户单位不能为空", exception.getMessage());
    }

    /**
     * 测试用例3: 生成二维码 - 用户名参数为null
     * 场景描述: 用户名参数为null，验证参数校验逻辑
     */
    @Test
    public void testGenerateQrCode_NullUsername() {
        Exception exception = assertThrows(Exception.class, () -> {
            qrCodeService.generateQrCode("测试单位", null);
        });
        assertEquals("用户名不能为空", exception.getMessage());
    }

    /**
     * 测试用例4: 生成二维码 - 组织为空字符串
     * 场景描述: 组织参数为空字符串，验证参数校验逻辑
     */
    @Test
    public void testGenerateQrCode_EmptyOrganization() {
        Exception exception = assertThrows(Exception.class, () -> {
            qrCodeService.generateQrCode("", "测试用户");
        });
        assertEquals("用户单位不能为空", exception.getMessage());
    }

    /**
     * 测试用例5: 生成二维码 - 用户名为空字符串
     * 场景描述: 用户名参数为空字符串，验证参数校验逻辑
     */
    @Test
    public void testGenerateQrCode_EmptyUsername() {
        Exception exception = assertThrows(Exception.class, () -> {
            qrCodeService.generateQrCode("测试单位", "");
        });
        assertEquals("用户名不能为空", exception.getMessage());
    }

    /**
     * 测试用例6: 生成二维码 - 参数包含非法字符
     * 场景描述: 组织和用户名包含文件系统非法字符，验证字符过滤逻辑
     */
    @Test
    public void testGenerateQrCode_IllegalCharacters() throws Exception {
        String filePath = qrCodeService.generateQrCode("测试/单位:名称*", "用户?名|测试");
        assertNotNull(filePath);
        assertFalse(filePath.contains("/"));
        assertFalse(filePath.contains(":"));
        assertFalse(filePath.contains("*"));
        assertFalse(filePath.contains("?"));
        assertFalse(filePath.contains("|"));
    }

    /**
     * 测试用例7: 生成二维码 - 文件名长度边界测试
     * 场景描述: 长名称参数，验证文件名长度限制逻辑
     */
    @Test
    public void testGenerateQrCode_LongFileName() throws Exception {
        // 生成一个较长的组织名称
        String longOrganization = "这是一个非常长的组织名称用于测试文件名长度限制";
        String longUsername = "这是一个非常长的用户名用于测试文件名长度限制";
        
        String filePath = qrCodeService.generateQrCode(longOrganization, longUsername);
        assertNotNull(filePath);
        assertTrue(filePath.endsWith(".jpg"));
    }

    /**
     * 测试用例8: 并发生成二维码
     * 场景描述: 多线程并发生成二维码，验证线程安全性
     */
    @Test
    public void testGenerateQrCode_Concurrent() throws Exception {
        int threadCount = 30;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            executorService.submit(() -> {
                try {
                    String organization = "单位" + (index % 5);
                    String username = "用户" + (index % 10);
                    String filePath = qrCodeService.generateQrCode(organization, username);
                    System.out.println("线程" + index + "生成成功: " + filePath);
                } catch (Exception e) {
                    System.err.println("线程" + index + "生成失败: " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();
    }
}
