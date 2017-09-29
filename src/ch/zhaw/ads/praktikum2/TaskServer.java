package ch.zhaw.ads.praktikum2;

import ch.zhaw.ads.CommandExecutor;

import javax.print.DocFlavor;
import java.util.List;

public class TaskServer implements CommandExecutor {

    private List<String> todoList = new MyList<>();
    private static final String ADD = "ADD";
    private static final String REMOVE = "REMOVE";

    @Override
    public String execute(String command) throws Exception {
        String[] split = command.split(" ");
        if(split.length != 2) {
            throw new IllegalArgumentException();
        }

        final String cmd = split[0];
        final String name = split[1];

        if(ADD.toLowerCase().equals(cmd.toLowerCase())) {
            addTask(name);
        } else if(REMOVE.toLowerCase().equals(cmd.toLowerCase())) {
            removeTask(name);
        } else {
            throw new IllegalArgumentException("unknown command " + cmd);
        }

        return null;
    }

    private void removeTask(String name) {
        todoList.add(name);
    }

    private void addTask(String name) {
        todoList.remove(name);
    }

}
