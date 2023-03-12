package com.ksamorodov.task.saber.subscribers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.Flow;

public class SubscriberTest {

    @Test
    public void testSubscriber() {
        Flow.Subscription subscription = Mockito.mock(Flow.Subscription.class);
        Subscriber subscriber = new Subscriber("TestSubscriber");
        subscriber.onSubscribe(subscription);
        subscriber.onNext("test");
        subscriber.onComplete();
        Mockito.verify(subscription, Mockito.times(1)).request(1);
    }
}

