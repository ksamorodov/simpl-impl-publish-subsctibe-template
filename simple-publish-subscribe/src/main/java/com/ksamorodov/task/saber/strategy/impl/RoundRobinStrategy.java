package com.ksamorodov.task.saber.strategy.impl;

import com.ksamorodov.task.saber.strategy.PublishingStrategy;

import java.util.List;
import java.util.concurrent.Flow;

/**
 * Класс, реализующий стратегию паблишера "Round Robin".
 * При этой стратегии каждый новый элемент публикуется у следующего подписчика в круговом порядке.
 * @param <T> тип элементов, которые публикуются подписчикам
 */
public class RoundRobinStrategy<T> implements PublishingStrategy<T> {

    private int currentIndex = 0;

    /**
     * Метод, публикующий новый элемент у следующего подписчика в круговом порядке.
     * @param subscribers список подписчиков, которым нужно опубликовать элемент
     * @param item элемент, который нужно опубликовать
     */
    @Override
    public void publish(List<Flow.Subscriber<? super T>> subscribers, T item) {
        if (subscribers.isEmpty()) {
            return;
        }

        Flow.Subscriber<? super T> subscriber = subscribers.get(currentIndex);

        // Опубликовать элемент подписчику
        subscriber.onNext(item);

        // Обновление индекса
        currentIndex = (currentIndex + 1) % subscribers.size();
    }

}

