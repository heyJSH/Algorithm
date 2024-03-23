import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    // 이동 방향을 나타내는 배열 (상, 하, 좌, 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 2) 미로의 크기를 입력 받음
        String[] size = br.readLine().split(" ");
        int N = Integer.parseInt(size[0]);
        int M = Integer.parseInt(size[1]);

        // 3) 미로 정보를 2차원 배열로 저장
        int[][] maze = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] row = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                maze[i][j] = Integer.parseInt(row[j]);
            }
        }

        // 4) BFS를 통해 최소 이동 칸 수를 구함
        int answer = bfs(N, M, maze);
        
        // 5) 최소 이동 칸 수 출력
        System.out.println(answer);
    }

    // BFS로 최소 이동 칸 수 구하는 함수
    static int bfs(int N, int M, int[][] maze) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M]; // 방문 여부를 체크하는 배열
        queue.offer(new int[]{0, 0}); // 시작점 (1, 1) 큐에 추가
        visited[0][0] = true; // 시작점 방문 표시
        int count = 1; // 시작점도 포함하므로 1로 초기화

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];
                
                // 상하좌우 이동
                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    // 범위 체크 및 이동 가능 여부 확인
                    if (nx >= 0 && nx < N && ny >= 0 && ny < M && maze[nx][ny] == 1 && !visited[nx][ny]) {
                        if (nx == N - 1 && ny == M - 1) // 도착점에 도착했을 때
                            return count + 1; // 이동한 칸 수 반환
                        queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
            count++; // 한 단계씩 이동할 때마다 카운트 증가
        }
        return -1; // 도착점에 도착할 수 없는 경우
    }
}
