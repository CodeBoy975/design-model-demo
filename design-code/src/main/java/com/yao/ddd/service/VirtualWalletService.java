package com.yao.ddd.service;

import com.yao.anemic.module.bo.VirtualWalletBo;
import com.yao.anemic.module.entry.VirtualWalletEntity;
import com.yao.anemic.module.entry.VirtualWalletTransactionEntity;
import com.yao.anemic.repository.VirtualWalletRepository;
import com.yao.anemic.repository.VirtualWalletTransactionRepository;
import com.yao.ddd.module.VirtualWallet;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;

/**
 *  充血模式的service
 * @author pengjie_yao
 * @date 2019/12/6 11:55
 */
public class VirtualWalletService {


    @Autowired
    private VirtualWalletRepository walletRepo;
    @Autowired
    private VirtualWalletTransactionRepository transactionRepo;

    public VirtualWallet getVirtualWallet(Long walletId) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWallet wallet = convert(walletEntity);
        return wallet;
    }

    /**
     *  获取余额
     * @param walletId
     * @return
     */
    public BigDecimal getBalance(Long walletId) {
        return walletRepo.getBalance(walletId);
    }

    /**
     *  出账
     * @param walletId
     * @param amount
     */
    public void debit(Long walletId, BigDecimal amount) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWallet wallet = convert(walletEntity);
        wallet.debit(amount);
        walletRepo.updateBalance(walletId, wallet.balance());
    }

    /**
     *  入账
     * @param walletId
     * @param amount
     */
    public void credit(Long walletId, BigDecimal amount) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWallet wallet = convert(walletEntity);
        wallet.credit(amount);
        walletRepo.updateBalance(walletId, wallet.balance());
    }

    /**
     *  记录交易数据
     * @param fromWalletId
     * @param toWalletId
     * @param amount
     */
    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
        //...跟基于贫血模型的传统开发模式的代码一样...
        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setCreateTime(System.currentTimeMillis());
        transactionEntity.setFromWalletId(fromWalletId);
        transactionEntity.setToWalletId(toWalletId);
        transactionEntity.setStatus("待执行状态");
        Long transactionId = transactionRepo.saveTransaction(transactionEntity);
        try {
            debit(fromWalletId, amount);
            credit(toWalletId, amount);
        } catch (ConstraintViolationException e) {
            transactionRepo.updateStatus(transactionId, "关闭的状态");
        } catch (Exception e) {
            transactionRepo.updateStatus(transactionId, "失败的状态");
        }
        transactionRepo.updateStatus(transactionId, "已执行状态");
    }

    /**
     * 实体之间的转换
     *
     * @param walletEntity
     * @return
     */
    private VirtualWallet convert(VirtualWalletEntity walletEntity) {
        VirtualWallet virtualWallet = new VirtualWallet(1L);
        BeanUtils.copyProperties(walletEntity, virtualWallet);
        return virtualWallet;
    }
}

