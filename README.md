# Пример реализации паттерна Publish-Subscribe с использованием Java Flow API

Данный пример демонстрирует реализацию паттерна `Publish-Subscribe` с использованием Java Flow API. Он состоит из двух классов: `com.ksamorodov.saber.test.service.PubSubWithSlowConsumers` и `SlowSubscriber`.

## com.ksamorodov.saber.test.service.PubSubWithSlowConsumers

Этот класс инициализирует `SubmissionPublisher` и двух `SlowSubscriber`, и подписывает их на этот издатель. Он отправляет 10 сообщений в очередь пакетами по одному сообщению, когда размер очереди не превышает установленный максимум (3 элемента). После того, как все сообщения отправлены, он закрывает издатель.

## SlowSubscriber

Этот класс реализует интерфейс `Flow.Subscriber` и реагирует на события, связанные с получением новых элементов издателем. Он имитирует долгую обработку элементов, задерживая поток на 100 миллисекунд после получения каждого элемента.

## Рекомендации

В целях оптимизации производительности и улучшения обработки элементов подписчиками, можно изменить стратегию паблишера. Например, можно отправлять элементы в очередь пакетами, вместо отправки каждого элемента отдельно. Также можно настроить количество элементов, которые отправляются в каждый пакет, и максимальный размер очереди.

Кроме того, можно добавить другие типы подписчиков, которые обрабатывают элементы быстрее или медленнее, чем SlowSubscriber.

## Заключение

Пример реализации паттерна Publish-Subscribe с использованием Java Flow API демонстрирует, как можно создавать простые системы обмена сообщениями между издателем и подписчиками, используя принципы асинхронного программирования и реактивного программирования. Эти принципы помогают создавать масштабируемые, отказоустойчивые и высокопроизводительные системы.
