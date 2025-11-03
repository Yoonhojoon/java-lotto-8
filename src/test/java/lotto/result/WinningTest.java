package lotto.result;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningTest {

    @DisplayName("당첨 번호는 6개, 범위/중복 검증, 보너스는 범위 및 중복 불가")
    @Test
    void 검증() {
        assertThatThrownBy(() -> new Winning(List.of(1,2,3,4,5), 7))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Winning(List.of(1,2,3,4,5,5), 7))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Winning(List.of(0,2,3,4,5,6), 7))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Winning(List.of(1,2,3,4,5,6), 46))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Winning(List.of(1,2,3,4,5,6), 6))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
