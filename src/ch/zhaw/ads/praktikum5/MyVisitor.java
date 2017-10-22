package ch.zhaw.ads.praktikum5;

import ch.zhaw.ads.praktikum3.Competitor;

public class MyVisitor implements Visitor<Competitor> {

    private StringBuilder stringBuilder;
    private int rank = 1;

    public MyVisitor(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    @Override
    public void visit(Competitor competitor) {
        competitor.setRank(rank);
        rank++;
        this.stringBuilder.append(competitor);
        this.stringBuilder.append("\n");
    }
}
