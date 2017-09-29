package ch.zhaw.ads.praktikum1;

import ch.zhaw.ads.CommandExecutor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BracketServer implements CommandExecutor {
    @Override
    public String execute(String command) throws Exception {
        Stack<String> stack = new ListStack<>();
        HashMap<String, String> closeToOpenBracket = new HashMap<>();
        closeToOpenBracket.put(")", "(");
        closeToOpenBracket.put("]", "[");
        closeToOpenBracket.put("}", "{");
        closeToOpenBracket.put(">", "<");

        // ignore all other character but brackets
        List<String> split = Arrays.stream(command.split(""))
                .filter(s -> closeToOpenBracket.keySet().contains(s) || closeToOpenBracket.values().contains(s))
                .collect(Collectors.toList());

        split.forEach(value -> {
            String peek = stack.peek();
            if(peek != null && String.valueOf(peek).equals(closeToOpenBracket.get(value))){
                stack.pop();
            } else {
                stack.push(value);
            }
        });
        if(stack.isEmpty()) {
            return "ok\n";
        } else {
            return "not ok\n";
        }
    }
}
