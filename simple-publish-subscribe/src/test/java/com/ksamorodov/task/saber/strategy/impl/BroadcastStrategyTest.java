package com.ksamorodov.task.saber.strategy.impl;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Flow;

import static org.mockito.Mockito.*;

class BroadcastStrategyTest {

    @Test
    void testPublish() {
        // Create a mock subscriber
        Flow.Subscriber<String> mockSubscriber = mock(Flow.Subscriber.class);

        // Create a list with the mock subscriber
        List<Flow.Subscriber<? super String>> subscribers = Arrays.asList(mockSubscriber);

        // Create a new instance of BroadcastStrategy
        BroadcastStrategy<String> broadcastStrategy = new BroadcastStrategy<>();

        // Publish a message
        String message = "Hello, world!";
        broadcastStrategy.publish(subscribers, message);

        // Verify that the message was sent to the mock subscriber
        verify(mockSubscriber).onNext(message);
    }

    @Test
    void testPublishMultipleSubscribers() {
        // Create two mock subscribers
        Flow.Subscriber<String> mockSubscriber1 = mock(Flow.Subscriber.class);
        Flow.Subscriber<String> mockSubscriber2 = mock(Flow.Subscriber.class);

        // Create a list with the mock subscribers
        List<Flow.Subscriber<? super String>> subscribers = Arrays.asList(mockSubscriber1, mockSubscriber2);

        // Create a new instance of BroadcastStrategy
        BroadcastStrategy<String> broadcastStrategy = new BroadcastStrategy<>();

        // Publish a message
        String message = "Hello, world!";
        broadcastStrategy.publish(subscribers, message);

        // Verify that the message was sent to both mock subscribers
        verify(mockSubscriber1).onNext(message);
        verify(mockSubscriber2).onNext(message);
    }

    @Test
    void testPublishNoSubscribers() {
        // Create an empty list of subscribers
        List<Flow.Subscriber<? super String>> subscribers = Arrays.asList();

        // Create a new instance of BroadcastStrategy
        BroadcastStrategy<String> broadcastStrategy = new BroadcastStrategy<>();

        // Publish a message
        String message = "Hello, world!";
        broadcastStrategy.publish(subscribers, message);

        // Verify that no messages were sent
        verifyNoMoreInteractions(mock(Flow.Subscriber.class));
    }

}

