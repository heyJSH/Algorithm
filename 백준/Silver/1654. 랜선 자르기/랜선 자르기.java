import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken()); // 가지고 있는 랜선의 개수
        int N = Integer.parseInt(st.nextToken()); // 필요한 랜선의 개수
        
        int[] cables = new int[K];
        long maxLength = 0; // 가장 긴 랜선의 길이
        
        // 가지고 있는 랜선의 길이 입력 받기
        for (int i = 0; i < K; i++) {
            cables[i] = Integer.parseInt(br.readLine());
            maxLength = Math.max(maxLength, cables[i]); // 가장 긴 랜선 길이 찾기
        }
        
        long left = 1; // 이진 탐색 시작 범위
        long right = maxLength; // 이진 탐색 끝 범위
        long answer = 0; // 최대 길이
        
        // 이진 탐색
        while (left <= right) {
            long mid = (left + right) / 2; // 중간 길이
            
            // 현재 길이로 만들 수 있는 랜선의 개수 계산
            long count = countCables(cables, mid);
            
            // 랜선의 개수가 필요한 개수보다 작으면 더 짧게 잘라야 함
            // 이진 탐색 범위를 왼쪽으로 좁힘
            if (count < N) {
                right = mid - 1;
            }
            // 랜선의 개수가 필요한 개수보다 많거나 같으면 더 길게 잘라도 됨
            // 답 후보이므로 기록하고 이진 탐색 범위를 오른쪽으로 좁힘
            else {
                answer = Math.max(answer, mid);
                left = mid + 1;
            }
        }
        
        // 최대 길이 출력
        System.out.println(answer);
    }
    
    // 주어진 길이로 만들 수 있는 랜선의 개수 계산하는 함수
    private static long countCables(int[] cables, long length) {
        long count = 0;
        for (int cable : cables) {
            count += cable / length;
        }
        return count;
    }
}
