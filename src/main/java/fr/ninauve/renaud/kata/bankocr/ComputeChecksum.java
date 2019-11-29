package fr.ninauve.renaud.kata.bankocr;

public class ComputeChecksum {

    public int ofAccountNumber(final String accountNumber) {

        int checksum = 0;
        for (int i = 0; i < 9; i++) {
            int number = Integer.valueOf(accountNumber.substring(i, i + 1));
            checksum += number * (9 - i);
        }
        return checksum % 11;
    }
}
