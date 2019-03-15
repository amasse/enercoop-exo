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
    public void should_count_top_3_ids() {
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
        List<Long> top = counter.getTop(3);
        assertThat(top).containsExactly(1L, 3L, 2L);
    }

}