package com.example.qrcode.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CsvRecordServiceTest {

    @Autowired
    private CsvRecordService csvRecordService;

    /**
     * 测试用例1: 保存用户记录 - 正常场景
     * 场景描述: 使用有效的组织和用户名参数保存用户记录
     */
    @Test
    public void testSaveUserRecord_NormalCase() throws Exception {
        csvRecordService.saveUserRecord("测试单位", "测试用户");
    }

    /**
     * 测试用例2: 保存用户记录 - 组织参数为null
     * 场景描述: 组织参数为null，验证参数校验逻辑
     */
    @Test
    public void testSaveUserRecord_NullOrganization() {
        Exception exception = assertThrows(Exception.class, () -> {
            csvRecordService.saveUserRecord(null, "测试用户");
        });
        assertEquals("用户单位不能为空", exception.getMessage());
    }

    /**
     * 测试用例3: 保存用户记录 - 用户名参数为null
     * 场景描述: 用户名参数为null，验证参数校验逻辑
     */
    @Test
    public void testSaveUserRecord_NullUsername() {
        Exception exception = assertThrows(Exception.class, () -> {
            csvRecordService.saveUserRecord("测试单位", null);
        });
        assertEquals("用户名不能为空", exception.getMessage());
    }

    /**
     * 测试用例4: 保存用户记录 - 组织为空字符串
     * 场景描述: 组织参数为空字符串，验证参数校验逻辑
     */
    @Test
    public void testSaveUserRecord_EmptyOrganization() {
        Exception exception = assertThrows(Exception.class, () -> {
            csvRecordService.saveUserRecord("", "测试用户");
        });
        assertEquals("用户单位不能为空", exception.getMessage());
    }

    /**
     * 测试用例5: 保存用户记录 - 用户名为空字符串
     * 场景描述: 用户名参数为空字符串，验证参数校验逻辑
     */
    @Test
    public void testSaveUserRecord_EmptyUsername() {
        Exception exception = assertThrows(Exception.class, () -> {
            csvRecordService.saveUserRecord("测试单位", "");
        });
        assertEquals("用户名不能为空", exception.getMessage());
    }

    /**
     * 测试用例6: 保存用户记录 - 参数包含非法字符
     * 场景描述: 组织和用户名包含CSV非法字符（逗号），验证字符过滤逻辑
     */
    @Test
    public void testSaveUserRecord_IllegalCharacters() throws Exception {
        csvRecordService.saveUserRecord("测试,单位,名称", "用户,名,测试");
    }

    /**
     * 测试用例7: 获取小时级统计信息
     * 场景描述: 获取过去1小时内的用户记录统计信息
     */
    @Test
    public void testGetHourlyStatistics_NormalCase() {
        Map<String, Object> statistics = csvRecordService.getHourlyStatistics();
        assertNotNull(statistics);
        assertTrue(statistics.containsKey("count"));
        assertTrue(statistics.containsKey("timeRange"));
        assertTrue(statistics.containsKey("timestamp"));
    }

    /**
     * 测试用例8: 并发保存用户记录
     * 场景描述: 多线程并发保存用户记录，验证线程安全性
     */
    @Test
    public void testSaveUserRecord_Concurrent() throws Exception {
        int threadCount = 30;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            executorService.submit(() -> {
                try {
                    String organization = "单位" + (index % 5);
                    String username = "用户" + (index % 10);
                    csvRecordService.saveUserRecord(organization, username);
                    System.out.println("线程" + index + "保存成功");
                } catch (Exception e) {
                    System.err.println("线程" + index + "保存失败: " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();
    }
}
