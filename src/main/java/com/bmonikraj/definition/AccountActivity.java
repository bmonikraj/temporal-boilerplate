package com.bmonikraj.definition;

import com.bmonikraj.model.DTO.MoneyTransferRequest;
import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface AccountActivity {

    void deposit(MoneyTransferRequest moneyTransferRequest);

    void withdraw(MoneyTransferRequest moneyTransferRequest);
}
