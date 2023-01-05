package blackjack.domain

open class Person(
    open val name: String = "",
    val ownCards: OwnCards = OwnCards(Draw.FIRST_DRAW_COUNT),
    private var _state: State = State.CONTINUE
) {
    val state: State
        get() = _state

    fun addCard(drawYn: Boolean) {
        if (drawYn) {
            ownCards.addCard(Card())
            _state = changeState(ownCards.sumCardNumber())
        }
    }

    private fun changeState(cardNumberSum: Int): State {
        return when {
            cardNumberSum > Draw.DRAW_LIMIT -> State.LOSE
            cardNumberSum == Draw.DRAW_LIMIT -> State.WIN
            else -> State.CONTINUE
        }
    }

    fun changeState(isWin: Boolean) {
        _state = if (isWin) State.WIN else State.LOSE
    }

    open fun checkDrawable(): Boolean = (_state == State.CONTINUE)
}
