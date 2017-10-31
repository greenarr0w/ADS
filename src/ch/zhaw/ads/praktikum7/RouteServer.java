package ch.zhaw.ads.praktikum7;

import ch.zhaw.ads.CommandExecutor;

import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

public class RouteServer implements CommandExecutor {
    private static final String SEPARATING_CHAR = " ";

    @Override
    public String execute(String command) throws Exception {

        Graph<DijkstraNode, Edge> graph =
                new AdjListGraph<DijkstraNode, Edge>(DijkstraNode.class, Edge.class);

        String[] lines = command.split("\n");
        for (String line : lines) {
            String[] split = line.split(SEPARATING_CHAR);
            String node1 = split[0];
            String node2 = split[1];
            double distance = Double.parseDouble(split[2]);

            try {
                graph.addEdge(node1, node2, distance);
                graph.addEdge(node2, node1, distance);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

        }

        final DijkstraNode winterthur = graph.findNode("Winterthur");
        breadthFirstSearch(winterthur);

        StringBuilder sb = new StringBuilder();
        DijkstraNode<Edge<DijkstraNode>> markedNode = graph.findNode("Lugano");
        while (markedNode != null) {
            sb.append((markedNode.name));
            sb.append("->");
            markedNode = markedNode.prev;
        }

        sb.append("\n");
        return sb.toString();
    }

    private DijkstraNode<Edge<DijkstraNode>> getMarkedNode(DijkstraNode<Edge<DijkstraNode>> node) {
        final Optional<Edge<DijkstraNode>> markedNode = node.edges.stream().filter(edge -> edge.getDest().getMark())
                .findFirst();

        return markedNode.isPresent() ? markedNode.get().dest : null;
    }


    private void breadthFirstSearch(final DijkstraNode startNode) {
        Queue<DijkstraNode> q = new PriorityQueue<>();
        startNode.dist = 0;
        q.add(startNode);
        while (!q.isEmpty()) {
            DijkstraNode<Edge> current = q.remove();
            current.setMark(true);
            for (Edge<DijkstraNode> e : current.edges) {
                DijkstraNode n = e.dest;
                if (!n.getMark()) {
                    double dist = e.getWeight() + current.dist;
                    if ((n.prev == null) || (dist < n.dist)) {
                        n.dist = dist;
                        n.prev = current;
                        q.add(n);
                    }
                }
            }
        }
    }


}
