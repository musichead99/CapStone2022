package level_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class backjoon_2798 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int card[] = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            card[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;

        for(int i = 0; i < N; i++) {
            for(int j = i+1; j < N; j++) {
                for(int k = j+1; k < N; k++) {
                    int newSum = card[i] + card[j] +card[k];
                    if(sum < newSum && M >= newSum) {
                        sum = newSum;
                    }
                }
            }
        }
        System.out.println(sum);
    }
}
