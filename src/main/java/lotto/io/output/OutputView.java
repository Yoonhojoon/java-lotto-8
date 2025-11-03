package lotto.io.output;

import java.util.List;
import java.util.Map;

import lotto.Lotto;
import lotto.result.Rank;

public class OutputView {
    public void printPurchaseCost() { System.out.println("구입금액을 입력해 주세요."); }

    public void printPurchaseAmount(int purchaseNum) {System.out.println("\n"+ purchaseNum + "개를 구매했습니다.");}

    public void printPurchaseRecords(List<Lotto> tickets) {
        for (lotto.Lotto lotto : tickets) {
            System.out.println(lotto);
        }
    }

    public void printWinNumber() {System.out.println("\n당첨 번호를 입력해 주세요.");}

    public void printBonusNumber() {System.out.println("\n보너스 번호를 입력해 주세요.");}

    public void printWinStatistics() {
        System.out.println("\n당첨 통계\n---");
    }

    public void printEachMatchStatus(Map<Rank, Long> counts){
        System.out.println("3개 일치 (5,000원) - " + counts.getOrDefault(Rank.FIFTH, 0L) + "개");
        System.out.println("4개 일치 (50,000원) - " + counts.getOrDefault(Rank.FOURTH, 0L) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + counts.getOrDefault(Rank.THIRD, 0L) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + counts.getOrDefault(Rank.SECOND, 0L) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + counts.getOrDefault(Rank.FIRST, 0L) + "개");
    }

    public void printTotalReturnStatus(double returnRatePercent) {
        System.out.println("총 수익률은 " + String.format("%.1f", returnRatePercent) + "%입니다.");
    }

    public void printError(String message) {
        System.out.println(message);
    }
}
