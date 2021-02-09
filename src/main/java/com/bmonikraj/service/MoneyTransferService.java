package com.bmonikraj.service;

import com.bmonikraj.constant.Constant;
import com.bmonikraj.definition.MoneyTransferWorkflow;
import com.bmonikraj.model.DTO.MoneyTransferRequest;
import com.bmonikraj.model.DTO.MoneyTransferResponse;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MoneyTransferService {
    private static final Logger logger = LoggerFactory.getLogger(MoneyTransferService.class);

    @Value("${workflow.id.money.transfer}")
    private String workflowIdMoneyTransfer;

    public MoneyTransferResponse transferAmountBetweenTwoAccounts(MoneyTransferRequest moneyTransferRequest) {
        MoneyTransferResponse moneyTransferResponse = new MoneyTransferResponse();
        try {
            moneyTransferRequest.setReferenceId(UUID.randomUUID().toString());

            WorkflowServiceStubs serviceStubs = WorkflowServiceStubs.newInstance();
            WorkflowOptions workflowOptions = WorkflowOptions.newBuilder()
                    .setTaskQueue(Constant.MONEY_TRANSFER_TASK_QUEUE)
                    .setWorkflowId(workflowIdMoneyTransfer)
                    .build();

            WorkflowClient workflowClient = WorkflowClient.newInstance(serviceStubs);
            MoneyTransferWorkflow moneyTransferWorkflow = workflowClient.newWorkflowStub(MoneyTransferWorkflow.class, workflowOptions);
            WorkflowExecution workflowExecution = WorkflowClient.start(moneyTransferWorkflow::transfer, moneyTransferRequest);
            logger.info("Transfer of {} from account {} to account {} is processing", moneyTransferRequest.getAmount(), moneyTransferRequest.getFromAccount(), moneyTransferRequest.getToAccount());
            logger.info("Workflow ID : {}, Run ID : {}", workflowExecution.getWorkflowId(), workflowExecution.getRunId());
            moneyTransferResponse.setStatus(Constant.STATUS_SUCCESS);
            moneyTransferResponse.setMessage(Constant.MESSAGE_SUCCESS);
            moneyTransferResponse.setReferenceId(moneyTransferRequest.getReferenceId());
        } catch (Exception exception) {
            logger.error(exception.toString());
            moneyTransferResponse.setStatus(Constant.STATUS_ERROR);
            moneyTransferResponse.setMessage(Constant.MESSAGE_ERROR);
            moneyTransferResponse.setMessage(Constant.REFERENCE_ID_ERROR);
        }
        return moneyTransferResponse;
    }
}
