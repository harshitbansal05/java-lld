package sa.com.barraq.stack;

public class SynchronizedStack<T> {
    private StackNode<T> stackTop;

    public synchronized void push(T item) {
        if (stackTop == null) stackTop = new StackNode<>(item);
        else {
            StackNode<T> newStackTop = new StackNode<>(item);
            newStackTop.setNext(stackTop);
            stackTop = newStackTop;
        }
    }

    public synchronized T pop() {
        if (stackTop == null) throw new RuntimeException("Stack is empty");
        T item = stackTop.getItem();
        stackTop = stackTop.getNext();
        return item;
    }
}
