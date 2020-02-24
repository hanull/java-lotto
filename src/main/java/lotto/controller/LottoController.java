package lotto.controller;

import java.io.IOException;
import java.util.List;

import lotto.domain.LottoCount;
import lotto.domain.LottoMachine;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;
import lotto.dto.LottoCountDto;
import lotto.dto.LottosDto;
import lotto.utils.InputUtil;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
	public static void run() {
		Lottos lottos = buyLottos();
		OutputView.printLottos(LottosDto.from(lottos));
		LottoResult lottoResult = new LottoResult(lottos.findAllLottoPrizes(readWinningLotto()));
		OutputView.printLottoResult(lottoResult);
	}

	private static Lottos buyLottos() {
		try {
			LottoMachine lottoMachine = LottoMachine.getInstance();
			LottoCount lottoCount = new LottoCount(readMoney());

			OutputView.printLottoCount(LottoCountDto.from(lottoCount).getLottoCount());
			return new Lottos(lottoMachine.makeRandomLottos(lottoCount.getLottoCount()));
		} catch (IllegalArgumentException e) {
			OutputView.printExceptionMessage(e);
			return buyLottos();
		}
	}

	private static int readMoney() {
		try {
			InputView.printInsertMoney();
			return InputUtil.inputMoney();
		} catch (NumberFormatException | IOException e) {
			OutputView.printWrongMoneyInput(e);
			return readMoney();
		}
	}

	private static WinningLotto readWinningLotto() {
		try {
			return new WinningLotto(readWinningNumber(), readBonusNumber());
		} catch (IllegalArgumentException e) {
			OutputView.printExceptionMessage(e);
			return readWinningLotto();
		}
	}

	private static List<String> readWinningNumber() {
		try {
			InputView.printInsertWinningNumber();
			return InputUtil.inputWinningNumber();
		} catch (IOException e) {
			e.printStackTrace();
			return readWinningNumber();
		}
	}

	private static int readBonusNumber() {
		try {
			InputView.printInsertBonusNumber();
			return InputUtil.inputBonusNumber();
		} catch (NumberFormatException | IOException e) {
			OutputView.printWrongBonusNumberInput(e);
			return readBonusNumber();
		}
	}
}
