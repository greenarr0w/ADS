package ch.zhaw.ads.praktikum1;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class BracketServerTest {

    BracketServer bs;
    final String OK = "ok\n";

    final String[] brackets = {"()", "}", "(()", "([])", "[(])"};
    final boolean[] res = {true, false, false, true, false};

    @Before
    public void setUp() throws Exception {
        bs = new BracketServer();
    }

    @Test
    public void testBracket() {
        for (int i = 0; i < brackets.length; i++) {
            String result = null;
            try {
                result = bs.execute(brackets[i]);
            } catch (Exception e) {
                fail("There was an Exception");
                e.printStackTrace();
            }
            if (res[i]) assertTrue(brackets[i], OK.equals(result));
            else assertFalse(brackets[i], OK.equals(result));
        }
    }
}
