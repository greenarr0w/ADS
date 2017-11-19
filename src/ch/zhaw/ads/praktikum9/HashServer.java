package ch.zhaw.ads.praktikum9;

import ch.zhaw.ads.CommandExecutor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HashServer implements CommandExecutor {
    private static final String SEPARATING_CHAR = ";";
    private Map<Competitor, Competitor> competitorMap = new HashMap<>();

    @Override
    public String execute(String command) throws Exception {

        if (command.contains("add") || command.contains("get")) {
            final String[] split = command.split(" ");
            final Competitor competitor = new Competitor(split[1], Integer.valueOf(split[2]));
            if (split[0].equals("add")) {
                competitorMap.put(competitor, competitor);
            } else if (split[0].equals("get")) {
                final Competitor foundCompetitor = competitorMap.get(competitor);
                return foundCompetitor.toString();
            }
        } else {
            System.out.println("command: " + command);
            String[] lines = command.split("\n");
            for (String line : lines) {
                String[] split = line.split(SEPARATING_CHAR);
                int startNr = Integer.parseInt(split[0]);
                String name = split[1];
                int jahrgang = Integer.parseInt(split[2]);
                String country = split[3];
                String time = split[4];
                final Competitor competitor = new Competitor(name, jahrgang);
                competitorMap.put(competitor, competitor);
            }
        }

        return competitorMap.toString();
    }
}


