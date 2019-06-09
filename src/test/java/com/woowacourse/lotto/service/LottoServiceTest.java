package com.woowacourse.lotto.service;

import com.woowacourse.lotto.domain.LottoBuyList;
import com.woowacourse.lotto.domain.ManualNumber;
import com.woowacourse.lotto.domain.PurchaseMoney;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoServiceTest {
    @Test
    void 로또_생성_개수_테스트() {
        int expectedLottoSize = 14;

        LottoBuyList lottoBuyList = LottoService.getAutoLottoBuyList(new PurchaseMoney(14000), new ManualNumber(0));

        assertThat(lottoBuyList.getLottoSize()).isEqualTo(expectedLottoSize);
    }
}