package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {

    private WinningNumbers winningNumbers;

    @BeforeEach
    void setUpWinningNumbersAndLottoTicket() {
        Number bonusNumber = new Number(7);
        Lotto winningNumber = Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 6));
        winningNumbers = new WinningNumbers(winningNumber, bonusNumber);
    }

    @Test
    @DisplayName("로또 티켓은 순위별 당첨 수를 계산한다.")
    void findWinningCountByRank() {
        final LottoTicket myLottoTicket = new LottoTicket(Arrays.asList(
                Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 6)),
                Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 7))
        ));
        final EnumMap<LottoRank, Integer> expectedWinningCountByRank = new EnumMap<>(Map.ofEntries(
                Map.entry(LottoRank.FIRST, 1),
                Map.entry(LottoRank.SECOND, 1),
                Map.entry(LottoRank.THIRD, 0),
                Map.entry(LottoRank.FOURTH, 0),
                Map.entry(LottoRank.FIFTH, 0)
        ));

        final EnumMap<LottoRank, Integer> actual = myLottoTicket.findWinningCountByRank(winningNumbers);

        assertThat(actual).isEqualTo(expectedWinningCountByRank);
    }

    @Test
    @DisplayName("당첨된 로또가 하나도 없는 경우, 순위별 당첨 수는 모두 0이다")
    void findWinningCountOfFailLottoTicket() {
        final LottoTicket myLottoTicket = new LottoTicket(Arrays.asList(
                Lotto.from(Arrays.asList(10, 11, 12, 13, 14, 15)),
                Lotto.from(Arrays.asList(1, 30, 31, 45, 39, 32))
        ));
        final EnumMap<LottoRank, Integer> expectedWinningCountByRank = new EnumMap<>(Map.ofEntries(
                Map.entry(LottoRank.FIRST, 0),
                Map.entry(LottoRank.SECOND, 0),
                Map.entry(LottoRank.THIRD, 0),
                Map.entry(LottoRank.FOURTH, 0),
                Map.entry(LottoRank.FIFTH, 0)
        ));

        final EnumMap<LottoRank, Integer> actual = myLottoTicket.findWinningCountByRank(winningNumbers);

        assertThat(actual).isEqualTo(expectedWinningCountByRank);
    }

    @Test
    @DisplayName("구매한 로또가 없는 경우, 순위별 당첨 수는 모두 0이다")
    void findWinningCountOfEmptyLottoTicket() {
        final LottoTicket myLottoTicket = new LottoTicket(Collections.emptyList());
        final EnumMap<LottoRank, Integer> expectedWinningCountByRank = new EnumMap<>(Map.ofEntries(
                Map.entry(LottoRank.FIRST, 0),
                Map.entry(LottoRank.SECOND, 0),
                Map.entry(LottoRank.THIRD, 0),
                Map.entry(LottoRank.FOURTH, 0),
                Map.entry(LottoRank.FIFTH, 0)
        ));

        final EnumMap<LottoRank, Integer> actual = myLottoTicket.findWinningCountByRank(winningNumbers);

        assertThat(actual).isEqualTo(expectedWinningCountByRank);
    }
}
