package com.yao.design.code.demo.demo2;

/**
 * @author pengjie_yao
 * @date 2019/12/18 16:53
 */
public class Demo {

    public static void main(String[] args) {
        ApiStatInfo apiStatInfo = new ApiStatInfo();
        // ...省略设置apiStatInfo数据值的代码
        // todo 改动4 设置timeoutCount值
        apiStatInfo.setTimeoutCount(289);

        ApplicationContext.getInstance().getAlert().check(apiStatInfo);

    }
}
