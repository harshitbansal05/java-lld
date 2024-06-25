package sa.com.barraq.stack;

public class CASStack<T> {
    private final SimulatedCompareAndSet<StackNode<T>> compareAndSet = new SimulatedCompareAndSet<>(null);

    public void push(T item) {
        StackNode<T> newStackTop = new StackNode<>(item);
        StackNode<T> currentStackTop = compareAndSet.getVal();
        newStackTop.setNext(currentStackTop);
        while (!compareAndSet.compareAndSet(currentStackTop, newStackTop)) {
            currentStackTop = compareAndSet.getVal();
            newStackTop.setNext(currentStackTop);
        }
    }

    public T pop() {
        StackNode<T> currentStackTop = compareAndSet.getVal();
        if (currentStackTop == null) return null;
        T item = currentStackTop.getItem();
        while (!compareAndSet.compareAndSet(currentStackTop, currentStackTop.getNext())) {
            currentStackTop = compareAndSet.getVal();
            if (currentStackTop == null) return null;
            item = currentStackTop.getItem();
        }
        return item;
    }
}
