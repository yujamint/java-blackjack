# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## Controller
- [x] 게임에 참여할 플레이어의 이름을 입력받는다.
- [x] 딜러와 플레이어들에게 카드를 두 장씩 랜덤하게 나눠준다.
- [x] 딜러와 플레이어들이 받은 카드를 출력한다.
  - [x] 딜러는 한장의 카드만 공개한다.
- [x] 플레이어 별로 카드를 더 받을지 물어본다.
  - [x] 플레이어는 카드를 한 장 뽑고 가진 카드 현황을 출력한다.
  - [x] 플레이어가 가진 카드 숫자의 총합이 21을 초과하면 그만 받도록 한다.
  - [x] 플레이어가 가진 카드 숫자의 총합이 21 이하라면 플레이어의 선택에 따라 카드를 계속 받을 수 있다.
- [x] 딜러는 자신의 숫자 카드 합이 17 이상이 되도록 카드를 더 뽑는다.
- [x] 딜러와 플레이어의 카드 합 결과를 계산한다.
- [x] 딜러와 플레이어 최종 카드 현황과 각 합을 출력한다.
- [x] 게임결과를 출력하고 게임을 종료한다.
  - [x] 딜러는 총 몇 승 몇 패를 했는지 출력한다.
  - [x] 플레이어는 각자의 승패 결과를 출력한다.

## Model
- [x] 블랙잭 전체 카드 생성
  - [x] 전체 카드 개수는 52장
  - [x] 문양은 하트, 다이아, 클로버, 스페이드
  - [x] 각 문양 별 Ace, 2~10, K,Q,J 로 구성
    - [x] Ace는 1 또는 11로 계산할 수 있다.
    - [x] K,Q,J는 각각 10으로 계산한다.
  - [x] 52장 카드는 중복되지 않는다.
- [x] 블랙잭 카드 드로우
  - [x] 모든 카드는 랜덤하게 셔플된다.
  - [x] 드로우는 셔플된 카드에서 순서대로 가져간다.
- [x] 블랙잭 카드 현황 저장한다.
  - [x] 전체 카드에서 남은 카드 현황
    - [x] 드로우된 카드는 제외한다.
  - [x] 딜러 카드 현황
  - [x] 플레이어 카드 현황
- [x] 블랙잭 게임 진행
  - [x] 게임의 플레이어 수는 딜러 제외 5명으로 제한한다.
  - [x] 플레이어와 딜러는 게임을 시작하며 카드를 2장씩 드로우한다.
  - [x] 딜러는 처음에 뽑은 카드 1장만 공개하고, 남은 카드는 최종 결과 확인 전까지 비공개한다.
  - [x] 보유한 카드의 숫자 합산
  - [x] 보유한 카드의 숫자 합산 유효성 확인
    - [x] 합산 결과가 21을 초과하면 더 이상 카드를 뽑지 않는다.
- [x] 딜러와 플레이어 간 승패 결정
  - [x] 딜러와 플레이어의 카드 숫자 합산 비교 
  - [x] 딜러와 플레이어 둘 다 버스트일 시 딜러 승
  - [x] 딜러와 플레이어 결과가 무승부일 때 딜러 승

## View
### InputView
- [x] 플레이어 이름 입력
  - [x] [예외처리] 쉼표 기준으로 분리하다(pobi,jason)
- [x] 카드 추가로 받을지 말지 여부 입력
  - [x] [예외처리] 'y' 또는 'n'이 아닌 입력
### OutputView
- [x] 초기 게임 세팅
  - [x] 플레이어 이름 입력 메세지 출력
  - [x] 딜러와 플레이어 첫 카드 세팅 결과 출력
  - [x] 딜러와 플레이어 별로 가진 카드를 출력
- [x] 게임 진행
  - [x] 플레이어가 카드를 추가로 받을지 여부 메세지 출력
  - [x] 플레이어가 가진 카드 현황을 출력
  - [x] 딜러 추가 카드 수령 메세지
- [x] 게임 결과
  - [x] 딜러와 플레이어 최종 카드 결과 출력
  - [x] 딜러와 플레이어 최종 승패 결과 출력

## 리팩토링
### 1단계 리뷰
- [x] [예외처리] 게임을 진행하기 위해 플레이어 최소 한 명 필요
- [x] [예외처리] 이름 빈 칸 여부 확인
- [x] 출력 시, 한국 이름에 받침이 들어갈 수 있다는 점을 고려하여 은(는) 지원
- [x] [예외처리] 이름 중복 검사 
- [x] [예외처리] 플레이어 이름 "딜러" 검사
- [x] 각각의 클래스가 알 필요 없는 정보 제거 
  - Controller는 딜러의 이름을 알 필요가 없다. `Dealer.getName()` 메서드를 통해 알아오도록 수정
