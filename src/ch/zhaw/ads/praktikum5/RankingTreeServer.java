package ch.zhaw.ads.praktikum5;

import ch.zhaw.ads.CommandExecutor;
import ch.zhaw.ads.praktikum3.Competitor;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class RankingTreeServer implements CommandExecutor {
    private static final String SEPARATING_CHAR = ";";

    @Override
    public String execute(String command) throws Exception {
        final SortedBinaryTree<Competitor> sortedBinaryTree = new SortedBinaryTree<>();
        String[] lines = command.split("\n");
        for (String line : lines) {
            String[] split = line.split(SEPARATING_CHAR);
            int startNr = Integer.parseInt(split[0]);
            String name = split[1];
            int jahrgang = Integer.parseInt(split[2]);
            String country = split[3];
            String time = split[4];
            sortedBinaryTree.add(new Competitor(startNr, name, jahrgang, country, time));
        }

        StringBuilder sb = new StringBuilder();
        sortedBinaryTree.traversal().inorder(new MyVisitor(sb));

        return sb.toString();
    }
}

