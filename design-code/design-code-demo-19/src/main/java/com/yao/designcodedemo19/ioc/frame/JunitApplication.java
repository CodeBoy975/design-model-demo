package com.yao.designcodedemo19.ioc.frame;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pengjie_yao
 * @date 2019/12/23 17:55
 */
public class JunitApplication {

    /**
     * 初始化
     */
    private static final List<TestCase> testCases = new ArrayList<>();

    /**
     *  注册
     * @param testCase
     */
    public static void register(TestCase testCase) {
        testCases.add(testCase);
    }

    public static void main(String[] args) {
        for (TestCase testCase : testCases) {
            testCase.run();
        }
    }
}
