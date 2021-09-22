package org.automation;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTest {

    @Test
    public void shouldAnswerWithTrue() {
        assertThat(true).isEqualTo(true);
    }
}
