import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] adjList; // 인접 리스트
    static boolean[] visited; // 정점 방문 여부를 나타내는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 정점의 개수
        int M = Integer.parseInt(st.nextToken()); // 간선의 개수

        adjList = new ArrayList[N + 1]; // 1부터 N까지의 정점을 나타내기 위해 크기를 N+1로 설정
        visited = new boolean[N + 1]; // 방문 여부를 나타내는 배열

        // 인접 리스트 초기화
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        // 간선 정보 입력받아 인접 리스트 구성
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjList[u].add(v);
            adjList[v].add(u);
        }

        int count = 0; // 연결 요소의 개수 초기화
        // DFS를 통해 연결 요소의 개수 구하기
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) { // 방문하지 않은 정점부터 DFS 호출
                dfs(i);
                count++; // 연결 요소 개수 증가
            }
        }

        System.out.println(count); // 연결 요소의 개수 출력
    }

    // DFS 함수 정의
    static void dfs(int v) {
        visited[v] = true; // 현재 정점 방문 표시
        // 현재 정점과 연결된 정점들에 대해 재귀적으로 DFS 호출
        for (int next : adjList[v]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }
}
