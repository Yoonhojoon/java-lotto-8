package lotto.io.input;

import java.util.Arrays;
import java.util.List;

import lotto.validation.LottoNumberValidator;

public class InputParser {
    public List<Integer> parseWinningNumbers(String input) {
        List<Integer> numbers = Arrays.stream(input.split(","))
            .map(String::trim)
            .map(Integer::parseInt)
            .toList();
        
        LottoNumberValidator.validateWinningNumbers(numbers);
        
        return numbers.stream()
            .distinct()
            .sorted()
            .toList();
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
