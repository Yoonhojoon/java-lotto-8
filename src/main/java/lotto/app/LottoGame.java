package lotto.app;

import java.util.List;
import java.util.Map;

import lotto.Lotto;
import lotto.io.input.InputParser;
import lotto.io.output.OutputView;
import lotto.number.NumberGenerator;
import lotto.number.RandomNumberGenerator;
import lotto.result.ProfitCalculator;
import lotto.result.Rank;
import lotto.result.ResultEvaluator;
import lotto.result.Winning;
import lotto.service.LottoIssuer;

public class LottoGame {
    private final OutputView outputView;
    private final LottoIssuer lottoIssuer;
    private final NumberGenerator numberGenerator;
    private final ResultEvaluator resultEvaluator;
    private final ProfitCalculator profitCalculator;
    private final InputController inputController;

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
        this.lottoIssuer = lottoIssuer;
        this.numberGenerator = numberGenerator;
        this.resultEvaluator = resultEvaluator;
        this.profitCalculator = profitCalculator;
        this.inputController = new InputController(outputView, inputParser);
    }

    public void run() {
        int purchaseCost = inputController.readPurchaseCost();
        List<Lotto> tickets = lottoIssuer.issue(purchaseCost, numberGenerator);
        outputView.printPurchaseAmount(tickets.size());
        outputView.printPurchaseRecords(tickets);

        List<Integer> winningNumbers = inputController.readWinningNumbers();
        int bonus = inputController.readBonusNumber(winningNumbers);
        Winning winning = new Winning(winningNumbers, bonus);

        Map<Rank, Long> counts = resultEvaluator.evaluateCounts(tickets, winning);
        outputView.printWinStatistics();
        outputView.printEachMatchStatus(counts);
        double ratePercent = profitCalculator.calculateRate(counts, purchaseCost) * 100.0;
        outputView.printTotalReturnStatus(ratePercent);
    }
}


