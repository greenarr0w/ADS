package ch.zhaw.ads.praktikum10;

import ch.zhaw.ads.CommandExecutor;

import java.util.Random;

public class SortServer implements CommandExecutor {

    @Override
    public String execute(String command) throws Exception {
        //int length = 1000000;
        int length = Integer.valueOf(command);
        int[] intArray = new int[length + 1];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = new Random().nextInt(length);
        }

        long end, start = System.currentTimeMillis();
        int count = 0;
        do {
            int[] copy = new int[intArray.length];
            System.arraycopy(intArray, 0, copy, 0, intArray.length);
            bubbleSortComplete(copy);

            count++;
            end = System.currentTimeMillis();
        } while (end - start < 1000);
        String print = "time=" + (double) (end - start) / count;
        System.out.println(print);

        bubbleSortComplete(intArray);
        boolean sorted = checkSorted(intArray);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("is sorted: ").append(sorted).append("\n")
                .append(print).append("\n");
        return stringBuilder.toString();
    }

    private static void bubbleSortComplete(int[] intArray) {
        boolean hasSwaped = false;
        for (int i = 0; i < intArray.length - 1; i++) {
            if (intArray[i] > intArray[i + 1]) {
                swap(intArray, i, i + 1);
                hasSwaped = true;
            }
        }
        if (hasSwaped) {
            bubbleSortComplete(intArray);
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


