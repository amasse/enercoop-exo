package org.enercoop.exo;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryCounterIntegrationTest {

    @Test
    public void should_answer_to_exercise() {
        Path path = Paths.get(".");
        System.out.println(path.toAbsolutePath());


    }
}
