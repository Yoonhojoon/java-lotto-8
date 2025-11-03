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
        LottoNumberValidator.validatePurchaseCost(purchaseCost);
        return purchaseCost;
    }
}
