package com.ksamorodov.task.saber.strategy;

import java.util.List;
import java.util.concurrent.Flow;

/**
 * Интерфейс, определяющий стратегию паблишера.
 * @param <T> тип публикуемых элементов.
 */
public interface PublishingStrategy<T> {
    /**
     * Метод, который публикует переданный элемент всем подписчикам, используя определенную стратегию.
     * @param subscribers список подписчиков.
     * @param item элемент для публикации.
     */
    void publish(List<Flow.Subscriber<? super T>> subscribers, T item);

}
