package kata.string.calculator;


import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StringCalculator {

    public  int add(final String input) throws NegativeNumberException {
         InputExpression inputExpression = new InputExpression(input);
        if (inputExpression.isEmpty()) {
            return 0;
        }
        final List<Integer> numbers = inputExpression.getNumbers();
        if (numbers.stream().anyMatch(isNegative())) {
            throw NegativeNumberException.create(getAllNumberNegatives(numbers));
        }
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

    private  List<Integer> getAllNumberNegatives(final List<Integer> numbers) {
        return numbers.stream().filter(isNegative()).collect(Collectors.toList());
    }

    private  Predicate<Integer> isNegative() {
        return number -> number < 0;
    }

}
