package ch.zhaw.ads.praktikum1;

import ch.zhaw.ads.CommandExecutor;

public class KgvServer implements CommandExecutor {

    @Override
    public String execute(String command) throws Exception {
        String[] split = command.split(" ");
        int a = Integer.parseInt(split[0]);
        int b = Integer.parseInt(split[1]);
        return String.valueOf(this.kgv(a, b));
    }

    private int ggt(int m, int n) {
        if (n == 0) {
            return m;
        }
        else {
            return ggt(n, m % n);
        }
    }

    private int kgv(int m, int n) {
        int o = ggt(m, n);
        int p = (m * n) / o;
        return p;
    }


}
