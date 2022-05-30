package kata.string.calculator;

import java.util.List;
import java.util.stream.Collectors;

public class NegativeNumberException extends RuntimeException {

    public NegativeNumberException(final String message) {
        super(message);
    }

    public static NegativeNumberException create(final List<Integer> numbers) {
        final String numbersMessage = numbers.stream().map(Object::toString).collect(Collectors.joining(", "));
        return new NegativeNumberException("negatives not allowed: " + numbersMessage);
    }
}
