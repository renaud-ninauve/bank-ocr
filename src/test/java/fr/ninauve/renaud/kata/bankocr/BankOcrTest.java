package fr.ninauve.renaud.kata.bankocr;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class BankOcrTest {

    @Parameterized.Parameters
    public static List<Object[]> params() {

        return asList(
                new Object[]{ BankOcrConstantsTest._000000000, "000000000"},
                new Object[]{ BankOcrConstantsTest._111111111, "111111111"},
                new Object[]{ BankOcrConstantsTest._222222222, "222222222"},
                new Object[]{ BankOcrConstantsTest._333333333, "333333333"},
                new Object[]{ BankOcrConstantsTest._444444444, "444444444"},
                new Object[]{ BankOcrConstantsTest._555555555, "555555555"},
                new Object[]{ BankOcrConstantsTest._666666666, "666666666"},
                new Object[]{ BankOcrConstantsTest._777777777, "777777777"},
                new Object[]{ BankOcrConstantsTest._888888888, "888888888"},
                new Object[]{ BankOcrConstantsTest._999999999, "999999999"});
    }

    private final String lines;
    private final String expected;

    public BankOcrTest(String lines, String expected) {
        this.lines = lines;
        this.expected = expected;
    }

    @Test
    public void parse() {

        String[] splitLines = lines.split("\n");
        String actual = new BankOcr().parseToAccountNumber(asList(splitLines));
        assertThat(actual, is(expected));
    }
}