package com.ksamorodov.task.saber.publisher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.FluxSink;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PublisherTest {

    @Mock
    private Flow.Subscriber<String> subscriber;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldDeliverMessagesToSubscriber() throws InterruptedException {
        Publisher publisher = new Publisher(FluxSink.OverflowStrategy.ERROR);
        publisher.subscribe(subscriber);

        CountDownLatch latch = new CountDownLatch(10);
        doAnswer(invocation -> {
            String message = invocation.getArgument(0);
            System.out.println("Received message: " + message);
            latch.countDown();
            return null;
        }).when(subscriber).onNext(any());

        latch.await(5, TimeUnit.SECONDS);
        verify(subscriber, times(10)).onNext(any());
        verify(subscriber, never()).onError(any());
        verify(subscriber).onComplete();
    }
}
