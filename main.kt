class Person(val name: String, val surname: String, val thirdName: String)

// реализуйте свойство firstSymbol для класса String
// "Белка".firstSymbol -> Результат 'Б'
// "автомобиль".firstSymbol -> Результат 'а'
val String.firstSymbol: Char
    get() = this[0]

// реализуйте свойство firstDigit для класса Int
// val a = 435
// a.firstDigit -> Результат 4
val Int.firstDigit: Int
    get() {
        var valueOfStr: String = this.toString()
        valueOfStr = valueOfStr[0].toString()
        return valueOfStr.toInt()
    }

// реализуйте свойство fio для класса Person
// Person("Андрей", "Стрельцов", "Александрович") -> Результат "Стрельцов Андрей Александрович"

val Person.fio: String
    get() = "${this.surname} ${this.name} ${this.thirdName}"

// реализуйте свойство biggestDigit для класса Int
// val a = 435
// a.biggestDigit -> Результат 5
val Int.biggestDigit: Int
    get() {
        var toStr = ""
        var maxVal: Int = Int.MIN_VALUE
        val valueString: String = this.toString()
        valueString.forEach {
            toStr = it.toString()
            if (toStr.toInt() > maxVal) maxVal = toStr.toInt()
        }
        return maxVal
    }
