package com.yao.design.code.demo.anemic.repository;

import com.yao.design.code.demo.anemic.module.entry.VirtualWalletEntity;

import java.math.BigDecimal;

/**
 *  虚拟钱包与数据库交互
 * @author pengjie_yao
 * @date 2019/12/5 15:41
 */
public class VirtualWalletRepository {

    /**
     *  查询余额
     * @param walletld
     * @return
     */
    public BigDecimal getBalance(Long walletld) {
        System.out.println("数据库根据id："+walletld+"查询余额");
        return new BigDecimal(100);
    }

    /**
     *  根据id获取实体
     * @param walletId
     * @return
     */
    public VirtualWalletEntity getWalletEntity(Long walletId) {
        System.out.println("数据库根据id："+walletId+"获取实体对象数据");
        return new VirtualWalletEntity();
    }

    /**
     *  数据库更新余额
     * @param walletId
     * @param subtract
     */
    public void updateBalance(Long walletId, BigDecimal subtract) {
        System.out.println("数据库执行更新id:"+walletId+"的余额更新为："+subtract);
    }
}
