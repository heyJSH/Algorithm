import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 사람의 수 N을 입력 받음
        int N = Integer.parseInt(br.readLine());

        // 각 사람이 돈을 인출하는데 걸리는 시간을 저장하는 배열
        int[] times = new int[N];

        // 각 사람이 돈을 인출하는데 걸리는 시간을 입력 받음
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(input[i]);
        }

        // 돈을 인출하는데 걸리는 시간을 오름차순으로 정렬
        Arrays.sort(times);

        // 각 사람이 돈을 인출하는데 필요한 시간의 합을 계산
        int total = 0;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += times[i];
            total += sum;
        }

        // 결과 출력
        System.out.println(total);
    }
}
