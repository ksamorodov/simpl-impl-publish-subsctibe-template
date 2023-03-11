package com.ksamorodov.task.saber.strategy.impl;

import com.ksamorodov.task.saber.strategy.PublishingStrategy;

import java.util.List;
import java.util.concurrent.Flow.Subscriber;

/**
 * Класс, реализующий стратегию широковещательной передачи сообщений
 * издателя подписчикам.
 * @param <T> тип передаваемых данных
 */
public class BroadcastStrategy<T> implements PublishingStrategy<T> {

    /**
     * Метод, рассылающий сообщение item всем подписчикам
     * @param subscribers список подписчиков
     * @param item передаваемое сообщение
     */
    @Override
    public void publish(List<Subscriber<? super T>> subscribers, T item) {
        for (Subscriber<? super T> subscriber : subscribers) {
            subscriber.onNext(item);
        }
    }

}
