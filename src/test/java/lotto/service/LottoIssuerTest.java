package lotto.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.Lotto;
import lotto.number.NumberGenerator;

class LottoIssuerTest {

    @DisplayName("구매 금액/1000 만큼 로또를 발행한다")
    @Test
    void 구매금액으로_발행_수량을_결정한다() {
        // given
        int purchaseCost = 5000;
        CountingFakeGenerator generator = new CountingFakeGenerator(Arrays.asList(1,2,3,4,5,6));
        LottoIssuer issuer = new LottoIssuer();

        // when
        List<Lotto> tickets = issuer.issue(purchaseCost, generator);

        // then
        assertThat(tickets).hasSize(5);
        assertThat(generator.getGenerateCallCount()).isEqualTo(5);
    }

    private static class CountingFakeGenerator implements NumberGenerator {
        private final List<Integer> numbers;
        private int generateCallCount = 0;

        private CountingFakeGenerator(List<Integer> numbers) {
            this.numbers = new ArrayList<>(numbers);
        }

        @Override
        public List<Integer> generate() {
            generateCallCount++;
            return new ArrayList<>(numbers);
        }

        public int getGenerateCallCount() {
            return generateCallCount;
        }
    }
}
