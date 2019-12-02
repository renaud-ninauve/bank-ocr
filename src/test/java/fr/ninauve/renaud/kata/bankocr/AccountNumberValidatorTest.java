package fr.ninauve.renaud.kata.bankocr;

import fr.ninauve.renaud.kata.bankocr.AccountNumberValidator.ValidationResult;
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
    public void is_ok_when_contains_9_numbers_and_checksum_equals_zero() {

        final String accountNumber = "123456789";
        when(computeChecksum.ofAccountNumber(accountNumber)).thenReturn(0);

        final ValidationResult actual = validator.validate(accountNumber);

        assertThat(actual, is(ValidationResult.OK));
    }

    @Test
    public void is_bad_format_valid_when_contains_10_numbers() {

        final String accountNumber = "1234567890";

        final ValidationResult actual = validator.validate(accountNumber);

        assertThat(actual, is(ValidationResult.BAD_FORMAT));
    }

    @Test
    public void is_bad_format_when_contains_question_mark() {

        final String accountNumber = "1234?6789";

        final ValidationResult actual = validator.validate(accountNumber);

        assertThat(actual, is(ValidationResult.BAD_FORMAT));
    }

    @Test
    public void is_not_valid_when_contains_9_numbers_and_checksum_not_zero() {

        final String accountNumber = "123456789";
        when(computeChecksum.ofAccountNumber(accountNumber)).thenReturn(42);

        final ValidationResult actual = validator.validate(accountNumber);

        assertThat(actual, is(ValidationResult.BAD_CHECKSUM));
    }
}