package org.enercoop.exo;

import java.util.Objects;

public class RankedId {

    private int rank;

    private int count;

    private Long id;

    private boolean exAequo;


    public RankedId(int rank, int count, Long id, boolean exAequo) {
        this.rank = rank;
        this.count = count;
        this.id = id;
        this.exAequo = exAequo;
    }

    public int getRank() {
        return rank;
    }

    public int getCount() {
        return count;
    }

    public Long getId() {
        return id;
    }

    public boolean isExAequo() {
        return exAequo;
    }

    @Override
    public String toString() {
        return "RankedId{" +
                "rank=" + rank +
                ", id=" + id +
                ", count=" + count +
                ", exAequo=" + exAequo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RankedId)) return false;
        RankedId rankedId = (RankedId) o;
        return getRank() == rankedId.getRank() &&
                getCount() == rankedId.getCount() &&
                isExAequo() == rankedId.isExAequo() &&
                Objects.equals(getId(), rankedId.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRank(), getCount(), getId(), isExAequo());
    }
}
