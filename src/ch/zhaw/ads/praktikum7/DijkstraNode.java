package ch.zhaw.ads.praktikum7;

public class DijkstraNode<E> extends Node<E> implements Comparable<DijkstraNode> {
    boolean mark;
    DijkstraNode<E> prev;
    double dist;

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public void setMark(boolean m) {
        mark = m;
    }

    public boolean getMark() {
        return mark;
    }

    public void setPrev(DijkstraNode<E> p) {
        prev = p;
    }

    public DijkstraNode<E> getPrev() {
        return prev;
    }

    public int compareTo(DijkstraNode n) {
        final double result = this.dist - n.getDist();
        return (int) result;
    }
}