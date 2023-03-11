package com.ksamorodov.task.saber;

import com.ksamorodov.task.saber.demo.Demonstration;
import com.ksamorodov.task.saber.enums.StrategyKind;

public class Application {
    public static void main(String[] args) {
        StrategyKind strategyKind = null;
        int batchSize = 0;
        if (args.length == 1) {
            strategyKind = StrategyKind.valueOf(args[0]);
        } else if (args.length == 2) {
            strategyKind = StrategyKind.valueOf(args[0]);
            batchSize = Integer.parseInt(args[1]);
        } else {
            System.err.println("Usage: java Application [strategy] [batch-size](Optional)");
            System.exit(1);
        }

        Demonstration.start(strategyKind, batchSize);

    }
}
