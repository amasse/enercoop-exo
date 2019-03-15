package org.enercoop.exo;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class IdCounterTest {

    @Test
    public void should_properly_count_ids() {
        // Given
        IdCounter.Builder builder = new IdCounter.Builder();
        UrlProvider provider = TestUtils.createProvider(
                "http://www.test.com?t=2&id=3&koo=3",
                "http://www.test.com?id=4&koo=3",
                "http://www.test.com?t=2&id=4"
        );

        // When
        builder.withProvider(provider);
        IdCounter counter = builder.build();

        // Then
        assertThat(counter.getOccurrencesOf(3L)).isEqualTo(1);
        assertThat(counter.getOccurrencesOf(4L)).isEqualTo(2);
    }

    @Test
    public void should_properly_compute_rank() {
        // Given
        IdCounter.Builder builder = new IdCounter.Builder();
        UrlProvider provider = TestUtils.createProvider(
                "http://www.test.com?t=2&id=1",
                "http://www.test.com?t=2&id=1",
                "http://www.test.com?t=2&id=1",
                "http://www.test.com?t=2&id=1",
                "http://www.test.com?t=2&id=2",
                "http://www.test.com?t=2&id=2",
                "http://www.test.com?t=2&id=3",
                "http://www.test.com?t=2&id=3",
                "http://www.test.com?t=2&id=3",
                "http://www.test.com?t=2&id=4",
                "http://www.test.com?t=2&id=5"
        );

        // When
        builder.withProvider(provider);
        IdCounter counter = builder.build();

        // Then
        List<RankedId> top = counter.getTop(4);

        assertThat(top).containsExactly(
                new RankedId(1, 4, 1L, false),
                new RankedId(2, 3, 3L, false),
                new RankedId(3, 2, 2L, false),
                new RankedId(4, 1, 4L, true),
                new RankedId(4, 1, 5L, true)
        );


    }

}