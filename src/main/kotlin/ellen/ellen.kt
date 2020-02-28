package ellen

import data.*
// todo userinfo를 ellen으로
// todo name을 memo로
//
// import data????
var id = 0
var money = 0 // 나중에 값 넣기
var comment = ""
var userInfo = myMoneyData.filter { it.idVal == id }

fun main(){
//    println(selectQuery("select * from ellen order by num desc"))


    var query = "insert into ellen(price , memo) values('999','테 스 트')"
    query(query)
//
//    query = "delete from ellen where seq=43"
//    query(query)
//
//    selectQuery("select * from ellen order by seq desc")?.forEach {
//        println(it.seq + " " + it.name)
//    }
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

                deleteMoney(0) // todo 고치기
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
    var minusMoney = ("-$money").toInt()
    myMoneyData.add(MoneyData(minusMoney, comment, id))
}
//출금

fun deleteMoney(indexNum : Int){
    for (i in 0 .. userInfo.size){
        print("#" + ( i + 1 ) + "금액 : " + userInfo[i].money + " 내용 : " + userInfo[i].comment )
        if((i + 1) % 3 == 0 ){
            println()
        }
    }
//    userInfo.remove()// todo 유저인포의 리드라인번째 값 지우기
}
// 삭제

////////////


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