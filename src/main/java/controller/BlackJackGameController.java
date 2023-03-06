package controller;

import domain.DrawCommand;
import domain.card.Card;
import domain.card.Cards;
import domain.card.ShuffledCardsGenerator;
import domain.game.BlackJackGame;
import domain.game.Result;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayerStatus;
import domain.user.Players;
import domain.user.User;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import view.InputView;
import view.OutputView;

public class BlackJackGameController {
    private static final int INITIAL_CARD_COUNT = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackJackGame blackJackGame = generateBlackJackGame();
        blackJackGame.drawDefaultCard();
        outputView.printSetUpResult(blackJackGame.getSetUpResult());
        progressPlayersTurn(blackJackGame);
        progressDealerTurn(blackJackGame);
        printUsersCardResult(blackJackGame);
        blackJackGame.judgeWinner();
        printFinalResult(blackJackGame);
    }

    private BlackJackGame generateBlackJackGame() {
        Cards cards = new Cards(new ShuffledCardsGenerator());
        Dealer dealer = new Dealer();
        Players players = repeat(this::setUpPlayers);
        return new BlackJackGame(players, dealer, cards);
    }

    private <T> T repeat(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            return repeat(supplier);
        }
    }

    private Players setUpPlayers() {
        return new Players(readUsersName());
    }

    private List<String> readUsersName() {
        outputView.printInputPlayerNameMessage();
        return inputView.readPlayerNames();
    }

    private void progressPlayersTurn(BlackJackGame blackJackGame) {
        List<Player> players = blackJackGame.getPlayers();
        for (Player player : players) {
            progressPlayerTurn(blackJackGame, player);
        }
    }

    private void progressPlayerTurn(BlackJackGame blackJackGame, Player player) {
        while (isLessThanBustScore(player) && DrawCommand.DRAW == repeat(() -> readDrawCommand(player))) {
            blackJackGame.drawOneMoreCard(player);
            printDrawResult(player);
        }
        if (isLessThanBustScore(player)) {
            printDrawResult(player);
        }
    }

    private boolean isLessThanBustScore(Player player) {
        return PlayerStatus.NORMAL == player.getStatus();
    }

    private DrawCommand readDrawCommand(Player player) {
        outputView.printAskOneMoreCardMessage(player.getName());
        return inputView.readDrawCommand();
    }

    private void printDrawResult(Player player) {
        outputView.printPlayerDrawResult(player.getName(), player.getCards());
    }

    private void progressDealerTurn(BlackJackGame blackJackGame) {
        blackJackGame.drawDealerCardUntilSatisfyingCondition();

        Dealer dealer = blackJackGame.getDealer();
        int dealerDrawCount = dealer.getCards().size() - INITIAL_CARD_COUNT;

        if (dealerDrawCount > 0) {
            outputView.printDealerDrawResult(dealerDrawCount);
        }
    }

    private void printUsersCardResult(BlackJackGame blackJackGame) {
        Map<User, List<Card>> userResult = new LinkedHashMap<>();
        userResult.put(blackJackGame.getDealer(), blackJackGame.getDealer().getCards());
        blackJackGame.getPlayers().forEach(player -> userResult.put(player, player.getCards()));
        outputView.printUsersCardResult(userResult);
    }

    private void printFinalResult(BlackJackGame blackJackGame) {
        Map<Result, Integer> dealerWinningRecord = blackJackGame.getDealer().getWinningRecord();
        Map<String, Result> userFinalResult = blackJackGame.getPlayerFinalResult();
        outputView.printFinalResult(dealerWinningRecord, userFinalResult);
    }
}
