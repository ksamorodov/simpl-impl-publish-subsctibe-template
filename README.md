This is a simple implementation of the Publish-Subscribe pattern using the Java Flow API. It includes different publishing strategies to handle slow consumers.

## How to run

To run the application, use the following command:
```
java Application [strategy] [batch-size](Optional)
```

`strategy`: the publishing strategy to use. It can be `BROADCAST`, `BATCHING` or `ROUND_ROBIN`.
`batch-size`: the number of items to batch together when using the batching strategy.

For example, to run application with `BROADCAST` strategy, use the following command:
```
java Application BROADCAST
```

## Implementation details
Application implements PublishSubscriber pattern with different publishing strategies to handle slow consumers.
The choice of strategy occurs through the design pattern strategy

## Example usage

To run the demonstration with the broadcast strategy, use the following command:

```
java Application BROADCAST
```

To run the demonstration with the batching strategy and a batch size of 3, use the following command:
```
java Application BATCHING 3
```


