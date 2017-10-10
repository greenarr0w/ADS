package ch.zhaw.ads.praktikum4;

import ch.zhaw.ads.CommandExecutor;

public class SnowflakeServer implements CommandExecutor {
    final Turtle turtle = new Turtle(0, 0.5);

    @Override
    public String execute(String command) throws Exception {
        final String[] split = command.split(" ");
        final int step = Integer.valueOf(split[0]);
        final double dist = Double.valueOf(split[1]);

        snowflake(step, dist);
        return turtle.getTrace();
    }

    private void snowflake(int step, double dist) {
        if (step == 0) {
            turtle.move(dist);
        } else {
            step--;
            dist = dist / 3;
            snowflake(step, dist);
            turtle.turn(60);
            snowflake(step, dist);
            turtle.turn(-120);
            snowflake(step, dist);
            turtle.turn(60);
            snowflake(step, dist);
        }
    }
}
