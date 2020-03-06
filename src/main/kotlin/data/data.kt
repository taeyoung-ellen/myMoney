package data

import java.sql.*
import java.util.*
import kotlin.collections.ArrayList

//data class MoneyData(
//    var money: Int,
//    var comment: String,
//    var idVal: Int
//)

//var myMoneyData = ArrayList<MoneyData>()

/////////////////

data class MyMoney(val num: Int, val price : Int, val memo: String, val day : Timestamp)
//data class TotalMoney(val price: Int)

internal var conn: Connection? = null

fun selectQuery(queryString: String): ArrayList<MyMoney>? {
    getConn()
    var stmt: Statement? = null
    var resultset: ResultSet? = null

    try {
        stmt = conn!!.createStatement()
        if (stmt.execute(queryString)) {
            resultset = stmt.resultSet
        }

        var moneyData = ArrayList<MyMoney>()
        while (resultset!!.next()) {
            moneyData.add(
                MyMoney(
                    resultset.getInt("num"),
                    resultset.getInt("price"),
                    resultset.getString("memo"),
                    resultset.getTimestamp("day")
                )
            )
        }

        return moneyData
    } catch (ex: SQLException) {
        ex.printStackTrace()
    } finally {
        if (resultset != null) {
            try {
                resultset.close()
            } catch (sqlEx: SQLException) {
            }
            resultset = null
        }
        if (stmt != null) {
            try {
                stmt.close()
            } catch (sqlEx: SQLException) {
            }
            stmt = null
        }
        if (conn != null) {
            try {
                conn!!.close()
            } catch (sqlEx: SQLException) {
            }
            conn = null
        }
    }
    return null
}

fun sumQuery(queryString: String): Int? {
    getConn()
    var stmt: Statement? = null
    var resultset: ResultSet? = null

    try {
        stmt = conn!!.createStatement()
        if (stmt.execute(queryString)) {
            resultset = stmt.resultSet
        }

        var moneyData = 0
        resultset!!.next()
        moneyData = resultset.getInt("total")

        return moneyData
    } catch (ex: SQLException) {
        ex.printStackTrace()
    } finally {
        if (resultset != null) {
            try {
                resultset.close()
            } catch (sqlEx: SQLException) {
            }
            resultset = null
        }
        if (stmt != null) {
            try {
                stmt.close()
            } catch (sqlEx: SQLException) {
            }
            stmt = null
        }
        if (conn != null) {
            try {
                conn!!.close()
            } catch (sqlEx: SQLException) {
            }
            conn = null
        }
    }
    return null
}

fun query(queryString:String) {
    getConn()
    var stmt: Statement? = null
    try {
        stmt = conn!!.createStatement()
        stmt!!.execute(queryString) // execute 실행
    } catch (ex: SQLException) {
        ex.printStackTrace()
    } finally {
        if (stmt != null) {
            try {
                stmt.close()
            } catch (sqlEx: SQLException) {
            }
            stmt = null
        }
        if (conn != null) {
            try {
                conn!!.close()
            } catch (sqlEx: SQLException) {
            }
            conn = null
        }
    }
}

fun getConn() {
    try {
        val connectionProps = Properties()
        connectionProps.put("user", "ellen")
        connectionProps.put("password", "qw12qw12")

        Class.forName("com.mysql.jdbc.Driver")
        conn = DriverManager.getConnection(
            "jdbc:" + "mysql" + "://" +
                    "192.168.1.100" +
                    ":" + "3306" + "/study" +
                    "",
            connectionProps
        )
    } catch (ex: SQLException) {
        ex.printStackTrace()
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}