package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoQuantityTest {

    @Test
    @DisplayName("입력한 수동으로 구매할 수량이 음수인 경우, 예외를 발생한다")
    void checkValidManualLottoQuantity() {
        final int manualLottoQuantity = -1;
        final Money money = new Money(1000);

        assertThatThrownBy(() -> LottoQuantity.of(manualLottoQuantity, money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매 수량은 반드시 양수여야 합니다.");
    }

    @Test
    @DisplayName("입력한 수동으로 구매할 수량이 지불한 돈을 초과한 경우, 예외를 발생한다")
    void checkEnoughMoney() {
        final int manualLottoQuantity = 2;
        final Money money = new Money(1000);

        assertThatThrownBy(() -> LottoQuantity.of(manualLottoQuantity, money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력한 수량만큼 구매할 수 없습니다.");
    }

    @ParameterizedTest(name = "{0}원으로 수동 로또 {1}장, 자동 로또{2}장 구매하는 경우")
    @MethodSource("lottoQuantityAndMoneyData")
    @DisplayName("입력한 수동으로 구매할 수량이 유효한 경우, 정상적으로 객체가 생성된다")
    void createLottoQuantity(final int moneyAmount, final int manualLottoQuantity, final int autoLottoQuantity) {
        final Money money = new Money(moneyAmount);

        final LottoQuantity lottoQuantity = LottoQuantity.of(manualLottoQuantity, money);

        assertThat(lottoQuantity.getManualLotto()).isEqualTo(manualLottoQuantity);
        assertThat(lottoQuantity.getAutoLotto()).isEqualTo(autoLottoQuantity);
    }

    static Stream<Arguments> lottoQuantityAndMoneyData() {
        return Stream.of(
                Arguments.of(1000, 1, 0),
                Arguments.of(1000, 0, 1),
                Arguments.of(2000, 2, 0),
                Arguments.of(2000, 1, 1),
                Arguments.of(2000, 0, 2)
        );
    }
}
