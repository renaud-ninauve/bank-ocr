package fr.ninauve.renaud.kata.bankocr;

import java.util.regex.Pattern;

public class AccountNumberValidator {

    private static final Pattern ACCOUNT_NUMBER_PATTERN = Pattern.compile("\\d{9}");

    private final ComputeChecksum computeChecksum;

    public AccountNumberValidator(ComputeChecksum computeChecksum) {

        this.computeChecksum = computeChecksum;
    }

    public ValidationResult validate(final String accountNumber) {

        if (!ACCOUNT_NUMBER_PATTERN.matcher(accountNumber).matches()) {
            return ValidationResult.BAD_FORMAT;
        }
        return computeChecksum.ofAccountNumber(accountNumber) == 0
                ? ValidationResult.OK : ValidationResult.BAD_CHECKSUM;
    }

    public enum ValidationResult {
        OK, BAD_FORMAT, BAD_CHECKSUM
    }
}
