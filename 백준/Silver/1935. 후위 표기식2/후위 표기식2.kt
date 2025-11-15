import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val N = readLine().trim().toInt()
    val expr = readLine().trim()

    val values = DoubleArray(26) //A to Z
    for(i in 0 until N) {
        val v = readLine().trim().toDouble()
        values[i] = v
    }

    val stack = ArrayDeque<Double>()
    for(ch in expr) {
        if(ch in 'A'..'Z') {
            val idx = ch - 'A'
            stack.addLast(values[idx])
        } else {
            val b = stack.removeLast()
            val a = stack.removeLast()
            val res = when(ch) {
                '+' -> a + b
                '-' -> a - b
                '*' -> a * b
                '/' -> a / b
                else -> throw IllegalArgumentException("Unknown operator: $ch")
            }
            stack.addLast(res)
        }
    }

    val result = stack.removeLast()
    println(String.format("%.2f", result))
}