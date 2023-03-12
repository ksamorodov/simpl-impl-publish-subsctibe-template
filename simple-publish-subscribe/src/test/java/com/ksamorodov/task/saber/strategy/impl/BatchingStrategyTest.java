package com.ksamorodov.task.saber.strategy.impl;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

import static org.mockito.Mockito.*;

class BatchingStrategyTest {

    @Test
    void testPublish() {
        int batchSize = 3;
        BatchingStrategy<String> strategy = new BatchingStrategy<>(batchSize);

        // create some mock subscribers
        Flow.Subscriber<String> subscriber1 = mock(Flow.Subscriber.class);
        Flow.Subscriber<String> subscriber2 = mock(Flow.Subscriber.class);

        List<Flow.Subscriber<? super String>> subscribers = new ArrayList<>();
        subscribers.add(subscriber1);
        subscribers.add(subscriber2);

        // publish one message, it should not be sent to subscribers yet
        strategy.publish(subscribers, "message1");
        verify(subscriber1, never()).onNext(any());
        verify(subscriber2, never()).onNext(any());

        // publish two more messages, they should be batched together and sent to subscribers
        strategy.publish(subscribers, "message2");
        strategy.publish(subscribers, "message3");

        verify(subscriber1, times(1)).onNext("message1");
        verify(subscriber1, times(1)).onNext("message2");
        verify(subscriber1, times(1)).onNext("message3");

        verify(subscriber2, times(1)).onNext("message1");
        verify(subscriber2, times(1)).onNext("message2");
        verify(subscriber2, times(1)).onNext("message3");

        // publish one more message, it should not be batched yet
        strategy.publish(subscribers, "message4");

        verify(subscriber1, never()).onNext("message4");
        verify(subscriber2, never()).onNext("message4");
    }

    @Test
    void testPublishRemaining() {
        BatchingStrategy<String> strategy = new BatchingStrategy<>(3);

        // create some mock subscribers
        Flow.Subscriber<String> subscriber1 = mock(Flow.Subscriber.class);
        Flow.Subscriber<String> subscriber2 = mock(Flow.Subscriber.class);

        List<Flow.Subscriber<? super String>> subscribers = new ArrayList<>();
        subscribers.add(subscriber1);
        subscribers.add(subscriber2);

        // publish some messages
        strategy.publish(subscribers, "message1");
        strategy.publish(subscribers, "message2");
        strategy.publish(subscribers, "message3");

        // call publishRemaining, all messages should be sent to subscribers and onComplete should be called
        strategy.publishRemaining(subscribers);

        verify(subscriber1, times(1)).onNext("message1");
        verify(subscriber1, times(1)).onNext("message2");
        verify(subscriber1, times(1)).onNext("message3");
        verify(subscriber1, times(1)).onComplete();

        verify(subscriber2, times(1)).onNext("message1");
        verify(subscriber2, times(1)).onNext("message2");
        verify(subscriber2, times(1)).onNext("message3");
        verify(subscriber2, times(1)).onComplete();
    }
}
