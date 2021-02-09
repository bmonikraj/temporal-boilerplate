package com.bmonikraj.implementation;

import com.bmonikraj.definition.AccountActivity;
import com.bmonikraj.model.DTO.MoneyTransferRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountActivityImpl implements AccountActivity {
    private static final Logger logger = LoggerFactory.getLogger(AccountActivityImpl.class);


    @Override
    public void deposit(MoneyTransferRequest moneyTransferRequest) {
        logger.info("Withdrawing {} from {}. ReferenceID: {}", moneyTransferRequest.getAmount(), moneyTransferRequest.getFromAccount(), moneyTransferRequest.getReferenceId());
    }

    @Override
    public void withdraw(MoneyTransferRequest moneyTransferRequest) {
        logger.info("Depositing {} to {}. ReferenceID: {}", moneyTransferRequest.getAmount(), moneyTransferRequest.getToAccount(), moneyTransferRequest.getReferenceId());
    }
}
