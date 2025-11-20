import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 횡단보도 수
        int K = Integer.parseInt(st.nextToken()); // 연속해야 하는 신호등 수
        int B = Integer.parseInt(st.nextToken()); // 고장난 신호등 수

        int[] arr = new int[N + 1]; // 1-based

        // 고장난 신호등 표시
        for (int i = 0; i < B; i++) {
            int broken = Integer.parseInt(br.readLine());
            arr[broken] = 1;  // 1이면 고장
        }

        // 처음 구간 [1..K]의 고장 개수
        int sum = 0;
        for (int i = 1; i <= K; i++) {
            sum += arr[i];
        }

        int answer = sum;
        
        // 슬라이딩 윈도우: [2..K+1], [3..K+2], ... , [N-K+1..N]
        for (int right = K + 1; right <= N; right++) {
            int left = right - K; // 이전 구간의 왼쪽 끝

            sum = sum - arr[left] + arr[right]; // 한 칸 오른쪽으로 민 윈도우의 고장 개수
            if (sum < answer) {
                answer = sum;
            }
        }

        System.out.println(answer);
    }
}
