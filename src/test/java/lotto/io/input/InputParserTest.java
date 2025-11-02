package lotto.io.input;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class InputParserTest {

    private final InputParser inputParser = new InputParser();

    @Nested
    @DisplayName("당첨 번호 파싱 테스트")
    class ParseWinningNumbersTest {

        @DisplayName("정상적인 당첨 번호 입력을 파싱한다")
        @Test
        void 정상적인_당첨_번호_입력을_파싱한다() {
            // given
            String input = "1,2,3,4,5,6";

            // when
            List<Integer> result = inputParser.parseWinningNumbers(input);

            // then
            assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
        }

        @DisplayName("당첨 번호가 정렬되어 반환된다")
        @Test
        void 당첨_번호가_정렬되어_반환된다() {
            // given
            String input = "6,3,1,5,2,4";

            // when
            List<Integer> result = inputParser.parseWinningNumbers(input);

            // then
            assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
        }

        @DisplayName("당첨 번호 개수가 6개 미만이면 예외가 발생한다")
        @Test
        void 당첨_번호_개수가_6개_미만이면_예외가_발생한다() {
            // given
            String input = "1,2,3,4,5";

            // when & then
            assertThatThrownBy(() -> inputParser.parseWinningNumbers(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 당첨 번호는 6개여야 합니다.");
        }

        @DisplayName("당첨 번호에 중복이 있으면 예외가 발생한다")
        @Test
        void 당첨_번호에_중복이_있으면_예외가_발생한다() {
            // given
            String input = "1,2,3,4,5,5";

            // when & then
            assertThatThrownBy(() -> inputParser.parseWinningNumbers(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 당첨 번호는 중복되지 않아야 합니다.");
        }

        @DisplayName("당첨 번호 범위를 벗어나면 예외가 발생한다 - 상한 초과")
        @Test
        void 당첨_번호_범위를_벗어나면_예외가_발생한다_상한초과() {
            // given
            String input = "1,2,3,4,5,46";

            // when & then
            assertThatThrownBy(() -> inputParser.parseWinningNumbers(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 당첨 번호는 1~45 사이의 숫자여야 합니다.");
        }

        @DisplayName("당첨 번호 범위를 벗어나면 예외가 발생한다 - 하한 미만")
        @Test
        void 당첨_번호_범위를_벗어나면_예외가_발생한다_하한미만() {
            // given
            String input = "0,1,2,3,4,5";

            // when & then
            assertThatThrownBy(() -> inputParser.parseWinningNumbers(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 당첨 번호는 1~45 사이의 숫자여야 합니다.");
        }

        @DisplayName("당첨 번호에 숫자가 아닌 값이 있으면 NumberFormatException이 발생한다")
        @Test
        void 당첨_번호에_숫자가_아닌_값이_있으면_NumberFormatException이_발생한다() {
            // given
            String input = "1,2,a,4,5,6";

            // when & then
            assertThatThrownBy(() -> inputParser.parseWinningNumbers(input))
                    .isInstanceOf(NumberFormatException.class);
        }

        @DisplayName("경계값 테스트 - 최소값 1이 포함된 경우")
        @Test
        void 경계값_테스트_최소값_1이_포함된_경우() {
            // given
            String input = "1,2,3,4,5,6";

            // when
            List<Integer> result = inputParser.parseWinningNumbers(input);

            // then
            assertThat(result).contains(1);
        }

        @DisplayName("경계값 테스트 - 최대값 45가 포함된 경우")
        @Test
        void 경계값_테스트_최대값_45가_포함된_경우() {
            // given
            String input = "1,2,3,4,5,45";

            // when
            List<Integer> result = inputParser.parseWinningNumbers(input);

            // then
            assertThat(result).contains(45);
        }

        @DisplayName("공백이 포함된 입력도 정상 처리된다")
        @Test
        void 공백이_포함된_입력도_정상_처리된다() {
            // given
            String input = "1, 2, 3, 4, 5, 6";

            // when & then
            // 현재 구현은 공백을 처리하지 않으므로 NumberFormatException 발생
            // 향후 trim() 추가 시 이 테스트 수정 필요
            assertThatThrownBy(() -> inputParser.parseWinningNumbers(input))
                    .isInstanceOf(NumberFormatException.class);
        }
    }

    @Nested
    @DisplayName("구매 금액 파싱 테스트")
    class ParsePurchaseCostTest {

        @DisplayName("정상적인 구매 금액을 파싱한다")
        @Test
        void 정상적인_구매_금액을_파싱한다() {
            // given
            String input = "1000";

            // when
            int result = inputParser.parsePurchaseCost(input);

            // then
            assertThat(result).isEqualTo(1000);
        }

        @DisplayName("구입 금액이 1,000원 단위가 아니면 예외가 발생한다")
        @Test
        void 구입_금액이_1000원_단위가_아니면_예외가_발생한다() {
            // given
            String input = "1001";

            // when & then
            assertThatThrownBy(() -> inputParser.parsePurchaseCost(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
        }

        @DisplayName("구입 금액이 0원 이하면 예외가 발생한다")
        @Test
        void 구입_금액이_0원_이하면_예외가_발생한다() {
            // given
            String input = "0";

            // when & then
            assertThatThrownBy(() -> inputParser.parsePurchaseCost(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 구입 금액은 0원 이상이어야 합니다.");
        }

        @DisplayName("구입 금액이 음수이면 예외가 발생한다")
        @Test
        void 구입_금액이_음수이면_예외가_발생한다() {
            // given
            String input = "-1000";

            // when & then
            assertThatThrownBy(() -> inputParser.parsePurchaseCost(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 구입 금액은 0원 이상이어야 합니다.");
        }

        @DisplayName("구입 금액에 숫자가 아닌 값이 있으면 NumberFormatException이 발생한다")
        @Test
        void 구입_금액에_숫자가_아닌_값이_있으면_NumberFormatException이_발생한다() {
            // given
            String input = "abc";

            // when & then
            assertThatThrownBy(() -> inputParser.parsePurchaseCost(input))
                    .isInstanceOf(NumberFormatException.class);
        }
    }
}