package fr.ninauve.renaud.kata.bankocr;

import fr.ninauve.renaud.kata.bankocr.AccountNumberValidator.ValidationResult;

import java.util.List;

public class BankOcr {

    private final AccountNumberParser parser;
    private final AccountNumberValidator validator;

    public BankOcr(final AccountNumberParser parser,
                   final AccountNumberValidator validator) {

        this.parser = parser;
        this.validator = validator;
    }

    public String extractAccountNumberFromOcr(final List<String> ocr) {

        final String parseResult = parser.parseToAccountNumber(ocr);
        final ValidationResult validationResult = validator.validate(parseResult);
        final String suffix = createValidationSuffix(validationResult);
        return suffix.isEmpty() ? parseResult : parseResult + " " + suffix;
    }

    private static String createValidationSuffix(final ValidationResult validationResult) {

        switch (validationResult) {
            case OK:
                return "";
            case BAD_FORMAT:
                return "ILL";
            case BAD_CHECKSUM:
                return "ERR";
            default:
                throw new IllegalArgumentException("unknown result");
        }
    }
}
