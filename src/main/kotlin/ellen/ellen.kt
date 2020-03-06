package ellen

import data.*

var id = ""
var trueFalse = true

fun main() {
    runProgram()
}

fun logIn() {

    println("1. 로그인 탱, 2. 로그인 민")
    var logInValue = readLine()!!


    when (logInValue) {
        "1" -> {
            println("탱 로그인")
            id = "ellen"
        }
        "2" -> {
            println("로그인 민")
            id = "justin"
        }
        else -> {
            println("로그인할 수 없습니다.")
            trueFalse = false
        }
    }


}

fun runProgram() {
    logIn()
    while (trueFalse) {
        var totalMoney = sumQuery("SELECT sum(price) as total FROM $id")
        println("현재 가진 돈은 $totalMoney 원")
        println(" 1 : 입금, 2 : 출금, 3. 삭제, 4. 로그아웃")
        var inputVal = readLine()!!

        when (inputVal) {
            "1" -> {
                println("money")
                var money = readLine()!!.toInt()
                println("memo")
                var memo = readLine()!!
                creditMoney(money, memo)
            }
            "2" -> {
                println("money")
                var money = readLine()!!.toInt()
                println("memo")
                var memo = readLine()!!
                debitMoney(money, memo)
            }
            "3" -> {
                println("select * from $id order by num asc")
                selectQuery("select * from $id order by num asc")?.forEach {
                    println(it.num.toString() + " " + it.price + " " + it.memo + " " + it.day)
                }
                var indexNum = readLine()!!.toInt()
                deleteMoney(indexNum)
            }
            "4" -> {
                logIn()
            }
            else -> {
                println("실행불가")
            }
        }
    }
}

fun creditMoney(money: Int, comment: String) {
    query("insert into $id(price , memo) values($money, '$comment')")

}
// 입금

fun debitMoney(money: Int, comment: String) {
    var minusMoney = ("-$money").toInt()
    query("insert into $id(price , memo) values($minusMoney, '$comment')")
}
//출금

fun deleteMoney(indexNum: Int) {

//    for (i in 0 .. userInfo.size){
//        print("#" + ( i + 1 ) + "금액 : " + userInfo[i].money + " 내용 : " + userInfo[i].comment )
//        if((i + 1) % 3 == 0 ){
//            println()
//        }
//    } //여기까지 예전것
    println("num")
    query("delete from $id where num = $indexNum")
}
// 삭제

//예제
/*
println(selectQuery("select * from userInfo order by seq desc"))

var query = "insert into userInfo(name) values('테스트이름')"
query(query)

query = "delete from userInfo where seq=43"
query(query)

selectQuery("select * from userInfo order by seq desc")?.forEach {
    println(it.num + " " + it.name)
}
*/