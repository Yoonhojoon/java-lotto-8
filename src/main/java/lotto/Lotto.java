package lotto;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        if (numbers.stream().distinct().count() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복되지 않아야 합니다.");
        }
    }

    public int countMatchingNumbers(List<Integer> otherNumbers) {
        int count = 0;
        for (Integer n : otherNumbers) {
            if (numbers.contains(n)) {
                count++;
            }
        }
        return count;
    }

    public boolean containsNumber(int number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        return "[" + String.join(
                ", ",
                numbers.stream().map(String::valueOf).toList()
        ) + "]";
    }
}
