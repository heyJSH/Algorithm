import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 배열 A 입력받기
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A); // 배열 A를 정렬

        // X의 개수 M 입력받기
        int M = Integer.parseInt(br.readLine());

        // X에 대해 배열 A를 탐색하여 존재 여부를 판별하고 결과를 출력
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int X = Integer.parseInt(st.nextToken());
            boolean exists = binarySearch(A, X); // 이진 탐색을 통해 X의 존재 여부 확인
            sb.append(exists ? "1\n" : "0\n");
        }

        System.out.println(sb.toString());
    }

    // 이진 탐색을 통해 배열 A에서 X의 존재 여부를 판별하는 메소드
    private static boolean binarySearch(int[] A, int X) {
        int left = 0;
        int right = A.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (A[mid] == X) {
                return true;
            } else if (A[mid] < X) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
