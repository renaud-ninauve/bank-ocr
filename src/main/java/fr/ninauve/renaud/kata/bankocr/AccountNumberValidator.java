package fr.ninauve.renaud.kata.bankocr;

import java.util.regex.Pattern;

public class AccountNumberValidator {

    private static final Pattern ACCOUNT_NUMBER_PATTERN = Pattern.compile("\\d{9}");

    private final ComputeChecksum computeChecksum;

    public AccountNumberValidator(ComputeChecksum computeChecksum) {

        this.computeChecksum = computeChecksum;
    }

    public boolean isValid(final String accountNumber) {

        if (!ACCOUNT_NUMBER_PATTERN.matcher(accountNumber).matches()) {
            return false;
        }
        return computeChecksum.ofAccountNumber(accountNumber) == 0;
    }
}
