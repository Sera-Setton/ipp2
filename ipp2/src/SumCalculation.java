import java.util.concurrent.*;

public class SumCalculation {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<Integer> task1 = executorService.submit(new SumTask(1, 20000));
        Future<Integer> task2 = executorService.submit(new SumTask(20001, 40000));
        Future<Integer> task3 = executorService.submit(new SumTask(40001, 60000));
        Future<Integer> task4 = executorService.submit(new SumTask(60001, 80000));
        Future<Integer> task5 = executorService.submit(new SumTask(80001, 100000));

        long startTime = System.currentTimeMillis();

        int sum = task1.get() + task2.get() + task3.get() + task4.get() + task5.get();

        long elapsedTime = System.currentTimeMillis() - startTime;

        System.out.println("Sum of numbers from 1 to 100000 is: " + sum);
        System.out.println("Elapsed time for each task:");
        System.out.println("Task 1: " + task1.get() + " ms");
        System.out.println("Task 2: " + task2.get() + " ms");
        System.out.println("Task 3: " + task3.get() + " ms");
        System.out.println("Task 4: " + task4.get() + " ms");
        System.out.println("Task 5: " + task5.get() + " ms");
        System.out.println("Total elapsed time: " + elapsedTime + " ms");

        executorService.shutdown();
    }
}

class SumTask implements Callable<Integer> {
    private final int start;
    private final int end;

    public SumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        long startTime = System.currentTimeMillis();

        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
        }

        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Task from " + start + " to " + end + " completed in " + elapsedTime + " ms with sum = " + sum);
        return sum;
    }
}
