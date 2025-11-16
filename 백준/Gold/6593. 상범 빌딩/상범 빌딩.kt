import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.ArrayDeque
import java.util.StringTokenizer

data class Node(val z: Int, val x: Int, val y: Int)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val sb = StringBuilder()

    while (true) {
        val line = readLine() ?: break
        if (line.isBlank()) continue

        val st = StringTokenizer(line)
        val L = st.nextToken().toInt()
        val R = st.nextToken().toInt()
        val C = st.nextToken().toInt()

        if (L == 0 && R == 0 && C == 0) break

        val building = Array(L) { Array(R) { CharArray(C) } }

        var startZ = 0
        var startX = 0
        var startY = 0

        var endZ = 0
        var endX = 0
        var endY = 0

        // L층 입력
        for (z in 0 until L) {
            for (x in 0 until R) {
                var row = readLine()
                // 층 사이의 빈 줄이 들어올 수 있어서 건너뛰기
                while (row != null && row.isEmpty()) {
                    row = readLine()
                }
                val chars = row!!.toCharArray()
                for (y in 0 until C) {
                    val ch = chars[y]
                    building[z][x][y] = ch
                    if (ch == 'S') {
                        startZ = z
                        startX = x
                        startY = y
                    } else if (ch == 'E') {
                        endZ = z
                        endX = x
                        endY = y
                    }
                }
            }
        }

        val minutes = bfs(building, L, R, C, startZ, startX, startY, endZ, endX, endY)

        if (minutes >= 0) {
            sb.append("Escaped in ").append(minutes).append(" minute(s).").append('\n')
        } else {
            sb.append("Trapped!").append('\n')
        }
    }

    print(sb.toString())
}

fun bfs(
    building: Array<Array<CharArray>>,
    L: Int,
    R: Int,
    C: Int,
    sz: Int,
    sx: Int,
    sy: Int,
    ez: Int,
    ex: Int,
    ey: Int
): Int {
    val dist = Array(L) { Array(R) { IntArray(C) { -1 } } }

    val dz = intArrayOf(1, -1, 0, 0, 0, 0)
    val dx = intArrayOf(0, 0, 1, -1, 0, 0)
    val dy = intArrayOf(0, 0, 0, 0, 1, -1)

    val q: ArrayDeque<Node> = ArrayDeque()
    q.addLast(Node(sz, sx, sy))
    dist[sz][sx][sy] = 0

    while (q.isNotEmpty()) {
        val (z, x, y) = q.removeFirst()

        if (z == ez && x == ex && y == ey) {
            // 시작을 0분으로 했으니 +1 안 하고 그대로 반환해도 됨
            return dist[z][x][y]
        }

        for (dir in 0 until 6) {
            val nz = z + dz[dir]
            val nx = x + dx[dir]
            val ny = y + dy[dir]

            if (nz !in 0 until L || nx !in 0 until R || ny !in 0 until C) continue
            if (building[nz][nx][ny] == '#') continue
            if (dist[nz][nx][ny] != -1) continue

            dist[nz][nx][ny] = dist[z][x][y] + 1
            q.addLast(Node(nz, nx, ny))
        }
    }

    return -1
}
