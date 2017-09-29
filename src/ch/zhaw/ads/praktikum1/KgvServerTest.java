package ch.zhaw.ads.praktikum1;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class KgvServerTest {

    KgvServer server;

    final String[] values = {"3 4", "2 4", "5 7", "1 1", "4 6"};
    final int[] kgv = {12, 4, 35, 1, 12};

    @Before
    public void setUp() throws Exception {
        server = new KgvServer();
    }

    @Test
    public void testKgv() {
        for (int i = 0; i < values.length; i++) {
            String result = null;
            try {
                result = server.execute(values[i]);
            } catch (Exception e) {
                fail("There was an Exception");
                e.printStackTrace();
            }
            assertEquals(kgv[i], Integer.parseInt(result));
        }
    }
}
