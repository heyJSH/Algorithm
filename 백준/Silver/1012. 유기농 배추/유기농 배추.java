import java.util.Scanner;

public class Main {
    // DFS를 이용하여 인접한 배추들을 그룹으로 묶는 메서드
    static void dfs(int[][] field, int x, int y, int m, int n) {
        if (x < 0 || y < 0 || x >= m || y >= n || field[x][y] == 0)
            return;
        
        field[x][y] = 0; // 방문한 배추는 0으로 표시
        
        // 상하좌우 인접한 배추들에 대해 재귀적으로 호출
        dfs(field, x + 1, y, m, n);
        dfs(field, x - 1, y, m, n);
        dfs(field, x, y + 1, m, n);
        dfs(field, x, y - 1, m, n);
    }

    // 배추 그룹을 찾고 필요한 배추흰지렁이의 수를 계산하는 메서드
    static int countCabbageWorms(int[][] field, int m, int n) {
        int worms = 0;
        
        // 배추 밭 순회
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (field[i][j] == 1) {
                    worms++; // 새로운 그룹에 배추흰지렁이 추가
                    dfs(field, i, j, m, n); // 배추 그룹 찾기
                }
            }
        }
        
        return worms;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt(); // 테스트 케이스의 개수
        
        // 각 테스트 케이스에 대해 실행
        for (int i = 0; i < t; i++) {
            int m = scanner.nextInt(); // 가로길이
            int n = scanner.nextInt(); // 세로길이
            int k = scanner.nextInt(); // 배추 개수
            
            int[][] field = new int[m][n]; // 배추밭의 상태를 나타내는 배열
            
            // 배추 위치 입력 및 배추밭 초기화
            for (int j = 0; j < k; j++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                field[x][y] = 1;
            }
            
            // 필요한 배추흰지렁이의 수 출력
            System.out.println(countCabbageWorms(field, m, n));
        }
        scanner.close();
    }
}
