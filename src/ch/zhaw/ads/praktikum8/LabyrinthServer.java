package ch.zhaw.ads.praktikum8;

import ch.zhaw.ads.CommandExecutor;
import ch.zhaw.ads.praktikum7.AdjListGraph;
import ch.zhaw.ads.praktikum7.DijkstraNode;
import ch.zhaw.ads.praktikum7.Edge;

import java.awt.*;

public class LabyrinthServer implements CommandExecutor {

    AdjListGraph<DijkstraNode, Edge> graph = new AdjListGraph<>(DijkstraNode.class, Edge.class);
    ServerGraphics graphics = new ServerGraphics();

    @Override
    public String execute(String command) throws Exception {
        graphics.setColor(Color.black);
        graphics.fillRect(0,0,1,1);

        graphics.setColor(Color.white);
        try {
            for (String s : command.split("\n")) {
                parseLine(s);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        graphics.setColor(Color.red);
        searchExit(graph.findNode("0-6"), graph.findNode("3-0"));
        return graphics.getTrace();
    }

    private void parseLine(String line) throws Throwable {
        String[] columns = line.split("\\s+");
        graph.addNode(columns[0]);
        graph.addNode(columns[1]);
        graph.addEdge(columns[0], columns[1], 0);
        drawPath(graphics, columns[0], columns[1], false);
    }

    private boolean searchExit(DijkstraNode current, DijkstraNode goal) {
        current.setMark(true);
        if (current == goal) {
            return true;
        } else {
            for (Object o : current.getEdges()) {
                Edge e = (Edge)o;
                DijkstraNode n = (DijkstraNode)e.getDest();
                if (!n.getMark()) {
                    if (searchExit(n, goal)) {
                        drawPath(graphics, current.getName(), n.getName(), true);
                        return true;
                    }
                }
            }
            current.setMark(false);
            return false;
        }
    }

    final double SCALE = 10;
    private void drawPath(ServerGraphics g, String from, String to, boolean mouse) {
        double xh0 = from.charAt(0) - '0';
        double yh0 = from.charAt(2) - '0';
        double xh1 = to.charAt(0) - '0';
        double yh1 = to.charAt(2) - '0';
        double x0 = Math.min(xh0,xh1)/SCALE;
        double y0 = Math.min(yh0,yh1)/SCALE;
        double x1 = Math.max(xh0,xh1)/SCALE;
        double y1 = Math.max(yh0,yh1)/SCALE;
        double w = 1/SCALE;
        if (mouse) g.drawLine(x0+w/2,y0+w/2,x1+w/2,y1+w/2);
        else {
            if (y0 == y1)
                g.fillRect(x0,y0,x1-x0+w,w);
            else
                g.fillRect(x0,y0,w,y1-y0+w);
        }
    }
}
