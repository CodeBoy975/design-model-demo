package com.yao.ddd.module;

import java.math.BigDecimal;

/**
 *  Domain领域模型(充血模型)
 *    如果以后有更多的业务，比如: 支持透支一定额度和冻结部分余额的功能的话，则只要往该类上加入以下代码
 * @author pengjie_yao
 * @date 2019/12/6 11:54
 */

public class VirtualWalletDemo {

    /**
     *  id
     */
    private Long id;
    /**
     *  创建时间
     */
    private Long createTime = System.currentTimeMillis();
    /**
     *  余额
     */
    private BigDecimal balance = BigDecimal.ZERO;
    /**
     *  是否允许超支
     */
    private boolean isAllowedOverdraft = true;
    /**
     *  超支金额
     */
    private BigDecimal overdraftAmount = BigDecimal.ZERO;
    /**
     *  冻结金额
     */
    private BigDecimal frozenAmount = BigDecimal.ZERO;

    public VirtualWalletDemo(Long preAllocatedId) {
        this.id = preAllocatedId;
    }

    public void freeze(BigDecimal amount) {  }
    public void unfreeze(BigDecimal amount) { }
    public void increaseOverdraftAmount(BigDecimal amount) {  }
    public void decreaseOverdraftAmount(BigDecimal amount) {  }
    public void closeOverdraft() {  }
    public void openOverdraft() {  }

    public BigDecimal balance() {
        return this.balance;
    }

    public BigDecimal getAvaliableBalance() {
        BigDecimal totalAvaliableBalance = this.balance.subtract(this.frozenAmount);
        if (isAllowedOverdraft) {
            totalAvaliableBalance = totalAvaliableBalance.add(this.overdraftAmount);
        }
        return totalAvaliableBalance;
    }

    public void debit(BigDecimal amount) {
        BigDecimal totalAvaliableBalance = getAvaliableBalance();
        if (totalAvaliableBalance.compareTo(amount) < 0) {
            System.out.println("抛异常");
        }
        this.balance.subtract(amount);
    }

    public void credit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("抛异常");
        }
        this.balance.add(amount);
    }

    
}

