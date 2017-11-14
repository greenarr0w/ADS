package ch.zhaw.ads.praktikum8;

import ch.zhaw.ads.CommandExecutor;

public class Aufgabe2Server implements CommandExecutor {

    @Override
    public String execute(String command) throws Exception {
        final ServerGraphics serverGraphics = new ServerGraphics();
        serverGraphics.drawLine(0, 0, 0.5, 1);
        serverGraphics.drawLine(0.5, 1, 1, 0);
        serverGraphics.drawLine(1, 0, 0, 0);
        serverGraphics.drawRect(0, 0, 1, 1);
        return serverGraphics.getTrace();
    }
}
