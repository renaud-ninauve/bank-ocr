package fr.ninauve.renaud.kata.bankocr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

public class BankOcr {

    private static final List<String> _0 = asList(
                    " _ ",
                    "| |",
                    "|_|",
                    "   ");
    private static final List<String> _1 = asList(
                    "   ",
                    "  |",
                    "  |",
                    "   ");
    private static final List<String> _2 = asList(
                    " _ ",
                    " _|",
                    "|_ ",
                    "   ");
    private static final List<String> _3 = asList(
                    " _ ",
                    " _|",
                    " _|",
                    "   ");
    private static final List<String> _4 = asList(
                    "   ",
                    "|_|",
                    "  |",
                    "   ");
    private static final List<String> _5 = asList(
                    " _ ",
                    "|_ ",
                    " _|",
                    "   ");
    private static final List<String> _6 = asList(
                    " _ ",
                    "|_ ",
                    "|_|",
                    "   ");
    private static final List<String> _7 = asList(
                    " _ ",
                    "  |",
                    "  |",
                    "   ");
    private static final List<String> _8 = asList(
                    " _ ",
                    "|_|",
                    "|_|",
                    "   ");
    private static final List<String> _9 = asList(
                    " _ ",
                    "|_|",
                    " _|",
                    "   ");

    private static final Map<List<String>, String> NUMBER_BY_STRINGS = new HashMap<>();
    static {
        NUMBER_BY_STRINGS.put(_0, "0");
        NUMBER_BY_STRINGS.put(_1, "1");
        NUMBER_BY_STRINGS.put(_2, "2");
        NUMBER_BY_STRINGS.put(_3, "3");
        NUMBER_BY_STRINGS.put(_4, "4");
        NUMBER_BY_STRINGS.put(_5, "5");
        NUMBER_BY_STRINGS.put(_6, "6");
        NUMBER_BY_STRINGS.put(_7, "7");
        NUMBER_BY_STRINGS.put(_8, "8");
        NUMBER_BY_STRINGS.put(_9, "9");
    }

    public String parseToAccountNumber(List<String> lines) {

        final String line0 = lines.get(0);
        final String line1 = lines.get(1);
        final String line2 = lines.get(2);
        final String line3 = lines.get(3);

        return IntStream.range(0, line0.length()/3)
                .mapToObj(i -> {
                    final int startInclusive = i * 3;
                    final int endExclusive = startInclusive + 3;
                    return asList(
                            line0.substring(startInclusive, endExclusive),
                            line1.substring(startInclusive, endExclusive),
                            line2.substring(startInclusive, endExclusive),
                            line3.substring(startInclusive, endExclusive)
                    );
                }).map(strings -> NUMBER_BY_STRINGS.getOrDefault(strings, "?"))
                .collect(Collectors.joining());
    }
}