- [x] 기능에 맞는 메소드명을 가지는지 확인
  - [x] Controller.setUp()
- [x] drawForFirstTurn 메서드는 Controller에서, drawCard 메서드는 Game 클래스에서 호출하고 있다.
  - Delaer 생성자에서 바로 2장의 카드를 넣어주고 있음
    - 기존에 User(Dealer,Player)를 생성하는 시점에 기본 카드 2장을 주입했다. 카드를 주입하는 것은 BlackJackGame 클래스의 역할이라고 생각하여 해당 객체가 카드를 주입하도록 수정
- [x] 출력 기능을 하는 메서드 명 변경(show -> print)
- [x] HashMap 선언 -> Map 선언
  - 전하고자 하는 의미를 담고자 LinkedHashMap으로 선언을 한다 vs 다형성을 위해 Map으로 선언한다. 어떤 것이 맞을까?
  - 전하고자 하는 의미 역시 기능이 변경됨에 따라 달라질 수 있다. 그렇기 때문에 Map으로 선언하며 수정에 열려 있도록 설계하는 것이 좋을 것 같다.
- [x] Map의 키로 사용하고 있는 String을 의미에 맞게 포장
  - Game 클래스에서 반환한 Map을 컨트롤러에서 바로 OutputView로 전달하고자 합니다.
  - Map의 Key로 Name 객체를 넣으면, 뷰에 Name이라는 도메인 객체를 전달하는 것이라 생각했습니다.
  - 뷰에 도메인 객체를 전달하지 않고자 Map의 Key 타입을 String으로 선언하고 Game 클래스에서 해당 타입에 맞게 만들어 반환하는 것을 선택했습니다.
  - 제이미가 의도하신 바는 Game 클래스에서는 Name 객체를 Key로 가지는 Map을 반환하고, 컨트롤러에서 이를 벗겨서 String을 Key로 가지는 Map을 뷰에 전달하는 것일까요?
  - [x] DTO를 통해 컨트롤러에서 뷰로 정보를 전달하자.
    - DTO를 사용함으로써 Map에 어떤 정보가 담겨 있는지, 그 의미를 전달할 수 있을 것이다.
- [x] 첫번째 카드 get 메서드는 딜러의 역할이 될 수 있다.
- [x] Controller.playGame() 메서드 분리의 이유 고민
  - run 메서드에서 setUp 메서드를 통해 BlackJackGame 객체를 가지고 있다. 굳이 메서드를 분리할 필요가 없다.
- [x] 동일성 비교시 NPE를 방지할 수 있는 방법
- [x] Number enum의 이름에 대한 고민
  - 정식 명칭에 맞게 Pattern은 Suit로, CardNumber는 Denomination으로 변경
- [x] CARD_ON_TOP 상수명이 올바른가?
  - Index를 나타내는 상수이므로 `_INDEX` 를 붙여주었다.
- [x] shuffle을 씀으로써 Cards 객체 테스트 코드 작성이 어려워진다는 점에 대한 고민
  - 전략 패턴을 적용함으로써 해결
- [x] Cards라는 객체가 카드 두 장을 뽑아주는 이유가 **첫 턴이라서** 그렇다는 걸 알 수 있을까?
  - 기존의 drawForFirstTurn 메서드는 Cards라는 도메인의 입장에서 봤을 때, 단순히 두 장의 카드를 뽑아서 반환하는 기능을 하는 것이다. 이 기능이 첫 턴을 위해 동작하는지는 Cards 입장에선 알 수도 없고, 알 필요도 없다.
- [x] Cards.isEmpty()를 메서드 분리한 이유가 있을까?
  - 외부에서 Cards가 비어 있는지 확인할 때, Cards.getCards().isEmpty() 와 같이 호출하는 것이 가독성을 떨어트린다고 생각하여 메서드를 분리하였다. 하지만, isEmpty() 메서드는 외부에서 호출하지 않기 때문에 분리하지 않도록 수정하였다.
