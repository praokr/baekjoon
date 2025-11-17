import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()

    // 소는 항상 1~10번
    val pos = IntArray(11) { -1 }     // 소 위치 기록
    val cnt = IntArray(11) { 0 }      // 위치 변경 횟수

    repeat(n) {
        val st = StringTokenizer(readLine())
        val cow = st.nextToken().toInt()
        val curPos = st.nextToken().toInt()

        if (pos[cow] != -1 && pos[cow] != curPos) {
            cnt[cow]++
        }
        pos[cow] = curPos
    }

    var result = 0
    for (i in 1..10) result += cnt[i]

    println(result)
}
