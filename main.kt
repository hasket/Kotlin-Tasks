import java.net.URL
import kotlin.random.Random

// создайте класс Card, который содержит в конструкторе одно поле numbers
class Card(val numbers: Map<Int, Set<Int>>)
// поле numbers — это Map, в которой в качестве ключа номер ряда (1 - 3), а в качестве значения набор чисел
// набор чисел должен уметь хранить только уникальные значения и в процессе работы программы должен уметь удалять из себя числа
// подумайте, какая структура данных лучше всего подойдёт для этой цели

// создайте класс Person, который имеет лишь одно поле в конструкторе — строку name
// в теле класса создайте поле card класса Card. При создании экземпляра класса оно должно генерироваться с помощью метода createCard()
class Person(name: String){
    val card = createCard()

    fun createCard(): Card{
        val cardOfPerson = mutableMapOf<Int, MutableSet<Int>>(
            1 to hashSetOf(),
            2 to hashSetOf(),
            3 to hashSetOf()
        )

        val arrayOfCard = arrayListOf<Int>()

        while (arrayOfCard.size < 16) {
            val randomValueInt = Random.nextInt(1, 99)
            if (!arrayOfCard.contains(randomValueInt)) arrayOfCard.add(randomValueInt)
        }

        for (index in arrayOfCard.indices){
            when(index){
                in 1..5 -> cardOfPerson[1]?.add(arrayOfCard[index])
                in 6..10 -> cardOfPerson[2]?.add(arrayOfCard[index])
                in 11 .. 15 -> cardOfPerson[3]?.add(arrayOfCard[index])
            }
        }

        return Card(cardOfPerson)
    }
}

// метод createCard() должен возвращать объект класса Card
// карточка должна содержать в себе 15 случайных чисел. Числа должны быть распределены в 3 ряда по 5 штук в каждом и не повторяться
// числа в карточки должны быть от 1 до 99 включительно. Для генерации чисел можно использовать функцию Random.nextInt()