- [x] 도메인 테스트 코드 작성
- [x] 일급 컬렉션 사용
  - List<Player>를 Players라는 일급 컬렉션으로 감싸며 유효성 검증 로직과 Player 별 승패 결과를 반환하는 로직을 가지도록 했다. 
  - Player의 기본 카드를 뽑는 로직도 일급 컬렉션에 포함하고 싶었지만, 해당 기능을 수행하기 위해서는 Cards 객체를 알고 있어야 한다.
  - 그렇기 때문에 해당 기능은 Players와 Cards 객체를 모두 알고 있는 BlackJackGame 클래스의 역할로 남기기로 했다.
  - 일급 컬렉션이 어떤 역할을 가져야 할지 고민될 때, 위와 같이 List<Player>만 알고 있어도 수행할 수 있는 로직만 가지도록 하면 될까?
- [x] drawOneMoreCardPlayer 시그니처
- [x] drawCardUntilOverSixteen 메서드명
  - 메서드명이 너무 긴가?..
- [x] playerWin 메서드명
- [x] compare라는 메서드명의 반환 타입
- [x] compareScore 메서드 로직
- [x] Dealer.SEVENTEEN 상수명
  - 딜러의 최소 요구 점수는 16점이기 때문에 의미에 맞게 `MIN_SCORE`로 수정
- [x] 무승부 처리
- [x] Player와 Dealer의 점수가 꼭 마지막에 주입 받으면서 관리되어야 할까?
  - 플레이어와 딜러의 점수는 카드를 추가로 받느냐에 따라 계속해서 달라질 수 있다고 생각합니다.
  - 점수가 달라질 수 있기 때문에 승패 결과도 계속해서 바뀔 여지가 있습니다.
  - 그렇기 때문에, 점수가 달라질 때마다 Dealer의 winningRecord를 업데이트해주기 보다는 모든 플레이어와 딜러가 카드 분배를 끝낸 뒤 한 번 업데이트 하는 것이 좋다고 생각합니다.
    - 현재 상황에선 점수 계산이 한 번만 필요하기 때문에 Score를 필요할 때만 계산하는 것이 좋은 것 같다. 
    - 제이미의 의견을 듣고 보니 점수가 여러번 필요한 상황이라면 확실히 계속 계산해주는 것이 좋을 것 같다.
    - 이 부분에 대해서는 여유가 되면 리팩토링을 진행하는 방향으로 해보자.
- [x] 상수에 숫자를 명시하는 것은 좋지 않다.
  - `UNDER_SEVENTEEN` -> `UNDER_MIN_SCORE` 수정
  - Score 클래스에서 ZERO 상수를 통해 calculateAceAsOne 메서드에서 ACE 카드가 존재하는지 확인하고 있습니다. 이때의 ZERO 상수 역시 상수명에서 숫자를 언급하기 때문에 좋지 않은 상수명인 걸까요?
    - [x] 상수명에서 매직넘버 제거
- [x] score를 final로 선언하려면?
  - Score라는 클래스로 Wrapping하며 final로 선언하였다.
  - Score를 불변 객체로 만드는 게 좋은 것일까?
- [x] calculate라는 메서드명의 반환 타입
  - calculate라는 메서드명은 주로 계산한 값을 반환하기 때문에 `setScore`라는 메서드 명으로 수정
- [x] sum이라는 변수명
  - sum이라는 메서드의 반환 값을 저장하는 변수이므로, sum
- [x] 여러 곳에서 쓰이고 있는 BLACKJACK 상수
  - User의 Status를 확인하고 변경하는 역할은 점수에 의해 동작하기 때문에, 이 역할을 Score 객체가 하도록 수정
  - 그에 따라 점수 비교에 쓰이던 BLACKJACK 상수도 Score만이 관리한다.
- [x] User는 왜 처음에 firstTurnCards를 가져야 할까?
  - 카드 주입을 생성되는 시점이 아니라 그 이후에 함으로써 해결
- [x] user 하위 객체들이 모두 name을 갖는다?
  - 딜러는 이름이 필요 없기 때문에 Player만 name을 갖도록 수정
- [x] checkBustByScore 메서드명
  - Bust만 확인하는 기능이라기보단, 점수에 따라 Status를 set하는 메서드이다. 이를 Score의 역할로 위임하고 점수에 따른 Status를 반환하도록 했다.
  - 메서드명 역시 calculateStatus 로 수정
