import kotlin.random.Random

class DiceRollerV2 {

    // сделайте интерфейс OnRollCallback функциональным
    fun interface OnRollCallback {
        fun call(firstDiceValue: Int, secondDiceValue: Int)
    }

    // сделайте интерфейс OnDoubleCallback функциональным
    fun interface OnDoubleCallback {
        fun call(diceValue: Int)
    }

    private var onRollCallback: OnRollCallback? = null
    private var onDoubleCallback: OnDoubleCallback? = null

    fun setCallbacks(onRollCallback: OnRollCallback, onDoubleCallback: OnDoubleCallback) {
        this.onRollCallback = onRollCallback
        this.onDoubleCallback = onDoubleCallback
    }

    fun roll() {
        if (onRollCallback == null || onDoubleCallback == null) {
            println("Вы должны вызвать функцию setCallbacks() прежде чем бросать кубики")
            return
        }

        val firstDiceValue = Random.nextInt(1, 7)
        val secondDiceValue = Random.nextInt(1, 7)

        if (firstDiceValue != secondDiceValue) {
            onRollCallback?.call(firstDiceValue, secondDiceValue)
        } else {
            onDoubleCallback?.call(firstDiceValue)
        }
    }
}

fun main() {
    val diceRoller = DiceRollerV2()
    val onRollCallback = DiceRollerV2.OnRollCallback {first, second ->
        println("На кубиках выпало $first и $second")
    }
    val onDoubleCallback = DiceRollerV2.OnDoubleCallback {
        println("Ура!!! Дубль на $it-ах! Бросаем ещё раз")
        diceRoller.roll()
    }
    diceRoller.setCallbacks(onRollCallback, onDoubleCallback)
    for (roll in 0..9)
        diceRoller.roll()
}