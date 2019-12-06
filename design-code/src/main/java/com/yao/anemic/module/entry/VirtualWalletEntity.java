package com.yao.anemic.module.entry;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 虚拟钱包
 * @author pengjie_yao
 * @date 2019/12/5 15:45
 */
@Data
public class VirtualWalletEntity {

    /**
     * id
     */
    private Long id;

    /**
     *  创建时间
     */
    private Long createTime;

    /**
     *  余额
     */
    private BigDecimal balance;
}
