package ru.netology.kubrakova.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.netology.kubrakova.configuration.OperationProperties;
import ru.netology.kubrakova.model.Operation;

import java.util.LinkedList;
import java.util.Queue;

@Service
public class AsyncInputOperationService {
    private final OperationService operationService;
    private final OperationProperties operationProperties;

    public AsyncInputOperationService(OperationService operationService, OperationProperties operationProperties) {
        this.operationService = operationService;
        this.operationProperties = operationProperties;
    }

    private final Queue<Operation> queue = new LinkedList<>();

    public void offerOperation(Operation operation) {
        queue.offer(operation);
    }

    private void processQueue() {
        while (true) {
            Operation operation = queue.poll();
            if (operation == null) {
                try {
                    System.out.println("Processing...");
                    Thread.sleep(operationProperties.getSleepMilliSeconds());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Processing:" + operation);
                operationService.addOperation(operation);
            }
        }
    }

    @PostConstruct
    public void startAsyncOperationProcessing() {
        Thread t = new Thread(this::processQueue);
        t.start();
    }
}
