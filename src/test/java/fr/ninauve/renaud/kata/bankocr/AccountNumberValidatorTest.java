package fr.ninauve.renaud.kata.bankocr;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountNumberValidatorTest {

    @Mock
    private ComputeChecksum computeChecksum;
    @InjectMocks
    private AccountNumberValidator validator;

    @Test
    public void is_valid_when_contains_9_numbers_and_checksum_equals_zero() {

        final String accountNumber = "123456789";
        when(computeChecksum.ofAccountNumber(accountNumber)).thenReturn(0);

        boolean actual = validator.isValid(accountNumber);

        assertThat(actual, is(true));
    }

    @Test
    public void is_not_valid_when_contains_10_numbers() {

        final String accountNumber = "1234567890";

        boolean actual = validator.isValid(accountNumber);

        assertThat(actual, is(false));
    }

    @Test
    public void is_not_valid_when_contains_question_mark() {

        final String accountNumber = "1234?6789";

        boolean actual = validator.isValid(accountNumber);

        assertThat(actual, is(false));
    }

    @Test
    public void is_not_valid_when_contains_9_numbers_and_checksum_not_equals() {

        final String accountNumber = "123456789";
        when(computeChecksum.ofAccountNumber(accountNumber)).thenReturn(42);

        boolean actual = validator.isValid(accountNumber);

        assertThat(actual, is(false));
    }
}