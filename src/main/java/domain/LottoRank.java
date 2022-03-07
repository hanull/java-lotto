package domain;

import java.util.Arrays;

public enum LottoRank {

    FAIL(0, -1),
    FIFTH(5_000, 3),
    FOURTH(50_000, 4),
    THIRD(1_500_000, 5),
    SECOND(30_000_000, 5),
    FIRST(2_000_000_000, 6);

    private final long amount;
    private final int matchCount;

    LottoRank(long amount, int matchCount) {
        this.amount = amount;
        this.matchCount = matchCount;
    }

    public static LottoRank findRank(int count, boolean bonusNumber) {
        if (isMatchFiveNumbers(count)) {
            return checkSecondOrThird(bonusNumber);
        }
        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank.getMatchCount() == count)
                .findFirst()
                .orElse(FAIL);
    }

    private static boolean isMatchFiveNumbers(int matchCount) {
        return matchCount == 5;
    }

    private static LottoRank checkSecondOrThird(boolean bonusNumber) {
        if (bonusNumber) {
            return SECOND;
        }
        return THIRD;
    }

    public long getAmount() {
        return amount;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
