package lotto.result;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import lotto.Lotto;

public class ResultEvaluator {

    public Map<Rank, Long> evaluateCounts(List<Lotto> tickets, Winning winning) {
        Map<Rank, Long> counts = new EnumMap<>(Rank.class);
        for (Lotto lotto : tickets) {
            int match = lotto.countMatchingNumbers(winning.getNumbers());
            boolean matchBonus = lotto.containsNumber(winning.getBonus());
            Rank rank = Rank.of(match, matchBonus);
            counts.put(rank, counts.getOrDefault(rank, 0L) + 1);
        }
        return counts;
    }
}


