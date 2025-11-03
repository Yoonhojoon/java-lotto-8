package lotto.result;

import java.util.Map;

public class ProfitCalculator {

    public long calculateTotalPrize(Map<Rank, Long> counts) {
        long total = 0L;
        if (counts == null) {
            return 0L;
        }
        for (Map.Entry<Rank, Long> entry : counts.entrySet()) {
            Rank rank = entry.getKey();
            long count = entry.getValue() == null ? 0L : entry.getValue();
            total += (long) rank.getPrize() * count;
        }
        return total;
    }

    public double calculateRate(Map<Rank, Long> counts, int purchaseCost) {
        if (purchaseCost <= 0) {
            return 0.0;
        }
        long totalPrize = calculateTotalPrize(counts);
        return (double) totalPrize / purchaseCost;
    }
}


