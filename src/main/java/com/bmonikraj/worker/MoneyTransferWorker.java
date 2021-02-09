package com.bmonikraj.worker;

import com.bmonikraj.constant.Constant;
import com.bmonikraj.implementation.AccountActivityImpl;
import com.bmonikraj.implementation.MoneyTransferWorkflowImpl;
import com.bmonikraj.service.MoneyTransferService;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
public class MoneyTransferWorker implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MoneyTransferWorker.class);

    @Override
    public void run() {
        logger.info("Starting MoneyTransferWorker as separate thread, name : {}", Thread.currentThread().getName());
        WorkflowServiceStubs serviceStubs = WorkflowServiceStubs.newInstance();
        WorkflowClient workflowClient = WorkflowClient.newInstance(serviceStubs);
        WorkerFactory factory = WorkerFactory.newInstance(workflowClient);
        Worker worker = factory.newWorker(Constant.MONEY_TRANSFER_TASK_QUEUE);
        worker.registerWorkflowImplementationTypes(MoneyTransferWorkflowImpl.class);
        worker.registerActivitiesImplementations(new AccountActivityImpl());
        factory.start();
    }
}
