package ch.zhaw.ads.praktikum4;

public class HanoiServer {

    public void hanoi(int n, char from, char to, char help) {
        if (n > 0) {
            hanoi(n - 1, from, help, to);
            System.out.println("move" + from + " to " + to);
            hanoi(n - 1, help, to, from);
        }
    }

}
