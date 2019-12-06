package com.yao.ddd.module;

import com.yao.anemic.repository.VirtualWalletRepository;
import com.yao.anemic.repository.VirtualWalletTransactionRepository;

import java.math.BigDecimal;

/**
 *  Domain领域模型(充血模型)
 * @author pengjie_yao
 * @date 2019/12/6 11:54
 */

public class VirtualWallet {
    /**
     *  id
     */
    private Long id;
    /**
     *  创建时间
     */
    private Long createTime = System.currentTimeMillis();
    ;
    private BigDecimal balance = BigDecimal.ZERO;

    public VirtualWallet(Long preAllocatedId) {
        this.id = preAllocatedId;
    }

    /**
     *  余额
     * @return
     */
    public BigDecimal balance() {
        return this.balance;
    }

    /**
     *  出账
     * @param amount
     */
    public void debit(BigDecimal amount) {
        if (this.balance.compareTo(amount) < 0) {
            System.out.println("抛出异常");
        }
        this.balance.subtract(amount);
    }

    /**
     *  进账
     * @param amount
     */
    public void credit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("抛出异常");
        }
        this.balance.add(amount);
    }


}

