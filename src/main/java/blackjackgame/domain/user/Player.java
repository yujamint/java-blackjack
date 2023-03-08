package blackjackgame.domain.user;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.game.Result;
import java.util.List;

public class Player extends User {
    private final Name name;
    private PlayerStatus status = PlayerStatus.NORMAL;
    private Result result;

    public Player(Name name) {
        super();
        this.name = name;
    }

    @Override
    public void receiveCard(Card card) {
        super.receiveCard(card);
        status = score.calculatePlayerStatus();
    }

    @Override
    public void receiveCards(List<Card> receivedCards) {
        super.receiveCards(receivedCards);
        status = score.calculatePlayerStatus();
    }

    @Override
    public void win(User dealer) {
        result = Result.WIN;
        dealer.lose();
    }

    public void draw() {
        result = Result.DRAW;
    }

    @Override
    public void lose() {
        result = Result.LOSE;
    }

    public boolean isLessThanBustScore() {
        return score.isLessThanBustScore();
    }

    @Override
    public UserStatus getStatus() {
        return status;
    }

    @Override
    public String getName() {
        return name.getName();
    }

    public Result getResult() {
        return result;
    }
}
