package lotto.result;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitCalculatorTest {

    @DisplayName("등수별 개수로 총 상금과 수익률을 계산한다")
    @Test
    void 수익률_계산() {
        // given
        Map<Rank, Long> counts = new EnumMap<>(Rank.class);
        counts.put(Rank.FIFTH, 2L);   // 2 * 5,000 = 10,000
        counts.put(Rank.FOURTH, 1L);  // 1 * 50,000 = 50,000
        counts.put(Rank.NONE, 3L);    // 0원
        int purchaseCost = 100_000;   // 총 지출

        ProfitCalculator calculator = new ProfitCalculator();

        // when
        long totalPrize = calculator.calculateTotalPrize(counts);
        double rate = calculator.calculateRate(counts, purchaseCost);

        // then
        assertThat(totalPrize).isEqualTo(60_000);
        assertThat(rate).isEqualTo(0.6); // 60,000 / 100,000
    }

    @DisplayName("등수 정보가 없으면 0으로 계산된다")
    @Test
    void 빈집계_처리() {
        Map<Rank, Long> counts = new EnumMap<>(Rank.class);
        ProfitCalculator calculator = new ProfitCalculator();

        assertThat(calculator.calculateTotalPrize(counts)).isZero();
        assertThat(calculator.calculateRate(counts, 50_000)).isZero();
    }
}
