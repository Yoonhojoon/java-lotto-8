package lotto.result;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.Lotto;

class ResultEvaluatorTest {

    @DisplayName("로또 목록을 등수별로 집계한다")
    @Test
    void 집계() {
        Winning winning = new Winning(Arrays.asList(1,2,3,4,5,6), 7);
        ResultEvaluator evaluator = new ResultEvaluator();

        List<Lotto> tickets = Arrays.asList(
                new Lotto(Arrays.asList(1,2,3,4,5,6)), // 1등
                new Lotto(Arrays.asList(1,2,3,4,5,8)), // 3등 (보너스 제외)
                new Lotto(Arrays.asList(1,2,3,4,8,7)), // 4등
                new Lotto(Arrays.asList(1,2,3,9,8,7)), // 5등
                new Lotto(Arrays.asList(10,11,12,13,14,15)) // 꽝
        );

        Map<Rank, Long> counts = evaluator.evaluateCounts(tickets, winning);

        assertThat(counts.getOrDefault(Rank.FIRST, 0L)).isEqualTo(1);
        assertThat(counts.getOrDefault(Rank.THIRD, 0L)).isEqualTo(1);
        assertThat(counts.getOrDefault(Rank.FOURTH, 0L)).isEqualTo(1);
        assertThat(counts.getOrDefault(Rank.FIFTH, 0L)).isEqualTo(1);
        assertThat(counts.getOrDefault(Rank.NONE, 0L)).isEqualTo(1);
    }

    @DisplayName("보너스 포함 2등 분기 확인")
    @Test
    void 이등_보너스() {
        Winning winning = new Winning(Arrays.asList(1,2,3,4,5,6), 7);
        ResultEvaluator evaluator = new ResultEvaluator();

        List<Lotto> tickets = Arrays.asList(
                new Lotto(Arrays.asList(1,2,3,4,5,7)) // 5개 + 보너스 → 2등
        );

        Map<Rank, Long> counts = evaluator.evaluateCounts(tickets, winning);

        assertThat(counts.getOrDefault(Rank.SECOND, 0L)).isEqualTo(1);
    }
}
