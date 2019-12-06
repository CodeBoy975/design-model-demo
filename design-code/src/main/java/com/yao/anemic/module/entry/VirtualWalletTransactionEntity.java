package com.yao.anemic.module.entry;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 *  虚拟钱包交易实体
 * @author pengjie_yao
 * @date 2019/12/5 15:56
 */
@Data
public class VirtualWalletTransactionEntity {

    /**
     *  金额
     */
    BigDecimal amount;

    /**
     * 创建时间
     */
    Long createTime;

    /**
     *  入账账号
     */
    Long fromWalletId;

    /**
     *  出账账号
     */
    Long toWalletId;

    /**
     *  状态
     */
    String status;

}
