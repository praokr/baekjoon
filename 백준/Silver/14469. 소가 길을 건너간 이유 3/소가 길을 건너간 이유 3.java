import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] start = new int[n];
        int[] time = new int[n];

        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            start[i] = s;
            time[i] = t;
        }

        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                if(start[i] > start[j]) {
                    int temp1 = start[i];
                    int temp2 = time[i];
                    start[i] = start[j];
                    time[i] = time[j];
                    start[j] = temp1;
                    time[j] = temp2;
                }
            }
        }

        int totalTime = start[0] + time[0];

        for(int i = 1; i < n; i++) {
            if(start[i] > totalTime) {
                totalTime = start[i] + time[i];
                continue;
            }
            totalTime += time[i];
        }

        System.out.println(totalTime);
    }
}