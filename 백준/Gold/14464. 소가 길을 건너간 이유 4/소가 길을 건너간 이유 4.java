import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] chickens = new int[C];
        for (int i = 0; i < C; i++)
            chickens[i] = Integer.parseInt(br.readLine());

        int[][] cows = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cows[i][0] = Integer.parseInt(st.nextToken()); // A
            cows[i][1] = Integer.parseInt(st.nextToken()); // B
        }

        Arrays.sort(chickens);

        Arrays.sort(cows, (a, b) -> a[0] - b[0]);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        int cowIdx = 0;
        int result = 0;

        for (int t : chickens) {

            while (cowIdx < N && cows[cowIdx][0] <= t) {
                pq.add(cows[cowIdx]);
                cowIdx++;
            }

            while (!pq.isEmpty() && pq.peek()[1] < t) {
                pq.poll();
            }

            if (!pq.isEmpty()) {
                pq.poll();
                result++;
            }
        }

        System.out.println(result);
    }
}
