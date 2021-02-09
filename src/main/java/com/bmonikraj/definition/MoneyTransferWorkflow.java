package com.bmonikraj.definition;

import com.bmonikraj.model.DTO.MoneyTransferRequest;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface MoneyTransferWorkflow {

    @WorkflowMethod
    void transfer(MoneyTransferRequest moneyTransferRequest);
}
