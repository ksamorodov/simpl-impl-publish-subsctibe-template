package com.ksamorodov.task.saber;

import com.ksamorodov.task.saber.demo.Demonstration;
import com.ksamorodov.task.saber.enums.StrategyKind;

public class Application {

    /**
     * Метод, запускающий демонстрацию работы паттерна Publish-Subscribe.
     *
     * @param args аргументы командной строки, задающие настройки демонстрации
     *             в следующем порядке: стратегия паблишера (например, "BROADCAST", "BATCHING", ROUND_ROBIN)
     */
    public static void main(String[] args) {
        StrategyKind strategyKind = null;
        int batchSize = 0;

        // Проверяем количество аргументов командной строки и устанавливаем значение strategyKind и batchSize в соответствии с этим
        if (args.length == 1) {
            strategyKind = StrategyKind.valueOf(args[0].toUpperCase()); // Приводим значение аргумента к верхнему регистру для более надежного сравнения
        } else if (args.length == 2) {
            strategyKind = StrategyKind.valueOf(args[0].toUpperCase());
            batchSize = Integer.parseInt(args[1]);
        } else {
            // Если количество аргументов не соответствует ожидаемому, выводим сообщение об ошибке и завершаем программу
            System.err.println("Usage: java Application [strategy] [batch-size](Optional)");
            System.exit(1);
        }

        // Запускаем демонстрацию с заданными параметрами
        Demonstration.start(strategyKind, batchSize);
    }
}