- [x] List 변수명은 복수형으로 쓰기
- [x] 예외 메시지를 Enum으로 따로 뺀 이유에 대한 고민
  - 현재 Cards, Name, Player 3개의 클래스에서 유효성 검증을 하며 예외 메시지를 던지고 있습니다.
  - 각각의 클래스에서 던지는 예외 메시지를 상수로써 ErrorMessage라는 하나의 enum에서 관리하면 관리하기 편할 것이라고 생각했습니다.
  - 예외 메시지를 Enum에서 관리하게 되면, 클래스가 많아짐에 따라 이 부분도 너무 커지게 된다.
또한 예외 메시지는 개발자들이 확인하기 위한 부분이라서, Enum으로 빼면 한 눈에 들어오지 않을 수 있다.
- [x] `.`과 `()`이 많으면 가독성을 해친다.
- [x] 사용자에게 보이는 예외 메세지
- [x] 개행 출력
- [x] printSetUpCompleteMessage 가독성
- [x] PatternTest.generatePatternTest() 변수명, 가독성
- [x] Enum 갯수 테스트 보다는 Cards 갯수 테스트가 중요하다
  - Cards 객체를 생성할 때 모든 Card Enum 객체를 추가한다.
  - 즉, 52장의 카드를 추가하는데 이를 어떻게 검증할 수 있을까?
  - Cards는 getter를 제공하고 있지 않기 때문에 어떤 기능을 통해 검증할 수 있을지 고민했다.
  - drawCard가 하나의 카드를 뽑는 기능을 하기 때문에 이를 52번 진행한 뒤, 또 한 장의 카드를 뽑을 경우 예외가 발생한다.
  - 이를 통해 원래 카드가 52장 있었다는 것을 검증하려 한다.
  - 이는 **카드를 뽑는 것**에 대한 기능 테스트이기 때문에 처음에 52장의 카드가 생성된다는 것을 검증할 수는 없는 것일까?
- [x] 주석 통일감
  - 매개변수를 @MethodSource를 통해 받아오는 경우, given이 생략되는 경우가 있어 given/when/then 주석을 생략한 메서드가 몇 있다.
  - 이 경우, 통일감을 해칠 수 있다.
  - 그렇다면, given을 생략하고 when/then에 해당하는 부분만 주석을 작성하는 방법과 모든 given/when/then 주석을 제거하는 방법 중 어떤 것이 좋을까?
- [x] 플레이어가 생성자 테스트에서 플레이어가 2장의 카드를 받는지 검증하는 것이 적합할까?
- [x] ScoreTest 반대 케이스 테스트
### 1단계 2차 피드백
- [x] 컨트롤러에서 카드 뽑기 로직과 관련된 상수를 가지고 있는 것이 맞는지에 대한 고민
- [x] repeat 메서드명 개선 (어떨 떄 반복하는지 드러나지 않는다.)
- [x] `printInputPlayerNameMessage`, `readPlayerNames` 메서드명 통일
- [x] `isLessThanBustScore`의 책임이 컨트롤러에 있는 게 맞을까?
- [x] 딜러가 카드를 뽑았는지 여부를 계산하는 로직을 컨트롤러가 가지는 게 맞는가?
  - 디미터의 법칙에 대해 공부해보자
- [x] 게임 결과를 받을 때, blackJackGame.getDealer()를 통해 딜러에게 물어보기 보단, blackJackGame에게만 물어보도록 수정
  - 이렇게 수정할 경우, BlackJackGame 객체가 Dealer.getWinningRecord()를 그대로 호출하기만 하는 메서드를 갖게 된다.
  - 그저 다른 객체의 메서드를 사용하고 결과를 그대로 반환해주는 경우에도 BlackJackGame이 호출하도록 하는 게 맞는 걸까?
  - 같은 상황을 레이어드 아키텍처에 대입해서 생각해봐도 컨트롤러가 바로 repository 계층에 접근하지 않고, 무조건 서비스를 통해 접근하는 건가?
- [x] getPlayerFinalResult의 결과를 반환받는 변수명은 userFinalReuslt?
- [x] Cards 객체가 생성자로 받는 CardsGenerator의 변수명을 shuffledCardsGenerator 대신 CardsGenerator로 수정
- [x] 불필요한 개행 제거
  - [x] Name 클래스
- [x] drawTwoCards 메서드명 (세 장 뽑게 될 경우 메서드명 변경해야 됨. 변경에 닫혀있다!)
- [x] 각 문양 별 카드 Enum의 속성 바로 초기화해줄 수 있음
- [ ] cards를 generator에서 만들어도 되지만, cards에서 만들고 generator에선 셔플만 하면 어떨까?
  - 생성의 역할은 Cards에게 맡기고, shuffle한다는 행위에 대한 역할만 generator에게 맡기는 것이 좋은 방법일까?
