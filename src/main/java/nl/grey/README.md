### Notes

### 1. Callable

`Callable` is like a task that you give to a worker thread, but with two special features:
- It returns a value when the task is done
- It can throw an exception

It’s similar to `Runnable`, but:
- Returns value
- Throws exception

```java
Callable<Integer> task = () -> {
    // some long calculation
    return 42; // returns a value
};
```

### 2. Future

A `Future` is like a receipt or ticket you get when you submit a Callable task.
It lets you check:

- Is the task done?
- Give me the result when you're finished
- Cancel the task (if needed)

```java
ExecutorService executor = Executors.newSingleThreadExecutor();
Future<Integer> futureResult = executor.submit(task);

// Wait and get result
Integer result = futureResult.get();
```

`futureResult.get()` blocks (waits) until the Callable finishes and gives you the value.