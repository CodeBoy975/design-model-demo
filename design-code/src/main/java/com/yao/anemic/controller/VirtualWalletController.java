package com.yao.anemic.controller;

import com.yao.anemic.service.VirtualWalletService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 *  贫血模式-虚拟钱包设计
 * @author pengjie_yao
 * @date 2019/12/5 15:32
 */
public class VirtualWalletController {


    @Autowired
    private VirtualWalletService virtualWalletService;


    /**
     *  查询余额
     * @param walletld
     * @return
     */
    public BigDecimal getBalance(Long walletld) {
        return virtualWalletService.getBalance(walletld);
    }


    /**
     *  出账
     * @param walletId
     * @param amount
     */
    public void debit(Long walletId, BigDecimal amount) {
        virtualWalletService.debit(walletId,amount);
    }


    /**
     *  入账
     * @param walletId
     * @param amount
     */
    public void credit(Long walletId, BigDecimal amount) {
        virtualWalletService.credit(walletId,amount);
    }


    /**
     *  转账
     * @param fromWalletId
     * @param toWalletId
     * @param amount
     */
    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
        virtualWalletService.transfer(fromWalletId, toWalletId, amount);
    }


}
