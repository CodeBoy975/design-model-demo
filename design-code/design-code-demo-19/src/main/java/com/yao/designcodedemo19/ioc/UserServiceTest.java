package com.yao.designcodedemo19.ioc;

/**
 *  控制反转的例子
 *   该类可以看出，所有的流程都是由程序员进行控制，则如果用框架实现的话，查看frame下的类
 * @author pengjie_yao
 * @date 2019/12/23 17:50
 */
public class UserServiceTest {

    public static boolean doTest() {
        System.out.println("执行测试例子");
        return true;
    }

    public static void main(String[] args) {

        // 这部分逻辑可以放到框架中
        if (doTest()) {
            System.out.println("测试成功");
        }else {
            System.out.println("测试失败");
        }
    }
}
