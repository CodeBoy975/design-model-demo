package com.yao.anemic.service;

import java.math.BigDecimal;

/**
 * @author pengjie_yao
 * @date 2019/12/5 15:34
 */
public interface VirtualWalletService {
    /**
     *  查询余额
     * @param walletld
     * @return
     */
    BigDecimal getBalance(Long walletld);

    /**
     *  出账
     * @param walletId
     * @param amount
     */
    void debit(Long walletId, BigDecimal amount);

    /**
     *  入账
     * @param walletId
     * @param amount
     */
    void credit(Long walletId, BigDecimal amount);

    /**
     *  转账
     * @param fromWalletId
     * @param toWalletId
     * @param amount
     */
    void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount);
}
