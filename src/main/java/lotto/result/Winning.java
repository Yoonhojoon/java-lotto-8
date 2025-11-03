package lotto.result;

import java.util.ArrayList;
import java.util.List;

import lotto.validation.LottoNumberValidator;

public class Winning {
    private final List<Integer> numbers;
    private final int bonus;

    public Winning(List<Integer> numbers, int bonus) {
        LottoNumberValidator.validateWinningNumbers(numbers);
        LottoNumberValidator.validateBonus(bonus, numbers);
        this.numbers = new ArrayList<>(numbers);
        this.bonus = bonus;
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }

    public int getBonus() {
        return bonus;
    }
}


