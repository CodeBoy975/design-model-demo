package com.yao.design.code.demo.anemic.module.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 *  虚拟钱包BO
 * @author pengjie_yao
 * @date 2019/12/5 15:39
 */
@Data
public class VirtualWalletBo {

    /**
     *  id
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
