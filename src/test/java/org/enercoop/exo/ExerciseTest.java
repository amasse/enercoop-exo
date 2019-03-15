package org.enercoop.exo;

import org.junit.Test;

import java.util.List;

public class ExerciseTest {

    @Test
    public void should_answer_to_exercise() {
        // Given
        UrlProvider provider = UrlProvider.getFor("./src/test/resources/logs");
        IdCounter counter = new IdCounter.Builder().withProvider(provider).build();

        // When
        List<RankedId> top = counter.getTop(5);

        // Then -> Print output !
        top.forEach(System.out::println);

    }
}
