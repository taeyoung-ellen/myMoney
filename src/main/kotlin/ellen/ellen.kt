package ellen

import data.*

// import data????
var id = 0
var money = 0 // 나중에 값 넣기
var comment = ""

fun main(){

}

fun logIn() {

    println("1. 로그인 탱, 2. 로그인 민")
    var logInValue = readLine()!!
    when (logInValue) {
        "1" -> {
            id = 1
            runProgram()
        }
        "2" -> {
            id = 2
            runProgram()
        }
        else -> println("로그인할 수 없습니다.")
    }
}

fun runProgram() {
    while (true) {
        println("현재 가진 돈은 " + money)
        println(" 1 : 입금, 2 : 출금, 3. 삭제, 4. 로그아웃")
        var inputVal = readLine()!!

        when (inputVal) {
            "1" -> {
                money = readLine()!!.toInt()
                comment = readLine()!!
                creditMoney(money, comment)
            }
            "2" -> {
                debitMoney(money, comment)
            }
            "3" -> {

                deleteMoney(0)
            }
            "4" -> {
                logIn()
            }
        }
    }
}

fun creditMoney(money: Int, comment: String) {
    myMoneyData.add(MoneyData(money, comment, id))
}
// 입금

fun debitMoney(money: Int, comment: String) {
    var minusMoney = ("-$money").toInt() //todo yeahhhhhhh
    myMoneyData.add(MoneyData(minusMoney, comment, id))
}
//출금

fun deleteMoney(indexNum : Int){

}
// 삭제

