package sa.com.barraq;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLongArray;

public class CAtomic {

    public void atomicInteger() {
        int[] inputArray = new int[]{11, 17, 19, 23, 31};

        // use an array of integers to initialize an instance of AtomicIntegerArray
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(inputArray);

        // index we'll manipulate
        int index = 3;

        // get operation
        int item = atomicIntegerArray.get(index);
        System.out.println("Item at index 3 = " + item);

        // set operation
        atomicIntegerArray.set(index, 37);
        System.out.println("Item now at index 3 = " + atomicIntegerArray.get(3));

        // compare and set operation.
        atomicIntegerArray.compareAndSet(index, 37, 41);
        System.out.println("Item at index 3 after compareAndSet operation = " + atomicIntegerArray.get(index));

        // addAndGet() - adds the passed-in argument and returns the result
        int result = atomicIntegerArray.addAndGet(index, 5);
        System.out.println("Item at index 3 after addAndGet(5) = " + result);

        // getAndAdd() - returns the value, and then adds the passed-in argument
        result = atomicIntegerArray.getAndAdd(index, 5);
        System.out.println("Item at index 3 after addAndGet(5) = " + result);

        // getAndIncrement() - gets the value and then increments
        result = atomicIntegerArray.getAndIncrement(index);
        System.out.println("Item at index 3 after getAndIncrement() = " + result);

        // incrementAndGet() - increments and then returns the result
        result = atomicIntegerArray.incrementAndGet(index);
        System.out.println("Item at index 3 after incrementAndGet() = " + result);

        // decrementAndGet() - decrements and then gets the result
        result = atomicIntegerArray.decrementAndGet(index);
        System.out.println("Item at index 3 after decrementAndGet() = " + result);

        // getAndDecrement() -
        result = atomicIntegerArray.getAndDecrement(index);
        System.out.println("Item at index 3 after getAndDecrement() = " + result);
    }

    public void atomicLong() {
        long[] inputArray = new long[]{11, 17, 19, 23, 31};

        // use an array of ints to initialize an instance of AtomicIntegerArray
        AtomicLongArray atomicIntegerArray = new AtomicLongArray(inputArray);

        // index we'll manipulate
        int index = 3;

        // get operation
        long item = atomicIntegerArray.get(index);
        System.out.println("Item at index 3 = " + item);

        // set operation
        atomicIntegerArray.set(index, 37);
        System.out.println("Item now at index 3 = " + atomicIntegerArray.get(3));

        // compare and set operation.
        atomicIntegerArray.compareAndSet(index, 37, 41);
        System.out.println("Item at index 3 after compareAndSet operation = " + atomicIntegerArray.get(index));

        // addAndGet() - adds the passed-in argument and returns the result
        long result = atomicIntegerArray.addAndGet(index, 5);
        System.out.println("Item at index 3 after addAndGet(5) = " + result);

        // getAndAdd() - returns the value, and then adds the passed-in argument
        result = atomicIntegerArray.getAndAdd(index, 5);
        System.out.println("Item at index 3 after addAndGet(5) = " + result);

        // getAndIncrement() - gets the value and then increments
        result = atomicIntegerArray.getAndIncrement(index);
        System.out.println("Item at index 3 after getAndIncrement() = " + result);

        // incrementAndGet() - increments and then returns the result
        result = atomicIntegerArray.incrementAndGet(index);
        System.out.println("Item at index 3 after incrementAndGet() = " + result);

        // decrementAndGet() - decrements and then gets the result
        result = atomicIntegerArray.decrementAndGet(index);
        System.out.println("Item at index 3 after decrementAndGet() = " + result);

        // getAndDecrement() -
        result = atomicIntegerArray.getAndDecrement(index);
        System.out.println("Item at index 3 after getAndDecrement() = " + result);
    }
}
