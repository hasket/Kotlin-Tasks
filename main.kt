import kotlin.random.Random

class Lotto {

    // определите поле, в котром будут храниться добавленные игроки `Person`
    // поле thrownNumbers должно хранить в себе набор выброшенных чисел.
    val thrownNumbers: MutableSet<Int> = mutableSetOf() // определите подходящую структуру данных
    private val personsOfLotto: MutableList<Person> = mutableListOf()

    fun addPerson(person: Person) {
        personsOfLotto.add(person)
    }

    fun start() {
        var winnerPerson: MutableSet<String> = mutableSetOf()
        // вывести сообщение "Перед началом игры необходимо добавить хотя бы двух игроков" и завершить работу, если количество добавленных игроков меньше 2

        if (personsOfLotto.size < 2) {
            println("Перед началом игры необходимо добавить хотя бы двух игроков")
            return
        }
        do {
            var randValue = Random.nextInt(1, 100)
            if (thrownNumbers.contains(randValue)) continue
            thrownNumbers.add(randValue)
            println("Выброшенное число: $randValue")
            for (cardOfPerson in personsOfLotto) {
                for (cardLine in cardOfPerson.card.numbers.values) {
                    if (cardLine.contains(randValue)) cardLine.remove(randValue)
                    if (cardLine.isEmpty()) winnerPerson.add(cardOfPerson.name)
                }
            }
            // достать номер. Номер может быть в диапазоне от 1 до 99 включительно
            // после каждого выброшенного числа удалять это число из карточек всех игроков, если такое число имеется
            // выбрасывать новые числа до тех пор, пока не определится победитель
            // побеждает тот, у кого в одном из рядов нет больше чисел. Победителей может быть более одного
            // после того как появляется победитель, для каждого победителя вывести текст "Победитель: [имя_победителя]!!!"
        } while (winnerPerson.isEmpty())
        winnerPerson.forEach {
            println("Победитель: $it!!!")
        }
    }
}

class Card(val numbers: Map<Int, MutableSet<Int>>)

class Person(val name: String) {

    val card: Card = createCard()

    private fun createCard(): Card {
        val numbers: Set<Int> = generateNumbers()

        val iterator: Iterator<Int> = numbers.iterator()
        var currentLine = 1

        val cardNumbers: MutableMap<Int, MutableSet<Int>> = mutableMapOf(
            1 to mutableSetOf(),
            2 to mutableSetOf(),
            3 to mutableSetOf()
        )

        while (iterator.hasNext()) {
            val number = iterator.next()
            cardNumbers[currentLine]?.add(number)

            if (currentLine < 3) {
                currentLine++
            } else {
                currentLine = 1
            }
        }

        return Card(cardNumbers)
    }

    private fun generateNumbers(): Set<Int> {
        val numbers: MutableSet<Int> = mutableSetOf()

        while (numbers.size < NUMBERS_COUNT) {
            numbers.add(Random.nextInt(MIN_NUMBER, MAX_NUMBER))
        }

        return numbers
    }

    private companion object {

        private const val NUMBERS_COUNT = 15
        private const val MAX_NUMBER = 100
        private const val MIN_NUMBER = 1
    }
}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Решение преподов

class Card(val numbers: Map<Int, MutableSet<Int>>)

class Person(val name: String) {

    val card: Card = createCard()

    private fun createCard(): Card {
        val numbers: Set<Int> = generateNumbers()

        val iterator: Iterator<Int> = numbers.iterator()
        var currentLine = 1

        val cardNumbers: MutableMap<Int, MutableSet<Int>> = mutableMapOf(
            1 to mutableSetOf(),
            2 to mutableSetOf(),
            3 to mutableSetOf()
        )

        while (iterator.hasNext()) {
            val number = iterator.next()
            cardNumbers[currentLine]?.add(number)

            if (currentLine < 3) {
                currentLine++
            } else {
                currentLine = 1
            }
        }

        return Card(cardNumbers)
    }

    private fun generateNumbers(): Set<Int> {
        val numbers: MutableSet<Int> = mutableSetOf()

        while (numbers.size < NUMBERS_COUNT) {
            numbers.add(Random.nextInt(MIN_NUMBER, MAX_NUMBER))
        }

        return numbers
    }

    private companion object {

        private const val NUMBERS_COUNT = 15
        private const val MAX_NUMBER = 100
        private const val MIN_NUMBER = 1
    }
}

class Lotto {

    private val persons: MutableList<Person> = mutableListOf()
    val thrownNumbers: MutableSet<Int> = mutableSetOf()

    fun addPerson(person: Person) {
        persons.add(person)
    }

    fun start() {
        if (persons.size < 2) {
            println("Перед началом игры необходимо добавить хотя бы двух игроков")
            Game().start()
        } else {
            do {
                val number = throwNumber()

                for (person in persons) {
                    val cardNumbers = person.card.numbers
                    for (key in cardNumbers.keys) {
                        cardNumbers[key]?.remove(number)
                    }
                }
            } while (!hasWinners())
        }
    }

    private fun throwNumber(): Int {
        val number = Random.nextInt(1, 100)

        return if (thrownNumbers.contains(number)) {
            throwNumber()
        } else {
            thrownNumbers.add(number)
            println("Выброшенное число: $number")
            number
        }
    }

    private fun hasWinners(): Boolean {
        val winners: MutableList<Person> = mutableListOf()

        for (person in persons) {
            val cardNumbers = person.card.numbers
            for (key in cardNumbers.keys) {
                if (cardNumbers[key]?.isEmpty() == true) {
                    winners.add(person)
                }
            }
        }

        return if (winners.isEmpty()) {
            false
        } else {
            for (winner in winners) {
                println("Победитель: ${winner.name}!!!")
            }
            true
        }
    }
}
