package fr.ninauve.renaud.kata.bankocr;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ComputeChecksumTest {

    @Test
    public void test() {

        int actual = new ComputeChecksum().ofAccountNumber("345882865");

        assertThat(actual, is(0));
    }
}