package org.enercoop.exo;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;

public class IdCounter {

    private static final String PARAM_ID = "id";
    private Multiset<Long> idCount = HashMultiset.create();

    private IdCounter() {
    }

    private void add(UrlProvider provider) {
        provider.provide().forEach(url ->
                idCount.add(Stream.of(url.getQuery().split("&"))
                        .filter(s -> s.startsWith(PARAM_ID))
                        .map(s -> Long.valueOf(s.split("=")[1]))
                        .findFirst()
                        .orElse(0L)));
    }

    public int getOccurrencesOf(Long id) {
        return idCount.count(id);
    }

    public List<RankedId> getTop(int size) {
        return getRankedIds(getTopCount(size));

    }

    private List<RankedId> getRankedIds(List<Integer> topCount) {
        return idCount.entrySet().stream()
                .filter(entry -> topCount.contains(entry.getCount()))
                .map(entry -> new RankedId(getRank(topCount, entry), entry.getCount(), entry.getElement(), isExAequo(entry.getCount())))
                .sorted(comparing(RankedId::getRank).thenComparing(RankedId::getId))
                .collect(Collectors.toList());

    }

    private int getRank(List<Integer> topCount, Multiset.Entry<Long> entry) {
        return topCount.indexOf(entry.getCount()) + 1;
    }

    private boolean isExAequo(int count) {
        return idCount.entrySet().stream().filter(entry -> entry.getCount() == count).count() > 1;
    }

    private List<Integer> getTopCount(int size) {
        return idCount.entrySet().stream()
                .map(Multiset.Entry::getCount)
                .distinct()
                .sorted(reverseOrder())
                .limit(size)
                .collect(Collectors.toList());
    }

    public static class Builder {
        private IdCounter counter;

        public Builder() {
            counter = new IdCounter();
        }

        public Builder withProvider(UrlProvider provider) {
            counter.add(provider);
            return this;
        }

        public IdCounter build() {
            return counter;
        }
    }

}
