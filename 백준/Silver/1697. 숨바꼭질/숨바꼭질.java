import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]); // 수빈이의 위치
        int K = Integer.parseInt(inputs[1]); // 동생의 위치
        
        System.out.println(bfs(N, K)); // BFS를 통해 수빈이가 동생을 찾는 최소 시간 출력
    }

    // BFS를 통해 수빈이가 동생을 찾는 최소 시간을 계산하는 함수
    public static int bfs(int N, int K) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[100001]; // 방문 여부를 체크하기 위한 배열
        int[] time = new int[100001]; // 각 위치까지의 최소 시간을 저장하는 배열

        queue.offer(N); // 시작 위치를 큐에 추가
        visited[N] = true; // 시작 위치 방문 표시

        while (!queue.isEmpty()) {
            int current = queue.poll(); // 현재 위치
            
            if (current == K) // 동생의 위치에 도달한 경우
                return time[current]; // 해당 위치까지의 최소 시간 반환

            // 현재 위치에서 -1, +1, *2 위치로 이동하는 경우를 계산하여 큐에 추가
            int[] nextPositions = {current - 1, current + 1, current * 2};
            for (int next : nextPositions) {
                if (next >= 0 && next <= 100000 && !visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                    time[next] = time[current] + 1; // 현재 위치에서 다음 위치까지의 시간 = 현재 위치까지의 시간 + 1
                }
            }
        }
        return -1; // 동생의 위치에 도달할 수 없는 경우
    }
}
