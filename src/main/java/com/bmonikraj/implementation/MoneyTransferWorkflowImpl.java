package com.bmonikraj.implementation;

import com.bmonikraj.definition.AccountActivity;
import com.bmonikraj.definition.MoneyTransferWorkflow;
import com.bmonikraj.model.DTO.MoneyTransferRequest;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class MoneyTransferWorkflowImpl implements MoneyTransferWorkflow {
    private static final Logger logger = LoggerFactory.getLogger(MoneyTransferWorkflowImpl.class);

    private final RetryOptions retryOptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(1))
            .setMaximumInterval(Duration.ofSeconds(100))
            .setBackoffCoefficient(2)
            .build();
    private final ActivityOptions activityOptions = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            .setRetryOptions(retryOptions)
            .build();

    private final AccountActivity accountActivity = Workflow.newActivityStub(AccountActivity.class, activityOptions);

    @Override
    public void transfer(MoneyTransferRequest moneyTransferRequest) {
        accountActivity.withdraw(moneyTransferRequest);
        accountActivity.deposit(moneyTransferRequest);
    }
}
