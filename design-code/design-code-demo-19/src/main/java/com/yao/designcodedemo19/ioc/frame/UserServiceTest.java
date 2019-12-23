package com.yao.designcodedemo19.ioc.frame;

/**
 * @author pengjie_yao
 * @date 2019/12/23 17:56
 */
public class UserServiceTest extends TestCase {
    @Override
    public boolean doTest() {

        System.out.println("用户层的测试例子");
        return false;
    }

    JunitApplication junitApplication = new JunitApplication();
// 注册操作还可以通过配置的方式来实现，不需要程序员显示调用register()
// junitApplication.register(new UserServiceTest());
}
