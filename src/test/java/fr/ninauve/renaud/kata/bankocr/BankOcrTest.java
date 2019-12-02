package fr.ninauve.renaud.kata.bankocr;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static fr.ninauve.renaud.kata.bankocr.BankOcrConstantsTest._000000000;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BankOcrTest {

    @InjectMocks
    private BankOcr bankOcr;

    @Mock
    private AccountNumberParser parser;
    @Mock
    private AccountNumberValidator validator;

    private static final List<String> ocr = asList(_000000000.split("\n"));
    private static final String parseResult = "123456789";
    private static final String parseResult_err = "123456789 ERR";
    private static final String parseResult_ill = "123456789 ILL";

    @Test
    public void return_account_number_only_when_ok() {


        when(parser.parseToAccountNumber(ocr)).thenReturn(parseResult);
        when(validator.validate(parseResult)).thenReturn(AccountNumberValidator.ValidationResult.OK);

        final String actual = bankOcr.extractAccountNumberFromOcr(ocr);

        assertThat(actual, is(parseResult));
    }

    @Test
    public void return_account_number_ill_when_bad_format() {


        when(parser.parseToAccountNumber(ocr)).thenReturn(parseResult);
        when(validator.validate(parseResult)).thenReturn(AccountNumberValidator.ValidationResult.BAD_FORMAT);

        final String actual = bankOcr.extractAccountNumberFromOcr(ocr);

        assertThat(actual, is(parseResult_ill));
    }

    @Test
    public void return_account_number_err_when_bad_checksum() {


        when(parser.parseToAccountNumber(ocr)).thenReturn(parseResult);
        when(validator.validate(parseResult)).thenReturn(AccountNumberValidator.ValidationResult.BAD_CHECKSUM);

        final String actual = bankOcr.extractAccountNumberFromOcr(ocr);

        assertThat(actual, is(parseResult_err));
    }
}