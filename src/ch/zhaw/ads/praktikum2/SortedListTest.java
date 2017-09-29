package ch.zhaw.ads.praktikum2;

import org.junit.Assert;
import org.junit.Test;

public class SortedListTest {

    @Test
    public void addAsc() throws Exception {
        final SortedList<Integer> sortedList = new SortedList<>(true);
        sortedList.add(0);
        sortedList.add(2);
        sortedList.add(1);
        sortedList.add(3);

        for (Integer i = 0; i < sortedList.size(); i++) {
            Assert.assertEquals(i, sortedList.get(i));
        }
    }

    @Test
    public void addDesc() throws Exception {
        final SortedList<Integer> sortedList = new SortedList<>(false);
        sortedList.add(0);
        sortedList.add(2);
        sortedList.add(1);
        sortedList.add(3);

        Assert.assertEquals(4, sortedList.size());

        Assert.assertEquals(new Integer(3), sortedList.get(0));
        Assert.assertEquals(new Integer(2), sortedList.get(1));
        Assert.assertEquals(new Integer(1), sortedList.get(2));
        Assert.assertEquals(new Integer(0), sortedList.get(3));

    }


}
