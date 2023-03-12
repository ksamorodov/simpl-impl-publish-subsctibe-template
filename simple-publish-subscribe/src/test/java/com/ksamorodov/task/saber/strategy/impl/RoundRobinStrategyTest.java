package com.ksamorodov.task.saber.strategy.impl;


import com.ksamorodov.task.saber.strategy.PublishingStrategy;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.List;
import java.util.concurrent.Flow;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class RoundRobinStrategyTest {

    @Test
    void publish_noSubscribers_nothingHappens() {
        // Arrange
        List<Flow.Subscriber<? super String>> subscribers = List.of();
        PublishingStrategy<String> strategy = new RoundRobinStrategy<>();

        // Act
        strategy.publish(subscribers, "test");

        // Assert
        // Nothing should happen since there are no subscribers
    }

    @Test
    void publish_oneSubscriber_publishesToIt() {
        // Arrange
        Flow.Subscriber<? super String> subscriber = mock(Flow.Subscriber.class);
        List<Flow.Subscriber<? super String>> subscribers = List.of(subscriber);
        PublishingStrategy<String> strategy = new RoundRobinStrategy<>();

        // Act
        strategy.publish(subscribers, "test");

        // Assert
        verify(subscriber).onNext("test");
    }

    @Test
    void publish_multipleSubscribers_publishesInRoundRobinOrder() {
        // Arrange
        Flow.Subscriber<? super String> subscriber1 = mock(Flow.Subscriber.class);
        Flow.Subscriber<? super String> subscriber2 = mock(Flow.Subscriber.class);
        Flow.Subscriber<? super String> subscriber3 = mock(Flow.Subscriber.class);
        List<Flow.Subscriber<? super String>> subscribers = List.of(subscriber1, subscriber2, subscriber3);
        PublishingStrategy<String> strategy = new RoundRobinStrategy<>();

        // Act
        strategy.publish(subscribers, "test1");
        strategy.publish(subscribers, "test2");
        strategy.publish(subscribers, "test3");
        strategy.publish(subscribers, "test4");

        // Assert
        InOrder inOrder = Mockito.inOrder(subscriber1, subscriber2, subscriber3);
        inOrder.verify(subscriber1).onNext("test1");
        inOrder.verify(subscriber2).onNext("test2");
        inOrder.verify(subscriber3).onNext("test3");
        inOrder.verify(subscriber1).onNext("test4");
    }

    @Test
    void publish_multipleSubscribers_wrapsAroundAfterLastSubscriber() {
        // Arrange
        Flow.Subscriber<? super String> subscriber1 = mock(Flow.Subscriber.class);
        Flow.Subscriber<? super String> subscriber2 = mock(Flow.Subscriber.class);
        List<Flow.Subscriber<? super String>> subscribers = List.of(subscriber1, subscriber2);
        PublishingStrategy<String> strategy = new RoundRobinStrategy<>();

        // Act
        strategy.publish(subscribers, "test1");
        strategy.publish(subscribers, "test2");
        strategy.publish(subscribers, "test3");
        strategy.publish(subscribers, "test4");
        strategy.publish(subscribers, "test5");

        // Assert
        InOrder inOrder = Mockito.inOrder(subscriber1, subscriber2);
        inOrder.verify(subscriber1).onNext("test1");
        inOrder.verify(subscriber2).onNext("test2");
        inOrder.verify(subscriber1).onNext("test3");
        inOrder.verify(subscriber2).onNext("test4");
        inOrder.verify(subscriber1).onNext("test5");
    }

}
