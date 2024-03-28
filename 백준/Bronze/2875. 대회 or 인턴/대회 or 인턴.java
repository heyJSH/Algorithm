import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 여학생 수
        int M = Integer.parseInt(st.nextToken()); // 남학생 수
        int K = Integer.parseInt(st.nextToken()); // 인턴쉽 참여 인원

        br.close();

        // 2. 팀 수 계산하기
        int teamCount = 0;
        while (N >= 2 && M >= 1 && N + M >= K + 3) {
            N -= 2; // 여학생 2명 제외
            M -= 1; // 남학생 1명 제외
            teamCount++; // 팀 수 증가
        }

        // 3. 결과 출력
        System.out.println(teamCount);
    }
}
