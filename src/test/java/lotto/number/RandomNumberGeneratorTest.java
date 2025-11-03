package lotto.number;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

class RandomNumberGeneratorTest {

    private final RandomNumberGenerator generator = new RandomNumberGenerator();

    @DisplayName("6개, 1~45 범위, 중복 없음, 오름차순 정렬을 만족한다")
    @RepeatedTest(5)
    void 생성_규칙을_만족한다() {
        // when
        List<Integer> numbers = generator.generate();

        // then
        assertThat(numbers).hasSize(6);
        assertThat(numbers).isSorted();

        Set<Integer> set = new HashSet<>(numbers);
        assertThat(set).hasSize(6);
        assertThat(numbers).allMatch(n -> n >= 1 && n <= 45);
    }
}
