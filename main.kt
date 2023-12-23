import kotlin.random.Random

fun main() {
    val catDownloaderV2 = CatDownloaderV2()

    // перепишите данную реализацию согласно требованиям в задаче
    // val downloadedCats = catDownloaderV2.downloadCats(CATS_COUNT)
    //val onNext: (Cat) -> Unit = { cat -> showCat(cat) }

    catDownloaderV2.downloadCats(
        CATS_COUNT,
        onNext = { cat -> showCat(cat) },
        onComplete = { showComplete() },
        onError = { showError() }
    )

    //В новой реализации в выводе такого сообщения необходимости не будет
    //println("Котики $downloadedCats показаны")
}


fun showCat(cat: Cat) {
    println("Котик ${cat.name} успешно загружен")
}

fun showComplete() {
    println("Загрузка котиков завершена")
}

fun showError() {
    println("Упс. При загрузке котика произошла ошибка :(")
}

// константа для выбора количества загруженных котиков
const val CATS_COUNT = 5



class CatDownloaderV2 {

    // перепишите метод согласно заданию. Используйте функции в качестве параметров для реагирования на события загрузки котиков
    fun downloadCats(
        count: Int,
        onNext: (Cat) -> Unit,
        onComplete: () -> Unit,
        onError: () -> Unit
    ) {
        for (i in 1..count){
            val cat = getCatFromInternet()
            when(cat){
                null -> onError.invoke()
                else -> onNext.invoke(cat)
            }
        }
        onComplete.invoke()
    }



    // этот метод переписывать не нужно. Но если вам хочется добавить разнообразия — вы можете придумать свою логику генерации котиков ¯\_(ツ)_/¯
    private fun getCatFromInternet(): Cat? {
        return when (Random.nextInt(5)) {
            0 -> null
            1 -> Cat("Борис")
            2 -> Cat("Кузьма")
            3 -> Cat("Барсик")
            4 -> Cat("Кефирчик")
            else -> null
        }
    }

}

data class Cat(val name: String)
