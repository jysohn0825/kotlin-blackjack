package blackjack.view

import blackjack.domain.*

object OutputView {

    fun printParticipantInfo(game: Game) {
        println()
        val participantNames = game.getParticipantNames().joinToString(",")
        println("${participantNames}에게 ${Draw.FIRST_DRAW_COUNT}장의 카드를 나누었습니다.")
        game.getParticipant().forEach {
            printOwnCards(it)
            println()
        }
    }

    fun printOwnCards(person: Person) {
        print("${person.name}카드: ${person.ownCards.cards.joinToString(", ") { it.cardNumber.display + it.pattern.display }}")
    }

    fun printDealerCardAddYn(game: Game): Boolean {
        println()
        return if (game.dealer.checkDrawable()) {
            println()
            println("딜러는 ${Dealer.LEAST_CARD_SUM}이하라 한장의 카드를 더 받았습니다.")
            true
        } else {
            false
        }
    }

    fun printCardInfo(game: Game) {
        println()
        game.getParticipant().forEach {
            printOwnCards(it)
            println(" - 결과: ${it.ownCards.sumCardNumber()}")
        }
    }

    fun printResult(game: Game) {
        println()
        println("## 최종 승페")
        val dealer: Dealer = game.dealer
        val gamer: List<Gamer> = game.gamerList
        println("${dealer.name}: ${dealer.states.count { it == State.WIN }}승 ${dealer.states.count { it == State.LOSE }}패")
        gamer.forEach { println("${it.name}: ${if (it.state == State.WIN) "승" else "패"}") }
    }
}
