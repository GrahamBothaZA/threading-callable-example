package nl.grey;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 1. Create callable tasks
        Callable<String> taskOne = () -> {
            System.out.println("Task 1 started on: " + Thread.currentThread().getName());
            Thread.sleep(5000); // Simulate work
            System.out.println("Task 1 complete");
            return "Task 1 result string";
        };

        Callable<Integer> taskTwo = () -> {
            System.out.println("Task 2 started on: " + Thread.currentThread().getName());
            Thread.sleep(2000); // Simulate work
            System.out.println("Task 2 complete");
            return 25;
        };

        Callable<ResultObject> taskThree = () -> {
            System.out.println("Task 3 started on: " + Thread.currentThread().getName());
            Thread.sleep(1000); // Simulate work
            System.out.println("Task 3 complete");
            return new ResultObject(200, "Task 3 result body");
        };

        // 3. Submit the tasks
        System.out.println("Submitting task 1...");
        Future<String> futureOne = executor.submit(taskOne);

        System.out.println("Submitting task 2..");
        Future<Integer> futureTwo = executor.submit(taskTwo);

        System.out.println("Submitting task 3...");
        Future<ResultObject> futureThree = executor.submit(taskThree);

        // 3. Execute other logic while the tasks execute
        System.out.println("Doing something else in main thread...");

        // 4. Get the results when ready, the program will wait until it is available
        System.out.println("Task 1 result: " + futureOne.get());
        System.out.println("Task 2 result: " + futureTwo.get());
        System.out.println("Task 3 result: " + futureThree.get());

        System.out.println("------\nThe process only proceeds when the results have all been returned\n------");

        // 5. How to submit a Callable task with a timeout
        System.out.println("Submitting task 1 again...");
        Future<String> futureFour = executor.submit(taskOne);
        try {
            String result = futureFour.get(2, TimeUnit.SECONDS);
            System.out.println("Task 1 result: " + result);
        } catch (TimeoutException e) {
            System.out.println("Task 1 took too long!");
            futureFour.cancel(true); // Interrupt if running
        }

        // 5. Always shutdown executor to avoid memory issues
        executor.shutdown();
    }
}