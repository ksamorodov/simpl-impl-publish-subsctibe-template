package com.ksamorodov.task.saber.strategy;

import java.util.List;
import java.util.concurrent.Flow;

public interface PublishingStrategy<T> {

    void publish(List<Flow.Subscriber<? super T>> subscribers, T item);

}
