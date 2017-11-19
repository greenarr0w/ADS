package ch.zhaw.ads.praktikum9;

public class Competitor implements Comparable {
    private String name;
    private int jahrgang;

    public Competitor(String name, int jahrgang) {
        this.name = name;
        this.jahrgang = jahrgang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJahrgang() {
        return jahrgang;
    }

    public void setJahrgang(int jahrgang) {
        this.jahrgang = jahrgang;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + ", Jahrgang: " + this.jahrgang + "\n";
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 13 + name.hashCode();
        hash = hash * 17 + jahrgang;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public int compareTo(Object o) {
        return this.hashCode() - o.hashCode();
    }
}
