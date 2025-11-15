import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val st1 = StringTokenizer(readLine(), " ")
    val N = st1.nextToken().toInt()
    val M = st1.nextToken().toInt()

    val st2 = StringTokenizer(readLine(), " ")
    val arr = IntArray(N) { st2.nextToken().toInt() }

    var start = 0
    var end = 0
    var sum = 0
    var count = 0

    while(true) {
        if(sum >= M) {
            if(sum == M) count++
            sum -= arr[start]
            start++
        } else {
            if(end == N) break
            sum += arr[end]
            end++
        }
    }

    println(count)
}