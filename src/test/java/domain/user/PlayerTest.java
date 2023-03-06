package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CloverCard;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("플레이어는 ")
class PlayerTest {
    @DisplayName("카드 한 장을 받아 패에 넣는다.")
    @Test
    void receiveCardTest() {
        Player player = new Player(new Name("플레이어"));
        List<Card> cards = player.getCards();

        player.receiveCard(CloverCard.CLOVER_FOUR);

        assertThat(cards.size()).isEqualTo(1);
    }

    @DisplayName("합산 점수가 21을 초과하면 Bust 상태가 된다.")
    @Test
    void calculateStatusTest() {
        Player player = new Player(new Name("플레이어"));

        player.receiveCard(CloverCard.CLOVER_TEN);
        player.receiveCard(CloverCard.CLOVER_KING);
        player.receiveCard(CloverCard.CLOVER_QUEEN);
        UserStatus result = player.getStatus();

        assertThat(player.getScore()).isGreaterThan(21);
        assertThat(result).isEqualTo(PlayerStatus.BUST);
    }
}