- [x] 게임 순서에 맞게 딜러부터 카드 분배하도록 수정
- [x] drawPlayersDefaultCard 메서드 분리 필요성이 있었는가?
- [x] drawDealerCardUntilSatisfyingCondition 메서드명과 상수명 통일
- [x]  judgeWinner 결과를 각 player와 dealer에 심어주는 게 맞을까? (player는 dealer를 모르고, dealer는 player를 모른다.)
- [x] Score.DEALER_MIN_SCORE 상수명 개선
- [x] Dealer와 Player 모두 name을 가진다면 User에서 관리할 수 있지 않을까?
  - 딜러는 무조건 "딜러"라는 이름을 반환합니다.
  - 그렇기 떄문에 때문에 이를 Name 객체로 생성해주기 보다는 Dealer 클래스 내에서 상수로 선언하여 사용하는 것이 좋다고 생각했습니다.
  - 제이미는 `new Name("딜러")`와 같이 Name 객체를 만들고 이를 Dealer가 인스턴스 변수로 갖는 것이 좋다고 생각하시나요??
- [x] CardsTest 변수명 개선
- [x] CardsTest FixedCardsGenerator를 메서드 안에 넣어줄 수 있다.
- [x] 불필요한 주석 삭제
- [x] 테스트 클래스의 인스턴스 변수 접근제어자 추가
- [x] BlackJackGameTest
  - [x] 현재 모든 메서드에서 Player와 Dealer를 blackJackGame으로부터 뽑아서 쓰고 있다. 이를 개선해보자.
  - [x] 2장을 뽑는다는 메서드를 검증할 때, 단순히 2장의 카드가 있다는 걸 보여주기보단 기존에는 한 장도 없었단 걸 함께 보여주자.
  - [x] FixedCardsGenerator가 리스트의 일부를 반환해주는 역할을 한다면 어떨까?
  - [x] player가 버스트인지 확인할 수 있도록 하자.
    - 주석으로 확인하면, 변경 포인트가 2개가 되는 것이다.
- [x] DealerTest 테스트 메서드명 수정
- [x] Name이 null을 받게 될 상황을 고려해 NPE 방지
- [x] Player를 생성했을 때, 카드를 가지고 있지 않다가 receiveCard를 하면 받은 카드가 패 안에 있다는 걸 검증하는 테스트 작성

### 궁금한 점
**부모 클래스의 생성자를 그대로 쓰면 자식 클래스는 생성자 코드를 생략해도 될까?**
  - 현재 Delaer는 생성자에서 별다른 로직을 가지고 있지 않다.
  - 그렇기 때문에 부모 클래스인 User의 생성자를 그대로 사용하는데, 이러한 경우에도 생성자를 만들어서 super()만 호출하는 게 좋은가?
  - 다른 로직을 가지지 않고, 슈퍼 클래스의 생서자만 사용한다는 걸 보여주기 위해서?
  - 코드의 중복을 제거하기 위해 상속을 사용했지만, 생성자까지 생략해도 좋은지 궁금하다.

### 2단계 구현
- [x] 베팅
  - [x] 플레이어는 각자 베팅 금액을 정한다.
  - [x] 플레이어가 버스트일 경우 배팅 금액을 모두 잃게 된다.
  - [x] 2장의 카드로만 21점이 될 경우 블랙잭이다.
  - [x] 블랙잭일 경우 배팅 금액의 1.5배를 딜러에게 받는다.
  - [x] 딜러와 플레이어가 모두 블랙잭일 경우 플레이어는 배팅한 금액을 돌려받는다.
  - [x] 딜러가 버스트일 경우 버스트가 아닌 플레이어는 모두 배팅 금액을 얻는다.  
- [x] FinalResult
  - [x] 점수를 비교한다.
    - [x] 플레이어 버스트 -> 딜러 승리
    - [x] 플레이어 버스트 X, 딜러 버스트 -> 플레이어 승리
    - [x] 플레이어 버스트 X, 딜러 버스트 X
      - [x] 플레이어 점수 == 딜러 점수 -> 무승부
      - [x] 플레이어 점수 > 딜러 점수 -> 플레이어 승리
        - [x] 플레이어 블랙잭일 때 -> 플레이어 1.5배 승리
      - [x] 플레이어 점수 < 딜러 점수 -> 딜러 승리

