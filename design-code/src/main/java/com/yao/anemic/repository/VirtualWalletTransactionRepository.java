package com.yao.anemic.repository;

import com.yao.anemic.module.entry.VirtualWalletTransactionEntity;

/**
 * @author pengjie_yao
 * @date 2019/12/6 11:43
 */
public class VirtualWalletTransactionRepository {

    /**
     *  保存交易的实体信息
     * @param transactionEntity
     * @return
     */
    public Long saveTransaction(VirtualWalletTransactionEntity transactionEntity) {
        System.out.println("数据库保存交易的实体信息");
        return 0L;
    }

    /**
     * 更新交易流水的状态
     *
     * @param transactionId
     * @param status
     */
    public void updateStatus(Long transactionId, String status) {
        System.out.println("更新交易流水的状态为:"+status);
    }
}
