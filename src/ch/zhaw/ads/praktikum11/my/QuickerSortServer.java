package ch.zhaw.ads.praktikum11.my;

import ch.zhaw.ads.CommandExecutor;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;

import static ch.zhaw.ads.praktikum10.SortServer.checkSorted;

public class QuickerSortServer implements CommandExecutor {

    private static int QSTHREASHHOLD = 50;

    @Override
    public String execute(String command) throws Exception {
        String separator = " ";
        String[] split = command.split(separator);
        String action = split[0];

        int length = Integer.valueOf(split[1]);
        int[] intArray = new int[length + 1];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = new Random().nextInt(length);
        }


        if(split.length > 2) {
            String givenQSThreshold = split[2];
            QSTHREASHHOLD = Integer.valueOf(givenQSThreshold);
        }

        long end, start = System.currentTimeMillis();
        int count = 0;
        if (action.toLowerCase().equals("quickersort")) {
            do {
                int[] copy = new int[intArray.length];
                System.arraycopy(intArray, 0, copy, 0, intArray.length);
                quickerSort(copy, 0, copy.length - 1);
                count++;
                end = System.currentTimeMillis();
            } while (end - start < 1000);
        } else if (action.toLowerCase().equals("arraysort")) {
            do {
                int[] copy = new int[intArray.length];
                System.arraycopy(intArray, 0, copy, 0, intArray.length);
                Arrays.sort(copy);
                count++;
                end = System.currentTimeMillis();
            } while (end - start < 1000);
        } else {
            throw new IllegalArgumentException("Wrong command");
        }
        String print = "time=" + (double) (end - start) / count;
        System.out.println(print);

        quickerSort(intArray, 0, intArray.length - 1);
        boolean sorted = checkSorted(intArray);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("quickersort").append(" is sorted: ").append(sorted).append("\n")
                .append(print).append("\n");
        //Arrays.stream(intArray).forEach(value -> stringBuilder.append(value).append("\n"));
        return stringBuilder.toString();

    }

    public static void quickerSort(int[] a, int left, int right) {
        if (right - left < QSTHREASHHOLD) {
            insertionSort(a, left, right);
        } else {

            int r = partition(a, left, right);
            quickerSort(a, left, r);
            quickerSort(a, r + 1, right);
        }
    }

    public static void insertionSort(int[] intArray, int left, int right) {
        for (int k = left + 1; k < right + 1; k++)
            if (intArray[k] < intArray[k - 1]) {
                int x = intArray[k];
                int i;
                for (i = k; ((i > 0) && (intArray[i - 1] > x)); i--) {
                    intArray[i] = intArray[i - 1];
                }
                intArray[i] = x;
            }
    }

    public static int partition(int[] a, int left, int right) {
        // chose middle value of range for our pivot
        int pivot = a[(left + right) / 2];
        left--;
        right++;
        while (true) {
            do left++;
            while (a[left] < pivot);
            do right--;
            while (a[right] > pivot);
            if (left < right) {//swap
                int tmp = a[left];
                a[left] = a[right];
                a[right] = tmp;
            } else {
                return right;
            }
        }
    }
}
