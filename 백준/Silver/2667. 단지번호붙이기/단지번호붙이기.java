import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int N; // 지도의 크기를 저장할 변수
    static int[][] grid; // 입력으로 받은 지도 정보를 저장할 2차원 배열
    static boolean[][] visited; // 방문 여부를 저장할 2차원 배열
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우로 이동하기 위한 배열
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt(); // 지도의 크기 입력
        scanner.nextLine(); // 개행 문자 처리

        grid = new int[N][N]; // 지도 배열 초기화
        visited = new boolean[N][N]; // 방문 여부 배열 초기화

        // 지도 정보 입력
        for (int i = 0; i < N; i++) {
            String line = scanner.nextLine(); // 한 줄씩 입력 받음
            for (int j = 0; j < N; j++) {
                grid[i][j] = line.charAt(j) - '0'; // 문자열로 받은 숫자를 int로 변환하여 저장
            }
        }

        // 각 단지의 크기를 저장할 리스트 생성
        List<Integer> complexes = new ArrayList<>();

        // 모든 좌표에 대해 탐색 진행
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 집이 있고 아직 방문하지 않았다면 DFS 호출
                if (grid[i][j] == 1 && !visited[i][j]) {
                    complexes.add(dfs(i, j)); // 각 단지의 크기를 리스트에 추가
                }
            }
        }

        // 단지 크기를 오름차순으로 정렬
        Collections.sort(complexes);

        // 결과 출력
        System.out.println(complexes.size());
        for (int size : complexes) {
            System.out.println(size);
        }
    }

    // DFS를 통해 단지의 크기를 구하는 메소드
    static int dfs(int x, int y) {
        visited[x][y] = true; // 현재 위치 방문 처리
        int count = 1; // 현재 위치를 포함한 단지의 크기

        // 상하좌우에 대해 이동하면서 단지의 크기를 찾음
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i]; // 다음 위치의 행
            int ny = y + dy[i]; // 다음 위치의 열

            // 다음 위치가 유효하고, 집이 있으며 아직 방문하지 않았다면 DFS 수행
            if (isValidMove(nx, ny) && grid[nx][ny] == 1 && !visited[nx][ny]) {
                count += dfs(nx, ny); // DFS 재귀 호출로 단지 크기 갱신
            }
        }

        return count; // 단지의 크기 반환
    }

    // 이동할 좌표가 유효한지 확인하는 메소드
    static boolean isValidMove(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N; // 지도 범위 내에 있는지 확인
    }
}
