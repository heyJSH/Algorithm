import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 입력 받기
        String[] nk = br.readLine().split(" ");
        int N = Integer.parseInt(nk[0]); // 동전의 종류 수
        int K = Integer.parseInt(nk[1]); // 목표 가치

        int[] coins = new int[N]; // 각 동전의 가치를 저장할 배열
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        br.close();

        // 2. 동전 개수 계산하기
        int count = 0; // 필요한 동전의 최소 개수
        for (int i = N - 1; i >= 0; i--) { // 가장 큰 가치의 동전부터 시작
            count += K / coins[i]; // 현재 가치의 동전으로 만들 수 있는 최대 개수 더하기
            K %= coins[i]; // 현재 가치의 동전을 사용한 후 남은 가치 업데이트
        }

        // 3. 결과 출력
        System.out.println(count);
    }
}
