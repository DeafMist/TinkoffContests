package alltoscala.stack.task1.service.impl;

import alltoscala.stack.task1.client.Client;
import alltoscala.stack.task1.client.model.Response;
import alltoscala.stack.task1.dto.ApplicationStatusResponse;
import alltoscala.stack.task1.service.Handler;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

public class HandlerImpl implements Handler {
    private static final Logger LOGGER = Logger.getLogger(HandlerImpl.class.getName());

    private final AtomicReference<Duration> lastRequestTime = new AtomicReference<>();

    private final AtomicInteger retriesCount = new AtomicInteger();

    private final Client client;

    public HandlerImpl(Client client) {
        this.client = client;
    }

    @Override
    public ApplicationStatusResponse performOperation(String id) {
        lastRequestTime.set(Duration.ofMillis(System.currentTimeMillis()));
        retriesCount.set(0);

        CompletableFuture<Response> completableFuture1 = CompletableFuture
                .supplyAsync(() -> client.getApplicationStatus1(id))
                .thenApply(response -> {
                    retriesCount.addAndGet(1);
                    lastRequestTime.set(Duration.ofMillis(System.currentTimeMillis()));

                    while (response instanceof Response.RetryAfter retryAfter) {
                        Executor delayed = CompletableFuture
                                .delayedExecutor(retryAfter.delay().toMillis(), TimeUnit.MILLISECONDS);
                        response = CompletableFuture.supplyAsync(() -> client.getApplicationStatus1(id), delayed).join();
                        retriesCount.addAndGet(1);
                        lastRequestTime.set(Duration.ofMillis(System.currentTimeMillis()));
                    }

                    return response;
                });

        CompletableFuture<Response> completableFuture2 = CompletableFuture
                .supplyAsync(() -> client.getApplicationStatus2(id))
                .thenApply(response -> {
                    retriesCount.addAndGet(1);
                    lastRequestTime.set(Duration.ofMillis(System.currentTimeMillis()));

                    while (response instanceof Response.RetryAfter retryAfter) {
                        Executor delayed = CompletableFuture
                                .delayedExecutor(retryAfter.delay().toMillis(), TimeUnit.MILLISECONDS);
                        response = CompletableFuture.supplyAsync(() -> client.getApplicationStatus2(id), delayed).join();
                        retriesCount.addAndGet(1);
                        lastRequestTime.set(Duration.ofMillis(System.currentTimeMillis()));
                    }
                    return response;
                });

        CompletableFuture<Object> completableFuture = CompletableFuture.anyOf(completableFuture1, completableFuture2);
        try {
            Response response = (Response) completableFuture.get(15, TimeUnit.SECONDS);
            return toApplicationStatusResponse(response);
        } catch (Exception ex) {
            LOGGER.warning("Can't get response from services");
        }
        return new ApplicationStatusResponse.Failure(lastRequestTime.get(), retriesCount.get());
    }

    private ApplicationStatusResponse toApplicationStatusResponse(Response response) {
        if (response instanceof Response.Success success) {
            return new ApplicationStatusResponse.Success(success.applicationId(), success.applicationStatus());
        }

        return new ApplicationStatusResponse.Failure(lastRequestTime.get(), retriesCount.get());
    }
}
