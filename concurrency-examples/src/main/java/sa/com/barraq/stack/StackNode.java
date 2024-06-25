package sa.com.barraq.stack;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StackNode<T> {
    private T item;
    private StackNode<T> next;

    public StackNode(T item) {
        this.item = item;
    }
}
