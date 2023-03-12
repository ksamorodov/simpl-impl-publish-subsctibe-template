package com.ksamorodov.task.saber.publisher;

import com.ksamorodov.task.saber.strategy.impl.BroadcastStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Flow;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

public class PublisherTest {

    @Mock
    private BroadcastStrategy<String> strategy;

    @Mock
    private Flow.Subscriber<? super String> subscriber;

    private Publisher<String> publisher;

    @BeforeEach
    public void setUp() {
        strategy = new BroadcastStrategy<>();
        publisher = new Publisher<>(strategy);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSubscribe() {
        publisher.subscribe(subscriber);

        verify(subscriber).onSubscribe(any(Flow.Subscription.class));
    }
}
