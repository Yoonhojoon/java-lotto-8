package lotto.app;

import java.util.List;

import lotto.io.input.InputParser;
import lotto.io.input.InputView;
import lotto.io.output.OutputView;
import lotto.validation.LottoNumberValidator;

public class InputController {
    private final OutputView outputView;
    private final InputParser inputParser;

    public InputController(OutputView outputView, InputParser inputParser) {
        this.outputView = outputView;
        this.inputParser = inputParser;
    }

    public int readPurchaseCost() {
        while (true) {
            outputView.printPurchaseCost();
            try {
                String input = InputView.read();
                return inputParser.validatePurchaseCost(input);
            } catch (RuntimeException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    public List<Integer> readWinningNumbers() {
        while (true) {
            outputView.printWinNumber();
            try {
                String input = InputView.read();
                return inputParser.parseWinningNumbers(input);
            } catch (RuntimeException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    public int readBonusNumber(List<Integer> winningNumbers) {
        while (true) {
            outputView.printBonusNumber();
            try {
                String input = InputView.read();
                int bonus = Integer.parseInt(input);
                LottoNumberValidator.validateBonus(bonus, winningNumbers);
                return bonus;
            } catch (RuntimeException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}


