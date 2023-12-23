// реализуйте функцию toInt() для класса Boolean
fun Boolean.toInt(): Int{
    return when(this){
        true -> 1
        else -> 0
    }
}
// true.toInt() -> Результат '1'
// false.toInt() -> Результат '0'


// реализуйте функцию printLn() для класса String
// "Hello".printLn() -> Вывод в лог 'Hello'
fun String.printLn(){
    println(this)
}

// реализуйте функцию percentOf() для класса Int
// val value = 5
// value.percentOf(10) -> Результат '50'
fun Int.percentOf(value: Int): Int{
    return ((100*this)/value)
}

// реализуйте функцию divideBySeparator() для класса String
// "Привет мир".divideBySeparator('_') -> Результат 'Привет_мир'

fun String.divideBySeparator(separator: Char): String{
    var array = toCharArray()
    var result = ""
    for(i in array){
        if (i == ' '){
            result += separator
        } else {
            result += i
        }
    }
    return result
}

