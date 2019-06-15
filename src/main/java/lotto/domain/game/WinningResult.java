package lotto.domain.game;

import lotto.domain.machine.Money;
import lotto.domain.ticket.LottoTickets;

import java.util.*;

public class WinningResult {
    private Map<Rank, Integer> rankResultInformation;
    private double winningRate;

    public WinningResult(final LottoTickets lottoTickets, final WinningLotto winningLotto) {
        Map<Rank, Integer> rankInformation = new LinkedHashMap<>();
        Arrays.stream(Rank.values())
                .forEach(x -> rankInformation.put(x, 0));
        for (Rank rank : Rank.values()) {
            rankInformation.put(rank, 0);
        }
        List<Rank> rankResult = lottoTickets.getTicketsRank(winningLotto);
        rankResult.forEach(x -> rankInformation.put(x, rankInformation.get(x) + 1));
        rankResultInformation = Collections.unmodifiableMap(rankInformation);
        winningRate = calculateWinningRate(lottoTickets.lottoTicketsSize());
    }

    public Map<Rank, Integer> getRankInformation() {
        return rankResultInformation;
    }

    public double getWinningRate() {
        return winningRate;
    }

    private double calculateWinningRate(int lottoTicketsAmount) {
        int winningMoney = rankResultInformation.entrySet().stream()
                .map(x -> x.getKey().getWinningMoney() * x.getValue())
                .mapToInt(Integer::intValue)
                .sum();
        return winningMoney / (double) (lottoTicketsAmount * Money.getLottoPrice()) * 100;
    }

}