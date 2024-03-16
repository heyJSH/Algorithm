import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 숫자 카드의 개수 N 입력 받기
        int N = Integer.parseInt(br.readLine());

        // 숫자 카드에 적힌 정수를 해시맵에 저장하여 개수 카운트
        HashMap<Integer, Integer> cardCount = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            cardCount.put(num, cardCount.getOrDefault(num, 0) + 1);
        }

        // 구해야 할 숫자의 개수 M 입력 받기
        int M = Integer.parseInt(br.readLine());

        // 구해야 할 숫자들 입력 받기
        st = new StringTokenizer(br.readLine());

        // 각 숫자별로 상근이가 가지고 있는 숫자 카드의 개수 찾아서 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            int count = cardCount.getOrDefault(num, 0);
            sb.append(count).append(" ");
        }

        System.out.println(sb.toString());
    }
}
