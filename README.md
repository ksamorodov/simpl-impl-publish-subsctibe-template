# Saber Test Task

This test task is comprised of two parts, each implementing a simple version of the Publish-Subscribe pattern using Java Flow API and Project Reactor. The goal of the implementation is to provide flexibility in handling slow consumers by allowing the user to select the desired publisher strategy.

## Implementation Details

The test task is split into two modules: simple-publish-subscribe for the first part and project-react-publich-subscribe for the second part.

### Simple Publish-Subscribe
To run the first part, use the following command:

 ```
 java Application [strategy] [batch-size](Optional)
 ```

`strategy`: the publishing strategy to use. It can be `BROADCAST`, `BATCHING` or `ROUND_ROBIN`.
`batch-size`: the number of items to batch together when using the batching strategy.

To run the second part, use the following command:

```
java Application [strategy]
```

`strategy`: the publishing strategy to use. It can be: `IGNORE`, `ERROR`, `DROP`, `LATEST`, `BUFFER`;

The application requires Java 14 or higher to run.

## Conclusion

The implementation of the Publish-Subscribe pattern using Java Flow API and Project Reactor provides flexibility in handling slow consumers. By allowing users to select the desired publisher strategy, it ensures that subscribers receive data in a timely and efficient manner. The use of Mockito tests helps ensure the correctness of the implementation.
