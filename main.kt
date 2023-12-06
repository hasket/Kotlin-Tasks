object PiggyBank {

    var pigCash = ArrayList<Money>()  // список монеток/купюр
    private var isBroken: Boolean = false // свойство, определяющее, разбита ли копилка

    fun putMoney(money: Money) {
        // проверьте, не разбита ли копилка
        // добавьте монетку в копилку
        when (isBroken) {
            true -> println("Вы разбили копилку, вы больше ничего положить туда не можете")
            else -> pigCash.add(money).also { println("Добавлено в копилку $money") }
        }

    }

    fun shake(): Money? {
        var money: Money? = null
        when (isBroken) {
            true -> println("Вы разбили копилку, больше оттуда ничего не вытрясти") // вытрясти монетку из копилки (если в копилке нет монетки, вернуть null). Помните, купюры вытрясти нельзя.
            else -> {
                var iterPig = pigCash.iterator()
                while (iterPig.hasNext()){
                    var elPig = iterPig.next()
                    if (elPig.isCoin){
                        money = elPig
                        iterPig.remove()
                        break
                    }
                }
            }
        }
        return money
    }

    fun smash(): ArrayList<Money> {
        isBroken = true
        // установить флаг, что копилка разбита true, и вернуть все монетки, которые были в копилке
        println("Копилка разбита, вы достали оттуда: $pigCash")
        return pigCash
    }

}

class Money private constructor(
        val amount: Float,
        val isCoin: Boolean
) {
    // вы должны ограничить создание класса таким образом, чтобы можно было создать только ограниченный набор номиналов (см. задание)
    companion object {
        val coins_10 = Money(0.1f, true)
        val coins_50 = Money(0.5f, true)
        val coins_100 = Money(1f, true)
        val bill_50 = Money(50f, false)
        val bill_100 = Money(100f, false)
        val bill_500 = Money(500f, false)
        val bill_1000 = Money(1000f, false)
    }

    // переопределите метод toString() так, чтобы он возвращал строку типа "10 коп." или "1 руб.", если это монетка, и "100 руб.", если это купюра
    override fun toString(): String {
        return when (amount) {
            0.1f -> "10 коп."
            0.5f -> "50 коп."
            else -> "${amount.toInt()} руб."
        }
    }
}


// создайте класс Money, который будет отражать сущность монетки/купюры с двумя полями: amount и isCoin