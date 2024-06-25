package sa.com.barraq.mergeSort;

public class MergeSort {

    void mergeSort(int[] arr) throws InterruptedException {
        int n = arr.length;
        mergeSort(arr, 0, n-1);
    }

    void mergeSort(int[] arr, int i, int j) throws InterruptedException {
        if (i == j) return;
        int m = i + (j - i) / 2;
        Thread t1 = new Thread(() -> {
            try {
                mergeSort(arr, i, m);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                mergeSort(arr, m + 1, j);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        int l = 0, k = 0;
        while (l < (m - i + 1) && k < (j - m)) {
            if (arr[k] < arr[l]) {

            } else {
                l++;
            }
        }
    }
}
