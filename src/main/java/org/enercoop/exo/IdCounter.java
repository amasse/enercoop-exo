package org.enercoop.exo;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;

public class IdCounter {

    public static final String PARAM_ID = "id";
    private Multiset<Long> idCount = HashMultiset.create();

    private IdCounter() {}

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

    public List<Long> getTop(int size) {
        return idCount.entrySet().stream()
                .sorted(comparing(Multiset.Entry::getCount, reverseOrder()))
                .limit(size)
                .map(Multiset.Entry::getElement)
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
