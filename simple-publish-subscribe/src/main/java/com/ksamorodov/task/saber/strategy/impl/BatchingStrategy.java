package com.ksamorodov.task.saber.strategy.impl;

import com.ksamorodov.task.saber.strategy.PublishingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

/**
 * Класс, реализующий стратегию пакетной публикации (Batching) сообщений по подписчикам.
 * Пакетная публикация сообщений заключается в том, что сообщения сначала собираются в список,
 * а затем одновременно публикуются всем подписчикам.
 * @param <T> тип сообщений
 */
public class BatchingStrategy<T> implements PublishingStrategy<T> {

    private final int batchSize;
    private List<T> items = new ArrayList<>();

    public BatchingStrategy(int batchSize) {
        this.batchSize = batchSize;
    }

    /**
     * Метод, реализующий пакетную публикацию сообщений по подписчикам.
     * Сообщения добавляются в список и публикуются всем подписчикам,
     * когда размер пакета достигает batchSize.
     * @param subscribers список подписчиков
     * @param item новое сообщение
     */
    @Override
    public void publish(List<Flow.Subscriber<? super T>> subscribers, T item) {
        // Добавляем сообщение в список
        items.add(item);

        // Если размер пакета достиг batchSize, публикуем сообщения всем подписчикам
        if (items.size() == batchSize) {
            for (Flow.Subscriber<? super T> subscriber : subscribers) {
                for (T itemToPublish : items) {
                    subscriber.onNext(itemToPublish);
                }
            }

            // Очищаем список сообщений
            items.clear();
        }
    }

    /**
     * Метод, публикующий оставшиеся сообщения всем подписчикам при завершении публикации.
     * Вызывается, когда паблишер закончил публикацию всех сообщений.
     * Каждое сообщение публикуется всем подписчикам, а затем подписчикам
     * отправляется сигнал о завершении публикации.
     * @param subscribers список подписчиков
     */
    public void publishRemaining(List<Flow.Subscriber<? super T>> subscribers) {
        for (Flow.Subscriber<? super T> subscriber : subscribers) {
            for (T itemToPublish : items) {
                subscriber.onNext(itemToPublish);
            }

            // Отправляем сигнал о завершении публикации подписчику
            subscriber.onComplete();
        }
    }

}
