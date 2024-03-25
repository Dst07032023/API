package com.Den.Day5;

import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class HamcrestMatchersIntro {

    @Test
    public void simpleTest1() {

        assertThat(5+5, is(10));

    }
}
