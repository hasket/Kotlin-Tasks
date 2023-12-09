// «[день] — [isWeekend()] день».
fun main() {
    for (day in Week.values()) {
        println("${day.localizedName} - ${day.isWeekend()}")
    }
}

enum class Week(val localizedName: String) {
    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSADAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота"),
    SUNDAY("Воскресенье");

    fun isWeekend(): String =
        when (this) {
            SATURDAY -> "выходной"
            SUNDAY -> "выходной"
            else -> "будний"
        }
}
