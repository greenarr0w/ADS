package ch.zhaw.ads.praktikum4;

import ch.zhaw.ads.CommandExecutor;

public class SnowflakeServer implements CommandExecutor {
    private Turtle turtle;

    @Override
    public String execute(String command) throws Exception {
        turtle = new Turtle(0.25, 0.75);

        final String[] split = command.split(" ");
        final int step = Integer.valueOf(split[0]);
        final double dist = Double.valueOf(split[1]);


        for (int i = 0; i < 3; i++) {
            snowflake(step, dist);
            turtle.turn(-120);
        }
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
