fun main() = with(System.`in`.bufferedReader()) {
    val input = readLine()!!
    val pos = input.toCharArray()

    val cow = Array(26) { IntArray(2) { -1 } }

    // 각 소의 출발/도착 위치 기록
    for (i in pos.indices) {
        val idx = pos[i] - 'A'
        if (cow[idx][0] == -1) cow[idx][0] = i
        else cow[idx][1] = i
    }

    var result = 0

    // 두 소 A(i)와 B(j) 비교
    for (i in 0 until 26) {
        for (j in i + 1 until 26) {
            val A1 = cow[i][0]
            val A2 = cow[i][1]
            val B1 = cow[j][0]
            val B2 = cow[j][1]

            // A1 < B1 < A2 < B2 이면 만남
            if (A1 < B1 && B1 < A2 && A2 < B2) result++
            if (B1 < A1 && A1 < B2 && B2 < A2) result++
        }
    }

    println(result)
}
