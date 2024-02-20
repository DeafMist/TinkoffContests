package alltoscala.stack.task2.service.impl;

import alltoscala.stack.task2.client.Client;
import alltoscala.stack.task2.client.model.Address;
import alltoscala.stack.task2.client.model.Event;
import alltoscala.stack.task2.client.model.Result;
import alltoscala.stack.task2.service.Handler;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class HandlerImpl implements Handler {
    private final Client client;

    private final Executor delayed = CompletableFuture.delayedExecutor(timeout().toMillis(), TimeUnit.MILLISECONDS);

    public HandlerImpl(Client client) {
        this.client = client;
    }

    @Override
    public Duration timeout() {
        return Duration.ofSeconds(5);
    }

    @Override
    public void performOperation() {
        Event event = client.readData();
        for (Address address : event.recipients()) {
            CompletableFuture.supplyAsync(() -> client.sendData(address, event.payload()))
                    .thenApply(result -> {
                        while (result == Result.REJECTED) {
                            result = CompletableFuture
                                    .supplyAsync(() -> client.sendData(address, event.payload()), delayed).join();
                        }
                        return result;
                    });
            }
    }
}
