package com.yao.design.code.demo.anemic.service.Impl;

import com.yao.design.code.demo.anemic.module.bo.VirtualWalletBo;
import com.yao.design.code.demo.anemic.module.entry.VirtualWalletEntity;
import com.yao.design.code.demo.anemic.module.entry.VirtualWalletTransactionEntity;
import com.yao.design.code.demo.anemic.repository.VirtualWalletRepository;
import com.yao.design.code.demo.anemic.repository.VirtualWalletTransactionRepository;
import com.yao.design.code.demo.anemic.service.VirtualWalletService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;

/**
 * 虚拟钱包service
 *
 * @author pengjie_yao
 * @date 2019/12/5 15:34
 */
public class VirtualWalletServiceImpl implements VirtualWalletService {


    @Autowired
    private VirtualWalletRepository virtualWalletRepository;

    @Autowired
    private VirtualWalletTransactionRepository transactionRepo;

    /**
     * 根据id获取虚拟钱包
     *
     * @param walletId
     * @return
     */
    public VirtualWalletBo getVirtualWallet(Long walletId) {
        VirtualWalletEntity walletEntity = virtualWalletRepository.getWalletEntity(walletId);
        VirtualWalletBo walletBo = convert(walletEntity);
        return walletBo;
    }

    /**
     * 实体之间的转换
     *
     * @param walletEntity
     * @return
     */
    private VirtualWalletBo convert(VirtualWalletEntity walletEntity) {
        VirtualWalletBo virtualWalletBo = new VirtualWalletBo();
        BeanUtils.copyProperties(walletEntity, virtualWalletBo);
        return virtualWalletBo;
    }


    /**
     * 查询余额
     *
     * @param walletld
     * @return
     */
    @Override
    public BigDecimal getBalance(Long walletld) {
        return virtualWalletRepository.getBalance(walletld);
    }

    /**
     * 出账
     *
     * @param walletId
     * @param amount
     */
    @Override
    public void debit(Long walletId, BigDecimal amount) {

        VirtualWalletEntity walletEntity = virtualWalletRepository.getWalletEntity(walletId);
        BigDecimal balance = walletEntity.getBalance();
        if (balance.compareTo(amount) < 0) {
            System.out.println("此处抛出异常");
        }
        virtualWalletRepository.updateBalance(walletId, balance.subtract(amount));
    }

    /**
     * 入账
     *
     * @param walletId
     * @param amount
     */
    @Override
    public void credit(Long walletId, BigDecimal amount) {
        VirtualWalletEntity walletEntity = virtualWalletRepository.getWalletEntity(walletId);
        BigDecimal balance = walletEntity.getBalance();
        virtualWalletRepository.updateBalance(walletId, balance.add(amount));
    }

    /**
     * 转账
     *
     * @param fromWalletId
     * @param toWalletId
     * @param amount
     */
    @Override
    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
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
}
