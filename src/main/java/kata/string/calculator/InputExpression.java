package kata.string.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputExpression {

    private static final List<String> DEFAULT_DELIMITERS = Arrays.asList(",", "\n");

    private static final String START_DELIMITERS_EXPRESSION = "//";
    private static final String END_DELIMITERS_EXPRESSION = "\n";

    private final String expression;

    public InputExpression(final String expression) {
        this.expression = expression;
    }

    public List<Integer> getNumbers() {

        return getNumbers(getDelimiters());
    }

    private List<String> getDelimiters() {
        final List<String> separators = new ArrayList<>(DEFAULT_DELIMITERS);
        if (hasCustomSeparator()) {
            separators.add(expression.substring(getBeginDelimitersIndex(), getEndDelimitersIndex()));
        }
        return separators;
    }

    public List<Integer> getNumbers(final List<String> separators) {
        return getStringNumbers(separators).stream().map(Integer::valueOf).collect(Collectors.toList());
    }

    private List<String> getStringNumbers(final List<String> delimiters) {
        final String regex = delimiters.stream().map(Pattern::quote).collect(Collectors.joining("|"));
        return Stream.of(getNumberExpression().split(regex)).collect(Collectors.toList());
    }
    private String getNumberExpression() {
        return !hasCustomSeparator() ? expression : expression.substring(getEndDelimitersIndex() + 1);
    }
    public boolean isEmpty() {
        return "".equals(expression);
    }

    private boolean hasCustomSeparator() {
        return expression.startsWith(START_DELIMITERS_EXPRESSION);
    }

    private int getEndDelimitersIndex() {
        return expression.indexOf(END_DELIMITERS_EXPRESSION);
    }

    private int getBeginDelimitersIndex() {
        return expression.indexOf(START_DELIMITERS_EXPRESSION) + START_DELIMITERS_EXPRESSION.length();
    }
}
