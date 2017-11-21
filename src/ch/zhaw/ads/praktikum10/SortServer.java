package ch.zhaw.ads.praktikum10;

import ch.zhaw.ads.CommandExecutor;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

public class SortServer implements CommandExecutor {

    @Override
    public String execute(String command) throws Exception {
        //int length = 1000000;
        String separator = " ";
        String[] split = command.split(separator);
        String action = split[0];
        Consumer<int[]> consumer;
        if(action.toLowerCase().equals("insertion")) {
            consumer = this::insertionSort;
        } else if(action.toLowerCase().equals("bubble")) {
            consumer = this::bubbleSortComplete;
        } else if(action.toLowerCase().equals("selection")) {
            consumer = this::selectionSort;
        } else {
            throw new IllegalArgumentException("Wrong command");
        }

        int length = Integer.valueOf(split[1]);
        int[] intArray = new int[length + 1];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = new Random().nextInt(length);
        }

        long end, start = System.currentTimeMillis();
        int count = 0;
        do {
            int[] copy = new int[intArray.length];
            System.arraycopy(intArray, 0, copy, 0, intArray.length);
            consumer.accept(copy);
            count++;
            end = System.currentTimeMillis();
        } while (end - start < 1000);
        String print = "time=" + (double) (end - start) / count;
        System.out.println(print);

        consumer.accept(intArray);
        boolean sorted = checkSorted(intArray);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(action).append(" is sorted: ").append(sorted).append("\n")
                .append(print).append("\n");
        Arrays.stream(intArray).forEach(value -> stringBuilder.append(value).append("\n"));
        return stringBuilder.toString();
    }

    private void bubbleSortComplete(int[] intArray) {
        boolean hasSwapped = true;
        while (hasSwapped) {
            hasSwapped = false;
            for (int i = 0; i < intArray.length - 1; i++) {
                if (intArray[i] > intArray[i + 1]) {
                    swap(intArray, i, i + 1);
                    hasSwapped = true;
                }
            }
        }
    }

    private void selectionSort(int[] intArray) {
        for (int k = 0; k < intArray.length; k++) {
            int min = k;
            for (int i = k + 1; i < intArray.length; i++) {
                if (intArray[i] < intArray[min]) {
                    min = i;
                }
            }
            if (min != k) {
                swap(intArray, min, k);
            }
        }
    }

    private void insertionSort(int[] intArray) {
        for (int k = 1; k < intArray.length; k++)
            if (intArray[k] < intArray[k - 1]) {
                int x = intArray[k];
                int i;
                for (i = k; ((i > 0) && (intArray[i - 1] > x)); i--) {
                    intArray[i] = intArray[i - 1];
                }
                intArray[i] = x;
            }
    }

    private static void swap(int[] intArray, int index1, int index2) {
        int tmp = intArray[index1];
        intArray[index1] = intArray[index2];
        intArray[index2] = tmp;
    }

    private boolean checkSorted(int[] intArray) {
        for (int i = 0; i < intArray.length - 1; i++) {
            if (intArray[i] > intArray[i + 1]) {
                return false;
            }
        }
        return true;
    }


}


