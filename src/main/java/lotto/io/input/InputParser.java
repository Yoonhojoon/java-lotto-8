package lotto.io.input;

import java.util.Arrays;
import java.util.List;

public class InputParser {
    public List<Integer> parseWinningNumbers(String input) {
        List<Integer> numbers = Arrays.stream(input.split(","))
            .map(Integer::parseInt)
            .toList();
        
        validateWinningNumbers(numbers);
        
        return numbers.stream()
            .distinct()
            .sorted()
            .toList();
    }

    private void validateWinningNumbers(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }
        if (numbers.stream().anyMatch(number -> number < 1 || number > 45)) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 1~45 사이의 숫자여야 합니다.");
        }
        if (numbers.stream().distinct().count() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복되지 않아야 합니다.");
        }
    }

    public int validatePurchaseCost(String input) {
        int purchaseCost = Integer.parseInt(input);
        if (purchaseCost % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
        }
        if (purchaseCost <= 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 0원 이상이어야 합니다.");
        }
        return purchaseCost;
    }
}
