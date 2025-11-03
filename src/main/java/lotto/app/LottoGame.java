package lotto.app;

import java.util.List;
import java.util.Map;

import lotto.Lotto;
import lotto.io.input.InputParser;
import lotto.io.input.InputView;
import lotto.io.output.OutputView;
import lotto.number.NumberGenerator;
import lotto.number.RandomNumberGenerator;
import lotto.result.ProfitCalculator;
import lotto.result.ResultEvaluator;
import lotto.result.Winning;
import lotto.service.LottoIssuer;

public class LottoGame {
    private final OutputView outputView;
    private final InputParser inputParser;
    private final LottoIssuer lottoIssuer;
    private final NumberGenerator numberGenerator;
    private final ResultEvaluator resultEvaluator;
    private final ProfitCalculator profitCalculator;

    public LottoGame() {
        this(new OutputView(), new InputParser(), new LottoIssuer(), new RandomNumberGenerator(), new ResultEvaluator(), new ProfitCalculator());
    }

    public LottoGame(OutputView outputView,
                     InputParser inputParser,
                     LottoIssuer lottoIssuer,
                     NumberGenerator numberGenerator,
                     ResultEvaluator resultEvaluator,
                     ProfitCalculator profitCalculator) {
        this.outputView = outputView;
        this.inputParser = inputParser;
        this.lottoIssuer = lottoIssuer;
        this.numberGenerator = numberGenerator;
        this.resultEvaluator = resultEvaluator;
        this.profitCalculator = profitCalculator;
    }

    public void run() {
        outputView.printPurchaseCost();
        int purchaseCost = inputParser.validatePurchaseCost(InputView.read());

        List<Lotto> tickets = lottoIssuer.issue(purchaseCost, numberGenerator);
        outputView.printPurchaseAmount(tickets.size());
        outputView.printPurchaseRecords(tickets);

        outputView.printWinNumber();
        List<Integer> winningNumbers = inputParser.parseWinningNumbers(InputView.read());

        outputView.printBonusNumber();
        int bonus = Integer.parseInt(InputView.read());
        Winning winning = new Winning(winningNumbers, bonus);

        Map<lotto.result.Rank, Long> counts = resultEvaluator.evaluateCounts(tickets, winning);
        outputView.printWinStatistics();
        outputView.printEachMatchStatus(counts);
        double ratePercent = profitCalculator.calculateRate(counts, purchaseCost) * 100.0;
        outputView.printTotalReturnStatus(ratePercent);
    }
}


