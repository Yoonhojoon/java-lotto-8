package lotto.io.output;

public class OutputView {
    public void printPurchaseCost() { System.out.println("구입금액을 입력해 주세요."); }

    public void printPurchaseAmount(int purchaseNum) {System.out.println(purchaseNum + "개를 구매했습니다.");}

    public void printPurchaseRecords() {}

    public void printWinNumber() {System.out.println("당첨 번호를 입력해 주세요.");}

    public void printBonusNumber() {System.out.println("보너스 번호를 입력해 주세요.");}

    public void printWinStatistics() {
        System.out.println("당첨 통계\n---");
        printEachMatchStatus();
    }

    public void printEachMatchStatus(){}

    public void printTotalReturnStatus(int returnRate) {System.out.println("총 수익률은 "+ returnRate  +
            "%입니다.");}
}
