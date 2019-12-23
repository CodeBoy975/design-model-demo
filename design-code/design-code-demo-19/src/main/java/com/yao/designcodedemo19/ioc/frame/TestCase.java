package com.yao.designcodedemo19.ioc.frame;

/**
 * 框架实现
 * @author pengjie_yao
 * @date 2019/12/23 17:53
 */
public abstract class TestCase {

    public void run() {
        if(doTest()) {
            System.out.println("测试成功");
        }else {
            System.out.println("测试失败");
        }
    }

    /**
     *  测试执行方法
     * @return
     */
    public abstract boolean doTest();
}
