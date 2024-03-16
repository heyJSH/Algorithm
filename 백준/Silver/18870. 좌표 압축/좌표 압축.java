import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 좌표의 개수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] coords = new int[N]; // 좌표 배열
        List<Integer> sortedCoords = new ArrayList<>(); // 정렬된 좌표 리스트

        // 좌표 입력 및 정렬된 리스트에 추가
        for (int i = 0; i < N; i++) {
            int coord = Integer.parseInt(st.nextToken());
            coords[i] = coord;
            sortedCoords.add(coord);
        }

        // 정렬된 리스트를 정렬하여 중복 제거
        Collections.sort(sortedCoords);
        Map<Integer, Integer> compressedCoords = new HashMap<>(); // 좌표 압축 결과를 저장할 맵

        // 중복 제거된 좌표에 대해 압축된 좌표 값을 맵에 저장
				int compressedCoord = 0; // 압축된 좌표 값 초기화
				for (int coord : sortedCoords) { // 중복이 제거된 정렬된 좌표 리스트를 순회
				    if (!compressedCoords.containsKey(coord)) { // 해당 좌표가 이미 압축되어 있는지 확인
				        compressedCoords.put(coord, compressedCoord++); // 압축된 좌표 값을 맵에 저장하고 증가시킴
				    }
				}
				
				// 압축된 좌표 값 출력
				StringBuilder sb = new StringBuilder(); // 문자열을 효율적으로 처리하기 위한 StringBuilder 생성
				for (int coord : coords) { // 주어진 좌표 배열을 순회
				    sb.append(compressedCoords.get(coord)).append(" "); // 각 좌표에 대응하는 압축된 값을 StringBuilder에 추가
				}
        System.out.println(sb.toString());
    }
}
