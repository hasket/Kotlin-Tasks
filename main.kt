class CoffeeMachine {
    private var scanner = Scanner(System.`in`)
    private var water: Int = 0
    private var milk: Int = 0
    private var beans: Int = 0

    fun interface CookCoffee {
        fun cookingCoffee(coffeeRecipe: CoffeeRecipe)
    }

    fun interface CheckCoffeeResource {
        fun checkingConffeeResource(coffeeRecipe: CoffeeRecipe): String
    }

    // Начните написание программы с публичной функции start().


    fun createCoffee() {
        println("Введите название напитка или \"назад\" для возврата в главное меню")
        val checkResource = CoffeeMachine.CheckCoffeeResource {
            when (true) {
                (this.water < it.water) -> "Недостаточно воды!"
                (this.milk < it.milk) -> "Недостаточно молока!"
                (this.beans < it.beans) -> "Недостаточно кофе!"
                else -> "false"
            }
        }
        val coffeeMachine = CoffeeMachine.CookCoffee {
            val checkCoffee = checkResource.checkingConffeeResource(it)
            if (checkCoffee == "false") {
                this.water -= it.water
                this.milk -= it.milk
                this.beans -= it.beans
                println("${it.coffeeName} готов")
            } else {
                println(checkCoffee)
            }
        }
        var coffeeName = scanner.nextLine()
        when (coffeeName.lowercase()) {
            "назад" -> return
            "эспрессо" -> coffeeMachine.cookingCoffee(CoffeeRecipe.ESPRESSO)
            "американо" -> coffeeMachine.cookingCoffee(CoffeeRecipe.AMERICANO)
            "капучино" -> coffeeMachine.cookingCoffee(CoffeeRecipe.KAPUCHINO)
            "латте" -> coffeeMachine.cookingCoffee(CoffeeRecipe.LATTE)
            else -> println("Рецепт не найден!")
        }
    }

    fun fillCoffeeMachine() {
        this.water += (2000 - this.water)
        this.milk += (1000 - this.milk)
        this.beans += (500 - this.beans)
        println("Ингридиенты пополнены")
    }

    fun status() {
        println("Ингридиентов осталось:")
        println("${this.water} мл воды\n${this.milk} мл молока\n${this.beans} гр кофе")
    }

    fun start() {
        println("Кофемашина готова к работе")
        while (true) {
            println("Введите команду")
            var command = scanner.nextLine()
            when (command.lowercase()) {
                "кофе" -> createCoffee()
                "выключить" -> break
                "наполнить" -> fillCoffeeMachine()
                "статус" -> status()
            }
        }
        println("До свидания!")
    }

}

enum class CoffeeRecipe(
    val coffeeName: String,
    val water: Int,
    val milk: Int,
    val beans: Int
) {
    ESPRESSO("Эспрессо ", 60, 0, 10),
    AMERICANO("Американо", 120, 0, 10),
    KAPUCHINO("Капучино", 120, 60, 20),
    LATTE("Латте", 240, 120, 20)
}