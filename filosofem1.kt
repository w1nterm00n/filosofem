//философы садятся против часовой стрелки!!

fun main() {

    class Filosof(val filosofName: String ) {
        var name = filosofName
    }

    //рекурсивная функция которая находит порядковый номер философа
    fun gettingFilosofNumber(peopleAmount:Int, whoMadeChoice: MutableList<Int>): Int {
        var chosenPerson = (0..(peopleAmount - 1)).random() //получаю порядковый номер
        var isItDublicates = false
        for (i in 0 until whoMadeChoice.size) {
            if (whoMadeChoice.isNotEmpty() && whoMadeChoice[i] == chosenPerson) {       //чекаю совпадает ли он
                isItDublicates = true
            }
        }
        if (isItDublicates == false) {
            whoMadeChoice.add(chosenPerson)
            return chosenPerson         //если не совпадает - возвращаю его
        } else {
            return gettingFilosofNumber(peopleAmount, whoMadeChoice)   //если совпадает, запускаю по новой
        }
    }
    
    //0 это право, а 1 это лево..
    fun convertNumberToSpoon (choice:Int): String {
        if (choice == 0) {
            return "правую"
        } else {
            return "левую"
        }
    }

    fun showMessage (choice: Int) {
        var side = convertNumberToSpoon(choice)
        println("философ ест, он выбрал $side вилку")
    }


    println("введите количество философов ")
    var peopleAmount = readLine()?.toIntOrNull() ?: 0
    var filsofers = arrayOfNulls<Filosof>(peopleAmount)
    println("дайте имена ${peopleAmount} философам")

    val spoonArray = BooleanArray(peopleAmount) { true }

    fun checkAvailableSpoons (side:Int, num:Int): Boolean {
        //проверяю какие вилки свободны
        if (side == 1) {
            if (spoonArray[num] == true) {
                return true
            } else return false
        } else {
            if (num == (peopleAmount-1) ) {    //исключение для крайнего философа
                if (spoonArray[0] == true) {
                    return true
                } else return false
            } else {
                if (spoonArray[num + 1] == true) {
                    return true
                } else return false
            }
        }
    }

    for (i in 0 until peopleAmount){
        var name = readLine().toString()
        filsofers[i] = Filosof(name)
    }

    var whoMadeChoice = mutableListOf<Int>()   //сюда записываются порядковые номера тех кто сделал выбор, чтобы они не повторялись

    fun occupySpoon (choice: Int, num: Int) {
        if (choice == 1) {
            spoonArray[num] = false
        } else if (num < peopleAmount -1 ){
            spoonArray[num + 1] = false
        } else {
            spoonArray [0] = false
        }
    }

    for (i in 0 until peopleAmount){
        var num = gettingFilosofNumber(peopleAmount, whoMadeChoice)
        println(" очередь философа ${filsofers[num]?.name} ")
        var choice = (0..1).random()

        //если choice 1 - проверяем spoonArray[i]
        //если choice 0 - проверяем spoonArray[i+1], исключение если философ последний

        //если с одной стороны вилка занята - беру вилку с другой стороны
        if (checkAvailableSpoons(choice, num) == false) {
            println ("hello")
            if (choice == 1) {
                choice = 0
            } else {
                choice = 1
            }
            if (checkAvailableSpoons(choice, num) == false) {
                println(" размышляет")
            } else {
                showMessage(choice)
                occupySpoon(choice, num)
            }
        } else {
            showMessage(choice)
            occupySpoon(choice, num)
        }
        println ()
    }
}