### 2단계 리팩토링
- [x] CardsGenerator가 52개의 카드를 생성해준다는 사실을 알 수 있도록 메서드명 변경
- [x] 게임의 결과를 Game 클래스가 꼭 들고 있어야 될까?
- [x] 딜러 Name 객체로 이름 관리하도록 수정
- [x] 에러메시지 리터럴로 출력

**2단계 1차 코드리뷰**
- [x] 컨트롤러에서 입력값을 통해 추가드로우 여부를 결정하고, HIT일 때만 BlackJackGame을 호출한다면, BlackJackGame은 STAY라는 선택지가 존재한다는 걸 알 수 있을까?
  - 컨트롤러는 입/출력과 비즈니스 로직 사이의 연결만 하고 게임 진행에 관련된 로직, 즉, 비즈니스 로직은 없는 게 좋은 걸까?
- [x] getDealerExtraCount가 0보다 큰지 비교하는 로직은 컨트롤러가 아니라 도메인 자체에서 가져도 되지 않을까?
- [x] HIT과 STAY가 관리되는 클래스 이름으로 DrawCommand가 적당할까?
- [ ] 인스턴스 변수 3개 미만으로 관리하기
- [x] Referee.judgeWinner()는 결국 getBetResut()와 세트로 동작하는 메서드이다. 이를 public 메서드로 분리할 필요가 있을까?
  - judgeWinner와 getBetResult는 결과를 얻기 위해서는 꼭 함께 호출돼야 합니다.
  - 그렇기 때문에 BlackJackGame.calculateDealerProfit 메서드 안에서 Referee.judgeWinner를 호출하도록 하고 BlackJackGame.judgeWinner는 private으로 수정했습니다.
- [x] judgeWinner()를 한 게임에서 두 번 호출하면 어떤 문제가 생길까??
  - 두 메서드가 모두 public으로 열려있을 필요는 없다는 건 알겠지만, judgeWinner 메서드는 당시에 유저들이 들고 있는 패를 기준으로 승자를 정하는데, 두 번 호출됐을 때 어떤 문제가 발생할 수 있는지 예를 알고 싶습니다.
- [x] 현재 블랙잭게임에서 DTO를 키로 가지는 맵을 반환하는데, 이렇게 도메인이 DTO를 알고 있는 게 괜찮을까??
  - 뷰를 위해 보내줄 DTO를 생성하는 것이 도메인의 역할일까?
- [x] Bet이라는 도메인이 게임 결과(Result)와 수익(Profit)을 알고 있는 것이 맞을까?
- [x] bet 금액과 profit은 Bet에서 관리하는데, 계산은 Result가 하고 있는 것이 맞을까?
- [x] "딜러"뿐 아니라 new Name("딜러") 역시 상수가 될 수 있지 않을까?
- [x] List 생성 시, Collections.emptyList()를 넘겨주는 것과, 빈 매개변수를 전달하는 것의 차이
  - emptyList는 unmodifiable 리스트를 반환한다. 하지만, new로 객체를 생성하면서 파라미터로 전달하면 그 결과물이 unmodifiable하진 않음. 즉, 차이는 없는 것 같다.
- [x] Players를 생성할 때, List<Name>과 List<Bet>의 사이즈가 다르다면 어떤 일이 발생할까?
- [x] MAX_SCORE라는 상수 이름은 게임 내에서 얻을 수 있는 최대 점수라는 의미로 받아들여지지 않을까?
- [x] 명사 메서드명 개선
- [x] " 딜러" 와 같은 이름은 생성되는데, 이러한 검증을 어디서 하는 것이 좋을까?
- [x] System.out.print(NEWLINE) -> System.out.println()으로 개선
- [x] Fixtures 상수 이름개선
- [x] Bet 성공 케이스 추가, 오타 개선
- [x] 테스트 케이스에서 카드 변수로 묶어놓기
- [x] Players -> Names 클래스명 수정에 따라 테스트 메서드 수정(프로덕트 코드 포함)
- [x] 천 단위 이상은 5_000과 같이 언더바 사용
- [ ] Players.getPlayers()를 통해 진행되는 로직은 Players내에서 해결할 수 있지 않을까?

### 게임 도메인의 역할?
- [x] 딜러의 카드를 2장 뽑는다.
- [x] 플레이어마다 카드를 2장 뽑는다.
- [x] 플레이어마다 카드를 한 장씩 더 뽑는다.
  - [x] 버스트면 중지
- [x] 딜러가 점수를 만족할 때 까지 한 장씩 더 뽑는다.
- [x] 점수를 비교한다.
