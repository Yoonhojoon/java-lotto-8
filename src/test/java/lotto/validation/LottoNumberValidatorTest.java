package lotto.validation;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LottoNumberValidatorTest {

    @Nested
    @DisplayName("보너스 번호 검증")
    class ValidateBonusNumberTest {

        @Test
        @DisplayName("보너스 번호가 1~45이고 당첨 번호와 중복되지 않으면 통과한다")
        void 보너스_정상() {
            List<Integer> winning = List.of(1, 2, 3, 4, 5, 6);
            int bonus = 7;

            assertThatCode(() -> LottoNumberValidator.validateBonus(bonus, winning))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다")
        void 보너스_중복_예외() {
            List<Integer> winning = List.of(1, 2, 3, 4, 5, 6);
            int bonus = 6;

            assertThatThrownBy(() -> LottoNumberValidator.validateBonus(bonus, winning))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }

        @Test
        @DisplayName("보너스 번호가 범위를 벗어나면 예외가 발생한다 - 하한 미만")
        void 보너스_범위_하한() {
            List<Integer> winning = List.of(1, 2, 3, 4, 5, 6);
            int bonus = 0;

            assertThatThrownBy(() -> LottoNumberValidator.validateBonus(bonus, winning))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 보너스 번호는 1~45 사이의 숫자여야 합니다.");
        }

        @Test
        @DisplayName("보너스 번호가 범위를 벗어나면 예외가 발생한다 - 상한 초과")
        void 보너스_범위_상한() {
            List<Integer> winning = List.of(1, 2, 3, 4, 5, 6);
            int bonus = 46;

            assertThatThrownBy(() -> LottoNumberValidator.validateBonus(bonus, winning))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 보너스 번호는 1~45 사이의 숫자여야 합니다.");
        }
    }
}


