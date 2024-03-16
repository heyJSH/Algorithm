import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 나무의 수
        int M = Integer.parseInt(st.nextToken()); // 상근이가 집으로 가져가려는 나무의 길이

        int[] trees = new int[N]; // 각 나무의 높이를 저장할 배열

        // 나무의 높이 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }

        // 최적의 높이를 이진 탐색을 이용하여 찾기
        int answer = binarySearch(trees, M);

        System.out.println(answer); // 결과 출력
    }

    // 이진 탐색을 이용하여 최적의 높이를 찾는 함수
    private static int binarySearch(int[] trees, int target) {
        int left = 0;
        int right = 1000000000; // 최대 나무의 높이
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            long total = 0; // 자른 나무의 총 길이를 저장할 변수

            // 현재 높이로 나무를 잘라서 가져갈 수 있는 총 길이 계산
            for (int tree : trees) {
                if (tree > mid) {
                    total += tree - mid;
                }
            }

            // 가져갈 수 있는 총 길이가 목표 길이와 같거나 더 많으면 높이를 더 높일 수 있음
            if (total >= target) {
                result = mid;
                left = mid + 1;
            }
            // 가져갈 수 있는 총 길이가 목표 길이보다 작으면 높이를 낮춰야 함
            else {
                right = mid - 1;
            }
        }

        return result;
    }
}
