package sa.com.barraq.stack;

public class SimulatedCompareAndSet<T> {

    private T val;

    public SimulatedCompareAndSet(T val) {
        this.val = val;
    }

    public synchronized boolean compareAndSet(T oldV, T newV) {
        if (val == null) {
            if (oldV == null) {
                val = newV;
                return true;
            }
            return false;
        }
        if (oldV == null) return false;
        if (!val.equals(oldV)) return false;
        val = newV;
        return true;
    }

    public synchronized T getVal() {
        return val;
    }
}
