package ch.zhaw.ads.praktikum11;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class QuickerSortForkJoin extends RecursiveAction {

    private static final int SORT_THRESHOLD = 40;
    private int[] array;
    private int left;
    private int right;
    private static final int SPLIT_THRESHOLD = 10000;

    public QuickerSortForkJoin(int[] array, int left, int right) {
        this.array = array;
        this.left = left;
        this.right = right;
    }

    public static void sort(int[] array) {
        int parallelism = Runtime.getRuntime().availableProcessors();
        ForkJoinPool forkJoinPool = new ForkJoinPool(parallelism);
        QuickerSortForkJoin rootTask = new QuickerSortForkJoin(array, 0, array.length - 1);
        forkJoinPool.invoke(rootTask);
    }

    @Override
    protected void compute() {
        int r = 0;

        if (left < right) {
            r = partition(array, left, right);
            ForkJoinTask t1 = null, t2 = null;

            if (r - left > SPLIT_THRESHOLD && right - r > SPLIT_THRESHOLD) {
                t1 = new QuickerSortForkJoin(array, left, r);
                t2 = new QuickerSortForkJoin(array, r + 1, right);
                invokeAll(t1, t2);
            } else {
                this.quickersort(array, left, r);
                this.quickersort(array, r + 1, right);
            }
        }
    }

    public static int partition(int[] array, int left, int right) {
        int pivot = array[(left + right) / 2];
        left--;
        right++;
        while (true) {
            do left++; while (array[left] < pivot);
            do right--; while (array[right] > pivot);

            if (left < right) {
                int tmp = array[left];
                array[left] = array[right];
                array[right] = tmp;
            } else {
                return right;
            }
        }
    }

    private void quickersort(int[] array, int left, int right) {
        if ((right - left) < SORT_THRESHOLD) {
            insertionSort(array, left, right);
        } else {
            int r = partition(array, left, right);
            quickersort(array, left, r);
            quickersort(array, r+1, right);
        }
    }

    private void insertionSort(int[] array, int left, int right) {
        for (int k = left + 1; k < right; k++) {
            if (array[k] < array[k-1]) {
                int x = array[k];
                int i;
                for (i = k; ((i > 0) && (array[i-1] > x)); i--) {
                    array[i] = array[i-1];
                }
                array[i] = x;
            }
        }
    }
}
