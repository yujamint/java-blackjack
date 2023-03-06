package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("블랙잭 게임 ")
class CardsTest {
    @Test
    @DisplayName("카드는 52장까지 뽑을 수 있다.")
    void drawCardTest() {
        //given
        Cards cards = new Cards();

        //when
        IntStream.range(0,52).forEach(index -> cards.drawCard());

        //then
        assertThrows(IllegalArgumentException.class, cards::drawCard);
    }

    @Test
    @DisplayName(" 카드를 두 장씩 뽑아서 반환할 수 있다.")
    void drawTwoCardsTest() {
        Cards cards = new Cards();

        List<Card> firstTurnCards = cards.drawTwoCards();

        assertThat(firstTurnCards.size()).isEqualTo(2);
    }
}
