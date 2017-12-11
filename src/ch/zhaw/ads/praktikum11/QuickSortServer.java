package ch.zhaw.ads.praktikum11;

import ch.zhaw.ads.CommandExecutor;

import java.util.Arrays;
import java.util.Random;

public class QuickSortServer implements CommandExecutor {
    private int[] values;
    private int[] valuesCopy;

    @Override
    public String execute(String command) throws Exception {
        int amount = Integer.parseInt(command);
        values = new int[amount];
        valuesCopy = new int[amount];
        createValues();

        return checkSorted(valuesCopy) + " " + measureQuickersort() + "\n";
    }

    private double measureQuickersort() {
        long end, start = System.currentTimeMillis();
        int count = 0;
        do {
            System.arraycopy(values, 0, valuesCopy, 0, values.length);
            Arrays.sort(valuesCopy);
            count++;
            end = System.currentTimeMillis();
        } while (end - start < 1000);
        return (double)(end-start)/count;
    }


    private void createValues() {
        Random r = new Random();
        for (int i = 0; i < values.length; i++) {
            values[i] = r.nextInt(1000000);
        }
    }

    private boolean checkSorted(int[] array) {
        int prev = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (prev > array[i]) return false;
            prev = array[i];
        }
        return true;
    }
}
