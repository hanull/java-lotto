package lotto.domain.ticketgenerator;

import lotto.domain.LottoNumbers;
import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.LottoTickets;
import lotto.domain.ticketpurchase.UserPurchase;

public class LottoTicketsRandomGenerator {
    public LottoTickets generate(UserPurchase userPurchase) {
        LottoTickets lottoTickets = new LottoTickets();
        for (int i = 0; i < userPurchase.randomTicketsSize(); i++) {
            LottoTicket randomTicket = new LottoTicket(LottoNumbers.getRandomNumbersTicketSize());
            lottoTickets.add(randomTicket);
        }
        return lottoTickets;
    }
}
