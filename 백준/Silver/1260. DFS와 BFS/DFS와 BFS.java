import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] graph; // 그래프를 인접 리스트로 표현할 배열
    static boolean[] visited; // 정점의 방문 여부를 나타내는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 정점의 개수, 간선의 개수, 시작 정점 입력
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]); // 정점의 개수
        int M = Integer.parseInt(input[1]); // 간선의 개수
        int V = Integer.parseInt(input[2]); // 시작 정점

        // 그래프 초기화
        graph = new ArrayList[N + 1]; // 1부터 인덱스를 사용하기 위해 크기를 N+1로 설정
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 간선 정보 입력
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int from = Integer.parseInt(input[0]); // 출발 정점
            int to = Integer.parseInt(input[1]); // 도착 정점
            graph[from].add(to); // 출발 정점에서 도착 정점으로의 간선 추가
            graph[to].add(from); // 양방향 그래프이므로 도착 정점에서 출발 정점으로의 간선도 추가
        }

        // 그래프 정점들의 리스트를 번호 순으로 정렬
        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i]);
        }

        // DFS, BFS 수행
        visited = new boolean[N + 1]; // 정점의 방문 여부를 나타내는 배열 초기화
        dfs(V); // DFS 호출
        System.out.println(); // DFS 결과 출력 후 줄 바꿈
        visited = new boolean[N + 1]; // BFS를 위해 방문 여부 배열 초기화
        bfs(V); // BFS 호출

        br.close(); // BufferedReader 닫기
    }

    // DFS 메서드
    static void dfs(int v) {
        visited[v] = true; // 현재 정점을 방문했음을 표시
        System.out.print(v + " "); // 현재 정점 출력
        for (int next : graph[v]) { // 현재 정점과 연결된 모든 정점에 대해서
            if (!visited[next]) { // 방문하지 않은 정점이라면
                dfs(next); // 재귀적으로 방문
            }
        }
    }

    // BFS 메서드
    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>(); // BFS에 사용할 큐 생성
        queue.offer(start); // 시작 정점을 큐에 삽입
        visited[start] = true; // 시작 정점을 방문했음을 표시

        while (!queue.isEmpty()) { // 큐가 비어있을 때까지 반복
            int current = queue.poll(); // 큐에서 정점을 하나 꺼내서 현재 정점으로 설정
            System.out.print(current + " "); // 현재 정점 출력
            for (int next : graph[current]) { // 현재 정점과 연결된 모든 정점에 대해서
                if (!visited[next]) { // 방문하지 않은 정점이라면
                    visited[next] = true; // 방문했음을 표시
                    queue.offer(next); // 큐에 삽입
                }
            }
        }
    }
}
